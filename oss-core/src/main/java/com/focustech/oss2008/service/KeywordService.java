package com.focustech.oss2008.service;

import java.util.List;
import java.util.Map;

/**
 * <li>關鍵詞信息處理Service</li>
 *
 * @author jibin
 */
public interface KeywordService {
    /**
     * 獲取關鍵詞排行
     *
     * @param funcId
     * @return
     */
    public List<Map<String, Object>> search(long funcId);

}
