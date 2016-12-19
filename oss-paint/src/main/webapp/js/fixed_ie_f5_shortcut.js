document.writeln('<script type="text/javascript" src="/js/shortcut.js"></script>');
function doSys(target,shortCut,evt){
	window.location.reload();
}
function AddEventHandler(oTarget,sEventType,fnHandler) {
	if(!oTarget) return ;
	if(oTarget.addEventListener) {
		oTarget.addEventListener(sEventType,fnHandler,false);
	} else if(oTarget.attachEvent) {
		oTarget.attachEvent("on"+sEventType,fnHandler);
	} else{
		oTarget["on"+sEventType] = fnHandler;
	}
}
AddEventHandler(window,"load",function(){
	var sc = new JShortCut();
	sc.bind("f5",doSys,false);
	sc.listen(document);
	window.focus();
});
