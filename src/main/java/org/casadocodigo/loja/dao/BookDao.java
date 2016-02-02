package org.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.casadocodigo.loja.interfaces.CRUD;
import org.casadocodigo.loja.models.Book;

public class BookDao implements CRUD<Book> {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void save(Book product) {
		manager.persist(product);
	}

	@Override
	public Book search(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Book t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Book> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book update(Book t) {
		// TODO Auto-generated method stub
		return null;
	}

}
