//�Τ_�K�[�e�d�ߦC��,�p�G�OFrameSet�ج[�N���ó̫�@�C
function hiddenAdd() {
	if (window.parent.isFrameSet != null) {//�ϥ�FrameSet�W�U����Φ�
		var tabObj = document.getElementById("list00_table");
		var rows = tabObj.rows;
		for (var i = 0; i < rows.length; i++) {
			var cell = rows[i].cells[rows[i].cells.length - 1];
			cell.innerHTML = "";
		}
	}
}
UITool.AddEventHandler(window, "load", hiddenAdd);

