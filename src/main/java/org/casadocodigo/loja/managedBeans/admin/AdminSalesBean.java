package org.casadocodigo.loja.managedBeans.admin;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.casadocodigo.loja.models.Book;
import org.casadocodigo.loja.models.Sale;
import org.casadocodigo.loja.websockets.ConnectedUsers;

@Model
public class AdminSalesBean {

	@Inject
	public AdminSalesBean(ConnectedUsers connectedUsers) {
		this.connectedUsers = connectedUsers;
	}

	@Deprecated
	public AdminSalesBean() {
	}

	private Sale sale = new Sale();
	private ConnectedUsers connectedUsers;

	@PostConstruct
	public void configure() {
		this.sale.setBook(new Book());
	}

	public String save() {
		connectedUsers.send(sale.toJson());
		// precisamos notificar os usuários sobre a promoção
		return "/admin/promocoes/form.xhtml?faces-redirect=true";
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

}
