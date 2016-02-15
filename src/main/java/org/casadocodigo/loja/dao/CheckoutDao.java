package org.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.casadocodigo.loja.interfaces.CRUD;
import org.casadocodigo.loja.models.Checkout;

public class CheckoutDao implements CRUD<Checkout> {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Checkout search(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Checkout t) {
		manager.persist(t);
	}

	@Override
	public void remove(Checkout t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Checkout update(Checkout t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Checkout> list() {
		// TODO Auto-generated method stub
		return null;
	}

	public Checkout findByUuid(String uuid) {
		return this.manager.createQuery("select c from Checkout c where c.uuid = :pUuid", Checkout.class)
				.setParameter("pUuid", uuid).getSingleResult();
		// query.setParameter("pUuid", uuid);
		// return query.getSingleResult();
	}

}
