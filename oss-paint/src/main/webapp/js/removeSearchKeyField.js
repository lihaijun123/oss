
/*
*�Τ_�K�[�e�d�ߦC���O�_�����S���Ȫ��P�_
*/
try {
	window._searchAll = false;
//�O�_�ݭn�����o�Ǧr�q���ȫ���C��SQL��
	var isNeedClearFields = true;
}
catch (e) {
}
function removeSearchKeyField(hasValue) {
	if (hasValue) {
		var fields = document.getElementsByName("searchField");
		for (var i = 0; i < fields.length; ) {
			fields[i].parentNode.removeChild(fields[i]);
		}
		if(document.getElementsByName("searchBtn") && document.getElementsByName("searchBtn")[0]) {
			document.getElementsByName("searchBtn")[0].outerHTML="<font color='red'>���b�d�ߡK�K</font>";
		}
	}
}

