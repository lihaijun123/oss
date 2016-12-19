
var Util = new Object();
Util.getUserAgent = navigator.userAgent;
Util.isGecko = Util.getUserAgent.indexOf("Gecko") != -1;
Util.isOpera = Util.getUserAgent.indexOf("Opera") != -1;

Util.getOffset = function(el, isLeft) {
    var retValue = 0;
    while (el != null) {
        retValue += el["offset" + (isLeft ? "Left" : "Top")];
        el = el.offsetParent;
    }
    return retValue;
};

Util.bindFunction = function(el, fucName) {
    return function() {
        return el[fucName].apply(el, arguments);
    };
};

Util.re_calcOff = function(el) {
    for (var i = 0; i < Util.dragArray.length; i++) {
        var ele = Util.dragArray[i];
        ele.elm.pagePosLeft = Util.getOffset(ele.elm, true);
        ele.elm.pagePosTop = Util.getOffset(ele.elm, false);
    }
    var nextSib = el.elm.nextSibling;
    while (nextSib) {
        nextSib.pagePosTop -= el.elm.offsetHeight;
        nextSib = nextSib.nextSibling;
    }
};

Util.hide = function() {
    Util.rootElement.style.display = "none";
};

Util.show = function() {
    Util.rootElement.style.display = "";
};

ghostElement = null;
getGhostElement = function() {
    if (!ghostElement) {
        ghostElement = document.createElement("DIV");
        ghostElement.className = "modbox";
        ghostElement.backgroundColor = "";
        ghostElement.style.border = "2px dashed #aaa";
        ghostElement.innerHTML = "&nbsp;";
    }
    return ghostElement;
};

function draggable(el) {
    this._dragStart = start_Drag;
    this._drag = when_Drag;
    this._dragEnd = end_Drag;
    this._afterDrag = after_Drag;
    this.isDragging = false;
    this.elm = el;
    this.header = document.getElementById(el.id + "_h");
    this.hasIFrame = this.elm.getElementsByTagName("IFRAME").length > 0;
    if (this.header) {
        this.header.style.cursor = "move";
        Drag.init(this.header, this.elm);
        this.elm.onDragStart = Util.bindFunction(this, "_dragStart");
        this.elm.onDrag = Util.bindFunction(this, "_drag");
        this.elm.onDragEnd = Util.bindFunction(this, "_dragEnd");
    }
};

function start_Drag() {
    Util.re_calcOff(this);
    this.origNextSibling = this.elm.nextSibling;
    var _ghostElement = getGhostElement();
    var offH = this.elm.offsetHeight;
    if (Util.isGecko) {
        offH -= parseInt(_ghostElement.style.borderTopWidth) * 2;
    }
    var offW = this.elm.offsetWidth;
    var offLeft = Util.getOffset(this.elm, true);
    var offTop = Util.getOffset(this.elm, false);
    Util.hide();
    this.elm.style.width = offW + "px";
    _ghostElement.style.height = offH + "px";
    this.elm.parentNode.insertBefore(_ghostElement, this.elm.nextSibling);
    this.elm.style.position = "absolute";
    this.elm.style.zIndex = 100;
    this.elm.style.left = offLeft + "px";
    this.elm.style.top = offTop + "px";
    Util.show();
    this.isDragging = false;
    return false;
};

function when_Drag(clientX, clientY) {
    if (!this.isDragging) {
        this.elm.style.filter = "alpha(opacity=70)";
        this.elm.style.opacity = 0.7;
        this.isDragging = true;
    }
    var found = null;
    var max_distance = 100000000;
    for (var i = 0; i < Util.dragArray.length; i++) {
        var ele = Util.dragArray[i];
        var distance = Math.sqrt(Math.pow(clientX - ele.elm.pagePosLeft, 2) + Math.pow(clientY - ele.elm.pagePosTop, 2));
        if (ele == this) {
            continue;
        }
        if (isNaN(distance)) {
            continue;
        }
        if (distance < max_distance) {
            max_distance = distance;
            found = ele;
        }
    }
    var _ghostElement = getGhostElement();
    if (found != null && _ghostElement.nextSibling != found.elm) {
        found.elm.parentNode.insertBefore(_ghostElement, found.elm);
        if (Util.isOpera) {
            document.body.style.display = "none";
            document.body.style.display = "";
        }
    }
};

