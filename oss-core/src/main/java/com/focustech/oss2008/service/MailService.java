package com.focustech.oss2008.service;

import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.mail.MailPreparationException;
import org.springframework.mail.javamail.MimeMessageHelper;

import freemarker.template.Template;

public interface MailService {
    /**
     * 根據當前線程創建MimeMessage
     */
    public MimeMessageHelper createMimeMessageHelper();

    /**
     * 根據當前線程創建MimeMessage
     *
     * @throws MessagingException
     */
    public MimeMessageHelper createMimeMessageHelperMutipart() throws MessagingException;

    /**
     * 發送郵件
     *
     * @throws MailPreparationException.當前線程的MimeMessage為空時
     */
    public void sendMail();

    /**
     * 根據給定模板和參數獲取信息內容
     *
     * @param template 模板
     * @param parameters 模板中的參數
     * @return 設置好模板內容的MimeMessageHelper
     */
    public String getTemplateContent(String template, Map<String, Object> parameters);

    /**
     * 根據給定的模板，發送郵件
     *
     * @param templateName 模板名稱
     * @param parameters 模板中需要的參數
     */
    public void sendMail(String templateName, Map<String, Object> parameters);

    /**
     * 根據系統生成的模板類，發送郵件
     *
     * @param template 模板類
     * @param parameters 模板中需要的參數
     */
    public void sendMail(Template template, Map<String, Object> parameters);

    /**
     * 發送系統郵件,默認的發件人為system.可以通過配置修改系統發件人的名稱.
     *
     * @throws MessagingException 設置的系統發件人名稱有誤
     */
    public void sendSystemMail() throws MessagingException;

    /**
     * 根據給定的模板，默認的發件人為system.可以通過配置修改系統發件人的名稱.
     *
     * @param template 模板名稱
     * @param parameters 模板中需要的參數
     * @throws MessagingException 設置的系統發件人名稱有誤
     */
    public void sendSystemMail(String template, Map<String, Object> parameters) throws MessagingException;

    public String getMailEncoding();
}
