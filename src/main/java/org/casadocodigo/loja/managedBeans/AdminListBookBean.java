package org.casadocodigo.loja.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.casadocodigo.loja.dao.BookDao;
import org.casadocodigo.loja.models.Book;

@Model
public class AdminListBookBean {
	private BookDao bookDao;
	
	private List<Book> books = new ArrayList<>();
	
	@Deprecated
	public AdminListBookBean() {
	}

	@Inject
	public AdminListBookBean(BookDao bookDao) {
		this.bookDao = bookDao;
		this.books = bookDao.list();
	}

	public List<Book> getBooks() {
		return books;
	}
	
	
	
}
