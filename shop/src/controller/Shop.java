package controller;

import java.util.Scanner;

public class Shop {

	static Scanner sc = new Scanner(System.in);
	
	public static int log = -1;
	private boolean isRun = true;
	
	private UserManager um = UserManager.instance;
	private ItemManager im = ItemManager.instance;
	
	public void run() {
		setShop();
		while(this.isRun) {
			printMenu();
			selectMenu();
		}
	}
	
	private void printMenu() {
		if(Shop.log == -1) {
			System.out.println("1.가입\n2.로그인\n100.관리자모드\n0.종료");
		}
		else if(Shop.log == -100){
			System.out.println("1.아이템관리\n2.카테고리관리\n3.장바구니관리\n4.유저관리\n0.뒤로가기");
		}
		else {
			System.out.println("1.쇼핑\n2.장바구니목록\n3.로그아웃\n4.회원탈퇴");
		}
	}
	
	private void selectMenu() {
		System.out.print("메뉴선택: ");
		String sel = sc.next();
	
		try {
			int menu = Integer.parseInt(sel);
			
			if(Shop.log == -1) {
				if(menu == 1) {
					um.join();
				}
				else if(menu == 2) {
					um.logIn();
				}
				else if(menu == 100) {
					um.adminLogIn();
				}
				else if(menu == 0) {
					programOff();
				}
			}
			else if(Shop.log == -100){
				if(menu == 1) {
					printItemManager();
				}
				else if(menu == 2) {
					printCateManager();
				}
				else if(menu == 3) {}
				else if(menu == 4) {}
				else if(menu == 0) {
					Shop.log = -1;
				}
			}
			else {
				if(menu == 1) {
					im.shopping();
				}
				else if(menu == 2) {
					im.printCartMenu();
				}
				else if(menu == 3) {
					Shop.log = -1;
				}
				else if(menu == 4) {
					um.leave();
				}
			}
			
		} catch (Exception e) {
			System.out.println("매뉴를 정확히 입력하세요");
		}
	}
	
	private void programOff() {
		this.isRun = false;
	}
	
	private void setShop() {
		im.setCate();
		im.setItems();
	}
	
	private void printItemManager() {
		while(true) {
			System.out.println("1.전체아이템\n2.아이템추가\n3.아이템삭제\n0.뒤로가기");
			System.out.print("메뉴선택: ");
			String sel = Shop.sc.next();
			
			if(sel.equals("1")) {
				im.printAllItems();
			}
			else if(sel.equals("2")) {
				im.addItem();
			}
			else if(sel.equals("3")) {
				im.removeItem();
			}
			else if(sel.equals("0")) {
				break;
			}
		}
	}
	private void printCateManager() {
		while(true) {
			System.out.println("1.전체카테고리\n2.카테고리추가\n3.카테고리삭제\n0.뒤로가기");
			System.out.print("메뉴선택: ");
			String sel = Shop.sc.next();
			
			if(sel.equals("1")) {
				im.printCate();
			}
			else if(sel.equals("2")) {
				im.addCategory();
			}
			else if(sel.equals("3")) {
				im.removeCate();
			}
			else if(sel.equals("0")) {
				break;
			}
		}
	}
}
