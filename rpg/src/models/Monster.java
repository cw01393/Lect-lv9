package models;

public class Monster {

	private String name;
	private int hp;
	private int maxHp;
	private int att;
	private int def;
	
	public Monster(String name, int maxHp, int att, int def) {
		this.name = name;
		this.hp = maxHp;
		this.maxHp = maxHp;
		this.att = att;
		this.def = def;
	}
	
	public String getName() {
		return this.name;
	}
	public int getHp() {
		return this.hp;
	}
	public int getMaxHp() {
		return this.maxHp;
	}
	public int getAtt() {
		return this.att;
	}
	public int getDef() {
		return this.def;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
}
