package org.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class Checkout {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private SystemUser buyer;
	
	private BigDecimal value;
	
	private String jsconCart;
	private String uuid;
	
	public Checkout(SystemUser buyer, ShoppingCart cart) {
		this.buyer = buyer;
		this.value = cart.getTotal();
		this.jsconCart = cart.toJson();
	}
	
	@Deprecated
	Checkout(){
		
	}
	
	@PrePersist
	public void prePersist(){
		this.uuid = UUID.randomUUID().toString();
	}
	
	public String getBuyerEmail(){
		return buyer.getEmail();
	}

	public BigDecimal getValue() {
		return value;
	}

	public String getUuid() {
		return uuid;
	}

}


