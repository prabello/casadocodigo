package org.casadocodigo.loja.infra;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.mail.Message.RecipientType;
import javax.mail.MailSessionDefinition;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@ApplicationScoped
@MailSessionDefinition(name = "java:jboss/mail/gmail", host = "smtp.gmail.com", user = "bacoquinho@gmail.com", password = "b4c0c0123", transportProtocol = "smtps", from = "compras@casadocodigo.com", properties = {
		"mail.smtp.port=465" })
public class MailSender {

	@Resource(mappedName = "java:jboss/mail/gmail")
	private Session session;

	public void send(String from, String to, String subject, String body) {
		MimeMessage message = new MimeMessage(session);

		try {
			message.setRecipients(RecipientType.TO, InternetAddress.parse(to));
			message.setFrom(new InternetAddress(from));
			message.setSubject("Sua compra foi registrada");
			message.setContent(body, "text/html");

			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
