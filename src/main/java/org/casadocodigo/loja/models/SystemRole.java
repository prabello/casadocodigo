package org.casadocodigo.loja.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SystemRole {

	@Id
	private String name;

	@Deprecated
	public SystemRole() {
	}

	public SystemRole(String name) {
		this.name = name;
	}

}
