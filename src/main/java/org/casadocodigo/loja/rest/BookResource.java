package org.casadocodigo.loja.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.casadocodigo.loja.dao.BookDao;
import org.casadocodigo.loja.models.Book;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

//@RequestScoped
@Path("/books")
@Stateful
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
// @Produces("application/json")
// @Consumes("application/json")
public class BookResource {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	private BookDao bookDao;

	@PostConstruct
	private void loadDao() {
		this.bookDao = new BookDao(entityManager);
	}

//	@GET
//	@Produces({ MediaType.APPLICATION_XML })
//	@Path("xml")
//	@Wrapped(element = "books")
//	public List<Book> lastBooks() {
//		return bookDao.lastReleases();
//	}

	@GET
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Path("json")
	@Wrapped(element = "books")
	public List<Book> lastBooksJson() {
		return bookDao.lastReleases();
	}
}
