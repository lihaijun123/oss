package com.focustech.oss2008.scheduler;

import java.util.Map;
import java.util.StringTokenizer;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.focustech.oss2008.Constants;
import com.focustech.oss2008.service.MailService;

import freemarker.template.Template;

/**
 * <li>日終數據處理</li>
 *
 * @author xufei
 */
public abstract class OssTarget {
    @Autowired
    private MailService mailService;
    // public static String mic_schema = "MIC2005.";
    // public static String oss_schema = "MICOSS2005.";
    protected StringBuffer errorInfo = null;
    protected Log log = LogFactory.getLog(Constants.LOG_ROOT_SCHEDULER);

    public void sendMail(String subject, String content, String receiverAddress) {
        try {
            MimeMessageHelper helper = mailService.createMimeMessageHelper();
            StringTokenizer token = new StringTokenizer(receiverAddress, ",");
            while (token.hasMoreTokens()) {
                helper.setTo(getReceivers(token.nextToken()));
            }
            helper.setSubject(subject);
            helper.setText(content);
            mailService.sendMail();
        }
        catch (Exception e) {
            log.error("Error OssTarget::sendMail(String subject, String content, String receiverAddress).", e);
        }
    }

    public String sendMail(String subject, Template content, String receiverAddress, String fromEmail, String fromName,
            Map<String, Object> map) {
        try {
            MimeMessageHelper helper = mailService.createMimeMessageHelper();
            helper.setTo(getReceivers(receiverAddress));
            helper.setSubject(subject);
            helper.setFrom(fromEmail, fromName);
            mailService.sendMail(content, map);

        }
        catch (Exception e) {
            log
                    .error(
                            "Error OssTarget::sendMail(String subject,Template content,String receiverAddress,String fromEmail,String fromName,Map<String, Object> map).",
                            e);
            return e.getMessage();
        }
        return null;
    }

    public void sendMail(String template, Map<String, Object> map, String subject, String receiverAddress) {
        try {
            MimeMessageHelper helper = mailService.createMimeMessageHelper();
            helper.setTo(getReceivers(receiverAddress));
            helper.setSubject(subject);
            mailService.sendSystemMail(template, map);
        }
        catch (MessagingException e) {
            log
                    .error(
                            "Error OssTarget::sendMail(String template, Map<String, Object> map, String subject, String receiverAddress).",
                            e);
        }
    }

    public void initErrorInfo() {
        errorInfo = new StringBuffer();
    }

    /**
     * 處理郵件地址問題
     *
     * @param receiverAddress
     * @return
     */
    private String[] getReceivers(String receiverAddress) {
        if (receiverAddress == null) {
            return null;
        }
        String mails = receiverAddress.replaceAll("[,，;；:︰]", " ");
        mails = mails.replaceAll("[ ]+", " ");
        mails = mails.trim();
        mails = mails.replaceAll(" ", ",");
        return mails.split(",");
    }
}
