package controller;

import java.util.ArrayList;

import models.User;

public class UserManager {
	
	private ArrayList<User> users = new ArrayList<>();
	private String adminId = "admin";
	private String adminPw = "0000";
	
	public static UserManager instance = new UserManager();
	private UserManager() {}
	
	
	public User getUser(int index) {
		return this.users.get(index);
	}
	public int getUserSize() {
		return this.users.size();
	}
	
	public void join() {
		System.out.print("가입할 id: ");
		String id = Shop.sc.next();
		
		int dupCheck = idCheckIdx(id);
		if(dupCheck == -1) {
			System.out.print("pw: ");
			String pw = Shop.sc.next();
			
			this.users.add(new User(id,pw));
			System.out.println("회원가입 완료");
		}
		else {
			System.out.println("중복된 아이디입니다");
		}
	}
	
	private int idCheckIdx(String id) {
		for(int i=0; i<this.users.size(); i++) {
			if(this.users.get(i).getId().equals(id)) {
				return i;
			}
		}
		return -1;
	}
	
	public void leave() {
		System.out.print("비밀번호 재입력: ");
		String pw = Shop.sc.next();
		
		if(this.users.get(Shop.log).getPw().equals(pw)) {
			this.users.remove(Shop.log);
			Shop.log = -1;
			System.out.println("회원탈퇴 완료");
		}
	}
	
	public void deleteUser() {
		System.out.print("삭제할 유저ID: ");
		String id = Shop.sc.next();
		int delIdx = idCheckIdx(id);
		
		if(delIdx != -1) {
			while(true) {
				System.out.printf("%s 유저를 정말 삭제하시겠습니까? [1.YES][2.NO]\n",id);
				String answer = Shop.sc.next();
				
				if(answer.equals("1")) {
					this.users.remove(delIdx);
					System.out.println("유저 삭제 완료");
				}
				else if(answer.equals("2")) {
					break;
				}
			}
		}
		else {
			System.out.println("해당 유저가 존재하지 않습니다");
		}
	}
	
	public void logIn() {
		System.out.print("id: ");
		String id = Shop.sc.next();
		System.out.print("pw: ");
		String pw = Shop.sc.next();
		
		int idx = idCheckIdx(id);
		if(idx != -1) {
			if(this.users.get(idx).getPw().equals(pw)) {
				Shop.log = idx;
				System.out.println("로그인 되었습니다");
			}
			else {
				System.out.println("비밀번호가 일치하지 않습니다");
			}
		}
		else {
			System.out.println("존재하지 않는 아이디입니다");
		}
	}
	
	public void adminLogIn() {
		System.out.print("id: ");
		String id = Shop.sc.next();
		System.out.print("pw: ");
		String pw = Shop.sc.next();
		
		if(id.equals(this.adminId) && pw.equals(this.adminPw)) {
			Shop.log = -100;
			System.out.println("관리자 모드 로그인");
		}
		else {
			System.out.println("로그인 정보가 일치하지 않습니다");
		}
	}
	
	public void printAllUsers() {
		int n = 1;
		for(User user : this.users) {
			System.out.printf("[%d]" + user +"\n",n);
			n++;
		}
	}
}
