package controller;

import models.Player;

public class Game {

	private boolean isRun = true;
	private GuildManager gm = GuildManager.instance;
	private Shop shop = Shop.instance;
	private InventoryManager im = InventoryManager.instance;
	private FileManager fm = FileManager.instance;
	
	public void run() {
		setGame();
		while(this.isRun) {
			printMainMenu();
			selectMenu();
		}
	}
	
	private void setGame() {
		Player.money = 100000;
		gm.setGuild();
		shop.setShop();
		
	}
	
	private void printMainMenu() {
		System.out.println("==========[메인메뉴]==========");
		System.out.println("[1.길드관리][2.상점][3.인벤토리]");
		System.out.println("[4.전투][5.저장][6.로드][0.종료]");
	}
	
	private void selectMenu() {
		System.out.print("메뉴선택: ");
		String sel = Player.sc.next();
		
		if(sel.equals("1")) {
			guildMenu();
		}
		else if(sel.equals("2")) {
			shopMenu();
		}
		else if(sel.equals("3")) {
			inventoryMenu();
		}
		else if(sel.equals("4")) {
			gm.battle();
		}
		else if(sel.equals("5")) {
			fm.save();
		}
		else if(sel.equals("6")) {
			fm.load();
		}
		else if(sel.equals("0")) {
			this.isRun = false;
		}
	}
	
	private void guildMenu() {
		while(true) {
			System.out.println("==========[길드관리]==========");
			System.out.println("[1.길드목록][2.길드원추가][3.길드원삭제]");
			System.out.println("[4.파티원교체][5.정렬][0.뒤로가기]");
			System.out.print("메뉴선택: ");
			String sel = Player.sc.next();
			
			if(sel.equals("1")) {
				gm.printAllGuild();
			}
			else if(sel.equals("2")) {
				gm.addGuild();
			}
			else if(sel.equals("3")) {
				gm.removeGuild();
			}
			else if(sel.equals("4")) {
				gm.changeParty();
			}
			else if(sel.equals("5")) {
				gm.sort();
			}
			else if(sel.equals("0")) {
				break;
			}
		}
	}
	
	private void shopMenu() {
		while(true) {
			System.out.println("==========[상점]==========");
			System.out.println("[1.무기][2.갑옷][3.반지][4.포션][0.뒤로가기]");
			System.out.print("메뉴선택: ");
			String sel = Player.sc.next();
			
			if(sel.equals("1") || sel.equals("2")
					|| sel.equals("3") || sel.equals("4")) {
				shop.buyItem(sel);
			}
			else if(sel.equals("0")) {
				break;
			}
		}
	}
	
	private void inventoryMenu() {
		while(true) {
			System.out.println("==========[인벤토리]==========");
			System.out.println("[1.착용][2.판매][0.뒤로가기]");
			System.out.print("메뉴선택: ");
			String sel = Player.sc.next();
			
			if(sel.equals("1")) {
				im.wearing();
			}
			else if(sel.equals("2")) {
				im.sellItem();
			}
			else if(sel.equals("0")) {
				break;
			}
		}
	}
	
}
