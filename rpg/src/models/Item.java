package models;

public class Item {

	private String kind;
	private String name;
	private int ability;
	private int price;
	
	public Item(String kind, String name, int ability, int price) {
		this.kind = kind;
		this.name = name;
		this.ability = ability;
		this.price = price;
	}
	
	public String getKind() {
		return this.kind;
	}
	public String getName() {
		return this.name;
	}
	public int getAbility() {
		return this.ability;
	}
	public int getPrice() {
		return this.price;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += "[" + this.name + "]";
		str += "[능력: " + this.ability + "]";
		str += "[가격: " + this.price + "]";
		return str;
	}
}
