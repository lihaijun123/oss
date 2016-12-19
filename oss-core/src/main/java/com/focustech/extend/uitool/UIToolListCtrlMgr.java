/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.focustech.extend.uitool;

import com.focustech.uitool.framework.bc.QueryMgr;

public class UIToolListCtrlMgr extends QueryMgr
{

   /* public UIToolListCtrlMgr()
    {
        uitoolConn = null;
        dataConn = null;
        sysId = "";
        funcID = -1L;
        superExamID = -1L;
        data = null;
        reqParam = null;
        custFindFields = null;
        custOrderFields = null;
        custDisplayFields = null;
    }

    public void init()
        throws Exception
    {
        if(sysId == null || "".equals(sysId))
        {
            throw new Exception("\u7CFB\u7EDF\u6807\u8BC6\u4E3A\u7A7A\uFF0C\u7CFB\u7EDF\u6CA1\u6709\u521D\u59CB\u5316\u3002");
        } else
        {
            setData(new UIToolListCtrlBt());
            getFuncDisplayInfo();
            return;
        }
    }

    public void fetchData()
        throws Exception
    {
        getAllCustomInfo();
        getAllDisplayFields();
        fetchData(getSql());
    }

    protected void getFuncDisplayInfo()
        throws Exception
    {
        StringBuffer strSql;
        PreparedStatement pstmt;
        ResultSet res;
        getLogger().info(ROLE + "::getFuncDisplayInfo(int)->Enter");
        strSql = new StringBuffer();
        strSql.append("SELECT FUNC_TITLE,DISPLAY_CSS,DISPLAY_JS,DISPLAY_PAGE,LIKE_TYPE,PAGE_MAX,TOTAL_PAGE_LIMIT,TOTAL_LIMIT ");
        strSql.append(" FROM UITOOL_FUNC_DISPLAY_INFO A");
        strSql.append(" WHERE A.FUNC_ID=?");
        pstmt = null;
        res = null;
        try
        {
            pstmt = getUitoolConn().prepareStatement(strSql.toString());
            pstmt.setLong(1, getFuncID());
            res = pstmt.executeQuery();
            if(res.next())
            {
                data.setPageTitle(StringTools.notNull(res.getString("FUNC_TITLE")));
                String css = StringTools.notNull(res.getString("DISPLAY_CSS"));
                data.setPageCss(css.split(";"));
                String js = StringTools.notNull(res.getString("DISPLAY_JS"));
                data.setPageJs(js.split(";"));
                data.setDisplayPage(StringTools.notNull(res.getString("DISPLAY_PAGE")));
                data.setUpLike(res.getInt("LIKE_TYPE"));
                data.setPageLimit(res.getLong("PAGE_MAX"));
                data.setPageTotalLimit(res.getLong("TOTAL_PAGE_LIMIT"));
                data.setTotalLimit(res.getLong("TOTAL_LIMIT"));
            }
        }
        catch(Exception e)
        {
            getLogger().error(ROLE + "::getFuncDisplayInfo(int)->excep" + strSql.toString());
            throw e;
        }
        DBTools.close(res);
        DBTools.close(pstmt);
        DBTools.close(res);
        DBTools.close(pstmt);
        getLogger().info(ROLE + "::getFuncDisplayInfo(int)->Exit");
        return;
    }

    private FuncSqlBean getSql()
        throws Exception
    {
        FuncSqlBean funcSqlBean = null;
        FunctionBean funcBean = uitool.getFunction(sysId, String.valueOf(getFuncID()));
        if(funcBean == null)
            throw new NLAppException("\u7CFB\u7EDF\u4E2D\u6CA1\u6709\u627E\u5230\u76F8\u5173\u529F\u80FD\u7684\u914D\u7F6E\u4FE1\u606F\uFF08func-id:" + funcID + "\uFF09\u3002");
        if(funcBean.getBeanName() != null && funcBean.getBeanName().length() > 0)
        {
            List lisData = UIToolUtils.executeFetch(funcBean.getBeanName(), reqParam);
            data.setLisData(lisData);
            return null;
        }
        funcBean.config(uitool);
        List lisSelectSql = funcBean.getSelectSql();
        if(lisSelectSql != null && lisSelectSql.size() > 0)
            funcSqlBean = (FuncSqlBean)lisSelectSql.get(0);
        if(funcSqlBean == null)
        {
            throw new NLAppException("\u7CFB\u7EDF\u4E2D\u6CA1\u6709\u627E\u5230\u76F8\u5173\u7684SQL\u914D\u7F6E\u4FE1\u606F\uFF08func-id:" + funcID + "\uFF09\u3002");
        } else
        {
            getLogger().debug(">>>>>>>>>>>:current query sql:" + funcSqlBean.toString());
            return funcSqlBean;
        }
    }

    protected String getIncludeSql(String sqlId)
        throws Exception
    {
        String includeSql = "";
        ExeSqlBean exeSql = uitool.getExeSql(sysId, String.valueOf(funcID), sqlId);
        if(exeSql == null)
            includeSql = "";
        else
            includeSql = UIToolUtils.getSql(exeSql, reqParam);
        return includeSql;
    }

    protected String parseIncludeSql(String sql)
        throws Exception
    {
        getLogger().info(ROLE + "::praseIncludeSql(string)->Enter");
        List lisParam = null;
        lisParam = StringTools.parseString(sql, UIToolUtils.SQL_P_INCLUDE);
        getLogger().debug(">>>>>>>>>>>:include sql:" + lisParam.toString());
        String sqlid = "";
        String includeSql = "";
        for(int i = 0; i < lisParam.size(); i++)
        {
            sqlid = (String)lisParam.get(i);
            includeSql = getIncludeSql(sqlid);
            includeSql = parseIncludeSql(includeSql);
            sql = sql.replaceAll("#" + sqlid + "#", includeSql);
        }

        getLogger().info(ROLE + "::praseIncludeSql(string)->Exit");
        return sql;
    }

    private ParsedSqlBean parseSql(ExeSqlBean sqlBean, FuncSqlBean funcBean, boolean returnNullParsedBean)
        throws Exception
    {
        if(sqlBean == null)
            if(returnNullParsedBean)
                return new ParsedSqlBean();
            else
                throw new Exception("\u5F53\u524D\u6267\u884CSQL\u4E3A\u7A7A:funcID=" + getFuncID());
        String sql = UIToolUtils.getSql(sqlBean, reqParam);
        if(sql == null || "".equals(sql))
            if(returnNullParsedBean)
                return new ParsedSqlBean();
            else
                throw new NLAppException("\u5F53\u524D\u529F\u80FD\u5BF9\u5E94\u53D6\u503CSQL\u4E3A\u7A7A.");
        getLogger().debug(">>>>>>>>>>>:parse include sql start");
        sql = parseIncludeSql(sql);
        getLogger().debug(">>>>>>>>>>>:parse include sql end");
        StringBuffer sbSql = new StringBuffer(sql);
        if(getSearchInfo().isSearching())
        {
            sbSql.append(getSearchInfo().getSearchWhere());
            sbSql.append(" ");
            sbSql.append(getSearchInfo().getSearchOrder());
        }
        sql = sbSql.toString();
        List lisParam = StringTools.parseString(sql, UIToolUtils.SQL_P_ARG);
        getLogger().debug(">>>>>>>>>>>:SQL:" + sbSql.toString());
        getLogger().debug(">>>>>>>>>>>:sql param:" + lisParam.toString());
        sql = sql.replaceAll(UIToolUtils.SQL_P_ARG.pattern(), "?");
        sbSql = new StringBuffer(sql);
        ExeSqlParamsGroupBean groupBean = funcBean.getParamGroup(funcBean.getArgGroup());
        if(groupBean == null)
        {
            throw new NLAppException("\u6BCF\u4E00\u4E2A\u529F\u80FD\u5904\u7406SQL\u5FC5\u9700\u8981\u6709\u4E00\u4E2A\u53C2\u6570\u7EC4\u4FE1\u606F\u3002");
        } else
        {
            String errMsg = groupBean.getErrMsg();
            Object args[] = UIToolUtils.getParamValueFromRequestData(groupBean, lisParam, reqParam);
            ParsedSqlBean parsedSql = new ParsedSqlBean();
            parsedSql.setArgs(args);
            parsedSql.setSql(sbSql);
            parsedSql.setErrorMsg(errMsg);
            return parsedSql;
        }
    }

    private void fetchData(FuncSqlBean sqlBean)
        throws Exception
    {
        getLogger().info(ROLE + "::fetchData(String)->Enter");
        if(reqParam.containsKey("_i_f_k_"))
        {
            data.setLisData(new ArrayList());
            return;
        }
        if(sqlBean == null)
            return;
        if(sqlBean.getExecuter() != null && sqlBean.getExecuter().length() > 0)
        {
            data.setLisData(UIToolUtils.executeFetch(sqlBean.getExecuter(), reqParam));
            return;
        }
        ExeSqlBean exeSql = sqlBean.getExeSql();
        ExeSqlBean countSql = sqlBean.getCountSql();
        getPageInfo().setPageType(exeSql.getPageType());
        String errMsg = "";
        try
        {
            getLogger().debug("\u89E3\u6790\u67E5\u8BE2SQL start...");
            ParsedSqlBean parsedExeSql = parseSql(exeSql, sqlBean, false);
            ParsedSqlBean parsedCountSql = null;
            if(sqlBean.isToCount())
            {
                getLogger().debug("\u89E3\u6790count-SQL start...");
                getSearchInfo().setSearchOrder("");
                parsedCountSql = parseSql(countSql, sqlBean, true);
            } else
            {
                getLogger().debug("\u4E0D\u8981\u8FDB\u884Ccount\u5904\u7406\u3002");
            }
            getSearchInfo().setSearchWhere("");
            setConnection(getDataConn());
            if(UIToolConst.PRINT_LIST_LOG)
                UIToolUtils.writeLog(reqParam, parsedExeSql.getSql().toString(), parsedExeSql.getArgs());
            if(getPageInfo() instanceof Download)
            {
                String beanName = UIToolUtils.getDownloadImplName(reqParam);
                Object obj = RequestUtils.applicationInstance(beanName);
                if(obj instanceof Downloadable)
                {
                    UIToolDownload download = (UIToolDownload)obj;
                    download.setConnection(getDataConn());
                    download.setLogger(getLogger());
                    String downFile = download.create(parsedExeSql.getSql(), parsedExeSql.getArgs(), data);
                    data.setDownFile(downFile);
                }
            } else
            {
                if(sqlBean.isToCount())
                {
                    if(!parsedCountSql.isEmpty())
                    {
                        Page page = (Page)getPageInfo();
                        page.getTotalRecord(parsedCountSql.getSql().toString(), parsedCountSql.getArgs(), getDataConn());
                    }
                } else
                {
                    Page page = (Page)getPageInfo();
                    page.setTotalNum(9223372036854775807L);
                }
                List lisData = query(parsedExeSql.getSql(), parsedExeSql.getArgs());
                data.setLisData(lisData);
            }
            getLogger().info(ROLE + "::fetchData(String)->Exit");
        }
        catch(SQLException e)
        {
            getLogger().error(ROLE + "::fetchData(String)->excep");
            throw new NLAppException(errMsg + "(func-sql-id:" + sqlBean.getId() + ",sql-id:" + sqlBean.getSqlId() + ",funcID:" + funcID + ")" + e.getMessage(), e);
        }
    }

    private void getAllCustomInfo()
        throws Exception
    {
        StringBuffer sbSql;
        PreparedStatement pstmt;
        ResultSet res;
        String userId;
        getLogger().info(ROLE + "::getAllCustomInfo()->Enter");
        if(hasCustomDisplayFields() || hasCustomFindFields() || hasCustomOrderFields())
        {
            getLogger().info(ROLE + "::getAllCustomInfo()->Exit, customer info review...");
            return;
        }
        sbSql = new StringBuffer();
        sbSql.append("SELECT USER_ID, FUNC_ID, FIND_FIELDS, ORDER_FIELDS, DISPLAY_FIELDS FROM UITOOL_LIST_FIELD_CUSTOMER");
        sbSql.append(" WHERE USER_ID=? AND FUNC_ID=?");
        pstmt = null;
        res = null;
        userId = UIToolUtils.getCurrUserId(reqParam);
        try
        {
            pstmt = getUitoolConn().prepareStatement(sbSql.toString());
            pstmt.setString(1, userId);
            pstmt.setLong(2, getFuncID());
            res = pstmt.executeQuery();
            if(res.next())
            {
                custDisplayFields = StringTools.toList(StringTools.notNull(res.getString("DISPLAY_FIELDS")), ",");
                custFindFields = StringTools.toList(StringTools.notNull(res.getString("FIND_FIELDS")), ",");
                custOrderFields = StringTools.toList(StringTools.notNull(res.getString("ORDER_FIELDS")), ",");
            }
        }
        catch(Exception e)
        {
            getLogger().error("ERR-SQL=[" + sbSql.toString() + "];ARGS=[" + userId + "," + getFuncID() + "]");
            throw e;
        }
        DBTools.close(res);
        DBTools.close(pstmt);
        DBTools.close(res);
        DBTools.close(pstmt);
        getLogger().info(ROLE + "::getAllCustomInfo()->Exit");
        return;
    }

    protected void getAllDisplayFields()
        throws Exception
    {
        StringBuffer sbSql;
        PreparedStatement pstmt;
        ResultSet res;
        getLogger().info(ROLE + "::getAllDisplayFields()->Enter");
        sbSql = new StringBuffer();
        sbSql.append("SELECT A.FIELD_NAME,A.DISPLAY_TEXT,A.IS_DISPLAY,A.IS_ORDER,A.URL, A.IS_FIND,A.FETCH_VALUE_TYPE,TITLE_URL");
        sbSql.append(",FIELD_EVENT,FIELD_FORMAT,IS_EXPAND,DISPLAY_INDEX,FIND_TYPE,NUM_TOTAL_DEF,ROW_ATTR");
        sbSql.append(" FROM UITOOL_LIST_DISPLAY_FIELD A");
        sbSql.append(" WHERE A.FUNC_ID=?");
        sbSql.append(" ORDER BY A.DISPLAY_INDEX");
        pstmt = null;
        res = null;
        try
        {
            pstmt = getUitoolConn().prepareStatement(sbSql.toString());
            pstmt.setLong(1, getFuncID());
            res = pstmt.executeQuery();
            DisplayField field = null;
            while(res.next())
            {
                field = new DisplayField();
                field.setField(res.getString("FIELD_NAME"));
                field.setFieldEvent(StringTools.notNull(res.getString("FIELD_EVENT")));
                field.setFieldFormat(StringTools.notNull(res.getString("FIELD_FORMAT")));
                field.setFieldText(StringTools.notNull(res.getString("DISPLAY_TEXT")));
                field.setFetchType(StringTools.notNull(res.getString("FETCH_VALUE_TYPE")));
                field.setUrl(StringTools.notNull(res.getString("URL")));
                field.setTitleUrl(StringTools.notNull(res.getString("TITLE_URL")));
                field.setFindType(res.getInt("FIND_TYPE"));
                field.setFind(res.getInt("IS_FIND"));
                field.setOrder(res.getInt("IS_ORDER"));
                field.setDispaly(res.getInt("IS_DISPLAY"));
                UIToolUtils.checkFieldRole(field, getReqParam());
                String numTotalDef = res.getString("NUM_TOTAL_DEF");
                if(numTotalDef != null)
                    try
                    {
                        Object obj = BeanTools.applicationInstance(numTotalDef);
                        if(obj instanceof UIToolDataCube)
                            field.setNumTotalDef((UIToolDataCube)obj);
                        else
                            getLogger().error(ROLE + "::error: invalid instance of UIToolDataCube[" + numTotalDef + "]");
                    }
                    catch(Exception e)
                    {
                        field.setNumTotalDef(null);
                    }
                if(field.isHasPure())
                    data.addField(field);
                if(field.isDispaly() && !hasCustomDisplayFields() || isCustomDisplayFields(field.getField()))
                {
                    data.addDisplayTitle(field);
                    field.setDispaly(true);
                } else
                {
                    field.setDispaly(false);
                }
                if(res.getInt("ROW_ATTR") == 1)
                    data.addAttribute(field);
                if(field.isOrder() && !hasCustomOrderFields() || isCustomOrderFields(field.getField()))
                {
                    data.addOrderFields(field);
                    field.setOrder(true);
                } else
                {
                    field.setOrder(false);
                }
                if(field.isFind() && !hasCustomFindFields() || isCustomFindFields(field.getField()))
                {
                    data.addFindFields(field);
                    field.setFind(true);
                } else
                {
                    field.setFind(false);
                }
                String fetchType = field.getFetchType();
                if(fetchType == null || "".equals(fetchType))
                    field.setSearchValue(null);
                else
                if(fetchType.toLowerCase().startsWith("javacode://"))
                {
                    fetchType = UIToolUtils.formatString(fetchType, null, reqParam);
                    field.setSearchValue((List)UIToolUtils.parseJavaCode(fetchType));
                } else
                if(fetchType.toLowerCase().startsWith("sqlcode://"))
                    field.setSearchValue((List)UIToolUtils.parseSqlCode(fetchType, getDataConn(), reqParam));
                int isExpand = res.getInt("IS_EXPAND");
                if(isExpand == 1)
                {
                    getDimFieldInfo(funcID, field.getField(), res.getInt("DISPLAY_INDEX"), field);
                    int iDimCount = field.getDimCount();
                    data.setMaxRowId(iDimCount);
                    field.parseFieldDimToCell();
                } else
                if(isExpand == 2)
                {
                    if(res.getInt("IS_DISPLAY") == 1)
                        getReportTitle(field);
                    data.setMaxRowId(field.getRows().size());
                }
            }
        }
        catch(Exception e)
        {
            throw e;
        }
        DBTools.close(res);
        DBTools.close(pstmt);
        DBTools.close(res);
        DBTools.close(pstmt);
        getLogger().info(ROLE + "::getAllDisplayFields()->Exit");
        return;
    }

    private void getDimFieldInfo(long funcID, String fieldName, int colId, DisplayField displayField)
        throws Exception
    {
        ResultSet res;
        PreparedStatement pstmt;
        StringBuffer sql;
        getLogger().info(ROLE + "::getDimFieldInfo(int,String,int)->Enter");
        res = null;
        pstmt = null;
        sql = new StringBuffer("SELECT A.DIM_ID, ROW_ID, A.IS_DISPLAY, DIM_NAME, B.IS_EXTEND, B.DIM_FETCH_TYPE");
        sql.append(" FROM UITOOL_FIELD_DIM_REF A, UITOOL_DIM_DEFINE B");
        sql.append(" WHERE A.DIM_ID = B.DIM_ID AND A.FUNC_ID=? AND A.COL_ID=? AND A.FIELD_NAME=?");
        sql.append(" ORDER BY A.ROW_ID");
        try
        {
            pstmt = getUitoolConn().prepareStatement(sql.toString());
            pstmt.setLong(1, funcID);
            pstmt.setInt(2, colId);
            pstmt.setString(3, fieldName);
            res = pstmt.executeQuery();
            FieldDim fieldDim = null;
            while(res.next())
            {
                int dimId = res.getInt("DIM_ID");
                fieldDim = new FieldDim();
                fieldDim.setColId(colId);
                fieldDim.setDimName(res.getString("DIM_NAME"));
                fieldDim.setIsDisplay(res.getInt("IS_DISPLAY"));
                fieldDim.setRowId(res.getInt("ROW_ID"));
                if(res.getInt("IS_EXTEND") == 1)
                {
                    String dimFetchType = StringTools.notNull(res.getString("DIM_FETCH_TYPE"));
                    if(dimFetchType.startsWith("sqlcode://"))
                    {
                        List lisRet = (List)UIToolUtils.parseSqlCode(dimFetchType, getDataConn(), reqParam);
                        if(lisRet == null || lisRet.size() <= 0)
                            continue;
                        List dimValues = new ArrayList();
                        DimValue dimValue = null;
                        Map tmp = null;
                        for(int i = 0; i < lisRet.size(); i++)
                        {
                            dimValue = new DimValue();
                            tmp = (Map)lisRet.get(i);
                            dimValue.setValueName(MapTools.getString(tmp, "key"));
                            dimValue.setValueText(MapTools.getString(tmp, "value"));
                            dimValues.add(dimValue);
                        }

                        fieldDim.setDimValue(dimValues);
                    } else
                    if(dimFetchType.startsWith("javacode://"))
                    {
                        dimFetchType = dimFetchType.substring(11);
                        List dimValue = UIToolUtils.executeFetch(dimFetchType, reqParam);
                        if(dimValue == null || dimValue.size() <= 0)
                            continue;
                        fieldDim.setDimValue(dimValue);
                    }
                } else
                if(UIToolUtils.containKey(reqParam, "prefix_dim_id_" + dimId))
                    getDimValueInfoFromRequest(dimId, fieldDim);
                else
                    getDimValueInfoFromDB(dimId, fieldDim);
                FieldDim lastDim = displayField.getLastFieldDim();
                if(lastDim != null)
                {
                    lastDim.setNextRowField(fieldDim);
                    fieldDim.setLastColSize(lastDim.getValueSize());
                }
                displayField.addFieldDim(fieldDim);
            }
        }
        catch(Exception e)
        {
            getLogger().error(ROLE + "::getDimFieldInfo(int,String,int)->excep:SQL=" + sql.toString());
            getLogger().error(ROLE + "::getDimFieldInfo(int,String,int)->excep:ARG=[" + funcID + "," + fieldName + "," + colId + "]");
            throw e;
        }
        DBTools.close(res);
        DBTools.close(pstmt);
        DBTools.close(res);
        DBTools.close(pstmt);
        getLogger().info(ROLE + "::getDimFieldInfo(int,String,int)->Exit");
        return;
    }

    private void getDimValueInfoFromRequest(int dimId, FieldDim fieldDim)
    {
        getLogger().info(ROLE + "::getDimValueInfoFromRequest(int)->Enter");
        String dimKey = "prefix_dim_id_" + dimId;
        DimValue dimValue = null;
        for(int valueId = 0; UIToolUtils.containKey(reqParam, dimKey + valueId); fieldDim.addDimValue(dimValue))
        {
            dimValue = new DimValue();
            dimValue.setValueId(valueId++);
            dimValue.setValueName("");
            dimValue.setValueText((String)UIToolUtils.getValueFromRequest(dimKey, reqParam));
            dimValue.setIsDisplay(1);
        }

        getLogger().info(ROLE + "::getDimValueInfoFromRequest(int)->Exit");
    }

    private void getDimValueInfoFromDB(int dimId, FieldDim fieldDim)
        throws Exception
    {
        ResultSet res;
        PreparedStatement pstmt;
        StringBuffer sql;
        getLogger().info(ROLE + "::getDimValueInfoFromDB(int)->Enter");
        res = null;
        pstmt = null;
        sql = new StringBuffer("SELECT DIM_VALUE_ID, DIM_VALUE_NAME, DIM_VALUE_TEXT, IS_DISPLAY ");
        sql.append(" FROM UITOOL_DIM_VALUE_DEFINE WHERE IS_DISPLAY=1 AND DIM_ID=?");
        try
        {
            pstmt = getUitoolConn().prepareStatement(sql.toString());
            pstmt.setInt(1, dimId);
            res = pstmt.executeQuery();
            DimValue dimValue = null;
            for(; res.next(); fieldDim.addDimValue(dimValue))
            {
                dimValue = new DimValue();
                dimValue.setValueId(res.getInt("DIM_VALUE_ID"));
                dimValue.setValueName(res.getString("DIM_VALUE_NAME"));
                dimValue.setValueText(res.getString("DIM_VALUE_TEXT"));
                dimValue.setIsDisplay(res.getInt("IS_DISPLAY"));
            }

        }
        catch(Exception e)
        {
            getLogger().error(ROLE + "::getDimValueInfoFromDB(int)->excep:SQL=" + sql.toString());
            getLogger().error(ROLE + "::getDimValueInfoFromDB(int)->excep:ARG=[" + dimId + "]");
            throw e;
        }
        DBTools.close(res);
        DBTools.close(pstmt);
        DBTools.close(res);
        DBTools.close(pstmt);
        getLogger().info(ROLE + "::getDimValueInfoFromDB(int)->Exit");
        return;
    }

    private void getReportTitle(DisplayField field)
        throws Exception
    {
        ResultSet res;
        PreparedStatement pstmt;
        StringBuffer sql;
        getLogger().info(ROLE + "::getReportTitle(DisplayField)->Enter");
        res = null;
        pstmt = null;
        sql = new StringBuffer("SELECT FUNC_ID,FIELD_NAME,ROW_NUM,CELL_NUM,DISPLAY_TEXT,URL,IS_DISPLAY");
        sql.append(",ROW_SPAN,CELL_SPAN,FETCH_VALUE_TYPE,TITLE_URL,FIELD_EVENT,FIELD_FORMAT,NUM_TOTAL_DEF");
        sql.append(" FROM UITOOL_LIST_RPT_FIELD WHERE FUNC_ID=? AND FIELD_NAME=? AND IS_DISPLAY=1");
        sql.append(" ORDER BY ROW_NUM,CELL_NUM");
        try
        {
            pstmt = getUitoolConn().prepareStatement(sql.toString());
            pstmt.setLong(1, getFuncID());
            pstmt.setString(2, field.getField());
            res = pstmt.executeQuery();
            HtmlTDBean cell = null;
            for(; res.next(); field.addCell(cell))
            {
                cell = new HtmlTDBean();
                cell.setId(res.getString("FIELD_NAME") + "_" + res.getInt("ROW_NUM") + "_" + res.getInt("CELL_NUM"));
                cell.setContent(res.getString("DISPLAY_TEXT"));
                cell.setRowIndex(res.getInt("ROW_NUM"));
                cell.setCellIndex(res.getInt("CELL_NUM"));
                cell.setRowSpan(res.getInt("ROW_SPAN"));
                cell.setColSpan(res.getInt("CELL_SPAN"));
                cell.setHref(res.getString("TITLE_URL"));
                cell.setFieldEvent(res.getString("FIELD_EVENT"));
                cell.setFieldFormat(res.getString("FIELD_FORMAT"));
                cell.setUrl(res.getString("URL"));
                cell.setFetchType(res.getString("FETCH_VALUE_TYPE"));
                String numTotalDef = res.getString("NUM_TOTAL_DEF");
                if(numTotalDef != null)
                    try
                    {
                        Object obj = BeanTools.applicationInstance(numTotalDef);
                        if(obj instanceof UIToolDataCube)
                            cell.setNumTotalDef((UIToolDataCube)obj);
                        else
                            getLogger().error(ROLE + "::error: invalid instance of UIToolDataCube[" + numTotalDef + "]");
                    }
                    catch(Exception e)
                    {
                        cell.setNumTotalDef(null);
                    }
                if(cell.getHref() != null && cell.getHref().length() > 0)
                    cell.setNodeFlg(true);
                String fetchType = cell.getFetchType();
                if(fetchType == null || "".equals(fetchType))
                    cell.setSearchValue(null);
                else
                if(fetchType.toLowerCase().startsWith("javacode://"))
                    cell.setSearchValue((List)UIToolUtils.parseJavaCode(fetchType));
                else
                if(fetchType.toLowerCase().startsWith("sqlcode://"))
                    cell.setSearchValue((List)UIToolUtils.parseSqlCode(fetchType, getDataConn(), reqParam));
            }

        }
        catch(Exception e)
        {
            getLogger().error(ROLE + "::getReportTitle(DisplayField)->excep:SQL=" + sql.toString());
            getLogger().error(ROLE + "::getReportTitle(DisplayField)->excep:ARG=[" + getFuncID() + "," + field.getField() + "]");
            throw e;
        }
        DBTools.close(res);
        DBTools.close(pstmt);
        DBTools.close(res);
        DBTools.close(pstmt);
        getLogger().info(ROLE + "::getReportTitle(DisplayField)->Exit");
        return;
    }

    private boolean isCustomFindFields(String fieldName)
    {
        return UIToolUtils.contain(getCustFindFields(), fieldName);
    }

    private boolean isCustomOrderFields(String fieldName)
    {
        return UIToolUtils.contain(getCustOrderFields(), fieldName);
    }

    private boolean isCustomDisplayFields(String fieldName)
    {
        return UIToolUtils.contain(getCustDisplayFields(), fieldName);
    }

    private boolean hasCustomFindFields()
    {
        return getCustFindFields() != null && getCustFindFields().size() > 0;
    }

    private boolean hasCustomOrderFields()
    {
        return getCustOrderFields() != null && getCustOrderFields().size() > 0;
    }

    private boolean hasCustomDisplayFields()
    {
        return getCustDisplayFields() != null && getCustDisplayFields().size() > 0;
    }

    public long getFuncID()
    {
        return funcID;
    }

    public void setFuncID(long funcID)
    {
        this.funcID = funcID;
    }

    public long getSuperExamID()
    {
        return superExamID;
    }

    public void setSuperExamID(long superExamID)
    {
        this.superExamID = superExamID;
    }

    public Map getReqParam()
    {
        return reqParam;
    }

    public void setReqParam(Map reqParam)
    {
        getLogger().debug("\u4F20\u5165\u63D0\u4EA4\u6570\u636E\u96C6\u4FE1\u606F:" + reqParam.toString());
        this.reqParam = reqParam;
    }

    public UIToolListCtrlBt getData()
    {
        return data;
    }

    public void setData(UIToolListCtrlBt bt)
    {
        data = bt;
    }

    public String getSysId()
    {
        return sysId;
    }

    public void setSysId(String sysId)
    {
        this.sysId = sysId;
    }

    public Connection getDataConn()
    {
        return dataConn;
    }

    public void setDataConn(Connection dataConn)
    {
        this.dataConn = dataConn;
    }

    public Connection getUitoolConn()
    {
        return uitoolConn;
    }

    public void setUitoolConn(Connection uitoolConn)
    {
        this.uitoolConn = uitoolConn;
    }

    public List getCustFindFields()
    {
        if(custFindFields == null)
        {
            String sf = (String)MapTools.getObjectIgnoreCase(reqParam, "s_f");
            custFindFields = StringTools.toList(sf, ",");
        }
        return custFindFields;
    }

    public void setCustFindFields(List custFindFields)
    {
        this.custFindFields = custFindFields;
    }

    public List getCustOrderFields()
    {
        if(custOrderFields == null)
        {
            String of = (String)MapTools.getObjectIgnoreCase(reqParam, "o_f");
            custOrderFields = StringTools.toList(of, ",");
        }
        return custOrderFields;
    }

    public void setCustOrderFields(List custOrderFields)
    {
        this.custOrderFields = custOrderFields;
    }

    public List getCustDisplayFields()
    {
        if(custDisplayFields == null)
        {
            String df = (String)MapTools.getObjectIgnoreCase(reqParam, "d_f");
            custDisplayFields = StringTools.toList(df, ",");
        }
        return custDisplayFields;
    }

    public void setCustDisplayFields(List custDisplayFields)
    {
        this.custDisplayFields = custDisplayFields;
    }

    public static final String ROLE;
    protected static UITool uitool;
    private Connection uitoolConn;
    private Connection dataConn;
    private String sysId;
    private long funcID;
    private long superExamID;
    private UIToolListCtrlBt data;
    private Map reqParam;
    private List custFindFields;
    private List custOrderFields;
    private List custDisplayFields;

    static
    {
        ROLE = com.nl.uitool.db.UIToolListCtrlMgr.class.getName();
        uitool = (UITool)SystemTool.getObject(UITool.ROOL);
    }*/
}
