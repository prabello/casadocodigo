package org.casadocodigo.loja.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.casadocodigo.loja.interfaces.CRUD;
import org.casadocodigo.loja.models.Book;

//@Stateful
public class BookDao implements CRUD<Book> {

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager manager;
	
	@Deprecated
	public BookDao() {
	}
	
	public BookDao(EntityManager manager){
		this.manager = manager;
	}

	@Override
	public void save(Book product) {
		manager.persist(product);
	}

	@Override
	public Book search(Integer id) {
		return this.manager.find(Book.class, id);
	}

	@Override
	public void remove(Book t) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Book> list() {
		return manager.createQuery("select distinct(b) from Book b join fetch b.authors", Book.class).getResultList();
	}

	@Override
	public Book update(Book t) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Book> lastReleases() {
		return this.manager
				.createQuery("select b from Book b where b.releaseDate <= now() order by b.id desc", Book.class)
				.setMaxResults(3).getResultList();
	}

	public List<Book> olderBooks() {
		return this.manager.createQuery("select b from Book b", Book.class).setMaxResults(20).getResultList();
	}

}
