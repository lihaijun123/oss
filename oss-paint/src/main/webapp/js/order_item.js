/**
 * �u�X�T�{���f
 * @param {Object} url
 */
function check(url) {
	if(window.confirm("�T�w���\���o�C�p���\�A�t�αNAR���i�o�e���R�a�A�ȱM���A����o���R�a�Ȥ�C")){
      var locUrl = location.href;
      location.href=url+"&_h_referer="+escape(locUrl);
  }
}

