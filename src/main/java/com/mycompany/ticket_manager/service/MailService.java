package com.mycompany.ticket_manager.service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author chien
 */
public class MailService extends Thread {

    private String from;
    private String password;
    private String author;

    public MailService() {
        Dotenv dotenv = Dotenv.load();
        from = dotenv.get("EMAIL");
        password = dotenv.get("PASSWORD");
        author = dotenv.get("PERSONAL");
    }

    private Session getSessionMailer() {
        Properties p = new Properties();
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", "587");
        Session s = Session.getInstance(p,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        return s;
    }

    public void senMail(String subject, String body, String to) {
        Session s = this.getSessionMailer();
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Message msg = new MimeMessage(s);
                    msg.setFrom(new InternetAddress(from, author));
                    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                    msg.setSubject(subject);
                    msg.setContent(body, "text/html; charset=UTF-8");
                    Transport.send(msg);
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }catch(UnsupportedEncodingException ex){
                    ex.printStackTrace();
                }
            }
        };
        thread.start();
    }

}
