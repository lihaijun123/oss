package com.focustech.oss2008.uitool.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.focustech.common.utils.DateUtils;
import com.focustech.oss2008.Constants;
import com.focustech.uitool.list.UIToolExeSqlCreate;
import com.focustech.uitool.list.utils.UIToolUtils;

/**
 * Oss2008DateRangeImpl.java
 *
 * @author jibin
 */
public class Oss2008DateRangeImpl implements UIToolExeSqlCreate {
    /*
     * (non-Javadoc)
     * @see com.nl.uitool.UIToolExeSqlCreate#create(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    public String create(Map reqData) {
        try {
            String strSql = "";
            Date now = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(now);
            if ((c.get(Calendar.DAY_OF_WEEK) == 1) || (c.get(Calendar.DAY_OF_WEEK) == 2)
                    || (c.get(Calendar.DAY_OF_WEEK) == 3) || (c.get(Calendar.DAY_OF_WEEK) == 4)
                    || (c.get(Calendar.DAY_OF_WEEK) == 5)) {
                strSql =
                        "TO_DATE(CREATED_TIME2, 'yyyy-MM-dd') <= ADD_DAYS(TO_DATE('"
                                + DateUtils.formatDate(now, Constants.DATEPATTERN_SEP) + "', 'yyyy-MM-dd') , 1)";
            }
            else if (c.get(Calendar.DAY_OF_WEEK) == 6) {
                strSql =
                        "TO_DATE(CREATED_TIME2, 'yyyy-MM-dd') <= ADD_DAYS(TO_DATE('"
                                + DateUtils.formatDate(now, Constants.DATEPATTERN_SEP) + "', 'yyyy-MM-dd') , 3)";
            }
            else if ((c.get(Calendar.DAY_OF_WEEK) == 7)) {
                strSql =
                        "TO_DATE(CREATED_TIME2, 'yyyy-MM-dd') <= ADD_DAYS(TO_DATE('"
                                + DateUtils.formatDate(now, Constants.DATEPATTERN_SEP) + "', 'yyyy-MM-dd') , 2)";
            }
            else {
                strSql = "1=1";
            }
            return strSql;
        }
        catch (Throwable e) {
            UIToolUtils.getLogger().error(e);
            return "";
        }
    }

    /**
     * test
     *
     * @param args
     */
    public static void main(String[] args) {
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        System.out.println(c.get(Calendar.DAY_OF_WEEK));
        c.add(Calendar.DAY_OF_WEEK, 1);
        System.out.println(c.get(Calendar.DAY_OF_WEEK));
        Oss2008DateRangeImpl oss = new Oss2008DateRangeImpl();
        System.out.println(oss.create(null));
    }
}
