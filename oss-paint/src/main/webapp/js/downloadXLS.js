//�Τ_�ЫرN�}��Ȥ�C��ɥX��Excel��椤�����s
UITool.AddEventHandler(window, "load", showDownloadBtn_preCheck);



//�ɥX���e�A�ˬd�C���O�_���ƾڰO��
function showDownloadBtn_preCheck() {

	if (document.getElementById("searchBtn")) {
		document.getElementById("searchBtn").outerHTML += ' <input type="button" name="downBtn" title="�O�s��e����U���Ҧ��ƾڨ�Excel���" value="�ɥX��Excel���" onclick="download_preCheck(\'down-xls\')">';
	}
}
function download_preCheck(dtype) {
	var listTable=document.getElementById("list00_table");//�C���e������table��H�]�Y���]�t�����>1�N�������ƾڡ^
	if(listTable.rows.length>1){
		download(dtype) ;
	}
	else{
		alert("�ثe�S���ƾڥi�ɥX�A�Х��d�ߡI");
	}
}