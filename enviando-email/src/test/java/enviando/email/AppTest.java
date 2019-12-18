package enviando.email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AppTest {

    private String userEmail = "email";
    private String senha = "senha";

    @org.junit.Test
    public void testeEmail() {

        // Olhe as configurações SMTP do seu email
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");// Autorização
            properties.put("mail.smtp.starttls", "true");// Autenticação
            properties.put("mail.smtp.host", "smtp.gmail.com");// Servidor gmail Google
            properties.put("mail.smtp.port", "465");// Porta do Servidor
            properties.put("mail.smtp.socketFactory.port", "465");// Expecifica a porta a ser conectada pelo socket
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");// Classe socket de
                                                                                              // conexão ao
                                                                                              // SMTP
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userEmail, senha);
                }
            });
            Address[] toUser = InternetAddress
                    .parse("email1, email2, email3");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userEmail));// Quem está enviando
            message.setRecipients(Message.RecipientType.TO, toUser);// Email de destino
            message.setSubject("Chegou e-mail enviado do java");// Assunto do e-mail
            message.setText(
                    "Olá programador, vc acaba de receber um e-mail enviado com Java do curso Formação Java Web do Alex");
            Transport.send(message);        

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
