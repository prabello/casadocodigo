package org.casadocodigo.loja.rest;

import java.math.BigDecimal;
import java.net.URI;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.casadocodigo.loja.dao.CheckoutDao;
import org.casadocodigo.loja.models.Checkout;
import org.casadocodigo.loja.services.PaymentGateway;

//@RequestScoped
@Path("/checkouts")
// @Produces({ "application/xml", "application/json" })
// @Consumes({ "application/xml", "application/json" })
public class CheckoutEndpoint {

	@Context
	private ServletContext ctx;
	private CheckoutDao checkoutDao;
	private PaymentGateway paymentGateway;

	@Deprecated
	CheckoutEndpoint() {
	}

	@Inject
	public CheckoutEndpoint(CheckoutDao checkoutDao, PaymentGateway paymentGateway) {
		this.checkoutDao = checkoutDao;
		this.paymentGateway = paymentGateway;
	}
	
	@POST
	public Response pay(@QueryParam("uuid")String uuid){
		String contextPath = ctx.getContextPath();
		Checkout checkout = checkoutDao.findByUuid(uuid);
		
		BigDecimal total = checkout.getValue();
		
		paymentGateway.pay(total);
		
		URI redirectUri = UriBuilder.fromPath("http://localhost:8080" + contextPath + "/site/index.html").queryParam("msg", "Compra realizada com sucesso").build();
		Response response = Response.seeOther(redirectUri).build();
		
		return response;
	}
}
