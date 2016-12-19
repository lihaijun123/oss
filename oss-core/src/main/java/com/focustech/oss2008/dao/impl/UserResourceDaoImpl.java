package com.focustech.oss2008.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.focustech.oss2008.dao.OssJdbcDaoSupport;
import com.focustech.oss2008.dao.UserResourceDao;

/**
 * Copyright (c) 2008, focustech All rights reserved
 *
 * @author tc-hexuey
 * @version 1.0 2008-9-27 上午11:32:24
 */
@Repository
public class UserResourceDaoImpl extends OssJdbcDaoSupport implements UserResourceDao {
    public void delete(String[] userId, Long[] resourceId) {
        List<Object[]> args = new ArrayList<Object[]>();
        for (int i = 0; i < userId.length; i++) {
            Object[] arg = new Object[2];
            arg[0] = userId[i];
            arg[1] = resourceId[i];
            args.add(arg);
        }
        String sql = "DELETE FROM OSS_ADMIN_USER_RESOURCE WHERE USER_ID=? AND RESOURCE_ID=?";
        getJdbcTemplate().batchUpdate(sql, args);
    }

    public void insert(String[] userId, Long[] resourceId) {
        List<Object[]> args = new ArrayList<Object[]>();
        for (int i = 0; i < userId.length; i++) {
            Object[] arg = new Object[2];
            arg[0] = userId[i];
            arg[1] = resourceId[i];
            args.add(arg);
        }
        String sql = "INSERT INTO OSS_ADMIN_USER_RESOURCE (USER_ID,RESOURCE_ID) VALUES (?,?)";
        getJdbcTemplate().batchUpdate(sql, args);
    }
}
