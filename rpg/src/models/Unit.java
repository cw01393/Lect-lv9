package models;

public class Unit {
	String name;
	int level;
	int hp;
	int maxHp;
	int att;
	int def;
	int exp;
	boolean party;
	Item weapon;
	Item armor;
	Item ring;
	Item potion;
	
	public Unit(String name, int level, int maxHp, 
			int att, int def, int exp) {
		this.name = name;
		this.level = level;
		this.hp = maxHp;
		this.maxHp = maxHp;
		this.att = att;
		this.def = def;
		this.exp = exp;
	}
	public Unit(String name, int level, int hp, int maxHp, 
			int att, int def, int exp, boolean party) {
		this.name = name;
		this.level = level;
		this.hp = hp;
		this.maxHp = maxHp;
		this.att = att;
		this.def = def;
		this.exp = exp;
		this.party = party;
	}
	
	public String getName() {
		return this.name;
	}
	public boolean getParty() {
		return this.party;
	}
	public int getLevel() {
		return this.level;
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
	public int getExp() {
		return this.exp;
	}
	public Item getWeapon() {
		return this.weapon;
	}
	public Item getArmor() {
		return this.armor;
	}
	public Item getRing() {
		return this.ring;
	}
	public Item getPotion() {
		return this.potion;
	}
	
	public void setExp(int exp) {
		this.exp = exp;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public void setParty(boolean p) {
		this.party = p;
	}
	public void setWeaponItem(Item weapon) {
		this.weapon = weapon;
	}
	public void setArmorItem(Item armor) {
		this.armor = armor;
	}
	public void setRingItem(Item ring) {
		this.ring = ring;
	}
	public void setPotionItem(Item potion) {
		this.potion = potion;
	}
//	public void setAllItem(Item weapon, Item armor, Item ring) {
//		this.weapon = weapon;
//		this.armor = armor;
//		this.ring = ring;
//	}
	
	public void printStatue() {
		System.out.printf("[%s][LEVEL:%d(%d)]",this.name, this.level,this.exp);
		if(this.ring == null) {
			System.out.printf("[HP: %d/%d]\n", this.hp, this.maxHp);
		}
		else {
			System.out.printf("[HP: %d/%d+%d]\n", this.hp, this.maxHp, this.ring.getAbility());
		}
		if(this.weapon == null) {
			System.out.printf("[?????????:%d]",this.att);
		}
		else {
			System.out.printf("[?????????:%d+%d]",this.att, this.weapon.getAbility());
		}
		if(this.armor == null) {
			System.out.printf("[?????????:%d]",this.def);
		}
		else {
			System.out.printf("[?????????:%d+%d]",this.def, this.armor.getAbility());
		}
		System.out.printf("[????????????:%b]\n\n",this.party);
	}
	
	public void printItemStatue() {
		if (this.weapon == null) {
			System.out.println("[?????? : ?????? ]");
		}
		else {
			System.out.println("[?????? : " + this.weapon.getName() + "]");
		}
		if (this.armor == null) {
			System.out.println("[????????? : ?????? ]");
		}
		else {
			System.out.println("[????????? : " + this.armor.getName() + "]");
		}
		if (this.ring == null) {
			System.out.println("[?????? : ?????? ]");
		}
		else {
			System.out.println("[?????? : " + this.ring.getName()+ "]");
		}		
		if (this.potion == null) {
			System.out.println("[?????? : ?????? ]");
		}
		else {
			System.out.println("[?????? : " + this.potion.getName()+ "]");
		}		
	}
}
