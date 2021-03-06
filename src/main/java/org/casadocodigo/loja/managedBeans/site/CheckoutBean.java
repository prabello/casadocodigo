package org.casadocodigo.loja.managedBeans.site;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.ws.rs.Path;

import org.casadocodigo.loja.dao.CheckoutDao;
import org.casadocodigo.loja.dao.SystemUserDao;
import org.casadocodigo.loja.models.Checkout;
import org.casadocodigo.loja.models.ShoppingCart;
import org.casadocodigo.loja.models.SystemUser;

@Named
@RequestScoped
@Path("payment")
public class CheckoutBean {

	private SystemUserDao systemUserDao;
	private ShoppingCart shoppingCart;
	private CheckoutDao checkoutDao;
	// private PaymentGateway paymentGateway;
	private FacesContext facesContext;
	private SystemUser systemUser = new SystemUser();

	@Deprecated
	public CheckoutBean() {
	}

	@Inject
	public CheckoutBean(SystemUserDao systemUserDao, ShoppingCart shoppingCart, CheckoutDao checkoutDao,
			FacesContext facesContext) {
		this.systemUserDao = systemUserDao;
		this.shoppingCart = shoppingCart;
		this.checkoutDao = checkoutDao;
		// this.paymentGateway = paymentGateway;
		this.facesContext = facesContext;
	}

	@Transactional
	public void checkout() {
		this.systemUserDao.save(this.systemUser);

		Checkout checkout = new Checkout(systemUser, shoppingCart);
		checkoutDao.save(checkout);

		// Assim era sincrono :(
		// paymentGateway.pay(shoppingCart.getTotal());

		// http://home/pedro/Documents/Caelum/37/wildfly9/wildfly-9.0.1.Final/standalone/deployments/casadocodigo.war/services/payment?uuid=3245120a-95bd-4680-9459-cbeea014b88e
		// String contextName = context.getRealPath("/");

		// http://localhost:8080/null/services/payment?uuid=77f55477-d455-4f53-92aa-58677490eb1a
		String contextName = facesContext.getExternalContext().getRequestContextPath();

		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
//		response.setStatus(307);
		response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
//		response.setHeader("Location", "/casadocodigo/services/payment?uuid=" + checkout.getUuid());
		response.setHeader("Location", contextName + "/services/payment?uuid=" + checkout.getUuid());
	}

	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

}
