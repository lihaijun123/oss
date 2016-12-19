/**
 * 使用說明
 *
 * @param viewTarget 目標顯示容器，不能為空
 * @param args第二個參數為對象︰每個屬性定義為；可以為空
 {
 * departmentId 指定顯示部門；多個部門時使用“,”分隔開；如果為空默認顯示當前用戶所屬部門
 * orgType 部門顯示類型；0︰不顯示，1︰合並顯示，2︰分級顯示；默認“0”
 * orgRange 部門顯示數據範圍； 0︰當前部門，1︰當前部門直屬，2︰當前部門和所有子部門；默認“0”
 * defOrgId 默認顯示機構ID
 * userId 指定顯示用戶；多個用戶時使用“,”分隔開；
 * userType  用戶顯示類型；0:不顯示；1︰顯示正常；2︰顯示所有(包含非正常員工)，默認值為“1”
 * defUserId 默認顯示用戶
 * resourceId 當前權限處理的資源ID
 }
 * @param departField 部門顯示字段名，可以為空，默認為"departId_"+window.panelIndex
 * @param saleField 用戶顯示字段名，可以為空，默認為"userId_"+window.panelIndex
 * @param editable 是否可以編輯︰true ；false
 * 什麼參數都不傳默認為當前用戶所屬部門下的所有正常用戶；
 *
 *
 * 注︰<script> .....</script>請寫在容器定義的下面
 */

