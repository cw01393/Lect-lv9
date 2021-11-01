package models;

import java.util.ArrayList;

import controller.Main;

public abstract class Unit {
	private String name;
	private String state = "노말";
	private int maxHp;
	private int hp;
	private int att;
	private int def;
	
	public Unit() {}
	public Unit(String name, int hp, int att, int def) {
		this.name = name;
		this.maxHp = hp;
		this.hp = hp;
		this.att = att;
		this.def = def;
	}
	
	public String getName() {
		return this.name;
	}
	public String getState() {
		return this.state;
	}
	public int getMaxHp() {
		return this.maxHp;
	}
	public int getHp() {
		return this.hp;
	}
	public int getAtt() {
		return this.att;
	}
	public int getDef() {
		return this.def;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public void setAtt(int att) {
		this.att = att;
	}
	public void setAll(int hp, int att, int def) {
		this.maxHp = hp;
		this.hp = hp;
		this.att = att;
		this.def = def;
	}
	
	public void attack(Unit enemy) {
		int power = Main.rn.nextInt(this.att) + this.att/2;
		int damage = power - enemy.def;
		if(damage < 0) damage = 0;
		
		enemy.hp -= damage;
		if(enemy.hp < 0) enemy.hp = 0;
		System.out.printf("[%s]가 [%s]에게 %d의 데미지를 입혔습니다\n",this.name,enemy.name,damage);
		if(enemy.hp == 0) {
			System.out.printf("[%s]가 [%s]를 처치하였습니다\n",this.name,enemy.name);
		}
	}
	
	public void printInfo() {
		System.out.printf("[%s] [%d/%d] [공:%d]",this.name,this.hp,this.maxHp,this.att);
		if(!this.state.equals("노말")) {
			System.out.printf(" [%s]",this.state);
		}
		System.out.println();
	}
	
	public abstract void skill(ArrayList<Unit> enemy);
	
}
