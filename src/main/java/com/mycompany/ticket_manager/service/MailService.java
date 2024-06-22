//package com.mycompany.ticket_manager.service;
//
//import java.util.Properties;
//import javax.mail.*;
//import javax.mail.internet.*;
//
//public class MailService {
//
//    public boolean sendMail() {
//        // Thay thế các giá trị này bằng thông tin email của bạn
//        String fromEmail = "chienka0003@gmail.com";
//        String password = "srdm rrks fjay rswu";
//        String toEmail = "chienboy03@gmail.com";
//        String subject = "Email subject";
//        String body = "Email body";
//
//        try {
//            // Cấu hình thuộc tính email
//            Properties props = new Properties();
//            props.put("mail.smtp.host", "smtp.gmail.com"); // Thay thế bằng máy chủ SMTP của bạn
//            props.put("mail.smtp.port", "587"); // Port for STARTTLS (usually 587)
//            props.put("mail.smtp.auth", "true"); // Enable authentication
//            props.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS
//            props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Explicitly specify the protocol
//            props.put("mail.smtp.connectiontimeout", "10000"); // 10 seconds connection timeout
//            props.put("mail.smtp.timeout", "10000"); // 10 seconds timeout
//            props.put("mail.smtp.writetimeout", "10000"); // 10 seconds write timeout
//
//            // Enable debugging
//            Session session = Session.getInstance(props, new Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(fromEmail, password);
//                }
//            });
//            session.setDebug(true);
//
//            // Tạo tin nhắn email
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(fromEmail));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
//            message.setSubject(subject);
//            message.setText(body);
//
//            // Gửi email
//            Transport.send(message);
//            System.out.println("Email sent successfully!");
//            return true;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//}
