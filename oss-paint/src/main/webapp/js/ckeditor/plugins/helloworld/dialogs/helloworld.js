/**
 * Title:CKEditor在线编辑器的代码插入插件
 * Author:铁木箱子(http://www.mzone.cc)
 * Date:2010-07-21
 */
CKEDITOR.dialog.add('helloworld', function(editor) {
	var _escape = function(value){
		return value;
	};
	return {
		title: editor.lang.dlgTitle,
		resizable: CKEDITOR.DIALOG_RESIZE_BOTH,
		minWidth: 220,
		minHeight: 250,
		contents: [{
			id: 'cb',
			name: 'cb',
			label: 'cb',
			title: 'cb',
			elements: [
//			{
//				type: 'textarea',
//				required: true,
//				label: editor.lang.mytxt,
//				style: 'width:350px;height:100px',
//				rows: 3,
//				id: 'mytxt',
//				'default': 'Hello World'
//			},
			{
				type: 'checkbox',
				required: true,
				label: '公司名称',
				id: 'box9'
			}
			,{
				type: 'checkbox',
				required: true,
				label: '客户姓名',
				id: 'box1'
//				,
//				onClick: function(evt) {
//					alert('button1');
//    			}
			}
			,{
				type: 'checkbox',
				required: true,
				label: '会员级别',
				id: 'box2'
			},{
				type: 'checkbox',
				required: true,
				label: '行业类型',
				id: 'box3'
			},{
				type: 'checkbox',
				required: true,
				label: '电话',
				id: 'box4'
			},{
				type: 'checkbox',
				required: true,
				label: '传真',
				id: 'box5'
			},{
				type: 'checkbox',
				required: true,
				label: '注册邮箱',
				id: 'box6'
			}
//			,{
//				type: 'checkbox',
//				required: true,
//				label: '联系人',
//				id: 'box7'
//			}
			,{
				type: 'checkbox',
				required: true,
				label: '系统时间',
				id: 'box8'
			},{
				type: 'checkbox',
				required: true,
				label: '订单服务内容',
				id: 'box10'
			}]
		}],
		onOk: function(){
			var mybox1 = this.getValueOf('cb', 'box1');
			var mybox2 = this.getValueOf('cb', 'box2');
			var mybox3 = this.getValueOf('cb', 'box3');
			var mybox4 = this.getValueOf('cb', 'box4');
			var mybox5 = this.getValueOf('cb', 'box5');
			var mybox6 = this.getValueOf('cb', 'box6');
			//var mybox7 = this.getValueOf('cb', 'box7');
			var mybox8 = this.getValueOf('cb', 'box8');
			var mybox9 = this.getValueOf('cb', 'box9');
			var mybox10 = this.getValueOf('cb', 'box10');
			if(mybox9){
				editor.insertHtml("公司名称：<a href='#' name='emailtemp_comName'>XXXX</a>");
			}
			if(mybox1){
				editor.insertHtml("客户姓名：<a href='#' name='emailtemp_memberName'>XXX先生/女士</a>");
			}
			if(mybox2){
				editor.insertHtml("会员级别：<a href='#' name='emailtemp_memberGrade'>XXX</a>");
			}
			if(mybox3){
				editor.insertHtml("主营行业：<a href='#' name='emailtemp_memberMainBusiness'>XXX</a>");
			}
			if(mybox4){
				editor.insertHtml("电话：<a href='#' name='emailtemp_memberPhone'>XXXXXXXX</a>");
			}
			if(mybox5){
				editor.insertHtml("传真：<a href='#' name='emailtemp_memberFax'>XXXXXXXX</a>");
			}
			if(mybox6){
				editor.insertHtml("注册邮箱：<a href='#' name='emailtemp_memberEmail'>XXXXXXXX</a>");
			}
//			if(mybox7){
//				editor.insertHtml("联系人：<a href='#' id='emailtemp_memberContact'>XXX</a>");
//			}
			if(mybox8){
				editor.insertHtml("系统时间：<a href='#' name='emailtemp_sysTime'>XXXX年XX月XX日 XX:XX:XX</a>");
			}
			if(mybox10){
				var ordersTable = '<p>订单服务内容：</p><table name="emailtemp_orders">'
									+'<col style="width:260px"><col style="width:150px"/><col style="width:150px"/><col style="width:200px;"/><col style="width:120px;"/>'
									+'<thead><tr><th>服务内容</th><th>所属展会</th><th>发布状态</th><th>发布时间</th><th>有效时间</th></tr></thead>'
									+'<tbody><tr><td>订单1</td><td>XXX</td><td>XXX</td><td>XXX</td><td>XXX</td></tr>'
									+'<tr><td>订单2</td><td>XXX</td><td>XXX</td><td>XXX</td><td>XXX</td></tr>'
									+'</tbody></table>';
				editor.insertHtml(ordersTable);
			}
			//var choseFlag = mybox1||mybox2||mybox3||mybox4||mybox5||mybox6||mybox7||mybox8||mybox9||mybox10;
			var choseFlag = mybox1||mybox2||mybox3||mybox4||mybox5||mybox6||mybox8||mybox9||mybox10;
			if(!choseFlag){
				alert("请选择需要插入的会员信息");
				return false;
			}




		},
		onLoad: function(){
		}
	};
});