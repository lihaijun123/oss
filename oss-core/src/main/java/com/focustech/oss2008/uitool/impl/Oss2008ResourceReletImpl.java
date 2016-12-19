package com.focustech.oss2008.uitool.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.focustech.oss2008.utils.RoleResourceUtils;
import com.focustech.uitool.list.UIToolExeSqlCreate;
import com.focustech.uitool.list.utils.UIToolConst;
import com.focustech.uitool.list.utils.UIToolUtils;

/**
 * Copyright (c) 2006, focustech All rights reserved 對于oss 2008系統中使用uitool工具的時候需求一些系統的上下文變量
 *
 * @author jibin 2008-12-19 下午02:15:04
 */
public class Oss2008ResourceReletImpl implements UIToolExeSqlCreate {
    @SuppressWarnings("unchecked")
    public String create(Map reqData) {
        try {
            HttpServletRequest request = (HttpServletRequest) reqData.get(UIToolConst.REQUEST_KEY_CURR_REQUEST);
            StringBuffer sqlsb = new StringBuffer();
            // 查看是否是銷售總監，銷售部經理，訂單經理才有的資源(該資源查看續訂資源不受續約保護期類型限制)
            long resourceId = 747;
            if (RoleResourceUtils.check(resourceId, request.getSession().getServletContext())) {
                //sqlsb.append("SYSDATE < nvl(b.end_time,");
            	sqlsb.append("SYSDATE() < ifnull(b.end_time,");
                sqlsb.append("(select m.end_time from crm_contract_product m ");
                sqlsb
                        .append("where m.CONTRACT_PRODUCT_ID = b.CONTRACT_PROD_PARENT_ID and m.contract_id = b.contract_id))");
            }
            else {
                //sqlsb.append("((nvl(d.protect_period_type,2) = 2 AND ");
            	sqlsb.append("((ifnull(d.protect_period_type,2) = 2 AND ");
                //sqlsb.append("SYSDATE >= (nvl(b.end_time,(select m.end_time  from crm_contract_product m ");
            	sqlsb.append("SYSDATE() >= (add_days(ifnull(b.end_time,(select m.end_time  from crm_contract_product m ");
                sqlsb
                        .append("where m.CONTRACT_PRODUCT_ID = b.CONTRACT_PROD_PARENT_ID and m.contract_id = b.contract_id)) ");
                //sqlsb.append("- d.protect_period_start) AND SYSDATE < (nvl(b.end_time,(select m.end_time ");
                sqlsb.append(",- d.protect_period_start)) AND SYSDATE() < (add_days(ifnull(b.end_time,(select m.end_time ");
                sqlsb.append("from crm_contract_product m where m.CONTRACT_PRODUCT_ID = b.CONTRACT_PROD_PARENT_ID ");
                //sqlsb.append("and m.contract_id = b.contract_id)) - nvl(d.protect_period_end,0))) ");
                sqlsb.append("and m.contract_id = b.contract_id)) ,- ifnull(d.protect_period_end,0))) ");
                //sqlsb.append("OR (nvl(d.protect_period_type,2) = 1 AND SYSDATE >= (nvl(b.start_time,");
                sqlsb.append("OR (ifnull(d.protect_period_type,2) = 1 AND SYSDATE() >= (add_days(ifnull(b.start_time,");
                sqlsb.append("(select m.start_time from crm_contract_product m where m.CONTRACT_PRODUCT_ID = b.CONTRACT_PROD_PARENT_ID ");
                        //.append("(select m.start_time from crm_contract_product m where m.CONTRACT_PRODUCT_ID = b.CONTRACT_PROD_PARENT_ID ");
                //sqlsb.append("and m.contract_id = b.contract_id)) + d.protect_period_start) AND ");
                sqlsb.append("and m.contract_id = b.contract_id)) , d.protect_period_start)) AND ");
                //sqlsb.append("SYSDATE < (nvl(b.start_time,(select m.start_time from crm_contract_product m ");
                sqlsb.append("SYSDATE() < (add_days(ifnull(b.start_time,(select m.start_time from crm_contract_product m ");
                sqlsb.append("where m.CONTRACT_PRODUCT_ID = b.CONTRACT_PROD_PARENT_ID and m.contract_id = b.contract_id)),ifnull(d.protect_period_end,0))))))");
                        //.append("where m.CONTRACT_PRODUCT_ID = b.CONTRACT_PROD_PARENT_ID and m.contract_id = b.contract_id)) + nvl(d.protect_period_end,0))))");
            }
            return sqlsb.toString();
        }
        catch (Throwable e) {
            UIToolUtils.getLogger().error(e);
            return "";
        }
    }
}
