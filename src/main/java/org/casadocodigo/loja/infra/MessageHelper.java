package org.casadocodigo.loja.infra;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class MessageHelper {

	@Inject
	private FacesContext context;
	
	public void addFlash(FacesMessage message){
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, message);
	}
}
