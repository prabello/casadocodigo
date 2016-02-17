package org.casadocodigo.loja.listeners.checkout;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.casadocodigo.loja.dao.CheckoutDao;
import org.casadocodigo.loja.models.Checkout;
import org.casadocodigo.loja.services.InvoiceGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateInvoiceListener implements MessageListener {

	private Logger logger = LoggerFactory.getLogger(GenerateInvoiceListener.class);
	private InvoiceGenerator invoiceGenerator;
	private CheckoutDao checkoutDao;

	@Deprecated
	public GenerateInvoiceListener() {
	}

	@Inject
	public GenerateInvoiceListener(InvoiceGenerator invoiceGenerator, CheckoutDao checkoutDao) {
		this.checkoutDao = checkoutDao;
		this.invoiceGenerator = invoiceGenerator;
	}

	@Override
	public void onMessage(Message message) {
		TextMessage text = (TextMessage) message;
		try {
			Checkout checkout = checkoutDao.findByUuid(text.getText());
			invoiceGenerator.invoiceFor(checkout);
		} catch (JMSException e) {
			logger.error("Problema na geração da nota fiscal {}",e);
		}
	}

}
