package com.focustech.oss2008.dao;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.focustech.oss2008.Constants;

/**
 * <li>oss jdbc 支持類</li>
 *
 * @author yangpeng 2008-6-24 下午02:53:54
 */
public abstract class OssJdbcDaoSupport implements InitializingBean {
    private SimpleJdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("ossDataSource")
    private DataSource ossDataSource;
    protected Log log = LogFactory.getLog(Constants.LOG_ROOT_DAO);

    public void init() {
        if (null == jdbcTemplate) {
            if (ossDataSource == null) {
                throw new IllegalArgumentException("ossDataSource is not prepared!");
            }
            else {
                jdbcTemplate = new SimpleJdbcTemplate(ossDataSource);
            }
        }
    }

    public SimpleJdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
