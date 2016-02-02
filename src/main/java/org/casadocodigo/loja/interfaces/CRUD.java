package org.casadocodigo.loja.interfaces;

import java.util.List;

public interface CRUD<T> {
	T search(Integer id);
	void save(T t);
	void remove(T t);
	T update(T t);
	List<T> list();
}
