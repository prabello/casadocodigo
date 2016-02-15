package org.casadocodigo.loja.services;

import java.math.BigDecimal;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;

import org.casadocodigo.loja.models.PaymentData;

public class PaymentGateway {

	public void pay(BigDecimal total) {
		Client client = ClientBuilder.newClient();
		PaymentData paymentData = new PaymentData(total);

		String uriToPay = "http://book-payment.herokuapp.com/payment";
		Entity<PaymentData> json = Entity.json(paymentData);
		WebTarget target = client.target(uriToPay);
		Builder request = target.request();
		String post = request.post(json, String.class);
		System.out.println(post);
	}
}
