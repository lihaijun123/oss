<%@page import="com.focustech.oss2008.utils.RoleResourceUtils"%>
<%
//914���K�[�t�ΰѥ[���귽���A�b�o���ɥΤ@�U
if(RoleResourceUtils.check(914L,pageContext) == false) {
    System.out.println("���ॿ���Q�X��");
    response.sendRedirect("/logout.do");
    return;
}
%>