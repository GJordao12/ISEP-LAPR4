package eapli.helpdesk.colaborador.domain;
import javax.mail.Session;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



/**
 * The type Email handler.
 */
public class EmailHandler {

    /**
     * The Session.
     */
    private final Session sessao;

    /**
     * The User Name.
     */
    private final String username;

    /**
     * Instantiates a new Email handler.
     *
     * @throws IOException the io exception
     */
    public EmailHandler() throws IOException {
        Properties appProperties = new Properties();
        String propFileName = "application.properties";

        InputStream inptStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        if (inptStream != null) {
            appProperties.load(inptStream);
        } else {
            throw new FileNotFoundException("Application property does not exist");
        }

        inptStream.close();

        username = appProperties.getProperty("email.from", "helpdeskService@outlook.pt");
        final String password = appProperties.getProperty("email.password", "helpdesk123");

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.office365.com");
        properties.put("mail.smtp.ssl.trust","*");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.mime.charset", "UTF-8");

        sessao = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

    }


    /**
     * Send email boolean.
     *
     * @param dest    the dest
     * @param subject the subject
     * @param content the content
     * @return the boolean
     */
    public boolean sendEmail(String dest, String subject, String content) {
        try {
            Message msg = new MimeMessage(sessao);
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dest));
            msg.setSubject(subject);
            msg.setText(content);
            Transport.send(msg);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

    }
}

