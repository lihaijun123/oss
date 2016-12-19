package com.focustech.oss2008.service;

import java.util.List;
import java.util.Map;

import com.focustech.oss2008.exception.OssCheckedException;

public interface IndustryInfoService {
    /**
     * 根據關鍵詞獲取目錄碼信息
     *
     * @param params
     * @param funcId
     * @return
     */
    public List<Map<String, Object>> searchCatCode(Map params, long funcId);

    /**
     * 搜索熱門關鍵詞情況
     *
     * @param params
     * @param funcId
     * @return
     */
    public List<Map<String, Object>> searchHotKeyword(Map params, long funcId);

    /**
     * 搜索熱門關鍵詞情況
     *
     * @param params
     * @param funcId
     * @return
     */
    public List<Map<String, Object>> searchHotKeyword2(Map params, Map<String, Object> params2, long funcId);

    /**
     * 銷售熱門關鍵詞TopRank情況
     *
     * @param params
     * @param funcId
     * @return
     */
    public List<Map<String, Object>> saleHotKeywordTopRank(Map params, long funcId);

    /**
     * 銷售熱門關鍵詞Spotlight情況
     *
     * @param params
     * @param funcId
     * @return
     */
    public List<Map<String, Object>> saleHotKeywordSpotlight(Map params, long funcId);

    /**
     * 典型案例（最新收費會員）
     *
     * @param params
     * @param funcId
     * @return
     */
    public List<Map<String, Object>> newMember(Map params, long funcId);

    /**
     * 典型案例（服務期最長會員）
     *
     * @param params
     * @param funcId
     * @return
     */
    public List<Map<String, Object>> longestMember(Map params, long funcId);

    /**
     * 典型案例（詢盤量最高的會員）
     *
     * @param params
     * @param funcId
     * @return
     */
    public List<Map<String, Object>> enquiryHighestMember(Map params, long funcId);

    /**
     * 詢盤信息（收費客戶月平均詢盤量）
     *
     * @param params
     * @param funcId
     * @return
     */
    public List<Map<String, Object>> enquiryInfo(Map params, long funcId);

    /**
     * 詢盤信息（免費客戶月平均詢盤量）
     *
     * @param params
     * @param funcId
     * @return
     */
    public List<Map<String, Object>> enquiryInfoFree(Map params, long funcId);

    /**
     * 詢盤信息（買家詢盤量分布）
     *
     * @param params
     * @param funcId
     * @return
     */
    public List<Map<String, Object>> buyerEnquiryInfo(Map params, long funcId);

    /**
     * 會員分布（收費會員分布城市）
     *
     * @param params
     * @param funcId
     * @return
     */
    public List<Map<String, Object>> memberInfo(Map params, long funcId);

    /**
     * 會員分布（免費會員分布城市）
     *
     * @param params
     * @param funcId
     * @return
     */
    public List<Map<String, Object>> memberInfoFree(Map params, long funcId);

    /**
     * 客戶服務信息
     *
     * @param params
     * @param funcId
     * @return
     */
    public List<Map<String, Object>> cstSvsInfo(Map params, long funcId);

    /**
     * 詢盤信息
     *
     * @param params
     * @param funcId
     * @return
     */
    public List<Map<String, Object>> eqyInfo(Map params, long funcId);

    /**
     * 詢盤量峰值月
     *
     * @param params
     * @param funcId
     * @return
     */
    public List<Map<String, Object>> eqyHigh(Map params, long funcId);

    /**
     * 詢盤量谷值月
     *
     * @param params
     * @param funcId
     * @return
     */
    public List<Map<String, Object>> eqyLast(Map params, long funcId);

    /**
     * 首次服務內容
     *
     * @param params
     * @param funcId
     * @return
     */
    public List<Map<String, Object>> fstSvsInfo(Map params, long funcId);

    /**
     * 當前服務內容
     *
     * @param params
     * @param funcId
     * @return
     */
    public List<Map<String, Object>> curSvsInfo(Map params, long funcId);

    /**
     * 檢驗當前會員是否為免費會員且無推廣服務
     *
     * @param mid
     * @param lanCode
     * @param csLevel
     * @param params
     * @throws OssCheckedException
     */
    public void checkMemberInfoAndRecordSearch(long mid, String lanCode, long csLevel, Map params)
            throws OssCheckedException;
}
