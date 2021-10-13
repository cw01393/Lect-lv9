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
	public User(String id, String pw, int totalSales) {
		this.id = id;
		this.pw = pw;
		this.totalSales = totalSales;
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
	public int getCartSize() {
		return this.cart.size();
	}
	public Cart getCart(int index) {
		return this.cart.get(index);
	}
	public void addCart(Cart item) {
		this.cart.add(item);
	}
	public void removeCart(int index) {
		this.cart.remove(index);
	}
	public void setTotalSales(int sales) {
		this.totalSales = sales;
	}
	public void setNewCart() {
		this.cart = new ArrayList<Cart>();
	}
	@Override
	public String toString() {
		return "[Id:" + id + "][구매금액: " + this.totalSales + "]";
	}
	
	
}
