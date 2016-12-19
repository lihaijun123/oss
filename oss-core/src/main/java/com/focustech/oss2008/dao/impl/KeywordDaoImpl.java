package com.focustech.oss2008.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.focustech.oss2008.dao.KeywordDao;
import com.focustech.oss2008.dao.OssHibernateDaoSupport;
import com.focustech.oss2008.model.OssKeywordChk;
import com.focustech.uitool.list.FunctionBean;
import com.focustech.uitool.list.dt.ParsedSqlBean;
import com.focustech.uitool.list.utils.UIToolExeSqlTools;

/**
 * <li>關鍵詞信息管理Dao</li>
 *
 * @author jibin
 */
@Repository
public class KeywordDaoImpl extends OssHibernateDaoSupport<OssKeywordChk> implements KeywordDao<OssKeywordChk> {

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.KeywordDao#getInfo(java.util.Map, long)
     */
    public List<Map<String, Object>> getInfo(long funcId) throws Exception {
        FunctionBean funcBean = UIToolExeSqlTools.getFunction(funcId, "0000");
        ParsedSqlBean psb = UIToolExeSqlTools.getParsedSql(funcBean, null, null);
        String sql = psb.getSql().toString();
        return getJdbcTemplate().queryForList(sql);
    }

}
