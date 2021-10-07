package controller;

import models.Account;
import models.Bank;

public class BankManager {
	
	private UserManager um = UserManager.instance;
	private AccountManager am = AccountManager.instance;
	private FileManager fm = FileManager.instance;
	
	public static BankManager instance = new BankManager();
	private BankManager() {}
	
	private boolean isRun = true;
	public void run() {
		while(this.isRun) {
			System.out.println(Bank.getName() + " ATM");
			printMenu();
			selectMenu();
		}
	}
	
	private void printMenu() {
		if(Bank.log == -1) {
			System.out.println("1.로그인\n2.회원가입\n3.관리자모드\n4.종료");
		}
		else if(Bank.log == -100){
			System.out.println("1.전체유저조회\n2.전체계좌조회\n3.저장\n4.로드\n5.로그아웃");
		}
		else {
			System.out.println("1.계좌개설\n2.계좌철회\n3.입금\n4.출금\n5.이체\n6.조회\n7.로그아웃\n8.회원탈퇴");
		}
	}
	
	private void selectMenu() {
		System.out.print("메뉴선택: ");
		String input = Bank.sc.next();
		
		try {
			int sel = Integer.parseInt(input);
			if(Bank.log == -1) {
				if(sel == 1) {
					Bank.log = um.login();
				}
				else if(sel == 2) {
					um.joinUser();
				}
				else if(sel == 3) {
					Bank.log = um.AdminLogin();
				}
				else if(sel == 4) {
					this.isRun = false;
				}
			}
			else if(Bank.log == -100){
				if(sel == 1) {
					um.printUsers();
				}
				else if(sel == 2) {
					um.printAccs();
				}
				else if(sel == 3) {
					fm.save();
				}
				else if(sel == 4) {
					fm.load();
				}
				else if(sel == 5) {
					Bank.log = um.logout();
				}
			}
			else {
				if(sel == 1) {
					am.addAcc();
				}
				else if(sel == 2) {
					am.closeAcc();
				}
				else if(sel == 3) {
					inputMoney();
				}
				else if(sel == 4) {
					withdraw();
				}
				else if(sel == 5) {
					transfer();
				}
				else if(sel == 6) {
					printInfo();
				}
				else if(sel == 7) {
					Bank.log = um.logout();
				}
				else if(sel == 8) {
					um.leaveUser();
				}
			}
			
		} catch (Exception e) {
			System.out.println("입력값을 확인하세요");
		}
	}
	
	private void inputMoney() {
		int accIdx = am.selectAcc();
		if(accIdx != -1) {
			System.out.print("입금할 금액 입력: ");
			String amount = Bank.sc.next();
			
			try {
				int money = Integer.parseInt(amount);
				
				if(money > 0) {
					money = money + um.getUser(Bank.log).getAcc(accIdx).getMoney();
					um.getUser(Bank.log).getAcc(accIdx).setMoney(money);
					System.out.println("입금완료!");
				}
			} catch (Exception e) {
				System.out.println("입력값을 확인해주세요");
			}
		}
	}
	private void withdraw() {
		int accIdx = am.selectAcc();
		if(accIdx != -1) {
			int nowMoney = um.getUser(Bank.log).getAcc(accIdx).getMoney();
			if(nowMoney > 0) {
				System.out.print("출금할 금액 입력: ");
				String amount = Bank.sc.next();
				
				try {
					int money = Integer.parseInt(amount);
					
					if(money > 0) {
						if(nowMoney >= money) {
							money = nowMoney-money;
							um.getUser(Bank.log).getAcc(accIdx).setMoney(money);
							System.out.println("출금완료!");
						}
						else {
							System.out.println("현금이 부족합니다");
						}
					}
					else {
						System.out.println("입력값을 확인해주세요");
					}
				} catch (Exception e) {
					System.out.println("입력값을 확인해주세요");
				}
			}
			else {
				System.out.println("현금이 부족합니다");
			}
		}
	}
	private void printInfo() {
		for(int i=0; i<um.getUser(Bank.log).getAccCnt(); i++) {
			Account acc = um.getUser(Bank.log).getAcc(i);
			System.out.printf("%d : %d원\n",acc.getAccNum(),acc.getMoney());
		}
	}
	private void transfer() {
		int accIdx = am.selectAcc();
		if(accIdx != -1) {
			int nowMoney = um.getUser(Bank.log).getAcc(accIdx).getMoney();
			if(nowMoney > 0) {
				System.out.print("이체할 계좌번호: ");
				String acc = Bank.sc.next();
				
				try {
					int accNum = Integer.parseInt(acc);
					
					Account receiver = searchIdx(accIdx, accNum);
					if(receiver != um.getUser(Bank.log).getAcc(accIdx)) {
						System.out.print("이체할 금액: ");
						String money = Bank.sc.next();
						int transMoney = Integer.parseInt(money);
						
						if(transMoney > 0  && nowMoney >= transMoney) {
							um.getUser(Bank.log).getAcc(accIdx).setMoney(nowMoney-transMoney);
							receiver.setMoney(receiver.getMoney() + transMoney);
							System.out.println("이체가 완료되었습니다");
						}
						else if(nowMoney < transMoney) {
							System.out.println("현금이 부족합니다");
						}
						else {
							System.out.println("입력값을 확인해주세요");
						}
					}
					else System.out.println("해당 계좌정보가 없습니다");
					
				} catch (Exception e) {
					System.out.println("입력값을 확인해주세요");
				}
			}
		}
	}
	private Account searchIdx(int myAcc, int accNum) {
		for(int i=0; i<um.getUserSize(); i++) {
			for(int j=0; j<um.getUser(i).getAccCnt(); j++) {
				if(accNum == um.getUser(i).getAcc(j).getAccNum()) {
					return um.getUser(i).getAcc(j);
				}
			}
		}
		
		return um.getUser(Bank.log).getAcc(myAcc);
	}
}
