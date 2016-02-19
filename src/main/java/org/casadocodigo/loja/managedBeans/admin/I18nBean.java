package org.casadocodigo.loja.managedBeans.admin;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class I18nBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private FacesContext context;

	private Locale locale;

	@Deprecated
	public I18nBean() {
	}

	@Inject
	public I18nBean(FacesContext context) {
		this.context = context;
	}

	@PostConstruct
	private void loadDefaultLocale() {
		this.locale = context.getApplication().getDefaultLocale();
	}

	public String changeLocale(String language) {
		this.locale = new Locale(language);
		// App toda
		// context.getApplication().setDefaultLocale(new Locale(language));
		return "/site/index.xhtml?faces-redirect=true";
	}

	public Locale getLocale() {
		return locale;
	}
}
