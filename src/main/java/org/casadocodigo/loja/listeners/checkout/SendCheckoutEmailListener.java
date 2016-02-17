package org.casadocodigo.loja.listeners.checkout;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.casadocodigo.loja.dao.CheckoutDao;
import org.casadocodigo.loja.infra.MailSender;
import org.casadocodigo.loja.models.Checkout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MessageDriven(activationConfig = @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/topics/checkoutsTopic") )
public class SendCheckoutEmailListener implements MessageListener {

	private Logger logger = LoggerFactory.getLogger(SendCheckoutEmailListener.class);
	private MailSender mailSender;
	private CheckoutDao checkoutDao;

	@Deprecated
	public SendCheckoutEmailListener() {
	}

	@Inject
	public SendCheckoutEmailListener(MailSender mailSender, CheckoutDao checkoutDao) {
		this.mailSender = mailSender;
		this.checkoutDao = checkoutDao;
	}

	@Override
	public void onMessage(Message message) {
		TextMessage text = (TextMessage) message;

		try {
			Checkout checkout = checkoutDao.findByUuid(text.getText());

			String mailBody = "Nova Compra.\n Seu código de acompanhamento é: " + checkout.getUuid();
			String emailDoComprador = checkout.getBuyerEmail();
			mailSender.send("compras@casadocodigo.com.br", emailDoComprador, "Nova Compra", mailBody);

		} catch (JMSException e) {
			logger.error("Problema no envio do email", e);
		}
	}

}
