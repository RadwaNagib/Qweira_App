import org.testng.annotations.Test;
import org.testng.reporters.EmailableReporter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class SendEmail {
    @Test
    public void mymail() {
        String username = "contact@qweira.com"; // email
        String password = "Mm123456@@";       // password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com"); // SMTP server host
        props.put("mail.smtp.port", "587");                // SMTP server port

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
          //  message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("wa.nagib86@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("besoocool3636@gmail.com"));

            message.setSubject("Result of Test");

            // Create Multipart object to hold multiple parts of the message
            Multipart multipart = new MimeMultipart();

            //  text content of the email
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("This is an automation test email sent from Java.");
            multipart.addBodyPart(messageBodyPart);

            // Attach .xml file
          //  MimeBodyPart xmlAttachment = new MimeBodyPart();
         //   xmlAttachment.attachFile(new File("C:/Users/Shass/IdeaProjects/Qweira.App/test-output/testng-failed.xml")); //the path to your .xml file
           // multipart.addBodyPart(xmlAttachment);//C:/Users/Shass/IdeaProjects/Qweira.App/

            // Attach .html file
            MimeBodyPart htmlAttachment = new MimeBodyPart();
            htmlAttachment.attachFile(new File("C:/Users/Shass/IdeaProjects/Qweira.App/test-output/emailable-report.html")); //the path to your .html file
            multipart.addBodyPart(htmlAttachment);//C:/Users/Shass/IdeaProjects/Qweira.App/

            //the content of the message to the Multipart object
            message.setContent(multipart);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }
}


