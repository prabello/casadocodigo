package org.casadocodigo.loja.models;

import java.math.BigDecimal;

public class InvoiceData {

	private BigDecimal value;
	private String buyerEmail;

	public InvoiceData(Checkout checkout) {
		this.value = checkout.getValue();
		this.buyerEmail = checkout.getBuyerEmail();
	}

	public BigDecimal getValue() {
		return value;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

}
