package com.focustech.oss2008.dao;

import java.util.List;

public interface CoreSgsTokenInfoDao<T> extends BaseHibernateDao<T> {
    @SuppressWarnings("unchecked")
    List findARMonthAll(String startTime, String endTime);
}
