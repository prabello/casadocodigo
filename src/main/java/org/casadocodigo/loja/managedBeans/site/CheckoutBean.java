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
import org.casadocodigo.loja.services.PaymentGateway;

@Named
@RequestScoped
@Path("payment")
public class CheckoutBean {

	private SystemUserDao systemUserDao;
	private ShoppingCart shoppingCart;
	private CheckoutDao checkoutDao;
	private PaymentGateway paymentGateway;
	private FacesContext facesContext;
	private SystemUser systemUser = new SystemUser();

	@Deprecated
	public CheckoutBean() {
	}

	@Inject
	public CheckoutBean(SystemUserDao systemUserDao, ShoppingCart shoppingCart, CheckoutDao checkoutDao,
			PaymentGateway paymentGateway, FacesContext facesContext) {
		this.systemUserDao = systemUserDao;
		this.shoppingCart = shoppingCart;
		this.checkoutDao = checkoutDao;
		this.paymentGateway = paymentGateway;
		this.facesContext = facesContext;
	}

	@Transactional
	public void checkout() {
		this.systemUserDao.save(this.systemUser);

		Checkout checkout = new Checkout(systemUser, shoppingCart);
		checkoutDao.save(checkout);

		//Assim era sincrono :(
//		paymentGateway.pay(shoppingCart.getTotal());
		
		String contextName = facesContext.getExternalContext().getContextName();
		
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		response.setStatus(307);
		response.setHeader("Location", "/"+contextName+"/services/payment?uuid="+checkout.getUuid());
		
	}

	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

}
