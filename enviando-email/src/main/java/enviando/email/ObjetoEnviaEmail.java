package enviando.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ObjetoEnviaEmail {

	private String userEmail = "email@gmail.com";
	private String senha = "**";
	private String listaDestinatarios = "";
	private String nomeRementente = "";
	private String assuntoEmail = "";
	private String textoEmail = "";

	public ObjetoEnviaEmail(String listaDestinatario, String nomeRemetente, String assuntoEmail, String textoEmail) {
		this.listaDestinatarios = listaDestinatario;
		this.nomeRementente = nomeRemetente;
		this.assuntoEmail = assuntoEmail;
		this.textoEmail = textoEmail;
	}

	public void enviarEmail(Boolean envioHTML) throws Exception {
		// Olhe as configurações SMTP do seu email
		Properties properties = new Properties();
		// Autorização
		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true");
		// Autenticação
		properties.put("mail.smtp.starttls", "true");
		// Servidor gmail Google
		properties.put("mail.smtp.host", "smtp.gmail.com");
		// Porta do Servidor
		properties.put("mail.smtp.port", "465");
		// Especifica a porta a ser conectada pelo socket
		properties.put("mail.smtp.socketFactory.port", "465");
		// Classe socket de conexão ao SMTP
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userEmail, senha);
			}
		});
		Address[] toUser = InternetAddress.parse(listaDestinatarios);
		Message message = new MimeMessage(session);
		// Quem está enviando
		message.setFrom(new InternetAddress(userEmail, nomeRementente));
		// Email de destino
		message.setRecipients(Message.RecipientType.TO, toUser);
		// Assunto do e-mail
		message.setSubject(assuntoEmail);
		// Texto do email
		if (envioHTML) {
			message.setContent(textoEmail, "text/html; charser=utf-8");
		} else {
			message.setText(textoEmail);
		}
		Transport.send(message);
	}

	public void enviarEmailAnexo(Boolean envioHTML) throws Exception {
		// Olhe as configurações SMTP do seu email
		Properties properties = new Properties();
		// Autorização
		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true");
		// Autenticação
		properties.put("mail.smtp.starttls", "true");
		// Servidor gmail Google
		properties.put("mail.smtp.host", "smtp.gmail.com");
		// Porta do Servidor
		properties.put("mail.smtp.port", "465");
		// Especifica a porta a ser conectada pelo socket
		properties.put("mail.smtp.socketFactory.port", "465");
		// Classe socket de conexão ao SMTP
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userEmail, senha);
			}
		});
		Address[] toUser = InternetAddress.parse(listaDestinatarios);
		Message message = new MimeMessage(session);
		// Quem está enviando
		message.setFrom(new InternetAddress(userEmail, nomeRementente));
		// Email de destino
		message.setRecipients(Message.RecipientType.TO, toUser);
		// Assunto do e-mail
		message.setSubject(assuntoEmail);
		/*
		 * Parte i do e-mail que é texto e a descrição do e-mail.
		 */
		MimeBodyPart corpoEmail = new MimeBodyPart();
		// Texto do email
		if (envioHTML) {
			corpoEmail.setContent(textoEmail, "text/html; charser=utf-8");
		} else {
			corpoEmail.setText(textoEmail);
		}

		List<FileInputStream> arquivos = new ArrayList<FileInputStream>();
		arquivos.add(simuladorPDF());/* Certificado */
		arquivos.add(simuladorPDF());/* Nota fiscal */
		arquivos.add(simuladorPDF());/* Documento texto */
		arquivos.add(simuladorPDF());/* Imagem */
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(corpoEmail);
		/*
		 * Parte 2 do e-mail que são os anexos em pdf
		 */
		int index = 0;

		for (FileInputStream fileInputStream : arquivos) {
			MimeBodyPart anexoEmail = new MimeBodyPart();
			// onde é passado o simuladorDePDF você passa o seu arquivo gravado no banco de
			// dados
			anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(fileInputStream, "application/pdf")));
			index++;
			anexoEmail.setFileName("anexoEmail " + index + ".pdf");
			multipart.addBodyPart(anexoEmail);
		}
		message.setContent(multipart);
		Transport.send(message);
	}

	/*
	 * Esse método simula o PDF ou qualquer arquivo que possa ser enviado por anexo
	 * no email. Você pode pegar o arquivo no seu banco de dados base64, byte[],
	 * Stream de arquivos. Pode estar e um banco de dados, ou em uma pasta.
	 */
	private FileInputStream simuladorPDF() throws Exception {
		Document document = new Document();
		File file = new File("fileanexo.pdf");
		file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("Conteudo do PDF anexo xom Java Mail, esse texto é do PDF"));
		document.close();
		return new FileInputStream(file);
	}
}
