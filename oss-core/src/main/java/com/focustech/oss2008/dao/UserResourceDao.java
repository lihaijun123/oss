package com.focustech.oss2008.dao;

/**
 * Copyright (c) 2008, focustech All rights reserved
 *
 * @author tc-hexuey
 * @version 1.0 2008-9-27 上午11:34:43 @param <T>
 */
public interface UserResourceDao<T> {
    public void delete(String[] userIds, Long[] resourceIds);

    public void insert(String[] userIds, Long[] resourceIds);
}
