package edu.mum.cs.clientservice.utility;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class EmailSender {

    static String host = "smtp.sendgrid.net";
    //static String port = "2525";
    static String port = "587";
    static final String username = "www.hexakomb.com";
    static final String password = "2581993@Becks";
    static String mailFrom = "support@hexakomb.com";
    public static String urlString = "";
    //  public static String urlString = "" ;


    public static void sendInvitation(String toAddress, String subject, String message)
            throws AddressException, MessagingException, UnsupportedEncodingException {

        // setting SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", host);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", username);
        properties.put("mail.password", password);

        // creating a new session with an authenticator of username & password of the sender
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getInstance(properties, auth);

        // creating a new e-mail message (MimeMessage)
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(mailFrom, "MUM ECOMMERCE"));

        if (!toAddress.isEmpty() || toAddress != null) {

            // creates message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(message, "text/html");

            // creates multi-part
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // add attachments

            // sets the multi-part as e-mail's content
            msg.setContent(multipart);

            //sending to multiple recipients

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            msg.setSubject(subject);
            msg.setSentDate(new Date());

            //Set the replyTo Email

            msg.setReplyTo(InternetAddress.parse("support@hexakomb.com"));

            // sends the e-mail
            Transport.send(msg);

        }
    }


    public static void registrationConfirmation(String toAddress, String subject, String message)
            throws AddressException, MessagingException, UnsupportedEncodingException {

        // setting SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", host);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", username);
        properties.put("mail.password", password);

        // creating a new session with an authenticator of username & password of the sender
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getInstance(properties, auth);

        // creating a new e-mail message (MimeMessage)
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(mailFrom, "PMS"));

        if (!toAddress.isEmpty() || toAddress != null) {

            // creates message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(message, "text/html");

            // creates multi-part
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // add attachments

            // sets the multi-part as e-mail's content
            msg.setContent(multipart);

            //sending to multiple recipients

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            msg.setSubject(subject);
            msg.setSentDate(new Date());

            //Set the replyTo Email

            msg.setReplyTo(InternetAddress.parse("support@hexakomb.com"));

            // sends the e-mail
            Transport.send(msg);

        }
    }
}
