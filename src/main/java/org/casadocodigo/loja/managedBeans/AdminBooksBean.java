package org.casadocodigo.loja.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import org.casadocodigo.loja.dao.AuthorDao;
import org.casadocodigo.loja.dao.BookDao;
import org.casadocodigo.loja.infra.FileSaver;
import org.casadocodigo.loja.infra.MessageHelper;
import org.casadocodigo.loja.models.Author;
import org.casadocodigo.loja.models.Book;

//@Named
//@RequestScoped
@Model
//@Stateful
public class AdminBooksBean {

	private Book product = new Book();
	private BookDao bookDao;
	private AuthorDao authorDao;
	@Inject
	private MessageHelper messageHelper;

	private Part summary;
	@Inject
	private FileSaver fileSaver;

	private List<Author> authors = new ArrayList<>();
	private List<Integer> selectedAuthorsIds = new ArrayList<>();

	@Deprecated
	public AdminBooksBean() {
	}

	@PostConstruct
	private void loadObjects() {
		this.authors = authorDao.list();
	}

	@Inject
	public AdminBooksBean(BookDao bookDao, AuthorDao authorDao) {
		this.bookDao = bookDao;
		this.authorDao = authorDao;
	}

	@Transactional
	public String save() {
		String summaryPath = fileSaver.write("summaries",summary);
		product.setSummaryPath(summaryPath);
		// populateBookAuthor();
		bookDao.save(product);
		// clearObjects();

		messageHelper.addFlash(new FacesMessage("Livro " + product.getTitle() + " gravado com sucesso"));

		return "/livros/lista?faces-redirect=true";
	}

	private void clearObjects() {
		this.product = new Book();
		this.selectedAuthorsIds.clear();
	}

	private void populateBookAuthor() {
		System.out.println(selectedAuthorsIds + "========");
		selectedAuthorsIds.stream().map((id) -> {
			return new Author(id);
		}).forEach(product::addAuthor);
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

	public Part getSummary() {
		return summary;
	}

	public void setSummary(Part summary) {
		this.summary = summary;
	}

}
