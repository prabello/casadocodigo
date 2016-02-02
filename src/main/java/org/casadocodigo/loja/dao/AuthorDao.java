package org.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.casadocodigo.loja.interfaces.CRUD;
import org.casadocodigo.loja.models.Author;

public class AuthorDao implements CRUD<Author> {

	@PersistenceContext
	private EntityManager manager;

	@Deprecated
	public AuthorDao() {
	}

	public AuthorDao(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public Author search(Integer id) {
		return null;
	}

	@Override
	public void save(Author t) {

	}

	@Override
	public void remove(Author t) {

	}

	@Override
	public List<Author> list() {
		return manager.createQuery("select a from Author a", Author.class).getResultList();
	}

	@Override
	public Author update(Author t) {
		// TODO Auto-generated method stub
		return null;
	}

}
