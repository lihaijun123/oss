/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.focustech.extend.uitool;

import org.apache.struts.action.ActionForward;

import com.focustech.uitool.list.action.UIToolAction;

// Referenced classes of package com.nl.uitool.action:
//            UIToolAction

public class UIToolListCtrlAction extends UIToolAction
{

	@Override
	public ActionForward execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

   /* public UIToolListCtrlAction()
    {
        form = null;
        searchOther = "";
    }

    public ActionForward execute()
        throws Exception
    {
        getLogger().info(com.nl.uitool.action.UIToolListCtrlAction.class.getName() + "::execute()->Enter");
        ActionForward af = null;
        String forward = "";
        Map params = getDataFromRequest();
        try
        {
            form = (UIToolListCtrlForm)getForm();
            //modify by lihaijun 2011-8-24 start
            setDefaultDateQuery(params);
            // end
            String searchType = (String)MapTools.getObjectIgnoreCase(params, "_st_");
            if(searchType != null && searchType.length() >= 0)
                setFindWhereNew(params);
            else
                setFindWhere(params);
            UIToolCtrlMgmt mgmt = new UIToolCtrlMgmt();
            if("cust".equals(form.getNextStep()))
            {
                UIToolCustMgmt custMgmt = new UIToolCustMgmt();
                custMgmt.saveCustomInfo(form.getFuncID(), params);
                params.put("d_f", "");
                params.put("o_f", "");
                params.put("s_f", "");
                form.setNextStep("");
            }
            if("custdel".equals(form.getNextStep()))
            {
                UIToolCustMgmt custMgmt = new UIToolCustMgmt();
                custMgmt.delete(form.getFuncID(), params);
                params.put("d_f", "");
                params.put("o_f", "");
                params.put("s_f", "");
                form.setNextStep("");
            }
            if(params.containsKey("evaltime"))
                forward = "evalTime";
            else
            if("".equals(form.getNextStep()))
            {
                UIToolListCtrlBt bt = mgmt.getDisplayInfos(form, params, false);
                form.setData(bt);
                StringBuffer sbOtherFields = new StringBuffer();
                sbOtherFields.append("&superID=");
                sbOtherFields.append(form.getSuperID());
                sbOtherFields.append(searchOther);
                form.setOtherFields(sbOtherFields.toString());
                if("".equals(bt.getDisplayPage()))
                    forward = "list";
                else
                    forward = bt.getDisplayPage();
                form.setReqData(params);
            } else
            if("download".equals(form.getNextStep()))
            {
                UIToolListCtrlBt bt = mgmt.getDisplayInfos(form, params, true);
                String title = StringTools.notNull(bt.getPageTitle(), "sheet");
                String file = bt.getDownFile();
                String path = getMapping().findForward("download").getPath();
                af = new ActionForward(path + "?downloadFile=" + file + "&downFileName=" + URLEncoder.encode(title), true);
                af.setModule("");
            } else
            {
                throw new NLAppException("\u975E\u6CD5\u8BF7\u6C42\u3002");
            }
            if(af == null)
                af = getMapping().findForward(forward);
            writeLog(params, form.getFuncID(), null);
        }
        catch(NLDBException dbe)
        {
            writeLog(params, form.getFuncID(), dbe);
            throw dbe;
        }
        catch(NLAppException nle)
        {
            getLogger().error(com.nl.uitool.action.UIToolListCtrlAction.class.getName() + "::execute()->excep", nle);
            addMessage("user.messages", new String[] {
                nle.getMessage()
            });
            if(af == null)
                af = getMapping().findForward("errorPage");
            writeLog(params, form.getFuncID(), nle);
        }
        catch(Exception e)
        {
            writeLog(params, form.getFuncID(), e);
            throw e;
        }
        getLogger().info(com.nl.uitool.action.UIToolListCtrlAction.class.getName() + "::execute()->Exit");
        return af;
    }

    protected void setFindWhere(Map reqParam)
    {
        String orderField = (String)reqParam.get("orderfield");
        String orderOper = (String)reqParam.get("orderoper");
        String order = "";
        if(orderField != null && orderField.length() > 0){
        	//构建排序sql
        	order = ListFieldsManager.buildOrderSql(orderField, orderOper);
        }
        form.setSearchOrder(order);
        String searchFields[] = form.getSearchField();
        String searchOpers[] = form.getSearchOper();
        if(searchFields == null || searchOpers == null || searchFields.length <= 0)
            return;
        String upLike = (String)reqParam.get("uplike");
        String condOper = (String)reqParam.get("condoper");
        if(condOper == null || condOper.length() <= 0)
            condOper = "AND";
        StringBuffer sbFindWhere = new StringBuffer();
        for(int i = 0; i < searchFields.length; i++)
        {
            searchOther += "&searchField=" + searchFields[i] + "&searchOper=" + searchOpers[i] + "&" + searchFields[i] + "=" + UIToolUtils.getValueFromRequest(searchFields[i], reqParam);
            if(searchFields[i] != null && searchFields[i].length() > 0 && searchOpers[i] != null && searchOpers[i].length() > 0)
            {
                String field = "";
                if(">=".equals(searchOpers[i]))
                    field = "S_";
                else
                if("<=".equals(searchOpers[i]))
                    field = "E_";
                field = field + searchFields[i];
                String value = StringTools.valueOf(UIToolUtils.getValueFromRequest(field, reqParam));
                if(value != null && value.length() > 0)
                {
                    if(sbFindWhere.length() > 0)
                    {
                        sbFindWhere.append(" ");
                        sbFindWhere.append(condOper);
                        sbFindWhere.append(" ");
                    }
                    if("like".equalsIgnoreCase(searchOpers[i]) && upLike != null && upLike.length() > 0)
                        sbFindWhere.append("UPPER(" + searchFields[i] + ")");
                    else{
                    	//修改了添加时间、更新时间和审核时间、执行时间查询不带时分秒的问题
                        if (searchFields[i].equals("ADD_TIME") || searchFields[i].equals("UPDATE_TIME")
                                || searchFields[i].equals("AUDIT_TIME") || searchFields[i].equals("auditTime")
                                || searchFields[i].equals("EXECUTION_TIME")) {
                    		sbFindWhere.append("DATE_FORMAT(" + searchFields[i] + ", '%Y-%m-%d')");
                    	} else {
                    		sbFindWhere.append(searchFields[i]);
                    	}
                    }
                    sbFindWhere.append(" ");
                    sbFindWhere.append(searchOpers[i]);
                    if("like".equalsIgnoreCase(searchOpers[i]))
                    {	//modify by lihaijun 字符链接改为mysql语法
                        if(upLike != null && upLike.length() > 0)
                            sbFindWhere.append(" concat('%' , UPPER(&" + field + "), '%')");
                        else
                            sbFindWhere.append(" concat('%',&" + field + ",'%'");
                    } else
                    {
                        sbFindWhere.append(" &" + field);
                    }
                }
            }
        }

        if(sbFindWhere.length() > 0)
        {
            sbFindWhere.insert(0, " AND (");
            sbFindWhere.append(")");
        }
        form.setFindWhere(sbFindWhere.toString());
    }

    protected void setFindWhereNew(Map reqParam)
    {
        String orderField = (String)reqParam.get("orderfield");
        String orderOper = (String)reqParam.get("orderoper");
        String order = "";
        if(orderField != null && orderField.length() > 0)
            order = " ORDER BY " + orderField + " " + orderOper;
        form.setSearchOrder(order);
        String searchFields[] = form.getSearchField();
        String searchOpers[] = form.getSearchOper();
        String searchValues[] = form.getSearchValue();
        if(searchFields == null || searchOpers == null)
            return;
        StringBuffer sbFindWhere = new StringBuffer();
        String operStr = "";
        boolean needValue = true;
        String condOper = (String)reqParam.get("condoper");
        if(condOper == null || condOper.length() <= 0)
            condOper = "AND";
        String tmp = "";
        for(int i = 0; i < searchFields.length; i++)
        {
            try
            {
                operStr = UIToolUtils.getUISysParam(searchOpers[i], "sql");
                needValue = UIToolUtils.getUISysBooleanParam(searchOpers[i], "needValue", true);
                if(searchFields[i] == null || searchFields[i].length() <= 0 || searchOpers[i] == null || searchOpers[i].length() <= 0 || (searchValues[i] == null || searchValues[i].length() <= 0) && needValue)
                    continue;
            }
            catch(Exception e)
            {
                getLogger().error("\u67E5\u8BE2\u64CD\u4F5C\u4E0D\u5BF9\u6B63\u786E:[" + searchOpers[i] + "]\uFF0C\u4F7F\u7528\u9ED8\u8BA4\u67E5\u8BE2\u201C=\u201D", e);
                operStr = UIToolConst.DEFAULT_FIND_OPER;
            }
            if(operStr == null || operStr.length() <= 0)
                operStr = UIToolConst.DEFAULT_FIND_OPER;
            tmp = searchFields[i];
            operStr = operStr.replaceAll(UIToolConst.DEFAULT_FIND_FIELD, tmp);
            operStr = operStr.replaceAll(UIToolConst.DEFAULT_FIND_VALUE, tmp + "_" + i);
            reqParam.put(tmp + "_" + i, searchValues[i]);
            if(sbFindWhere.length() > 0)
            {
                sbFindWhere.append(" ");
                sbFindWhere.append(condOper);
                sbFindWhere.append(" ");
            }
            sbFindWhere.append(operStr);
        }

        if(sbFindWhere.length() > 0)
        {
            sbFindWhere.insert(0, " AND (");
            sbFindWhere.append(")");
        }
        form.setFindWhere(sbFindWhere.toString());
    }

    public String getSearchOther()
    {
        return searchOther;
    }

    public void setForm(UIToolListCtrlForm form)
    {
        this.form = form;
    }

    protected void setDefaultDateQuery(Map params){

    }

    private UIToolListCtrlForm form;
    private String searchOther;*/
}
