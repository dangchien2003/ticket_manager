/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.repository;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author chien
 */
public class MailRepository extends Thread {

    private String from;
    private String password;
    private String author;

    public MailRepository() {
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
                    msg.setFrom(new InternetAddress(from));
                    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                    msg.setSubject(subject);
                    msg.setText(body);
                    Transport.send(msg);
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
            }
        };
        thread.start();
    }

}
