package org.casadocodigo.loja.resources;

import java.math.BigDecimal;
import java.net.URI;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.casadocodigo.loja.dao.CheckoutDao;
import org.casadocodigo.loja.models.Checkout;
import org.casadocodigo.loja.services.PaymentGateway;

@Path("/payment")
public class PaymentResource {

	// Essas threads são gerenciadas na mão, se usar o managedExecutor o
	// container cuida
	// private static ExecutorService executor =
	// Executors.newFixedThreadPool(50);

	private ServletContext context;
	private CheckoutDao checkoutDao;
	private PaymentGateway paymentGateway;

	// 8.9
	@Resource(name = "java:comp/DefaultManagedExecutorService")
	private ManagedExecutorService managedExecutorService;

	@Deprecated
	public PaymentResource() {
	}

	@Inject
	public PaymentResource(ServletContext context, CheckoutDao checkoutDao, PaymentGateway paymentGateway) {
		this.context = context;
		this.checkoutDao = checkoutDao;
		this.paymentGateway = paymentGateway;
	}

	@POST
	public void pay(@Suspended final AsyncResponse ar, @QueryParam("uuid") String uuid) {
		String contextPath = context.getContextPath();
		Checkout checkout = checkoutDao.findByUuid(uuid);

		// 8.9 sai o executor e usa managedExecutor
		// executor.submit(() -> {
		managedExecutorService.submit(() -> {
			BigDecimal total = checkout.getValue();

			try {
				paymentGateway.pay(total);

				URI redirectUri = UriBuilder.fromUri(contextPath + "/site/index.xhtml")
						.queryParam("msg", "Compra realizada com sucesso").build();

				Response response = Response.seeOther(redirectUri).build();

				ar.resume(response);

			} catch (Exception exception) {
				ar.resume(new WebApplicationException(exception));
				// ar.resume(exception);
			}

		});
	}

}