function end_Drag() {
    if (this._afterDrag()) {
        //remote call to save the change
        saveLayout();
    }
};

function saveLayout() {
    var portlets = "";
    for (var i = 0; i < Util.column.length; i++) {
        var objCol = Util.column[i];
        var rowIndex = 0;
        for (var j = 0; j < objCol.childNodes.length; j++) {
            var objDiv = objCol.childNodes[j];
            if (objDiv.tagName == "DIV" && (/^m_/.test(objDiv.id))) {	
				rowIndex++;	
				portlets += objDiv.id.split("_")[1] + "-" + rowIndex + "-" + i + "|";
            }
        }
    }
    
    //Ajax調用，更新用戶自定義Portlet的顯示順序
    if (portlets != "") {
        portlets = portlets.substr(0, portlets.length - 1);
        ajaxRequest("/portalConfig.do?method=saveUserPortlet", "&portlets=" + portlets, callBack_drag);
    }
}

function callBack_drag(result) {	
	var returnValue = eval(result);
    if (result && result[0] && result[0].errMsg) {
        alert("數據庫保存失敗，所做修改未保存到數據庫！\r\n錯誤原因︰" + result[0].errMsg);
    }
}

function after_Drag() {
    var returnValue = false;
    Util.hide();
    this.elm.style.position = "";
    this.elm.style.width = "";
    this.elm.style.zIndex = "";
    this.elm.style.filter = "";
    this.elm.style.opacity = "";
    var ele = getGhostElement();
    if (ele.nextSibling != this.origNextSibling) {
        ele.parentNode.insertBefore(this.elm, ele.nextSibling);
        returnValue = true;
    }
    ele.parentNode.removeChild(ele);
    Util.show();
    if (Util.isOpera) {
        document.body.style.display = "none";
        document.body.style.display = "";
    }
    return returnValue;
};

var Drag = {
    obj: null,
    init: function(elementHeader, element) {
        elementHeader.onmousedown = Drag.start;
        elementHeader.obj = element;
        if (isNaN(parseInt(element.style.left))) {
            element.style.left = "0px";
        }
        if (isNaN(parseInt(element.style.top))) {
            element.style.top = "0px";
        }
        element.onDragStart = new Function();
        element.onDragEnd = new Function();
        element.onDrag = new Function();
    },
    start: function(event) {
        var element = Drag.obj = this.obj;
        event = Drag.fixE(event);
        if (event.which != 1) {
            return true;
        }
        element.onDragStart();
        element.lastMouseX = event.clientX;
        element.lastMouseY = event.clientY;
        document.onmouseup = Drag.end;
        document.onmousemove = Drag.drag;
        return false;
    },
    drag: function(event) {
        event = Drag.fixE(event);
        if (event.which == 0) {
            return Drag.end();
        }
        var element = Drag.obj;
        var _clientX = event.clientY;
        var _clientY = event.clientX;
        if (element.lastMouseX == _clientY && element.lastMouseY == _clientX) {
            return false;
        }
        var _lastX = parseInt(element.style.top);
        var _lastY = parseInt(element.style.left);
        var newX, newY;
        newX = _lastY + _clientY - element.lastMouseX;
        newY = _lastX + _clientX - element.lastMouseY;
        element.style.left = newX + "px";
        element.style.top = newY + "px";
        element.lastMouseX = _clientY;
        element.lastMouseY = _clientX;
        element.onDrag(newX, newY);
        return false;
    },
    end: function(event) {
        event = Drag.fixE(event);
        document.onmousemove = null;
        document.onmouseup = null;
        var _onDragEndFuc = Drag.obj.onDragEnd();
        Drag.obj = null;
        return _onDragEndFuc;
    },
    fixE: function(ig_) {
        if (typeof ig_ == "undefined") {
            ig_ = window.event;
        }
        if (typeof ig_.layerX == "undefined") {
            ig_.layerX = ig_.offsetX;
        }
        if (typeof ig_.layerY == "undefined") {
            ig_.layerY = ig_.offsetY;
        }
        if (typeof ig_.which == "undefined") {
            ig_.which = ig_.button;
        }
        return ig_;
    }
};

