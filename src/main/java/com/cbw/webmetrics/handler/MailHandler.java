package com.cbw.webmetrics.handler;

import com.cbw.webmetrics.config.Config;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * send email to user mailbox
 */
public class MailHandler {

    private static String myEmailAccount = Config.MY_EMAIL_ACCOUNT;
    private static String myEmailPassword = Config.MY_EMAIL_PASSWORD;
    private static String myEmailSMTPHost = Config.MY_EMAIL_SMTP_HOST;
    public static final String smtpPort = Config.SMTP_PORT;

    /**
     * send method
     *
     * @param receiveMailAccount
     * @param content
     */
    public static void sendEmail(String receiveMailAccount, String content) {

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", myEmailSMTPHost);
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);

        Session session = Session.getInstance(props);
        session.setDebug(true);

        for (int i = 0; i < 3; i++) {
            try {
                MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount, content);
                Transport transport = session.getTransport();
                transport.connect(myEmailAccount, myEmailPassword);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                break;
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger("MailHandler").warning("send email error, email is: " + receiveMailAccount + ", content is: " + content + e);
            }
        }
    }

    /**
     * create a mail message info, including title, sender, content and so on
     *
     * @param session
     * @param sendMail
     * @param receiveMail
     * @param content
     * @return
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail, String content) {
        MimeMessage message = new MimeMessage(session);
        for (int i = 0; i < 3; i++) {
            try {
                message.setFrom(new InternetAddress(sendMail, "WebMonitor", "UTF-8"));
                message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "USER", "UTF-8"));
                message.setSubject("Web-Monitor-Warning", "UTF-8");
                message.setContent(content, "text/html;charset=UTF-8");
                message.setSentDate(new Date());
                message.saveChanges();
                return message;
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger("MailHandler").warning("create message error, content is: " + content + e);
            }
        }
        return message;
    }
}