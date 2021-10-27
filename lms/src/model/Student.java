package model;

import java.util.ArrayList;

public class Student extends User{
	
	private ArrayList<Subject> subs = new ArrayList<Subject>();
	
	public Student(String name, String pw, int code, String major) {
		super(name, pw, code, major);
	}
	
	public Subject getSub(int index) {
		return this.subs.get(index);
	}
	public int getSubSize() {
		return this.subs.size();
	}
	
	@Override
	public void print() {
		if(this.subs == null) {
			super.print();
		}
		else {
			super.print();
			int n = 1;
			for(Subject s : this.subs) {
				System.out.printf("ㄴ[%d][%s][%d점]\n",n,s.getTitle(),s.getScore());
				n++;
			}
		}
	}
	
}
