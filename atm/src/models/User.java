package models;

import java.util.ArrayList;

public class User {
	
	private int userCode;
	private String id;
	private String pw;
	private String name;
	private int accCnt;
	
	// 보유계좌의 객체주소 배열
	private ArrayList<Account> acc = new ArrayList<>();
	
	public User(int userCode, String id, String pw, String name) {
		this.userCode = userCode;
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
	
	public int getUserCode() {
		return this.userCode;
	}
	public String getId() {
		return this.id;
	}
	public String getPw() {
		return this.pw;
	}
	public String getName() {
		return this.name;
	}
	public int getAccCnt() {
		return this.accCnt;
	}
	public void setAccCnt(int cnt) {
		this.accCnt = cnt;
	}
	public Account getAcc(int index) {
		return this.acc.get(index);
	}
	public void addAcc(Account e) {
		this.acc.add(e);
	}
	public void removeAcc(int index) {
		this.acc.remove(index);
	}
	
	
}
