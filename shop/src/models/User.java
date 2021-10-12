package models;

import java.util.ArrayList;

public class User {

	private String id;
	private String pw;
	private int totalSales;
	private ArrayList<Cart> cart = new ArrayList<>();
	
	public User(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
	
	public String getId() {
		return this.id;
	}
	public String getPw() {
		return this.pw;
	}
	public int getTotalSales() {
		return this.totalSales;
	}
	public Cart getCart(int index) {
		return this.cart.get(index);
	}
	public void addCart(Item item) {
		this.cart.add(item);
	}
	public void removeCart(int index) {
		this.cart.remove(index);
	}
	public void setTotalSales(int sales) {
		this.totalSales = sales;
	}
	
}
