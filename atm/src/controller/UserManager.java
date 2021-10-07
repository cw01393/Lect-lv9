package controller;

import java.util.ArrayList;
import java.util.Random;

import models.Bank;
import models.User;

public class UserManager {
	
	public static UserManager instance = new UserManager();
	private UserManager() {}
	
	// users: 중앙(총)데이터
	private ArrayList<User> users = new ArrayList<>();
	private String adminId = "admin";
	private String adminPw = "0000";
	
	public User getUser(int index) {
		return this.users.get(index);
	}
	public int getUserSize() {
		return this.users.size();
	}
	public void remove(int idx) {
		this.users.remove(idx);
	}
	public void add(User e) {
		this.users.add(e);
	}
	
	// 가입
	public void joinUser() {
		System.out.print("id: ");
		String id = Bank.sc.next();
		if(!checkIdDup(id)) {
			System.out.print("pw: ");
			String pw = Bank.sc.next();
			System.out.print("name: ");
			String name = Bank.sc.next();
			
			User newUser = new User(randomCode(), id, pw, name);
			this.users.add(newUser);
			System.out.println("회원가입 완료!");
		}
	}
	private boolean checkIdDup(String id) {
		for(int i=0; i<this.users.size(); i++) {
			if(this.users.get(i).getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	private int randomCode() {
		Random rn = new Random();
		
		//중복체크
		while(true) {
			int rCode = rn.nextInt(9000)+1000;
			
			boolean check = false;
			for(int i=0; i<this.users.size(); i++) {
				if(this.users.get(i).getUserCode() == rCode) {
					check = true;
				}
			}
			if(!check) {
				return rCode;
			}
		}
	}
	
	public void leaveUser() { // 탈퇴
		System.out.print("비밀번호 재입력: ");
		String pw = Bank.sc.next();
		
		if(pw.equals(this.users.get(Bank.log).getPw())) {
			this.users.remove(Bank.log);
			Bank.log = -1;
			System.out.println("회원탈퇴 완료");
		}
		else {
			System.out.println("비밀번호가 일치하지 않습니다");
		}
	}
	
	public int login() {
		System.out.print("id: ");
		String id = Bank.sc.next();
		System.out.print("pw: ");
		String pw = Bank.sc.next();
		
		for(int i=0; i<this.users.size(); i++) {
			if(this.users.get(i).getId().equals(id) && this.users.get(i).getPw().equals(pw)) {
				System.out.println("로그인 되었습니다");
				return i;
			}
		}
		System.out.println("로그인 정보를 다시 확인해주세요");
		return -1;
	}
	
	public int logout() {
		System.out.println("로그아웃 되었습니다");
		return -1;
	}
	
	public int AdminLogin() {
		System.out.print("id: ");
		String id = Bank.sc.next();
		System.out.print("pw: ");
		String pw = Bank.sc.next();
		
		if(this.adminId.equals(id) && this.adminPw.equals(pw)) {
			return -100;
		}
		return -1;
	}
	
	public void printUsers() {
		for(int i=0; i<this.users.size(); i++) {
			User user = this.users.get(i);
			System.out.printf("id: %s/%s(%d)\n",user.getId(),user.getName(),user.getUserCode());
		}
	}
	public void printAccs() {
		for(int i=0; i<this.users.size(); i++) {
			User user = this.users.get(i);
			System.out.printf("id: %s/%s(%d)\n",user.getId(),user.getName(),user.getUserCode());
			for(int j=0; j<this.users.get(i).getAccCnt(); j++) {
				System.out.printf("%d : %d원\n",user.getAcc(j).getAccNum(),user.getAcc(j).getMoney());
			}
		}
	}
}
