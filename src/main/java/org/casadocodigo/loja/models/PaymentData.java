package org.casadocodigo.loja.models;

import java.math.BigDecimal;

public class PaymentData {
	
	@Deprecated
	public PaymentData(){
		
	}
	
	public PaymentData(BigDecimal value) {
		this.value = value;
	}

	private BigDecimal value;

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	
}
