//�����Үج[�ǭ�
function validateFields(obj) {
 	validateField(obj,obj,"validateMethod,limitNumAdmin");
}
// �����Үج[�ǭȡ]��q�����Ρ^
function validateFieldMod(obj) {
	validateField(obj,obj,"validateMethod,batchAction");
}
// ������s����
function getRadioValue(name){
	var radios = $(name,0);
	for(var i=0;i<radios.length;i++){
		if(radios[i].checked==true){
			return radios[i].value;
		}
	}
}
// ��q�קﴣ��P�_
function doSubmit() {
	if(getRadioValue("batchAction")=='0'){
		if(confirm("�z�O�_�T�{�n�R����e�������⪺�t�μƶq����H�p�G�R������������N�S���ƶq���W������C")){
			document.getElementById("crmAccountLimitOrgRole").submit();
	    }
	}else{
		/*
		 * if(!isPlusInteger($("limitNum").value)){ alert("�Ȥ�W�����O�����"); return
		 * false; }
		 */
		if($("funcID").value=='951155'){
			var check = true;
			var tmps = $("limitNums").value.split(",");
			for ( var i = 0; i < tmps.length; i++) {
				if($("limitNum").value < parseInt(tmps[i]) && $("limitNum").value!="-1"){
					//alert($("limitNum").value+"--"+tmps[i]);
					check = false;
					break;
				}else if(tmps[i]=="�L���q" && $("limitNum").value!="-1"){
					check = false;
					break;
				}
			}
			if(check){
				document.getElementById("crmAccountLimitOrgRole").submit();
			}else{
				if(confirm("�վ�᪺����ƶq�p�_��Ӫ�����ƶq�A�o�˥i��ɭP���Ǽƾڷ|�W�X�վ㪺����ƶq�A�z�O�_�T�{�n��s�H")){
					document.getElementById("crmAccountLimitOrgRole").submit();
				}else{
					return false;
				}
			}
		}
		document.getElementById("crmAccountLimitOrgRole").submit();
	}
}
// ��q������l�Ƴ]�m
function init(){
	if(getRadioValue("batchAction")=='0'){
		$("limitNum").disabled=true;
	}else{
		$("limitNum").disabled=false;
	}
}
// ajax�����e����B�������t�κ޲z���]�w������]�P��Ȥ�ƶq����޲z�^
function getLimitNumAdmin(id,text){
	//alert(id + "--" + text);
	if(id!=''){
		ajaxRequest("/accountLimitUser.do?method=getLimitNumAdmin", "userId="+id, limitNumAdminResult);
	}
}
function limitNumAdminResult(result){
	if(result && result[0]) {
		result = result[0];
		if(result.limitNumAdmin=='-1'){
			$("limitNum").innerHTML = '�L���q';
		}else{
			$("limitNum").innerHTML = result.limitNumAdmin;
		}
		$("limitNumAdmin").value = result.limitNumAdmin;
	}
}
// ajax�����e����B�������t�κ޲z���]�w������]����/����Ȥ�ƶq����޲z�^
function getLimitNum(departmentId,text){
	  //alert(departmentId+"--"+$("roleId").value);
	  if(departmentId!=''){
		  $("tmpDepId").value = departmentId;
	  }else{
		  departmentId = $("tmpDepId").value;
	  }
	  // alert(departmentId+"--"+$("roleId").value);
	  if(departmentId!='' && $("roleId").value!=''){
		  ajaxRequest("/accountLimitOrg.do?method=getLimitNumAdmin", "roleId="+$("roleId").value+"&departmentId="+departmentId, limitNumResult);
	  }
}
function limitNumResult(result){
	if(result && result[0]) {
		result = result[0];
		if(result.limitNumAdmin=='-1'){
			$("limitNum").innerHTML = '�L���q';
		}else{
			$("limitNum").innerHTML = result.limitNumAdmin;
		}
		$("limitNumAdmin").value = result.limitNumAdmin;
	}
}
function doSubmitUser() {
	if(getRadioValue("batchAction")=='0'){
		if(confirm("�z�O�_�T�{�n�R���H�W�Ҧ��P�⪺�Ȥ�ƶq����H")){
			document.getElementById("crmAccountLimitUser").submit();
	    }
	}else{
		document.getElementById("crmAccountLimitUser").submit();
	}
}
function doSubmitAdmin(){
	//alert($("limitNumAdmin").value+"--"+$("limitNum").value);
	if(parseInt($("limitNum").value) < parseInt($("limitNumAdmin").value) && $("limitNum").value!="-1"){
		if(confirm("�վ�᪺����ƶq�p�_��Ӫ�����ƶq�A�o�˥i��ɭP���Ǽƾڷ|�W�X�վ㪺����ƶq�A�z�O�_�T�{�n��s�H")){
			document.getElementById("crmAccountLimitOrgRole").submit();
	    }
	}else if($("limitNumAdmin").value=="�L���q" && $("limitNum").value!="-1"){
		if(confirm("�վ�᪺����ƶq�p�_��Ӫ�����ƶq�A�o�˥i��ɭP���Ǽƾڷ|�W�X�վ㪺����ƶq�A�z�O�_�T�{�n��s�H")){
			document.getElementById("crmAccountLimitOrgRole").submit();
	    }
	}else{
		document.getElementById("crmAccountLimitOrgRole").submit();
	}
}