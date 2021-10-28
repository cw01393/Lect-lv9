package model;

import java.util.ArrayList;
import java.util.Random;

public abstract class Unit {

	public static Random rn = new Random();
	
	private String name;
	private int maxHp;
	private int hp;
	private int power;
	private String state = "노말";
	
	public Unit() {}
	public Unit(String name, int hp, int power) {
		this.name = name;
		this.maxHp = hp;
		this.hp = hp;
		this.power = power;
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
	public int getPower() {
		return this.power;
	}
	public String getState() {
		return this.state;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public void allSet(int max, int power) {
		this.maxHp = max;
		this.hp = max;
		this.power = power;
	}
	
	public void printInfo() {
		System.out.printf("[%s] [%d/%d] [%d]",this.name,this.hp,this.maxHp,this.power);
		if(!this.state.equals("노말")) {
			System.out.printf(" [%s]",this.state);
		}
		System.out.println();
	}
	
	public void fight(Unit target) {
		int attack = rn.nextInt(this.power)+(this.power/2);
		target.hp -= attack;
		
		System.out.printf("[%s]가 [%s]에게 %d의 데미지를 입혔습니다!\n",this.name, target.name, attack);
		if(target.hp <= 0) {
			target.hp = 0;
			System.out.printf("[%s]가 [%s]를 처치하였습니다!\n",this.name,target.name);
		}
	}
	
	public abstract void skill(ArrayList<Unit> target);
}
