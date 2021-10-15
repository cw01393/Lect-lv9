package models;

import java.util.ArrayList;

public class Inventory {

	ArrayList<Item> inven = new ArrayList<Item>();
	
	public Item getInven(int index) {
		return this.inven.get(index);
	}
	
	public int getInvenSize() {
		return this.inven.size();
	}
	
	public void addInven(Item item) {
		this.inven.add(item);
	}
	
	public void removeInven(int index) {
		this.inven.remove(index);
	}
}
