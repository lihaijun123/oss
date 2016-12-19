function addCustomer() {
	var btn = document.getElementsByName("selbtn")[0];
	btn.value = " ¼W¥[ ";
}

function addBack() {
	var subjectId = document.getElementsByName('subjectId')[0].value; 
	var btn = document.getElementsByName("selbtn")[0];
	var parent = btn.parentElement;
	var btnField = document.createElement("input");
  	btnField.type="button";
  	btnField.value=" ªð¦^ ";
  	btnField.onclick=function(){
  		window.location='uitoolList.ui?funcID=608&subjectId='+subjectId;
  	};
  	parent.appendChild(btnField);
}

UITool.AddEventHandler(window, "load", addCustomer);
UITool.AddEventHandler(window, "load", addBack);