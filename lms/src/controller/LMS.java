package controller;

import java.util.Scanner;

public class LMS {

	public static Scanner sc = new Scanner(System.in);
	
	private static LMS instance = new LMS();
	private LMS() {}
	public static LMS getInstance() {
		return instance;
	}
	
	private StudentManager stm = StudentManager.getInstance();
	private SubjectManager sbm = SubjectManager.getInstance();
	
	public void run() {
		setManager();
		while(true) {
			startMenu();
		}
	}
	
	private void setManager() {
		stm.setProfessor();
		stm.setSubject();
	}
	
	private void startMenu() {
		System.out.println("[1]로그인 [2]회원가입");
	}
	
}
