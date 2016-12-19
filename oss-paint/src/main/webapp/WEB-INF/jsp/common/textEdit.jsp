<!-- textEdit -->
<script src="js/ckeditor/ckeditor.js" type="text/javascript" charset="utf-8"></script>
<script src="js/ckeditor/upload.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8">
	function initTextEdit(contentId){
		//replace html content elem
		CKEDITOR.replace(contentId);
		//get content of textEdit
		var oEditor = CKEDITOR.instances[contentId];
	    var strBody = oEditor.getData();
	    // add event
		CKEDITOR.instances[contentId].on("instanceReady", function () {
			//set keyup event
			this.document.on("keyup", function (){ckeditorKeyUp(contentId)});
			//and click event
			//this.document.on("click", AutoSave);
			//and select event
			//this.document.on("select", AutoSave);
		});
	    return encodeURIComponent(strBody);
	}



	function ckeditorWordsCountLimit(){
		return 10;
	}
	function ckeditorImgCountLimit(){
		return 10;
	}

</script>