package controller;

import java.util.ArrayList;

import models.User;

public class UserManager {
	
	private ArrayList<User> users = new ArrayList<>();
	
	public static UserManager instance = new UserManager();
	private UserManager() {}
		
	public void join() {
		System.out.print("가입할 id: ");
		String id = Shop.sc.next();
		
		if(!idDupCheck(id)) {
			System.out.print("pw: ");
			String pw = Shop.sc.next();
			
			this.users.add(new User(id,pw));
			System.out.println("회원가입 완료");
		}
		else {
			System.out.println("중복된 아이디입니다");
		}
	}
	
	private boolean idDupCheck(String id) {
		for(User user : this.users) {
			if(user.getId().equals(id)) return true;
		}
		return false;
	}
	
	public void leave() {
		System.out.print("비밀번호 재입력: ");
		String pw = Shop.sc.next();
		
		if(this.users.get(Shop.log).getPw().equals(pw)) {
			this.users.remove(Shop.log);
			Shop.log = -1;
		}
	}
}
