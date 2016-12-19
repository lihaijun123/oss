package com.focustech.oss2008.service.impl;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Repository;

import com.focustech.common.utils.FileUtils;
import com.focustech.oss2008.dao.OssJdbcDaoSupport;
import com.focustech.oss2008.model.TableConfig;


/**
 * <li>sql日終程序</li>
 *
 * @author zhanghua 2008-6-25 下午04:52:00
 */
@Repository
public class SQLSchedule extends OssJdbcDaoSupport {
    private static Pattern p = Pattern.compile("^[a-z]", Pattern.CASE_INSENSITIVE);
    public static final String directory = "config/sql";
    public static final String fileSuffix = "SQL";

    protected StringBuffer wrapperPagination(StringBuffer sql, TableConfig configuration) {
        return null;
    }

    public List<Map> getList(String sql) {
        List data = null;
        data = this.getJdbcTemplate().queryForList(sql);
        return data;
    }

    public void executeSQLFromFile(String fileDir) throws Exception {
        List<String> list = FileUtils.getFileList(fileDir, fileSuffix);
        for (int i = 0; i < list.size(); i++) {
            String fileName = list.get(i);
            List lisSQL = FileUtils.readFileToList(fileName);
            if (lisSQL != null) {
                for (int j = 0; j < lisSQL.size(); j++) {
                    String strSql = (String) lisSQL.get(j);
                    System.out.println(strSql);
                    this.getJdbcTemplate().update(strSql);
                }
            }
        }
    }

    private boolean startsWithChar(String strVal) {
        return p.matcher(strVal).find();
    }
}
