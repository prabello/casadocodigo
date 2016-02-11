package org.casadocodigo.loja.managedBeans.site;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.casadocodigo.loja.dao.BookDao;
import org.casadocodigo.loja.models.Book;
import org.casadocodigo.loja.models.ShoppingCart;
import org.casadocodigo.loja.models.ShoppingItem;

@Model
public class ShoppingCartBean {

	private ShoppingCart shoppingCart;
	private BookDao bookDao;
	
	@Inject
	public ShoppingCartBean(ShoppingCart shoppingCart, BookDao bookDao) {
		this.shoppingCart = shoppingCart;
		this.bookDao = bookDao;
	}
	@Deprecated
	public ShoppingCartBean() {
	}
	
	public String add(Integer id){
		Book book = bookDao.search(id);
		ShoppingItem shoppingItem = new ShoppingItem(book);
		shoppingCart.add(shoppingItem);
		return "/site/carrinho?faces-redirect=true";
	}
	
	public String remove(Integer id){
		Book book = bookDao.search(id);
		ShoppingItem shoppingItem = new ShoppingItem(book);
		shoppingCart.remove(shoppingItem);
		return "/site/carrinho?faces-redirect=true";
	}

}
