package org.casadocodigo.loja.managedBeans;

import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.casadocodigo.loja.dao.BookDao;
import org.casadocodigo.loja.models.Book;

@Model
//@Stateful
public class HomeBean {

	private final BookDao bookDao;

	@Deprecated
	public HomeBean() {
		this(null);
	}

	@Inject
	public HomeBean(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	
	public List<Book> lastReleases(){
		return bookDao.lastReleases();
	}
	
	public List<Book> olderBooks(){
		return bookDao.olderBooks();
	}
}
