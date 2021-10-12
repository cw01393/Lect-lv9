package models;

import java.util.ArrayList;

public class Cart {

	private String id;
	private ArrayList<Item> cart = new ArrayList<>();
	
	public Cart(String id) {
		this.id = id;
	}
	
	public String getid() {
		return this.id;
	}
	
	public Item getCartItem(int index) {
		return this.cart.get(index);
	}
	public void addCart(Item item) {
		this.cart.add(item);
	}
	public void removeCart(int index) {
		this.cart.remove(index);
	}
}
