/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.focustech.extend.uitool;

import com.focustech.uitool.framework.sc.NLAbstractMgmt;

// Referenced classes of package com.nl.uitool.db:
//            UIToolListCtrlMgr, UIToolChartCtrlMgr, UIToolOperCtrlMgr

public class UIToolCtrlMgmt extends NLAbstractMgmt
{

    /*public UIToolCtrlMgmt()
    {
        pageInfo = null;
    }

    public UIToolListCtrlBt getDisplayInfos(UIToolListCtrlForm form, Map reqData, boolean isDown)
        throws Exception
    {
        Connection uitoolConn;
        Connection dataConn;
        getLogger().info(ROLE + "::getDisplayInfos(int,int,Map,Page,Search)->Enter");
        UIToolListCtrlBt lisBt = null;
        uitoolConn = null;
        dataConn = null;
        UIToolListCtrlBt uitoollistctrlbt;
        try
        {
            UIToolListCtrlMgr lisMgr = new UIToolListCtrlMgr();
            String dataDs = UIToolUtils.getDataSource(UIToolUtils.getSysName(reqData), UIToolUtils.getDSTagName(reqData));
            String uitoolDs = UIToolUtils.getDataSource("uitool", "default-datasource");
            if(uitoolDs != null && uitoolDs.equals(dataDs))
            {
                uitoolConn = getConnection(uitoolDs);
                dataConn = uitoolConn;
                getLogger().debug("uitool and data use same datasource name:" + uitoolDs);
            } else
            {
                uitoolConn = getConnection(uitoolDs);
                dataConn = getConnection(dataDs);
            }
            getLogger().debug("uitool system default datasource name:" + uitoolDs);
            getLogger().debug("data system default datasource name:" + dataDs);
            lisMgr.setUitoolConn(uitoolConn);
            lisMgr.setDataConn(dataConn);
            lisMgr.setLogger(getLogger());
            lisMgr.setFuncID(form.getFuncID());
            lisMgr.setReqParam(reqData);
            lisMgr.setSysId(UIToolUtils.getSysId(reqData));
            lisMgr.init();
            int pageMaxRec = form.getPageLimit();
            if(isDown)
            {
                lisMgr.setPageInfo(new Download());
            } else
            {
                Page pageInfo = null;
                if(pageMaxRec <= 0)
                    pageMaxRec = StringTools.parseInt(SystemTool.getSystemParam(UIToolUtils.getSysName(reqData), "page_record_max_num"), NLGlobal.PAGE_REC_MAX_LIMIT);
                if(lisMgr.getData().getPageLimit() > 0L && (long)pageMaxRec > lisMgr.getData().getPageLimit())
                    pageMaxRec = (int)lisMgr.getData().getPageLimit();
                long currPage = form.getCurrPage();
                if(lisMgr.getData().getPageTotalLimit() > 0L && currPage > lisMgr.getData().getPageTotalLimit())
                    currPage = lisMgr.getData().getPageTotalLimit();
                form.setCurrPage(currPage);
                pageInfo = new Page(currPage, pageMaxRec);
                lisMgr.setPageInfo(pageInfo);
            }
            Search searchInfo = new Search();
            searchInfo.setSearchWhere(form.getFindWhere());
            searchInfo.setSearchOrder(form.getSearchOrder());
            lisMgr.setSearchInfo(searchInfo);
            lisMgr.fetchData();
            if(!isDown)
            {
                this.pageInfo = (Page)lisMgr.getPageInfo();
                form.setTotalRec(this.pageInfo.getTotalNum());
            }
            lisBt = lisMgr.getData();
            form.setPageLimit(pageMaxRec);
            commit(dataConn);
            commit(uitoolConn);
            getLogger().info(ROLE + "::getDisplayInfos(int,int,Map,Page,Search)->Exit");
            uitoollistctrlbt = lisBt;
        }
        catch(Exception e)
        {
            rollback(dataConn);
            rollback(uitoolConn);
            getLogger().error(ROLE + "::getDisplayInfos(int,int,Map,Page,Search)->excep");
            throw e;
        }
        close(dataConn);
        if(uitoolConn != null && !uitoolConn.isClosed())
            close(uitoolConn);
        return uitoollistctrlbt;
    }

    public UIToolChartCtrlBt getChartDisplayInfo(long funcID, long superID, int chartType, Map reqData, Search searchInfo)
        throws Exception
    {
        Connection uitoolConn;
        Connection dataConn;
        getLogger().info(ROLE + "::getDisplayInfos(int,int,Map,Page,Search)->Enter");
        UIToolChartCtrlBt chartBt = null;
        uitoolConn = null;
        dataConn = null;
        UIToolChartCtrlBt uitoolchartctrlbt;
        try
        {
            UIToolChartCtrlMgr chartMgr = new UIToolChartCtrlMgr();
            String dataDs = UIToolUtils.getDataSource(UIToolUtils.getSysName(reqData), UIToolUtils.getDSTagName(reqData));
            String uitoolDs = UIToolUtils.getDataSource("uitool", "default-datasource");
            if(uitoolDs != null && uitoolDs.equals(dataDs))
            {
                uitoolConn = getConnection(uitoolDs);
                dataConn = uitoolConn;
                getLogger().debug("uitool and data use same datasource name:" + uitoolDs);
            } else
            {
                uitoolConn = getConnection(uitoolDs);
                dataConn = getConnection(dataDs);
            }
            getLogger().debug("uitool system default datasource name:" + uitoolDs);
            getLogger().debug("data system default datasource name:" + dataDs);
            chartMgr.setUitoolConn(uitoolConn);
            chartMgr.setDataConn(dataConn);
            chartMgr.setLogger(getLogger());
            chartMgr.setPageInfo(null);
            chartMgr.setSearchInfo(searchInfo);
            chartMgr.setFuncID(funcID);
            chartMgr.setSuperExamID(superID);
            chartMgr.setReqParam(reqData);
            chartMgr.setSysId(UIToolUtils.getSysId(reqData));
            chartMgr.setChartType(chartType);
            chartMgr.init();
            chartBt = chartMgr.getChartData();
            commit(dataConn);
            commit(uitoolConn);
            getLogger().info(ROLE + "::getDisplayInfos(int,int,Map,Page,Search)->Exit");
            uitoolchartctrlbt = chartBt;
        }
        catch(Exception e)
        {
            rollback(dataConn);
            rollback(uitoolConn);
            getLogger().error(ROLE + "::getDisplayInfos(int,int,Map,Page,Search)->excep");
            throw e;
        }
        close(dataConn);
        if(uitoolConn != null && !uitoolConn.isClosed())
            close(uitoolConn);
        return uitoolchartctrlbt;
    }

    public UIToolOperCtrlBt getOperDisplayInfos(long funcID, Map reqParam)
        throws Exception
    {
        Connection uitoolConn;
        Connection dataConn;
        getLogger().info(ROLE + "::getOperDisplayInfos(int)->Enter");
        UIToolOperCtrlBt lisBt = null;
        uitoolConn = null;
        dataConn = null;
        UIToolOperCtrlBt uitooloperctrlbt;
        try
        {
            UIToolOperCtrlMgr oper = new UIToolOperCtrlMgr();
            String dataDs = UIToolUtils.getDataSource(UIToolUtils.getSysName(reqParam), UIToolUtils.getDSTagName(reqParam));
            String uitoolDs = UIToolUtils.getDataSource("uitool", "default-datasource");
            getLogger().debug("uitool system default datasource name:" + uitoolDs);
            getLogger().debug("data system default datasource name:" + dataDs);
            if(uitoolDs != null && uitoolDs.equals(dataDs))
            {
                uitoolConn = getConnection(uitoolDs);
                dataConn = uitoolConn;
                getLogger().debug("uitool and data use same datasource name:" + uitoolDs);
            } else
            {
                uitoolConn = getConnection(uitoolDs);
                dataConn = getConnection(dataDs);
            }
            oper.setUitoolConn(uitoolConn);
            oper.setDataConn(dataConn);
            oper.setLogger(getLogger());
            oper.setReqParam(reqParam);
            oper.setSysId(UIToolUtils.getSysId(reqParam));
            oper.initDisplay(funcID);
            lisBt = oper.getData();
            commit(dataConn);
            commit(uitoolConn);
            getLogger().info(ROLE + "::getOperDisplayInfos(int)->Exit");
            uitooloperctrlbt = lisBt;
        }
        catch(Exception e)
        {
            rollback(dataConn);
            rollback(uitoolConn);
            getLogger().error(ROLE + "::getOperDisplayInfos(int)->excep");
            throw e;
        }
        close(dataConn);
        if(uitoolConn != null && !uitoolConn.isClosed())
            close(uitoolConn);
        return uitooloperctrlbt;
    }

    public void exeOperInfos(long funcID, Map reqData)
        throws Exception
    {
        Connection uitoolConn;
        Connection dataConn;
        boolean commit;
        getLogger().info(ROLE + "::exeOperInfos(int,Map)->Enter");
        uitoolConn = null;
        dataConn = null;
        commit = true;
        try
        {
            UIToolOperCtrlMgr oper = new UIToolOperCtrlMgr();
            String dataDs = UIToolUtils.getDataSource(UIToolUtils.getSysName(reqData), UIToolUtils.getDSTagName(reqData));
            String uitoolDs = UIToolUtils.getDataSource("uitool", "default-datasource");
            getLogger().debug("uitool system default datasource name:" + uitoolDs);
            getLogger().debug("data system default datasource name:" + dataDs);
            if(uitoolDs != null && uitoolDs.equals(dataDs))
            {
                uitoolConn = getConnection(uitoolDs);
                dataConn = uitoolConn;
                getLogger().debug("uitool and data use same datasource name:" + uitoolDs);
            } else
            {
                uitoolConn = getConnection(uitoolDs);
                dataConn = getConnection(dataDs);
            }
            commit = dataConn.getAutoCommit();
            dataConn.setAutoCommit(false);
            oper.setDataConn(dataConn);
            oper.setUitoolConn(uitoolConn);
            oper.setLogger(getLogger());
            oper.setReqParam(reqData);
            oper.setSysId(UIToolUtils.getSysId(reqData));
            oper.initOper(funcID);
            commit(dataConn);
            commit(uitoolConn);
            getLogger().info(ROLE + "::exeOperInfos(int,Map)->Exit");
        }
        catch(Exception e)
        {
            rollback(dataConn);
            rollback(uitoolConn);
            getLogger().error(ROLE + "::exeOperInfos(int,Map)->excep");
            throw e;
        }
        if(dataConn != null)
            dataConn.setAutoCommit(commit);
        close(dataConn);
        if(uitoolConn != null && !uitoolConn.isClosed())
            close(uitoolConn);

        if(dataConn != null)
            dataConn.setAutoCommit(commit);
        close(dataConn);
        if(uitoolConn != null && !uitoolConn.isClosed())
            close(uitoolConn);
        return;
    }

    public Page getPageInfo()
    {
        return pageInfo;
    }

    public void setPageInfo(Page pageInfo)
    {
        this.pageInfo = pageInfo;
    }

    public static final String ROLE;
    private Page pageInfo;

    static
    {
        ROLE = com.nl.uitool.db.UIToolCtrlMgmt.class.getName();
    }*/
}
