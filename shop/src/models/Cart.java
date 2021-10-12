package models;

public class Cart {

	private String id;
	private String itemName;
	private int price;
	private int count;
	
	public Cart(String id, String itemName, int price) {
		this.id = id;
		this.itemName = itemName;
		this.price = price;
		this.count = 1;
	}
	
	public String getid() {
		return this.id;
	}
	public String getItemName() {
		return this.itemName;
	}
	public int getCount() {
		return this.count;
	}
	public int getPrice() {
		return this.price;
	}
	public void setCount(int count) {
		this.count = count;
	}
	

}
