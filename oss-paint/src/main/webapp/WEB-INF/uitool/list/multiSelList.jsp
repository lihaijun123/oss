<%
String strMul = request.getParameter("multiple");
if(strMul==null || strMul.length()<=0 || "1".equals(strMul)) {
    strMul = "1";
} else {
    strMul = "0";
}
request.setAttribute("multiple",strMul); 
%>
<%@include file="./lisCtrl.jsp"%>