package org.casadocodigo.loja.infra;

import javax.mail.MailSessionDefinition;

@MailSessionDefinition(name = "java:jboss/mail/gmail", host = "smtp.gmail.com", user = "bacoquinho@gmail.com", password = "b4c0c0123", transportProtocol = "smtps", from = "compras@casadocodigo.com", properties = {
		"mail.smtp.port=465" })
public class MailConfiguration {

}
