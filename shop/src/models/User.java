package models;

public class User {

	private String id;
	private String pw;
	private int totalSales;
	private Cart cart = new Cart(this.id);
	
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
	public Cart getCart() {
		return this.cart;
	}
	public void setTotalSales(int sales) {
		this.totalSales = sales;
	}
	
}