var _IG_initDrag = function(el) {
    Util.rootElement = el;
    Util._rows = Util.rootElement.tBodies[0].rows[0];
    Util.column = Util._rows.cells;
    Util.dragArray = new Array();
    var counter = 0;
    for (var i = 0; i < Util.column.length; i++) {
        var ele = Util.column[i];
        for (var j = 0; j < ele.childNodes.length; j++) {
            var ele1 = ele.childNodes[j];
            if (ele1.tagName == "DIV") {
                Util.dragArray[counter] = new draggable(ele1);
                counter++;
            }
        }
    }
};

/**
 * 刷新該Portlet
 */
function refreshPortlet(id) {
	var portletDiv = document.getElementById(id);
	var maskDiv = document.getElementById("mask_" + id.split("_")[1]);
	maskDiv.style.display = "";
	//maskDiv.style.left = portletDiv.clientLeft + portletDiv.offsetWidth / 2;
	//maskDiv.style.top = portletDiv.clientTop - portletDiv.offsetHeight / 2;	

	var frmName= "frm_" + id.split("_")[1];
	var frm = document.getElementById("frm_" + id.split("_")[1]);
	frm.contentWindow.location.reload();
	setTimeout(function(){maskDiv.style.display = "none";}, 500);	
}

/**
 * 編輯該功能Portlet
 */
function editFunctionPortlet(portletId) {
	openwin("portalConfig.do?method=listFunction&portletId=" + portletId, 800, 600);
}

/**
 * 編輯該隊列Portlet
 */
function editQueuePortlet(portletId) {
	openwin("portalConfig.do?method=listQueue&portletId=" + portletId, 800, 600);
}

/**
 * 激活工作區
 */
function activateCenterPage() {
	top.tabObj.activate('workid');
}

//打開新窗口(帶參數,如果不傳參數則采用默認值)
function openwin(url,iWidth,iHeight){
	if(iWidth==null||iWidth==0)
		iWidth=800;
	if(iHeight==null||iHeight==0)
		iHeight=520;
	var url;  //轉向網頁的地址;
	var name='op_win'; //網頁名稱，可為空;
	var iTop = (screen.availHeight-30-iHeight)/2; //獲得窗口的垂直位置;
	var iLeft = (screen.availWidth-10-iWidth)/2; //獲得窗口的水平位置;
	window.open(url,name,'height='+iHeight+',innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
}

function configPortal() {
	openwin("portalConfig.do?method=selectContainer", 800, 600);
}

function resetPortal() {
	location.href = "crmUserRemind.do?method=resetPortal";	
	doColumnMode("1");
}

function doColumnMode(columnType) {
	location.href = "crmUserRemind.do?method=doColumnMode&columnType=" + columnType;
}

function closePortlet(portletId) {
	if (confirm("確認要刪除該顯示項嗎？")) {
		location.href = "crmUserRemind.do?method=deletePortlet&portletId=" + portletId;
		window.location.reload();
	}
}

function selObj(ids, type, title, portletId){
	location.href = "crmUserRemind.do?method=getUserReminds&ids=" + ids + "&type=" + type + "&title=" + title + "&portletId=" + portletId;
}

/**   
 * 調整iframe的高度以適應所引用網頁的高度   
 */   
function iframeResize(frameId, frameName) {
    var dyniframe   = null;    
    var indexwin    = null;    
    if (document.getElementById){
        if(!frameId) {
            frameId = "contentFrame";
            frameName = "contentFrame";
        }
        dyniframe = document.getElementById(frameId);    
        indexwin = window;    
        if (dyniframe) {
			var contentHeight = window.frames[frameName].document.body.scrollHeight;
			if (contentHeight) {		
				dyniframe.height = contentHeight;
			}
        }
    }
}

function initIframeHeight(){
	var frms = document.getElementsByTagName("iframe");
	if (frms != null && frms.length > 0) {
		for (var i = 0; i < frms.length; i++) {
			var objFrm = frms[i];
			if (/^frm_/.test(objFrm.id)) {
				iframeResize(objFrm.id, objFrm.name);
			}
		}
	}
}

function initColumnMode(columnType){
	var radios = document.getElementsByName("columnMode");
	if (radios != null && radios.length > 0) {
		for (var i = 0; i < radios.length; i++) {
			var radio = radios[i];
			if (radio.value == columnType) {
				radio.checked = "checked";
				return;
			}
		}
	}
}

function initDrag(){
	var _table=document.getElementById("tbl_content");
	_IG_initDrag(_table);
}

function initPage(columnType) {
	initColumnMode(columnType);
	initDrag();
	initIframeHeight();
}
