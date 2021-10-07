package controller;

import java.util.Random;

import models.Account;
import models.Bank;
import models.User;

public class AccountManager {
	
	private UserManager um = UserManager.instance;

	public static AccountManager instance = new AccountManager();
	private AccountManager() {}
	
	public void addAcc() {
		User nowUser = um.getUser(Bank.log);
		if(nowUser.getAccCnt() < 3) {
			int accNum = newAccNum();
			Account acc = new Account(accNum);
			nowUser.addAcc(acc);
			nowUser.setAccCnt(nowUser.getAccCnt()+1);
			System.out.println("신규 개좌개설 완료!\n신규계좌번호: " + accNum);
		}
		else System.out.println("개설 가능한 계좌의 수를 초과하였습니다");
	}
	private int newAccNum() {
		Random rn = new Random();
		
		while(true) {
			int rNum = rn.nextInt(9000)+1000;
			
			boolean check = false;
			for(int i=0; i<um.getUserSize(); i++) {
				for(int j=0; j<um.getUser(i).getAccCnt(); j++) {
					if(rNum == um.getUser(i).getAcc(j).getAccNum()) {
						check = true;
					}
				}
			}
			if(!check) return rNum;
		}
	}
	
	public void closeAcc() {
		User nowUser = um.getUser(Bank.log);
		int sel = selectAcc();
		
		if(sel != -1) {
			nowUser.removeAcc(sel);
			System.out.println("계좌해지 완료");
			nowUser.setAccCnt(nowUser.getAccCnt()-1);
		}
	}
	
	public int selectAcc() {
		User nowUser = um.getUser(Bank.log);
		
		if(nowUser.getAccCnt() > 0) {
			for(int i=0; i<nowUser.getAccCnt(); i++) {
				System.out.println(i+1 + ")" + nowUser.getAcc(i).getAccNum());
			}
			System.out.print("계좌선택: ");
			String select = Bank.sc.next();
			
			try {
				int sel = Integer.parseInt(select)-1;
				if(sel >= 0 && sel <nowUser.getAccCnt()) {
					return sel;
				}
			} catch (Exception e) {
				System.out.println("입력값을 확인해주세요");
			}
		}
		return -1;
	}
}
