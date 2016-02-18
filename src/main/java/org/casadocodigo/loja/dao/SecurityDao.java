package org.casadocodigo.loja.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.casadocodigo.loja.models.SystemUser;

public class SecurityDao implements Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager manager;

	public SystemUser loadUserByUserName(String name) {
		return manager.createQuery("select u from SystemUser u where u.email = :login", SystemUser.class)
				.setParameter("login", name).getSingleResult();
	}

}
