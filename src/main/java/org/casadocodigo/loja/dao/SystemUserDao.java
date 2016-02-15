package org.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.casadocodigo.loja.interfaces.CRUD;
import org.casadocodigo.loja.models.SystemUser;

public class SystemUserDao implements CRUD<SystemUser> {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public SystemUser search(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(SystemUser t) {
		manager.persist(t);
	}

	@Override
	public void remove(SystemUser t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SystemUser update(SystemUser t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SystemUser> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
