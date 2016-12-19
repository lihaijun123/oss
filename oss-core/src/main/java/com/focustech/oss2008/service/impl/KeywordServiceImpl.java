package com.focustech.oss2008.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.oss2008.dao.KeywordDao;
import com.focustech.oss2008.model.OssKeywordChk;
import com.focustech.oss2008.service.AbstractServiceSupport;
import com.focustech.oss2008.service.KeywordService;

/**
 * <li>關鍵詞信息處理Service</li>
 *
 * @author jibin
 */
@Service
public class KeywordServiceImpl extends AbstractServiceSupport implements KeywordService {

    @Autowired
    private KeywordDao<OssKeywordChk> keywordDao;

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.KeywordService#searchCatCode(java.util.Map, long)
     */
    public List<Map<String, Object>> search(long funcId) {
        List<Map<String, Object>> lhm = null;
        try {
            lhm = keywordDao.getInfo(funcId);
        }
        catch (Exception e) {
            log.error(e);
        }
        return lhm;
    }

}
