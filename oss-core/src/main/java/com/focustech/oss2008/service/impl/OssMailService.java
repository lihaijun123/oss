package com.focustech.oss2008.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.focustech.oss2008.Constants;
import com.focustech.oss2008.service.MailService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * <li></li>
 *
 * @author yangpeng 2008-7-16 上午11:33:23
 */
@Service
public class OssMailService implements MailService {
    @Autowired
    private JavaMailSender mailSender;
    //@Autowired
    private Configuration configuration;
    /** 郵件編碼設置 */
    private String mailEncoding = "BIG5";
    /** 系統發郵件的發件人 */
    private String defaultSystemSender = "系統管理員";
    private String defaultSystemAddress = "xiayu@made-in-china.com";
    private static ThreadLocal<MimeMessageHelper> mimeMessages = new ThreadLocal<MimeMessageHelper>();
    protected Log log = LogFactory.getLog(Constants.LOG_ROOT_SERVICE);

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.MailService#createMimeMessageHelper()
     */
    public MimeMessageHelper createMimeMessageHelper() {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, mailEncoding);
        mimeMessages.set(mimeMessageHelper);
        return mimeMessages.get();
    }

    public MimeMessageHelper createMimeMessageHelperMutipart() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, mailEncoding);
        mimeMessages.set(mimeMessageHelper);
        return mimeMessages.get();
    }

    /**
     * 根據給定模板名稱，創建郵件內容
     *
     * @param templateName 模板名稱
     * @param parameters 模板中的參數
     * @return 設置好模板內容的MimeMessageHelper
     */
    protected MimeMessageHelper fillTemplateContent(String templateName, Map<String, Object> parameters) {
        Template template;
        try {
            template = configuration.getTemplate(templateName);
        }
        catch (IOException e) {
            log.error(e);
            throw new MailPreparationException("There is an IoException when get the mail template.");
        }
        return fillTemplateContent(template, parameters);
    }

    /**
     * 根據給定模板和參數獲取信息內容
     *
     * @param template 模板
     * @param parameters 模板中的參數
     * @return 設置好模板內容的MimeMessageHelper
     */
    public String getTemplateContent(String templateName, Map<String, Object> parameters) {
        String text = "";
        Template template;
        try {
            template = configuration.getTemplate(templateName);
        }
        catch (IOException e) {
            log.error(e);
            throw new MailPreparationException("There is an IoException when get the mail template.");
        }
        try {
            text = FreeMarkerTemplateUtils.processTemplateIntoString(template, parameters);
        }
        catch (IOException e) {
            log.error(e);
            throw new MailPreparationException("There is an IoException when get the mail template.");
        }
        catch (TemplateException e) {
            log.error(e);
            throw new MailPreparationException("There is an TemplateException when get the mail template.");
        }
        return text;
    }

    /**
     * 根據給定模板，創建郵件內容
     *
     * @param template 模板
     * @param parameters 模板中的參數
     * @return 設置好模板內容的MimeMessageHelper
     */
    protected MimeMessageHelper fillTemplateContent(Template template, Map<String, Object> parameters) {
        try {
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, parameters);
            MimeMessageHelper helper = mimeMessages.get();
            helper.setText(text, true);
            return helper;
        }
        catch (IOException e) {
            log.error(e);
            throw new MailPreparationException("There is an IoException when get the mail template.");
        }
        catch (TemplateException e) {
            log.error(e);
            throw new MailPreparationException("There is an TemplateException when get the mail template.");
        }
        catch (MessagingException e) {
            log.error(e);
            throw new MailPreparationException("There is an MessagingException when create mail content.");
        }
    }

    protected MimeMessageHelper setSystemMailSender() throws MessagingException {
        AssertCurrentMimeNotNull();
        MimeMessageHelper helper = mimeMessages.get();
        try {
            helper.setFrom(defaultSystemAddress, defaultSystemSender);
        }
        catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return helper;
    }

    protected void AssertCurrentMimeNotNull() {
        MimeMessageHelper mimeMessageHelper = mimeMessages.get();
        if (mimeMessageHelper == null) {
            throw new MailPreparationException("current mimeMessageHelper is null.");
        }
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.MailService#sendMail()
     */
    public void sendMail() {
        AssertCurrentMimeNotNull();
        MimeMessageHelper mimeMessageHelper = mimeMessages.get();
        // 將發送郵件取消。注意：這遍是將所有發送郵件的地方都取消了。
        // mailSender.send(mimeMessageHelper.getMimeMessage());
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.MailService#sendSystemMail()
     */
    public void sendSystemMail() throws MessagingException {
        setSystemMailSender();
        sendMail();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.MailService#sendSystemMail(java.lang.String, java.util.Map)
     */
    public void sendSystemMail(String template, Map<String, Object> parameters) throws MessagingException {
        setSystemMailSender();
        sendMail(template, parameters);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.MailService#sendMail(java.lang.String, java.util.Map)
     */
    public void sendMail(String templateName, Map<String, Object> parameters) {
        Template template;
        try {
            template = configuration.getTemplate(templateName);
        }
        catch (IOException e) {
            log.error(e);
            throw new MailPreparationException("There is an IoException when get the mail template.");
        }
        sendMail(template, parameters);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.MailService#sendMail(freemarker.template.Template, java.util.Map)
     */
    public void sendMail(Template template, Map<String, Object> parameters) {
        AssertCurrentMimeNotNull();
        fillTemplateContent(template, parameters);
        sendMail();
    }

    public String getMailEncoding() {
        return mailEncoding;
    }

    public void setMailEncoding(String mailEncoding) {
        this.mailEncoding = mailEncoding;
    }

    public String getDefaultSystemSender() {
        return defaultSystemSender;
    }

    public void setDefaultSystemSender(String defaultSystemSender) {
        this.defaultSystemSender = defaultSystemSender;
    }

    public String getDefaultSystemAddress() {
        return defaultSystemAddress;
    }

    public void setDefaultSystemAddress(String defaultSystemAddress) {
        this.defaultSystemAddress = defaultSystemAddress;
    }
}
