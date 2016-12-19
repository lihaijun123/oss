CKEDITOR.plugins.add( 'myAddImage',{
    init : function( editor )
    {
       /*
       /* 获取CKEditorFuncNum
       */
      var getFuncNum = function(url) {
         var reParam = new RegExp('(?:[\?&]|&amp;)CKEditorFuncNum=([^&]+)', 'i') ;
         var match = url.match(reParam) ;
          return (match && match.length > 1) ? match[1] : '' ;
        };
       /*
       /*  iframe onload处理
        *  这段可以放在外面，根据不同的返回值自行进行处理
        */
        var getAjaxResult = function (t){

            var _id = this.getId();
            var _doc = this.getFrameDocument();
            //获取页面返回值
            var data = _doc.getBody().getHtml();
			//alert(data);
            //firebrowser的处理
            CKEDITOR.tools.callFunction(t.listenerData, data);
            this.removeListener('load', getAjaxResult);
        }

        CKEDITOR.dialog.add( 'myAddImage', function( editor )
        {
            return {
                    title : '添加图片',
                    minWidth : 400,
                    minHeight : 200,
                    contents :
                    [
                        {
                            id : 'addImage',
                            label : '添加图片',
                            title : '添加图片',
                            filebrowser : 'uploadButton',
                            elements :
                            [

                              {
                                   id : 'photo',
                                   type : 'text',
                                   label : '上传图片',
                                   style: 'height:40px',
                                   size : 38
                              }

                            ]
                       }
                   ],
                   onOk : function(){
                       _src = this.getContentElement('addImage', 'photo').getValue();
                       if (_src.match(/(^\s*(\d+)((px)|\%)?\s*$)|^$/i)) {
                           alert('请输入上传文件');
                           return false;
                       }
					   var size = _src.substring(0,_src.lastIndexOf(".")).split("-");
					   var len = size.length;
					   var height = size[len-1];
					   var width = size[len-2];
					   if(height && width){
					   		if(width >= height){
						   		if(width > 200){
									height = height * 200 / width+"px";
									width = 200+"px";
								}
							}else{
								if(height > 200){
									width = width * 200 / height+"px";
									height = 200+"px";
								}
							}
							if(width <= 200 && height <= 200){
								width = width+"px";
								height = height+"px";
							}
					   } else {
					   		width = "200px";
							height = "200px";
					   }

                       this.imageElement = editor.document.createElement( 'img' );
                       this.imageElement.setAttribute( 'alt', '' );
                       this.imageElement.setAttribute( 'src', _src );
					   this.imageElement.setAttribute( 'style', "width:"+width+";height:"+height);
                       //图片插入editor编辑器
                       editor.insertElement( this.imageElement );
                   }
            };
        });
		//给自定义插件注册一个调用命令
        editor.addCommand( 'myImageCmd', new CKEDITOR.dialogCommand( 'myAddImage' ) );

		//注册一个按钮，用于调用自定义插件
        editor.ui.addButton( 'AddImage',
        {
                label : '图片',
                icon:'images/images.jpg', //toolbar上icon的地址,要自己上传图片到images下
                command : 'myImageCmd'
        });
    },
    requires : [ 'dialog' ]
});
