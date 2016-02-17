package org.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.casadocodigo.loja.interfaces.CRUD;
import org.casadocodigo.loja.models.Book;
import org.hibernate.jpa.QueryHints;

//@Stateful
public class BookDao implements CRUD<Book> {

	@PersistenceContext // (type=PersistenceContextType.EXTENDED)
	private EntityManager manager;

	@Deprecated
	public BookDao() {
	}

	public BookDao(EntityManager manager) {
		this.manager = manager;
	}

	public void save(Book product) {
		manager.persist(product);
	}

	public Book search(Integer id) {
		return this.manager.find(Book.class, id);
	}

	public void remove(Book t) {
		// TODO Auto-generated method stub

	}

	public List<Book> list() {
		TypedQuery<Book> query = manager.createQuery("select distinct(b) from Book b join fetch b.authors", Book.class);
		// query.setHint(QueryHints.HINT_CACHEABLE, true);

		return query.getResultList();
	}

	public Book update(Book t) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Book> lastReleases() {
		// ESSA QUERY TEM QUE SER <= now(), PARA QUE MOSTRE OS ULTIMOS LIVROS
		// LANÇADOS, MUDEI PARA >= para mostrar mais resultados
		TypedQuery<Book> query = this.manager
				.createQuery("select b from Book b where b.releaseDate >= now() order by b.id desc", Book.class)
				.setMaxResults(3);

		query.setHint(QueryHints.HINT_CACHEABLE, true);

		return query.getResultList();
	}

	// public List<Book> last(int number) {
	// TypedQuery<Book> query = this.manager.createQuery("select b from Book b
	// join fetch b.authors", Book.class)
	// .setMaxResults(number);
	// query.setHint(QueryHints.HINT_CACHEABLE, true);
	// return query.getResultList();
	// }

	public List<Book> olderBooks() {
		TypedQuery<Book> query = this.manager.createQuery("select b from Book b", Book.class).setMaxResults(20);

		query.setHint(QueryHints.HINT_CACHEABLE, true);
		return query.getResultList();
	}

}
