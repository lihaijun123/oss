<%@page import="com.focustech.uitool.framework.utils.MapTools"%>
<%@page import="com.focustech.uitool.list.dt.DisplayField"%>
<%@page import="com.focustech.uitool.list.dt.HtmlTDBean"%>
<%@page import="com.focustech.uitool.list.dt.HtmlTRBean"%>
<%@page import="com.focustech.uitool.list.dt.HtmlTableBean"%>
<%@page import="com.focustech.uitool.list.utils.UIToolUtils"%>
<%@page import="com.focustech.uitool.list.dt.UIToolListCtrlBt"%>
<%@page import="com.focustech.uitool.list.form.UIToolListCtrlForm"%>
<%@page import="com.focustech.uitool.list.utils.UIToolJSPTools"%>

<%@page import="com.focustech.common.utils.StringUtils"%>
<%@page import="com.focustech.common.utils.TCUtil"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>

<%@page import="java.util.Map"%>

<jsp:directive.page import="java.util.HashMap"/>
<jsp:directive.page import="java.util.ArrayList"/>

<%@page import="org.apache.struts.util.ResponseUtils"%>
<%@page import="java.util.regex.Pattern"%>
<%
{

UIToolListCtrlForm form1 = (UIToolListCtrlForm) request.getAttribute(NLGlobal.CURR_FORM_KEY);
UIToolListCtrlBt data1 = form1.getData();
boolean m = "1".equals(request.getAttribute("multiple"));
boolean selected = request.getAttribute("multiple") !=null;
%>
<script>
function deleteConfirm(url) {
	if(confirm("你确定要刪除当前信息吗？")) {
		window.location.href = url;
	}
}
<%if(selected){%>
function doNext() {
	var checked = false;
	var selIds = document.getElementsByName("sel_id");
	for(var i = 0 ; i < selIds.length ; i++) {
		if(selIds[i].checked) {
			checked = true;
			break;
		}
	}
	if(checked)
		document.nextForm.submit();
	else
		alert("请先在多选框进行选择！");
}
function doSelAll() {
	var items = document.getElementsByName("sel_id");
	for(var i = 0 ; i < items.length ;i++) {
		items[i].checked = true;
	}
}
function doUnSelAll() {
	var items = document.getElementsByName("sel_id");
	for(var i = 0 ; i < items.length ;i++) {
		items[i].checked = !items[i].checked;
	}
}
<%}%>
</script>
<%--陈云龙 --%>
<%--
<%
if(data1.getTotalLimit() <=0 || form1.getTotalRec() <= data1.getTotalLimit()) {
long totalRec = form1.getTotalRec();
long total = 0 ;
int maxRec = form1.getPageRecMaxLimit();
if(totalRec%maxRec > 0){
	total = totalRec/maxRec +1;
}else{
	total = totalRec/maxRec ;
}
%>
<table id="list00_rectable" width="98%" border="0" align="center" cellpadding="1" cellspacing="0" bgcolor="#C4D7FF">
<tr><td>当前页数/总页数︰<%=form1.getCurrPage()<=0?1:form1.getCurrPage()%>/<%=total%> 总记录数︰<font color="red"><%=totalRec%></font></td></tr>
</table>
<%} %>
--%>
<table id="list00_table" width="98%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#C4D7FF">
<%--        --%>
<%-- 顯示標題 --%>
<%--         --%>
<%
String otherFields = form1.getOtherFields();
if(!otherFields.startsWith("&") && !"".equals(otherFields)) {
	otherFields = "&" + otherFields;
}
otherFields = UIToolUtils.parseUrlSpecialChar(otherFields,true);

//----------------------------------------------------------------------------------------------------------------------
List titleData = data1.getLisTitleData() ;

if(titleData != null && titleData.size() > 0) {
HtmlTableBean table = data1.preparedTitleRows();
List rows = table.getRows();
titleData = table.getLastRowTitle();
HtmlTRBean row = null;
int iRows = rows.size();
for(int i = 0 ; i < rows.size();i++) {
	row = (HtmlTRBean)rows.get(i);
	//------------- check role action ----------------------------------------------------------------------------------
	List cells = row.getCells();
	for(int iCell = 0 ; cells !=null && iCell < cells.size() ;iCell++) {
		if("_oper_".equalsIgnoreCase(((HtmlTDBean)cells.get(iCell)).getId())) {
			HtmlTDBean cell = (HtmlTDBean)cells.get(iCell) ;
			String disTexts = cell.getContent();
			String[] vTexts = disTexts.split(":",2);
			if(vTexts.length >0) {
				cell.setContent(vTexts[0]);
			}
			if(vTexts.length >1) {
				vTexts = vTexts[1].split(",");
				int iv = 0;
				for( ; iv < vTexts.length ;iv++) {
					if(UIToolJSPTools.checkDataPurv(StringTools.parseLong(vTexts[iv],-1),request)) {
						break;
					}
				}
				if(iv >= vTexts.length) {
					cell.setContent("&nbsp;");
					cell.setHref("");
				} else {
					String[] tmp = cell.getHref().split(";");
					if(tmp.length >0) {
						cell.setHref(tmp[iv]);
					}
				}
			}
			break;
		}
	}
	//------------------------------------------------------------------------------------------------------------------
	if(i==0) {
		HtmlTDBean cell = row.insertCell(0);
		cell.setRowSpan(iRows);
		cell.setContent("序号");
		if(selected) {
			cell = row.insertCell(0);
			if(m) {
				cell.setContent("<a href=\"javascript:doSelAll()\">全选</a>/<a href=\"javascript:doUnSelAll()\">反选</a>");
			}
			cell.setRowSpan(iRows);
		}
	}
	row.setClazz("title");
	row.setRequest(request);
	row.setReqParam(form1.getReqData());
	out.println(row.toString());
}
%>
<%--         		--%>
<%-- 顯示數據行信息 	--%>
<%--         		--%>
<%
List attributes = data1.getAttributes();
StringBuffer attrs = new StringBuffer();
DisplayField df = null;
for(int i = 0 ; attributes!=null && i < attributes.size() ;i++) {
	df = (DisplayField) attributes.get(i);
	attrs.append(df.getField().toUpperCase());
	attrs.append("=\"#");
	attrs.append(df.getField().toUpperCase());
	attrs.append("#\"");
}

%>


<%--         --%>
<%-- 顯示數據  --%>
<%--         --%>
<%
//
String style = "odd";
int index = 0 ;
Map tfoot = new HashMap();
Map line = null;
Map roleCache = new HashMap();
while(data1.next()) {
	if(index++%2 == 0) {
		style = "odd";
	} else {
		style = "even";
	}
	//
	line = data1.getLineData();
%>
			<tr align="center" bgcolor="#FFFFFF" class="<%=style%>" id="data_tr" <%=UIToolUtils.formatUrl(attrs.toString(),line)%>>
<%
if(selected) {
	String checked = "";
	if(data1.getString("KEY").length() > 0 && data1.getString("KEY").equals(data1.getString("OLD_KEY"))) {
		checked = "checked";
	}
%>
				<td style="width:30px;"><input type="<%=m?"checkbox":"radio"%>" index="<%=index-1%>" value="<%=data1.getString("KEY")%>" text="<%=data1.getString("VALUE")%>" name="sel_id" <%=checked%>></td>
<%}%>
			<td><%=StringUtils.trimEndChar(TCUtil.sv(line.get("NUM_ROW_ZZZ")), ".0")%></td>
<%
	HtmlTDBean title = null;
	Object value = "";
	String format = "";
	String tdClass = "lisTd";
	int cellCharLength = MapTools.getInt(form1.getReqData(),"charlength",22);
	int charlength = MapTools.getInt(form1.getReqData(),"charlength",22);
	List totalDatas = null;

	for(int j = 0 ; j < titleData.size();j++) {
		title = (HtmlTDBean)titleData.get(j);
		value = line.get(title.getId());
		//合計處理
		if(title.getNumTotalDef() != null) {
			totalDatas = (List) tfoot.get(title.getId());
			if(totalDatas == null) {
				totalDatas = new ArrayList();
				tfoot.put(title.getId(),totalDatas);
			}
			totalDatas.add(value);
		}
		//
		if(title.getSearchValue() != null) {
			value = UIToolUtils.getValue(title.getSearchValue(),StringTools.valueOf(value));
		}
		//如果當前值是數字，則使用不同的className
		if(value instanceof Number){
			tdClass = "lisNumTd";
		} else {
			tdClass = "lisTd" + title.getField() ;;//+ "_" + j;
		}
		//格式化顯示內容,如果是使用JAVACODE
		format = title.getFieldFormat();
		String align="";
		//
		Map param = UIToolJSPTools.format(format,value,line,form1.getReqData(),request);
		value = param.get("value");
		align = MapTools.getString(param,"align","");
		cellCharLength = MapTools.getInt(param,"cellCharLength",charlength);
		if("".equals(title.getUrl())) {
			//
			String aTmp = "";
			aTmp=UIToolJSPTools.formatUrl(param);
			param.put("value",aTmp);
			aTmp = ResponseUtils.filter(aTmp);
			if(aTmp.contains("@")){
				aTmp=UIToolJSPTools.formatMail(param);
			}
			cellCharLength = MapTools.getInt(param,"cellCharLength",charlength);
			//
			aTmp = UIToolUtils.getSubString(StringTools.valueOf(aTmp),cellCharLength);
%>
				<td class="<%=tdClass%>" title="<%=ResponseUtils.filter(String.valueOf(value))%>" align="<%=align%>" ><%=aTmp%></td>
<%
		} else {
			if("_oper_".equalsIgnoreCase(title.getId())) {
				String[] uriArg = title.getUrl().split(";");
				String[] disTexts = title.getFieldText().split(";");
%>
				<td nowrap >
				<%
				//處理單行多值的問題
				if(disTexts.length > 1) {
					for(int i = 1 ; i < disTexts.length;i++) {
					//-------- check role action -----------------------------------------------------------------------------------
					String id = title.getId()+ "_" +i;
					if(!roleCache.containsKey(id)) {
						//第一次初始化動作權限
						long actId = -1;
						String[] vTexts = disTexts[i].split(":",2);
						if(vTexts.length >0) {
							disTexts[i] = vTexts[0];
							title.setFieldText(StringTools.toString(disTexts,";"));
						}
						if(vTexts.length >1) {
							actId = StringTools.parseLong(vTexts[1],actId);
							roleCache.put(id,Boolean.valueOf(UIToolJSPTools.checkDataPurv(actId,request)));
						} else {
							roleCache.put(id,new Boolean(true));
						}
					}
					if(((Boolean)roleCache.get(id)).booleanValue() == false) {
						//沒有權限使用當前動作
						continue;
					}
					//---------------------------------------------------------------------------------------------------
					String uri = uriArg[i-1];
					if(uri == null) uri = "";
					uri = uri.toLowerCase();
					boolean encode = (uri.startsWith("javascript:") || uri.startsWith("http://")) == false;
					if(encode) {
				%>
					<a href="<%=UIToolUtils.formatUrl(UIToolUtils.formatUrl(uriArg[i-1],data1.getLineData(),true),request,form1.getReqData(),true)%>&otherFields=<%=otherFields%>"><%=disTexts[i]%></a>
				<%
					} else {
				%>
					<a href="<%=UIToolUtils.formatUrl(UIToolUtils.formatUrl(uriArg[i-1],data1.getLineData(),false),request,form1.getReqData(),false)%>"><%=disTexts[i]%></a>
				<%
					}
					}
				} else { //兼容以前的配置信息
				%>
					<a href="<%=UIToolUtils.formatUrl(UIToolUtils.formatUrl(uriArg[0],data1.getLineData()),request,form1.getReqData())%>&otherFields=<%=otherFields%>">修改</a>
					<a href="#" onclick="javascript:deleteConfirm('<%=UIToolUtils.formatUrl(UIToolUtils.formatUrl(uriArg[1],data1.getLineData()),request,form1.getReqData())%>&otherFields=<%=otherFields%>');return false;">刪除</a>
				<%} %>
				</td>
<%
			} else {
				String tmp = title.getUrl().toLowerCase();
				boolean encode = (tmp.startsWith("javascript:")|| tmp.startsWith("http://")) == false;
%>
				<td class="<%=tdClass%>" align="<%=align%>"><a href="<%=UIToolUtils.formatUrl(UIToolUtils.formatUrl(title.getUrl(),data1.getLineData(),encode),request,form1.getReqData(),encode)%>"><%=ResponseUtils.filter(UIToolUtils.getSubString(StringTools.valueOf(value),cellCharLength))%></a></td>
<%
			} // enf if
		} // end if
	} // end for
%>
			</tr>
<%
} // end while
if(tfoot !=null && tfoot.isEmpty() == false) {
%>
	<tr align="center" bgcolor="#FFFFFF" class="cl_tfoot" id="data_tfoot">
<%if(selected){%><td colspan="2">合计</td>
<%} else {%><td>合计</td>
<%}
	HtmlTDBean title = null;
	Object value = null;
	int cellCharLength = 20;
	String tdClass = "lisTd";
	for(int j = 0 ; j < titleData.size();j++) {
		title = (HtmlTDBean)titleData.get(j);
		value = tfoot.get(title.getId());
		if(value != null) {
			value = UIToolUtils.executeCubeClass(title.getNumTotalDef(),(List)value,form1.getReqData());
			tfoot.put(title.getId(),value);
		}
	}
	for(int j = 0 ; j < titleData.size();j++) {
		title = (HtmlTDBean)titleData.get(j);
		value = tfoot.get(title.getId());
		String align="center";
		if(value == null) {
			value = "-";
			if(j == 0) {
				value = "合计";
			}
		} else {
			if(value instanceof Number) {
				tdClass = "lisNumTd";
			} else {
				tdClass = "lisTd" + title.getField() ;
			}
			//
			String format = title.getFieldFormat();
			Map param = UIToolJSPTools.format(format,value,tfoot,form1.getReqData(),request);
			value = param.get("value");
			align = MapTools.getString(param,"align","center");
			cellCharLength = MapTools.getInt(param,"cellCharLength",cellCharLength);
		}
%>
		<td class="<%=tdClass%>" title="<%=value%>" align="<%=align%>" nowrap><%=UIToolUtils.getSubString(StringTools.valueOf(value),cellCharLength)%></td>
<%
	} // for ---
%>	</tr>
<%
} // end if(tfoot.isEmpty == false)
%>
</table>
<%if(selected){ %>
<table><tr bgcolor="#E7F0FE"><td align="right" id="selOperTd">
<input type="button" name="selbtn" value="下一步>>" onclick="doNext()"/>
</td></tr></table>
<%}%>
<%
} // end if(titleData !=null)
else {out.println("<tr><td align='center'><font color='red'>沒有配置显示信息.</font></td></tr>");}
}
%>
