package model;

public class User {

	private String name;
	private String pw;
	private int code;
	private String major;
	
	public User(String name, String pw, int code, String major) {
		this.name = name;
		this.pw = pw;
		this.code = code;
		this.major = major;
	}
	
	public String getName() {
		return this.name;
	}
	public int getCode() {
		return this.code;
	}
	public String getMajor() {
		return this.major;
	}
	
	public void print() {
		System.out.printf("[�̸�][%s] [�й�][%d] [����][%s]\n",this.name, this.code, this.major);
	}
}
