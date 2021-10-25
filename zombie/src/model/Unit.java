package model;

import java.util.Random;

public class Unit implements Attackable{

	public static Random rn = new Random();
	
	private String name;
	private int maxHp;
	private int hp;
	private int att;
	private int def;
	private int pos;
	
	public Unit(String name, int maxHp, int att, int def, int pos) {
		this.name = name;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.att = att;
		this.def = def;
		this.pos = pos;
	}
	
	public String getName() {
		return this.name;
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
	public int getPos() {
		return this.pos;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public void setAtt(int att) {
		this.att = att;
	}
	public void setDef(int def) {
		this.def = def;
	}
	
	public void attack(Unit target) {
		int damage = (this.att-target.def)*(rn.nextInt(150)+50)/100;
		if(damage < 0) damage = 0;
		System.out.printf("[%s]의 공격\n",this.name);
		System.out.printf("[%d]의 데미지를 입혔습니다!]\n",damage);
		target.setHp(target.getHp()-damage);
		System.out.printf("[%s]의 남은 [HP: %d]\n",target.name, target.hp);
	}
	
	public void print() {
		System.out.println("[이름] " + this.name + " [HP] " + this.hp);
		System.out.println("[공격력] " + this.att + " [방어력] " + this.def + " [위치] " + this.pos);
	}
	
}
