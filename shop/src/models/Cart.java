package models;

public class Cart {

	private String id;
	private String itemName;
	private int count;
	
	public Cart(String id, String itemName) {
		this.id = id;
		this.itemName = itemName;
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
	

}
