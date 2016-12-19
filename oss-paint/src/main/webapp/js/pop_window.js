document.writeln('<link rel="stylesheet" type="text/css" href="css/dhtmlx/dhtmlxwindows.css">');
document.writeln('<link rel="stylesheet" type="text/css" href="css/dhtmlx/skins/dhtmlxwindows_web.css">');
document.writeln('<script src="js/dhtmlx/dhtmlxcommon.js"></script>');
document.writeln('<script src="js/dhtmlx/dhtmlxwindows.js"></script>');
//
var dhxWins =null;
var currPanel = null;
function popDWindow(title,url,width,height,currPanel_,viewPort,modal) {
	if(dhxWins == null) {
		dhxWins = new dhtmlXWindows();
		dhxWins.setSkin("web");
		viewPort = viewPort || false;
		dhxWins.enableAutoViewport(viewPort);
		//dhxWins.setViewport(0, 0, 400, 400,document.body);
		dhxWins.vp.style.height = "100%";
		dhxWins.vp.style.width = "100%";
		dhxWins.setImagePath("./images/dhtmlwindowimgs/");
	}
	currPanel = currPanel_;
	var w1 = dhxWins.createWindow("w1", 0, 0, width, height);
	if(modal == null || modal == undefined)
		modal = true;
	w1.setModal(modal);
	w1.button("minmax1").hide();
	w1.button("minmax2").hide();
	w1.button("park").hide();
	w1.button("park").disable();
	w1.setText(title);
	w1.attachURL(url);
	w1.center();
	onCloseDWindow();
}
function closeDWindow() {
	dhxWins.window("w1").close();
	var win = currPanel;
	currPanel = null;
	if(!win) 
		win = location;
	win.reload();
}
function attachDWE(btn,eventStr,func) {
	dhxWins.window("w1").button(btn).attachEvent(eventStr,func);
}
function onCloseDWindow(func) {
	if(!func) {
		func = function() {
			closeDWindow();
		}
	}
	attachDWE("close","onClick",func);
}
//----------------------------------------------------------------------------
/**
 */
function popTopDWindow(title,url,width,height,currPanel_) {
	if(top.popDWindow){
		top.popDWindow(title,url,width,height,currPanel_);
	} else {
		popDWindow(title,url,width,height,currPanel_);
	}
}
function closeDWindow2() {
	dhxWins.window("w1").close();
	var win = currPanel;
	currPanel = null;
	if(!win) 
		win = location;
	win.reload();
}