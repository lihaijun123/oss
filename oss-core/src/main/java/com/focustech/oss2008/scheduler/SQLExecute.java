package com.focustech.oss2008.scheduler;

import org.springframework.beans.factory.annotation.Autowired;

import com.focustech.oss2008.service.impl.SQLSchedule;

public class SQLExecute extends OssTarget {
    @Autowired
    private SQLSchedule sQLSchedule;
    private String mailTo = "";
    private String FILE_COM_PIC_CN = "";
    private String FILE_COM_PIC_EN = "";
    private String FILE_PIC_100 = "";
    private String triggerName = "";

    /**
     * 構造器
     *
     * @param file_com_pic_cn
     * @param file_com_pic_en
     * @param file_pic_100
     * @param mailTo
     * @param triggerName
     */
    public SQLExecute() {
    }

    public void targetTriggered() {
        try {
            sQLSchedule.executeSQLFromFile("config/sql");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
