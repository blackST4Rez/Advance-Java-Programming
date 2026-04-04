import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String user = "your.email@gmail.com";      // Replace with your email
        String pass = "your-app-password";         // Replace with your password
        String to   = "recipient@example.com";     // Replace with recipient

        // Simple mail
        send("smtp.gmail.com", user, pass, to, "Simple", "Hello!", null);
        // Mail with attachment
        send("smtp.gmail.com", user, pass, to, "With Attachment", "See file.", "/path/to/file.txt");
        // Receive mail
        receive("pop.gmail.com", user, pass);
    }

    static void send(String host, String user, String pass, String to, String subj, String text, String attach) throws Exception {
        Properties p = new Properties();
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", host);
        p.put("mail.smtp.port", "587");
        Session s = Session.getInstance(p, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
        Message m = new MimeMessage(s);
        m.setFrom(new InternetAddress(user));
        m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        m.setSubject(subj);
        if (attach == null) {
            m.setText(text);
        } else {
            MimeBodyPart p1 = new MimeBodyPart();
            p1.setText(text);
            MimeBodyPart p2 = new MimeBodyPart();
            p2.attachFile(attach);
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(p1);
            mp.addBodyPart(p2);
            m.setContent(mp);
        }
        Transport.send(m);
        System.out.println("Sent: " + subj);
    }

    static void receive(String host, String user, String pass) throws Exception {
        Properties p = new Properties();
        p.put("mail.pop3.host", host);
        p.put("mail.pop3.port", "995");
        p.put("mail.pop3.ssl.enable", "true");
        Session s = Session.getInstance(p);
        Store store = s.getStore("pop3s");
        store.connect(host, user, pass);
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);
        Message[] msgs = inbox.getMessages();
        System.out.println("Total messages: " + msgs.length);
        for (int i = Math.max(0, msgs.length - 3); i < msgs.length; i++) {
            System.out.println("Subject: " + msgs[i].getSubject());
            System.out.println("From: " + msgs[i].getFrom()[0]);
        }
        inbox.close(false);
        store.close();
    }
}