/*
----- 例子︰
<div id="viewTarget1">XXX︰</div> <!-- 顯示容器 -->
<script>new DepUserTag("viewTarget1",{"departmentId":"","orgType":"2","orgRange":"2","defOrgId":"","userId":"","userType":"2","defUserId":"","resourceId":""},"departmentId","saleId");</script>

*/
if(!window.DepUserTag) {
window.panelIndex = 1;
var levelSelValue = {};
window.DepUserTag = function (viewTarget,args,departField,saleField,editable,saleEvent,departEvent) {
	this.args = args || {};
	this.departField = departField || "departId_" + window.panelIndex;
	this.saleField = saleField || "userId_" + window.panelIndex;
	this.oldOrgRange = this.args.orgRange;
	this.oldUserType = this.args.userType;
	this.first = true;
	this.departDiv = "panel_depart_div_" + window.panelIndex;
	this.userDiv = "panel_user_div_"+window.panelIndex;
	this.editable = editable || false;
	this.saleEvent = saleEvent || function(){};
	this.departEvent = departEvent || function(){};
	//set default value
	this.args.orgRange = this.args.orgRange ||"0";
	this.args.orgType = this.args.orgType || "0";
	this.dParents = null;
	this.defOrgId = this.args.defOrgId;
	this.defUserId = this.args.defUserId;
	this.resourceId = this.args.resourceId || "1000000016";
	this.args.resourceId = this.resourceId;
	//
	this.viewTargetPanel = document.getElementById(viewTarget);
	if(!this.viewTargetPanel) {
		alert("沒有指定要顯示的容器。");
		return ;
	}
	var newDiv = null;
	if(this.args.orgType != "0") {
		newDiv = document.createElement("DIV");
		newDiv.id = this.departDiv;
		this.viewTargetPanel.appendChild(newDiv);
		//部門ID信息
		var hidDepart = document.createElement("INPUT");
		hidDepart.type ="hidden";
		hidDepart.id="hid_"+this.departField;
		hidDepart.name = this.departField;
		hidDepart.value= this.defOrgId||"";
		newDiv.appendChild(hidDepart);
		//
		var nameSpan = document.createElement("DIV");
		nameSpan.id="o_span_" + this.departField;
		nameSpan.innerHTML = "";
		nameSpan.className = "oldSelValue";
		newDiv.appendChild(nameSpan);
		nameSpan = document.createElement("DIV");
		nameSpan.id="n_span_" + this.departField;
		nameSpan.innerHTML = "";
		newDiv.appendChild(nameSpan);
	}
	if(this.oldUserType != "0") {
		newDiv = document.createElement("DIV");
		newDiv.id = this.userDiv;
		this.viewTargetPanel.appendChild(newDiv);
		//顯示原用戶
		var nameSpan = document.createElement("DIV");
		nameSpan.id="o_u_span_" + this.saleField;;
		nameSpan.innerHTML = "";
		nameSpan.className = "oldSelValue";
		newDiv.appendChild(nameSpan);
		nameSpan = document.createElement("DIV");
		nameSpan.id="n_u_span_" + this.saleField;
		nameSpan.innerHTML = "";
		newDiv.appendChild(nameSpan);
		//用戶ID信息
		var hidEle = document.createElement("INPUT");
		hidEle.type ="hidden";
		hidEle.id="hid_u_"+this.saleField;
		hidEle.name = this.saleField;
		hidEle.value= this.defUserId||"";
		newDiv.appendChild(hidEle);
	}
	//後台服務url
	var url = window.location.href;
	url = url.substring(0, url.indexOf(window.location.pathname));
	this.url = url+  "/user.do?method=depUsers" ;
	window.panelIndex++;
	this.init(1);
};
DepUserTag.prototype.init  = function (level) {
	var vParams = "";
	//分級顯示的時候不管怎麼設置都是先取當前部門信息
	if(this.args.orgType == "2" && this.first) {
		//如果要分級顯示則先顯示當前部門;
		this.args.orgRange = "0";
	} else if(this.args.orgType == "2") {
		this.args.orgRange = "1";
	}
	for(var key in this.args) {
		vParams += "&" + key + "=" + escape(this.args[key]);
	}
	//只要部門顯示，默認就不顯示用戶
	if(this.args.orgType !="0" && this.first) {
		this.args.userType = 0; // 不取數據
	}
	//當前對象
	var _this = this;
	var frc = new FRC();
	frc.url = this.url;

	frc.paramStr=vParams;
	//alert(vParams);
	frc.callBack = function(data) {
		//alert(data);
		var x = data//Ext.decode(response.responseText);
		x = x[0];
		var departs = x["subDepartments"];
		var users = x["users"];
		var dParents = x["parents"];
		if(_this.first && dParents) {
			_this.dParents = dParents;
			_this.args.defOrgId = "";;
			var length = dParents.length
			var pstr = "";
			//顯示原選擇部門
			for(var i = length-1 ; i >= 0 ;i--) {
				if(pstr !="") pstr += "&gt;&gt;";
				pstr += dParents[i][1] + "("+dParents[i][0]+")" ;
			}
			_this.setSelDepartValueText(_this.defOrgId,pstr);
			//
		}
		if(_this.oldUserType != "0" && _this.first) {
			var tmp = false;
			for(var i = 0 ; users != 'undefined' && i < users.length ; i++) {
				var user = users[i];
				if(user.userId == _this.defUserId) {
					tmp = true;
					_this.setSelUserValueText(_this.defUserId,user.fullname + "("+_this.defUserId+")");
				}
			}
			if(tmp == false) {
				_this.setSelUserValueText("","");
			}
			_this.args.defUserId = "";
		}
		if(_this.oldUserType != "0" && (!users || users.length <=0)) {
			_this.clearUserObj();
		}
		if(!departs || departs.length <=0) {
			_this.deleteAfter(_this.departDiv,_this.departField,level);
		}
		if(_this.args.orgType == "0") {
			if(user.length ==1 && level <=1) {
				var panel = document.getElementById(_this.userDiv);
				panel.innerHTML = users[0]["fullname"]+"("+ users[0]["userId"] +")" +'<input type="hidden" name="'+ _this.saleField +'" value="'+users[0]["userId"]+'">';
			}
		}
		if(_this.args.orgType != "0") {
			_this.addChildEle(_this.departDiv,_this.departField,departs,"departmentId","departmentName",true,level,_this.defOrgId);
		}
		if(_this.oldUserType != "0" && _this.first==false) {
			_this.changeUserOption(_this.userDiv,_this.saleField,users,"userId","fullname",_this.setSelUserValueText,1,_this.defUserId);
		}
	};
	frc.remoteCall();
};

DepUserTag.prototype.addChildEle = function (panel,name,datas,value,text,doChange,level,defValue) {
	var _this=this;
	var newSel = document.getElementById(name+"_"+level);
	if(!newSel && datas.length > 0) {
		newSel = document.createElement("SELECT");
		newSel.id = name + "_" + level;
		newSel.level = level;
		newSel.name = newSel.id;
		newSel.editable = this.editable ;
		var showArea_div = document.getElementById(panel);
		showArea_div.appendChild(newSel);
	} else if(newSel && datas.length <= 0) {
		var showArea_div = document.getElementById(panel);
		showArea_div.removeChild(newSel);
	} else if(newSel) {
		newSel.options.length = 0 ;
	}
	if(datas.length <=0) return ;
	newSel.options[0] = new Option("請選擇" ,"");
	//
	//
	if(doChange == true) {
		newSel.onchange = function(e) {
			e = e || window.event;
			var obj = e.srcElement || e.target;
			//
			_this.first = false;
			//
			_this.args.userType = _this.oldUserType ;
			_this.args.orgRange = _this.oldOrgRange;
			if(_this.args.orgType == 1) {
				_this.args.orgRange = 3;//不顯示任何
			}
			//
			var value = obj.options[obj.selectedIndex].value;
			var text = obj.options[obj.selectedIndex].text;
			//記住每一級別選擇的值
			levelSelValue["id_"+level] = value;
			levelSelValue["val_"+level] = text;
			//根據當前選擇的根據傳到後台，如果當前沒有選擇則使用前一級別的值
			if(document.getElementById("hid_" + name)) {
				_this.getAllParentInfo(level);
			}
			if(value == "") {
				//如果當前為空則刪除所有對象
				_this.deleteAfter(_this.departDiv,_this.departField,obj.level);
				_this.setSelDepartValueText("","");
				if(_this.oldUserType !="0") {
					_this.clearUserObj();
				}
				return ;
			}
			_this.args.departmentId = value;
			_this.init(obj.level+1);
		} ;
	} else if((typeof doChange) == 'function') {
		//_this.setSelUserValueText(_this.saleField,_this.defUserId,user.fullname + "("+_this.defUserId+")");
		newSel.onchange = function(e) {
			e = e || window.event;
			var obj = e.srcElement || e.target;
			doChange.apply(_this,arguments)
		};
	}
	//
	for(var i = 1 ; i <= datas.length ;i++) {
		var data = datas[i-1];
		newSel.options[i]= new Option(data[text]+"("+ data[value] +")" ,data[value]);
		if(defValue == data[value]) {
			newSel.options[i].selected = true;
			this.fireEvent(newSel,"change");
		}
	}
};
DepUserTag.prototype.fireEvent = function(obj,event) {
	if(obj.fireEvent) {
		obj.fireEvent("on"+event);
	} else if(document.createEvent) {
		var e = document.createEvent("Events");
		e.initEvent(event,true,false);
		obj.dispatchEvent(e);
	} else {
		alert("explorer event object error.");
	}
}
DepUserTag.prototype.clearUserObj = function() {
	var userObj=document.getElementById(this.saleField+"_1");
	userObj.options.length = 1;
	userObj.selectedIndex = 0 ;
	this.fireEvent(userObj,"change");
}

DepUserTag.prototype.getAllParentInfo = function(level) {
	var pstr = "";
	for(var i = 1 ; i <= level ;i++) {
		if(!levelSelValue["id_"+(i)]) break;
		if(pstr !="") pstr += "&gt;&gt;";
		pstr += levelSelValue["val_"+(i)];
	}
	var id = levelSelValue["id_"+(level)];
	if(!id) {
		id = levelSelValue["id_"+(level-1)];
	}
	if(pstr && pstr !='undefined') {
		this.setSelDepartValueText(id,pstr);
	}
}
DepUserTag.prototype.setSelDepartValueText = function(id,text) {
	document.getElementById("hid_" + this.departField).value = id || this.defOrgId;
	if(this.first) {
		document.getElementById("o_span_"+ this.departField).innerHTML = "原選擇部門︰" + text;
	} else {
		document.getElementById("n_span_"+ this.departField).innerHTML = "當前選擇部門︰" + text;
	}
	//
	if((typeof this.departEvent) == 'function') {
		this.departEvent.apply(this,[id,text]);
	}
};
DepUserTag.prototype.setSelUserValueText = function(id,text) {
	var _this = this || _this;
	//
	if(!id || id == 'undefined' || (typeof id) == 'object') {
		var userObj= document.getElementById(_this.saleField+"_1");
		if(userObj) {
			id = userObj.options[userObj.selectedIndex].value;
			text = userObj.options[userObj.selectedIndex].text;
		}
	}
	if(!id) {
		id = "";
		text = "";
	}
	//
	document.getElementById("hid_u_" + _this.saleField).value = id|| _this.defUserId;
	if(_this.first) {
		document.getElementById("o_u_span_"+ _this.saleField).innerHTML = "原選擇用戶︰" + text;
	} else {
		document.getElementById("n_u_span_"+ _this.saleField).innerHTML = "當前選擇用戶︰" + text;
	}
	//
	if((typeof _this.saleEvent) == 'function') {
		_this.saleEvent.apply(_this,[id,text]);
	}
};
//刪除當前節點後的所有子節點
DepUserTag.prototype.deleteAfter = function (panel,name,level) {
	var index = level+1;
	while(true) {
		var newSel = document.getElementById(name+"_"+index++);
		if(newSel) {
			var showArea_div = document.getElementById(panel);
			showArea_div.removeChild(newSel);
		} else {
			break;
		}
	}
};
//
//
DepUserTag.prototype.changeUserOption = function(panel,name,datas,value,text,doChange,level,defValue){
	var userObj = document.getElementById(this.saleField+ "_1");
	if(userObj) {
		userObj.options.length = 1 ;
		userObj.options[0] = new Option("請選擇" ,"");
		for(var i = 1 ; i <= datas.length ;i++) {
			var data = datas[i-1];
			userObj.options[i]= new Option(data[text]+"("+ data[value] +")" ,data[value]);
			if(defValue == data[value]) {
				userObj.options[i].selected = true;
			}
		}
		this.fireEvent(userObj,"change");
	} else {
		this.addChildEle(panel,name,datas,value,text,doChange,level,defValue);
	}
};
}
;