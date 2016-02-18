package br.com.casadocodigo.loja.secutiry;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.casadocodigo.loja.dao.SecurityDao;
import org.casadocodigo.loja.models.SystemUser;
import java.io.Serializable;

//@Model
@SessionScoped
@Named
public class CurrentUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	public CurrentUser(HttpServletRequest request, SecurityDao securityDao) {
		this.request = request;
		this.securityDao = securityDao;
	}

	@Deprecated
	public CurrentUser() {
	}

	private HttpServletRequest request;
	private SecurityDao securityDao;
	private SystemUser systemUser;

	public boolean hasRole(String name){
		return this.request.isUserInRole(name);
	}
	
	public SystemUser getSystemUser() {
		return systemUser;
	}

	@PostConstruct
	private void loadSystemUser() {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			this.systemUser = securityDao.loadUserByUserName(principal.getName());
		}
	}
}
