package controller;

import java.util.ArrayList;

import models.Item;
import models.Player;

public class Shop {

	ArrayList<Item> shop = new ArrayList<>();
	private Player pl = Player.instance;
	
	public static Shop instance = new Shop();
	private Shop() {}
	
	public void setShop() {
		shop.add(new Item("weapon", "나무검",3,1000));
		shop.add(new Item("weapon", "철검",5,2000));
		shop.add(new Item("weapon", "레이피어",7,2500));
		shop.add(new Item("armor", "티셔츠",1,300));
		shop.add(new Item("armor", "가죽갑옷",4,800));
		shop.add(new Item("armor", "강철갑옷",7,1500));
		shop.add(new Item("ring", "은반지",7,3000));
		shop.add(new Item("ring", "금반지",17,6000));
		shop.add(new Item("ring", "다이아반지",35,20000));
		shop.add(new Item("potion", "파란포션",10,1000));
		shop.add(new Item("potion", "빨간포션",20,2000));
		shop.add(new Item("potion", "하얀포션",30,3000));
	}
	
	public void buyItem(String sel) {
		while(true) {
			int idx = selectItem(sel);
			if(idx == -100) {
				break;
			}
			else if(idx >= 0 && idx < this.shop.size()) {
				Item buyItem = this.shop.get(idx);
				if(Player.money >= buyItem.getPrice()) {
					Player.money -= buyItem.getPrice();
					System.out.printf("[%s]를 구입했습니다\n",buyItem.getName());
					pl.inven.addInven(buyItem);
					System.out.println("보유 골드: " + Player.money);
				}
				else {
					System.out.println("골드부족!");
				}
			}
		}
	}
	
	private int selectItem(String sel) {
		String kind = "";
		if(sel.equals("1")) kind = "weapon";
		else if(sel.equals("2")) kind = "armor";
		else if(sel.equals("3")) kind = "ring";
		else if(sel.equals("4")) kind = "potion";
		
		System.out.printf("============[%s]===========\n",kind);
		int n = 1;
		for(int i=0; i<this.shop.size(); i++) {
			if(kind.equals(this.shop.get(i).getKind())) {
				Item item = this.shop.get(i);
				System.out.print("[" + n + "]");
				item.printItem();
				n ++;
			}
		}
		System.out.println("구입할 아이템 번호를 입력하세요(0.뒤로가기)");
		String num = Player.sc.next();
		
		try {
			int itemNum = Integer.parseInt(num);
			
			if(itemNum == 0) return -100;
			else {
				n = 0;
				for(int i=0; i<this.shop.size(); i++) {
					if(kind.equals(this.shop.get(i).getKind())) {
						n++;
					}
					if(n == itemNum) return i;
				}
			}
			
		} catch (Exception e) {
		}
		
		return -1;
	}
	
}
