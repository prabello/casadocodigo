package org.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 492727913358260628L;
	
	private Map<ShoppingItem, Integer> items = new LinkedHashMap<>();

	public void add(ShoppingItem item) {
		items.put(item, getQuantity(item) + 1);
	}

	private int getQuantity(ShoppingItem item) {
		if (!items.containsKey(item)) {
			items.put(item, 0);
		}
		return items.get(item);
	}
	
	public Integer getQuantity(){
		return items.values().stream().reduce(0, (next,accumulator) -> next + accumulator);
	}
	
	public Collection<ShoppingItem> getList(){
		return new ArrayList<>(items.keySet());
	}
	
	public BigDecimal getTotal(ShoppingItem item){
		return item.getTotal(getQuantity(item));
	}
	
	public BigDecimal getTotal(){
		BigDecimal total = BigDecimal.ZERO;
		
		for (ShoppingItem item : items.keySet()) {
			total = total.add(getTotal(item));
		}
		
		return total;
	}
	
	public void remove(ShoppingItem shoppingItem){
		items.remove(shoppingItem);
	}
	
	public boolean isEmpty(){
		return items.isEmpty();
	}

}