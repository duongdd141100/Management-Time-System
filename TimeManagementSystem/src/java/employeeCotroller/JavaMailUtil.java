/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeeCotroller;

import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Do Duc Duong
 */
public class JavaMailUtil {

    public static int sendEmail(String email) throws Exception {
        Properties p = new Properties();
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp", "true");
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", "587");
        String myAcc = "duongdd141100@gmail.com";
        String pass = "Doducduong14@";
        Session session = Session.getInstance(p, new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(myAcc, pass);
            }

        });
        int min = 100000;
        int max = 999999;
        int code = (int) (Math.random() * (max - min + 1) + min);
        Message message = prepareMessage(session, myAcc, email, code);
        Transport.send(message);
        return code;
    }

    private static Message prepareMessage(Session session, String myAcc, String email, int random_int) {
        try {
            Message mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(myAcc));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mess.setSubject("Code For Reset Your Password!");

            mess.setText("Your code to chage password is " + random_int);
            return mess;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }
    
    public static void sendEmail(String email, String subject, String text) throws Exception {
        Properties p = new Properties();
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp", "true");
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", "587");
        String myAcc = "duongdd141100@gmail.com";
        String pass = "Doducduong14@";
        Session session = Session.getInstance(p, new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(myAcc, pass);
            }

        });
        Message message = prepareMessage(session, myAcc, email, subject, text);
        Transport.send(message);
    }

    private static Message prepareMessage(Session session, String myAcc, String email, String subject, String text) {
        try {
            Message mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(myAcc));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mess.setSubject(subject);
            mess.setText(text);
            return mess;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    
}
