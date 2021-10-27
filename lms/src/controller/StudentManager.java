package controller;

import java.util.ArrayList;
import java.util.Random;

import model.Professor;
import model.Student;
import model.User;

public class StudentManager {

//	private ArrayList<Professor> pro = new ArrayList<>();
//	private ArrayList<Student> group = new ArrayList<>();
	private ArrayList<User> group = new ArrayList<>();
	private ArrayList<String> majors = new ArrayList<>();
	
	private static StudentManager instance = new StudentManager();
	private StudentManager() {}
	public static StudentManager getInstance() {
		return instance;
	}
	
	public void setSubject() {
		for(User u : this.group) {
			if(u instanceof Professor) {
				addSubject(u);
			}
		}
	}
	
	private void addSubject(User u) {
		boolean check = false;
		for(int i=0; i<this.majors.size(); i++) {
			if(u.getMajor().equals(this.majors.get(i))) {
				check = true;
			}
		}
		if(!check) this.majors.add(u.getMajor());
	}
	
	public void setProfessor() {
		this.group.add(new Professor("박민규","0000",randomCode(),"경영"));
		this.group.add(new Professor("최정한","0000",randomCode(),"기계공학"));
		this.group.add(new Professor("주지훈","0000",randomCode(),"국어국문"));
	}
	
	private int randomCode() {
		Random rn = new Random();
		while(true) {
			boolean check = false;
			int num = rn.nextInt(9000)+1000;
			for(User s : this.group) {
				if(s.getCode() == num) {
					check = true;
				}
			}
			if(!check) {
				return num;
			}
		}
	}
}
