<%@page import="com.focustech.oss2008.utils.RoleResourceUtils"%>
<%
//914為添加系統參加的資源號，在這里借用一下
if(RoleResourceUtils.check(914L,pageContext) == false) {
    System.out.println("不能正直被訪問");
    response.sendRedirect("/logout.do");
    return;
}
%>