package models;

public class Account {
	private int accNum;
	private int money;
	
	public Account(int accNum) {
		this.accNum = accNum;
	}
	public Account(int accNum, int money) {
		this.accNum = accNum;
		this.money = money;
	}
	
	public int getAccNum() {
		return this.accNum;
	}
	public int getMoney() {
		return this.money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
}
