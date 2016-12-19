/**
 * Title:CKEditor插件示范
 * Author:铁木箱子(http://www.mzone.cc)
 * Date:2010-08-02
 */
CKEDITOR.plugins.add('helloworld', {
	lang:['zh-cn','en'],
    requires: ['dialog'],
    init: function(a){
        var b = a.addCommand('helloworld', new CKEDITOR.dialogCommand('helloworld'));
        a.ui.addButton('helloworld', {
            label: a.lang.tbTip,
            command: 'helloworld',
            icon: this.path + 'images/hello.png'
        });
        CKEDITOR.dialog.add('helloworld', this.path + 'dialogs/helloworld.js');
    }
});