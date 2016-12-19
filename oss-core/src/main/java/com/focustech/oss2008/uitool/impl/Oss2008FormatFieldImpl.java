package com.focustech.oss2008.uitool.impl;

import org.apache.commons.lang.StringUtils;

import com.focustech.oss2008.bt.CrmAccountEntity;
import com.focustech.oss2008.utils.Encode;

/**
 * 處理合同編號字符串並帶鏈接
 *
 * @author jibin
 */
public class Oss2008FormatFieldImpl {

    /**
     * 處理頁面合同id
     *
     * @param contractIds
     * @return
     */
    public static String convertContractIds(String contractIds) {
        if ((contractIds == null) || "".equals(contractIds)) {
            return "";
        }
        String str = "";
        String[] ids = contractIds.split(",");
        for (String id : ids) {
            if (!"".equals(str)) {
                str += ",";
            }
            str += "<a href='contract.do?method=info&contractId=" + id + "'>" + id + "</a>";
        }
        return str;
    }

    /**
     * 顯示廣告圖片.
     *
     * @param picId
     * @param lanCode
     * @return
     */
    public static String showAdvertisePic(String picId, String lanCode) {
        if (StringUtils.isEmpty(picId)) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        sb.append("<a href='/edt/servlet/ossshowimage?tid=8&fid=4&id=");
        sb.append(Encode.encode(picId));
        sb.append("&cache=0&lan_code=");
        sb.append(lanCode);
        sb.append("&mode=MIC'>");
        sb.append("<img src='/edt/servlet/ossshowimage?tid=8&fid=4&id=");
        sb.append(Encode.encode(picId));
        sb.append("&cache=0&lan_code=");
        sb.append(lanCode);
        sb.append("&mode=MIC' width='100' height='100' border='0' /></a>");

        return sb.toString();
    }

    /**
     * 獲取活動信息ID鏈接
     *
     * @param originId 活動信息ID
     * @param originType 客戶來源
     * @param originInfomation 語言版本
     * @param originUrl 外部抓取地址
     * @return
     */
    public static String getLinkUrl(String originId, String originType, String originInfomation, String originUrl) {
        String str = "";
        if (CrmAccountEntity.ACCOUNT_ORIGIN_TYPE_MEMBER.equals(originType)) {
            // 如果語言版本為空，則不給鏈接
            if ((originInfomation == null) || "".equals(originInfomation)) {
                str = originId;
            }
            else {
                str =
                        "<a href='/customer.do?method=MicDetails&cstId=" + originId + "&website=" + originInfomation
                                + "'>" + originId + "</a>";
            }
        }
        else if (CrmAccountEntity.ACCOUNT_ORIGIN_TYPE_FETCH.equals(originType)
                || CrmAccountEntity.ACCOUNT_ORIGIN_TYPE_ALIBABA.equals(originType)
                || CrmAccountEntity.ACCOUNT_ORIGIN_TYPE_GLOBAL.equals(originType)) {
            if ((originUrl != null) && (originUrl.length() > 0)) {
                str = "<a href='" + originUrl + "'>" + originId + "</a>";
            }
            else {
                str = originId;
            }
        }
        else if (CrmAccountEntity.ACCOUNT_ORIGIN_TYPE_EXHIBITION.equals(originType)) {
            str =
                    "<a href='/edt/exhibitionBusCard.do?action=view&businessCardId=" + originId + "'>" + originId
                            + "</a>";
        }
        else if (CrmAccountEntity.ACCOUNT_ORIGIN_TYPE_ENQUIRY.equals(originType)) {
            str =
                    "<a href='/uitoolList.ui?funcID=40035&REC_ID=" + originId + "&searchOper=%3D&searchField=REC_ID'>"
                            + originId + "</a>";
        }
        else {
            str = originId;
        }
        return str;
    }
}
