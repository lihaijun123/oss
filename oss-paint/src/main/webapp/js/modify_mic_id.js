function modify() {
	var btn = document.getElementsByName("selbtn")[0];
	btn.value = " O K ";
  if('cssFloat' in btn.style)
      btn.style.cssFloat = "left";
  if('styleFloat' in btn.style)
      btn.style.styleFloat = "left";
  document.getElementsByName("nextForm")[0].target="_self";
	btn.onclick=doNext1;
}

//document.onkeydown = sumbtic;
function sumbtic(e){
  var keynum;
  if(window.event)
  {
      keynum = event.keyCode;
  }
  else if(e.which)
  {
      keynum = e.which;
  }
  if(keynum == 13){
    doNext1();
  }

}

function selMICID(){
	if(document.getElementsByName("micids")[0]){
		var ids = document.getElementsByName("micids")[0].value;
		var arr = ids.split(",");
		var items = document.getElementsByName("sel_id");
		for(var i=0;i<arr.length;i++){
			for(var j=0;j<items.length;j++){
				if(arr[i]==items[j].value){
					items[j].checked=true;
				}
			}
		}
	}
}

function doNext1(){
	document.nextForm.submit();
}

UITool.AddEventHandler(window, "load", modify);
UITool.AddEventHandler(window, "load", selMICID);
UITool.AddEventHandler(document, "keydown", sumbtic);



