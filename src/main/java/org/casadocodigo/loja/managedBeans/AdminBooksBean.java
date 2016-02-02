package org.casadocodigo.loja.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.casadocodigo.loja.dao.AuthorDao;
import org.casadocodigo.loja.dao.BookDao;
import org.casadocodigo.loja.models.Author;
import org.casadocodigo.loja.models.Book;

//@Named
//@RequestScoped
@Model
public class AdminBooksBean {
	
	private Book product = new Book();
	private BookDao bookDao;
	private AuthorDao authorDao;
	
	private List<Author> authors = new ArrayList<>();
	private List<Integer> selectedAuthorsIds = new ArrayList<>();
	
	@Deprecated
	public AdminBooksBean() {
	}
	
	@PostConstruct
	private void loadObjects(){
		this.authors = authorDao.list();
	}
	
	@Inject
	public AdminBooksBean(BookDao bookDao,AuthorDao authorDao) {
		this.bookDao = bookDao;
		this.authorDao = authorDao;
	}
	
	@Transactional
	public void save(){
		populateBookAuthor();
		bookDao.save(product);
		clearObjects();
	}

	private void clearObjects() {
		this.product = new Book();
		this.selectedAuthorsIds.clear();
	}

	private void populateBookAuthor() {
		System.out.println(selectedAuthorsIds + "========");
		selectedAuthorsIds.stream().map( (id) -> {
			return new Author(id);
		}).forEach(product :: addAuthor);
	}
	
	public Book getProduct() {
		return product;
	}

	public List<Integer> getSelectedAuthorsIds() {
		return selectedAuthorsIds;
	}

	public void setSelectedAuthorsIds(List<Integer> selectedAuthorsIds) {
		this.selectedAuthorsIds = selectedAuthorsIds;
	}

	public List<Author> getAuthors() {
		return authors;
	}

}
