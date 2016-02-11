package org.casadocodigo.loja.managedBeans;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.casadocodigo.loja.dao.BookDao;
import org.casadocodigo.loja.models.Book;

@Named
@RequestScoped
@Stateful
public class ProductDetailBean {

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager manager;
	
	private BookDao bookDao;
	private Book book;
	private Integer id;

	@Deprecated
	public ProductDetailBean() {
	}

	@Inject
	public ProductDetailBean(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	@PostConstruct
	private void loadManager(){
		this.bookDao = new BookDao(manager);
	}
	
//	@PostConstruct <- Usando f:viewAction no detalhe.xhtml
	public void loadBook(){
		this.book = bookDao.search(id);
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
