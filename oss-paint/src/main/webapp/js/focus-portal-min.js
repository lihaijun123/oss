var Util=new Object();Util.getUserAgent=navigator.userAgent;Util.isGecko=Util.getUserAgent.indexOf("Gecko")!=-1;Util.isOpera=Util.getUserAgent.indexOf("Opera")!=-1;Util.getOffset=function(A,C){var B=0;while(A!=null){B+=A["offset"+(C?"Left":"Top")];A=A.offsetParent}return B};Util.bindFunction=function(A,B){return function(){return A[B].apply(A,arguments)}};Util.re_calcOff=function(B){for(var A=0;A<Util.dragArray.length;A++){var C=Util.dragArray[A];C.elm.pagePosLeft=Util.getOffset(C.elm,true);C.elm.pagePosTop=Util.getOffset(C.elm,false)}var D=B.elm.nextSibling;while(D){D.pagePosTop-=B.elm.offsetHeight;D=D.nextSibling}};Util.hide=function(){Util.rootElement.style.display="none"};Util.show=function(){Util.rootElement.style.display=""};ghostElement=null;getGhostElement=function(){if(!ghostElement){ghostElement=document.createElement("DIV");ghostElement.className="modbox";ghostElement.backgroundColor="";ghostElement.style.border="2px dashed #aaa";ghostElement.innerHTML="&nbsp;"}return ghostElement};function draggable(A){this._dragStart=start_Drag;this._drag=when_Drag;this._dragEnd=end_Drag;this._afterDrag=after_Drag;this.isDragging=false;this.elm=A;this.header=document.getElementById(A.id+"_h");this.hasIFrame=this.elm.getElementsByTagName("IFRAME").length>0;if(this.header){this.header.style.cursor="move";Drag.init(this.header,this.elm);this.elm.onDragStart=Util.bindFunction(this,"_dragStart");this.elm.onDrag=Util.bindFunction(this,"_drag");this.elm.onDragEnd=Util.bindFunction(this,"_dragEnd")}}function start_Drag(){Util.re_calcOff(this);this.origNextSibling=this.elm.nextSibling;var C=getGhostElement();var E=this.elm.offsetHeight;if(Util.isGecko){E-=parseInt(C.style.borderTopWidth)*2}var A=this.elm.offsetWidth;var D=Util.getOffset(this.elm,true);var B=Util.getOffset(this.elm,false);Util.hide();this.elm.style.width=A+"px";C.style.height=E+"px";this.elm.parentNode.insertBefore(C,this.elm.nextSibling);this.elm.style.position="absolute";this.elm.style.zIndex=100;this.elm.style.left=D+"px";this.elm.style.top=B+"px";Util.show();this.isDragging=false;return false}function when_Drag(E,D){if(!this.isDragging){this.elm.style.filter="alpha(opacity=70)";this.elm.style.opacity=0.7;this.isDragging=true}var G=null;var A=100000000;for(var C=0;C<Util.dragArray.length;C++){var F=Util.dragArray[C];var H=Math.sqrt(Math.pow(E-F.elm.pagePosLeft,2)+Math.pow(D-F.elm.pagePosTop,2));if(F==this){continue}if(isNaN(H)){continue}if(H<A){A=H;G=F}}var B=getGhostElement();if(G!=null&&B.nextSibling!=G.elm){G.elm.parentNode.insertBefore(B,G.elm);if(Util.isOpera){document.body.style.display="none";document.body.style.display=""}}}function end_Drag(){if(this._afterDrag()){saveLayout()}}function saveLayout(){var B="";for(var D=0;D<Util.column.length;D++){var E=Util.column[D];var F=0;for(var C=0;C<E.childNodes.length;C++){var A=E.childNodes[C];if(A.tagName=="DIV"&&(/^m_/.test(A.id))){F++;B+=A.id.split("_")[1]+"-"+F+"-"+D+"|"}}}if(B!=""){B=B.substr(0,B.length-1);ajaxRequest("/portalConfig.do?method=saveUserPortlet","&portlets="+B,callBack_drag)}}function callBack_drag(result){var returnValue=eval(result);if(result&&result[0]&&result[0].errMsg){alert("\u6570\u636e\u5e93\u4fdd\u5b58\u5931\u8d25\uff0c\u6240\u505a\u4fee\u6539\u672a\u4fdd\u5b58\u5230\u6570\u636e\u5e93\uff01\r\n\u9519\u8bef\u539f\u56e0\uff1a"+result[0].errMsg)}}function after_Drag(){var A=false;Util.hide();this.elm.style.position="";this.elm.style.width="";this.elm.style.zIndex="";this.elm.style.filter="";this.elm.style.opacity="";var B=getGhostElement();if(B.nextSibling!=this.origNextSibling){B.parentNode.insertBefore(this.elm,B.nextSibling);A=true}B.parentNode.removeChild(B);Util.show();if(Util.isOpera){document.body.style.display="none";document.body.style.display=""}return A}var Drag={obj:null,init:function(B,A){B.onmousedown=Drag.start;B.obj=A;if(isNaN(parseInt(A.style.left))){A.style.left="0px"}if(isNaN(parseInt(A.style.top))){A.style.top="0px"}A.onDragStart=new Function();A.onDragEnd=new Function();A.onDrag=new Function()},start:function(B){var A=Drag.obj=this.obj;B=Drag.fixE(B);if(B.which!=1){return true}A.onDragStart();A.lastMouseX=B.clientX;A.lastMouseY=B.clientY;document.onmouseup=Drag.end;document.onmousemove=Drag.drag;return false},drag:function(F){F=Drag.fixE(F);if(F.which==0){return Drag.end()}var E=Drag.obj;var D=F.clientY;var C=F.clientX;if(E.lastMouseX==C&&E.lastMouseY==D){return false}var B=parseInt(E.style.top);var A=parseInt(E.style.left);var H,G;H=A+C-E.lastMouseX;G=B+D-E.lastMouseY;E.style.left=H+"px";E.style.top=G+"px";E.lastMouseX=C;E.lastMouseY=D;E.onDrag(H,G);return false},end:function(B){B=Drag.fixE(B);document.onmousemove=null;document.onmouseup=null;var A=Drag.obj.onDragEnd();Drag.obj=null;return A},fixE:function(A){if(typeof A=="undefined"){A=window.event}if(typeof A.layerX=="undefined"){A.layerX=A.offsetX}if(typeof A.layerY=="undefined"){A.layerY=A.offsetY}if(typeof A.which=="undefined"){A.which=A.button}return A}};var _IG_initDrag=function(E){Util.rootElement=E;Util._rows=Util.rootElement.tBodies[0].rows[0];Util.column=Util._rows.cells;Util.dragArray=new Array();var B=0;for(var D=0;D<Util.column.length;D++){var F=Util.column[D];for(var C=0;C<F.childNodes.length;C++){var A=F.childNodes[C];if(A.tagName=="DIV"){Util.dragArray[B]=new draggable(A);B++}}}};function refreshPortlet(E){var D=document.getElementById(E);var B=document.getElementById("mask_"+E.split("_")[1]);B.style.display="";var A="frm_"+E.split("_")[1];var C=document.getElementById("frm_"+E.split("_")[1]);C.contentWindow.location.reload();setTimeout(function(){B.style.display="none"},500)}function editFunctionPortlet(A){openwin("portalConfig.do?method=listFunction&portletId="+A,800,600)}function editQueuePortlet(A){openwin("portalConfig.do?method=listQueue&portletId="+A,800,600)}function activateCenterPage(){top.tabObj.activate("workid")}function openwin(B,C,F){if(C==null||C==0){C=800}if(F==null||F==0){F=520}var B;var A="op_win";var E=(screen.availHeight-30-F)/2;var D=(screen.availWidth-10-C)/2;window.open(B,A,"height="+F+",innerHeight="+F+",width="+C+",innerWidth="+C+",top="+E+",left="+D+",toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no")}function configPortal(){openwin("portalConfig.do?method=selectContainer",800,600)}function resetPortal(){location.href="crmUserRemind.do?method=resetPortal";doColumnMode("1")}function doColumnMode(A){location.href="crmUserRemind.do?method=doColumnMode&columnType="+A;window.location.reload()}function closePortlet(A){if(confirm("\u786e\u8ba4\u8981\u5220\u9664\u8be5\u663e\u793a\u9879\u5417\uff1f")){location.href="crmUserRemind.do?method=deletePortlet&portletId="+A;window.location.reload()}}function selObj(C,B,D,A){location.href="crmUserRemind.do?method=getUserReminds&ids="+C+"&type="+B+"&title="+D+"&portletId="+A}function iframeResize(D,A){var B=null;var E=null;if(document.getElementById){if(!D){D="contentFrame";A="contentFrame"}B=document.getElementById(D);E=window;if(B){var C=window.frames[A].document.body.scrollHeight;if(C){B.height=C}}}}function initIframeHeight(){var A=document.getElementsByTagName("iframe");if(A!=null&&A.length>0){for(var B=0;B<A.length;B++){var C=A[B];if(/^frm_/.test(C.id)){iframeResize(C.id,C.name)}}}}function initColumnMode(C){var D=document.getElementsByName("columnMode");if(D!=null&&D.length>0){for(var B=0;B<D.length;B++){var A=D[B];if(A.value==C){A.checked="checked";return }}}}function initDrag(){var A=document.getElementById("tbl_content");_IG_initDrag(A)}function initPage(A){initIframeHeight();initColumnMode(A);initDrag()}
