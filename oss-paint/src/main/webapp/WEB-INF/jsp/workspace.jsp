<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="/resources/css/ext-all-main.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="/extjs/ext-base-main.js"></script>
<script type="text/javascript" src="/extjs/ext-all-main.js"></script>
<script type="text/javascript" src="/extjs/style-main.js"></script>
<script type="text/javascript" src="/extjs/page_widget-main.js"></script>
<script type="text/javascript" src="/extjs/ext-base.js"></script>
<script type="text/javascript" src="/extjs/FocusRemoteCaller.js"></script>
<script language="javascript" src="js/pop_window.js"></script>
<script language="javascript" src="/js/bulletin.js"></script>
<script type="text/javascript" src="/js/help/iframe_menu.js" ></script>
<style type="text/css">
html,body {
	font: normal 12px verdana;
	margin: 0;
	padding: 0;
	border: 0 none;
	overflow: hidden;
	height: 100%;
}
p {
	margin: 5px;
}
.settings {
	background-image: url(/test/icons/fam/folder_wrench.png);
}
.nav {
	background-image: url(/test/icons/fam/folder_go.png);
}
</style>
<script type="text/javascript">
function closeDWindow() {
	dhxWins.window("w1").close();
}
var tabObj ;
Ext.onReady(function(){

	var wkTabUrl = "/navigation.jsp";
	if($("#tab_url_0").length > 0){
		wkTabUrl = $("#tab_url_0").val();
	}

	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
	// shorthand
    var Tree = Ext.tree;
    var tabPanel = null;
    var tree = new Tree.TreePanel({
        el:'center2',
        useArrows:true,
        autoScroll:true,
        animate:true,
        enableDD:false,
        containerScroll: true,
        loader: new Tree.TreeLoader({
            dataUrl:'/showmenu.do'
        })
    });
    // set the root node
    var root = new Tree.AsyncTreeNode({
        text: '后台管理系统',
        draggable:false,
        id:'source'
    });
    tree.setRootNode(root);
    // render the tree
    tree.render();
    root.expand();

    function treeEvent(node,e){
    	if(node.attributes.href == "javascript:window.status='';") {
   			node.toggle();
   			e.stopEvent();
    		return ;
    	}
    	var pageFrame = Ext.get("center1");
   		tabPanel.activate("workid");
	}
    tree.on('click', treeEvent);
	var viewport = new Ext.Viewport({
	     layout:'border',
	     items:[
	         new Ext.BoxComponent({ // raw
	             region:'north',
	             el: 'north',
	             height:32
	         }), {
	             region:'west',
	             title: '菜单栏',
	             collapsible: true,
	             split:true,
	             width: 225,
	             minSize: 175,
	             maxSize: 400,
	             layout:'fit',
	             margins:'0 5 0 0',
	             el:'center2',
	             autoScroll:true


	          },
	         tabPanel = new Ext.TabPanel({
	             region:'center',
	             deferredRender:false,
	             activeTab:0,
	             id:"ossTab",
	             items:[{
	                 el:'center3',
	                 id:'tab2',
	                 title: '首页',
	                 closable:false,
	                 autoScroll:false,
	                 html:"<div style='width:100%;height:100%'><iframe id='homePage' name = 'homePage' src='" + wkTabUrl + "' frameBorder='0'  style='width:100%;height:100%' scrolling='yes'></iframe></div>"
	             },
	             {
	                 el:'center1',
	                 id:'workid',
	                 title: '工作区',
	                 closable:false,
	                 autoScroll:false,
	                 html:"<div style='width:100%;height:100%'><iframe id='centerPage' name = 'centerPage' src='/navigation.jsp' frameBorder='0'  style='width:100%;height:100%;position:relative;' scrolling='yes'></iframe></div>"
	             }]
	         })
	      ]
	});
	tabObj = tabPanel;
	//menu
	tabObj.items.first().on('activate',function(){
		Ext.get('homePage').dom.contentWindow.location.reload();
	})
	var menu = new Ext.menu.Menu({
	id: 'mainMenu',
	items: [
	    {
	        text: '角色列表',
	        checked: true,       // when checked has a boolean value, it is assumed to be a CheckItem
	        checkHandler: onItemCheck
	    },
	    {
	        text: '权限列表',
	        checked: true,
	        checkHandler: onItemCheck
	    }
	]
	});
    function onItemCheck(item, checked){
        //Ext.example.msg('Item Check', 'You {1} the "{0}" menu item.', item.text, checked ? 'checked' : 'unchecked');
    }
});
function changeListModel() {
	var lisModel = document.getElementById("a_list_model");
	if(lisModel.innerText == '普通') {
	    var frc = new FRC();
	    frc.url = "/lisModel.do?flag=1";
	    frc.callBack = function (data) {}
	    frc.remoteCall();
	    lisModel.innerText = '自定义';
	    lisModel.textContent = '自定义';
	} else {
	    var frc = new FRC();
	    frc.url = "/lisModel.do?flag=0";
	    frc.callBack = function (data) {}
	    frc.remoteCall();
		lisModel.innerText = '普通';
		lisModel.textContent = '普通';
	}
}
</script>
</head>
<body>
<div id="north" style="height: 32px; margin: 0px; padding: 0px; border: 0px solid #000;">
	<table width="100%" style="background: none; border: none">
		<tr>
			<td id="toolbar" width="350px" style="padding-left: 10px;font-size: 13px;font-weight: bold ;color:#15428B; vertical-align: middle; background: none; border: none;font-family︰tahoma,arial,verdana,sans-serif">
				<img src="images/logo.gif"/>
			</td>
			<td
				style="margin-right: 10px; font-size: 12px; font-color: red; background: none; border: none; vertical-align: bottom; "
				align="right" valign="bottom" nowrap="nowrap">
				<p style="font-size: 12px">	${info} &nbsp;&nbsp;&nbsp;&nbsp;
				<a href="logout.do">注销</a>&nbsp;
				<!-- <a href="#">切換用戶</a> -->
				<a href='#' onclick="javascript:popDWindow('修改用户信息','toolBox.do?method=modifySelf&userId=${userId}',800,520);">信息修改&nbsp;</a>
				<!--  <a href='#' onclick="show('${aut_model}',true)">公告栏</a>
				<%if(session.getAttribute("SF_K") !=null) {%>
				<a id="a_list_model" href="javascript:changeListModel();">自定义</a>
				<%}else{ %>
				<a id="a_list_model" href="javascript:changeListModel();">普通</a>
				<%} %>-->
				</p>
			</td>
		</tr>
	</table>
	<c:forEach var="tab" items="${workspaceTabs }" varStatus="status">
		<input type="hidden" id="tab_url_${status.index }" value="${tab.resourceInterface}"/>
	</c:forEach>
</div>
<div id="centerdsa1"></div>
<div id="props-panel"
	style="width: 200px; height: 200px; overflow: hidden;"></div>
<div id="center1"></div>
<div id="center2"></div>
<div id="center3"></div>
</body>
</html>
<script>
   /*
    日期控件，用于事件的记录，每日提醒等
    */
  	 function onSelect(picker, date){
		alert(date);
    }
 var mmmk = new Ext.DatePicker({});
	//mmmk.render("ccc");

	// mmm.on("select", onSelect, this);

</script>
<%
//request.getSession().setAttribute("SF_K","1");
%>