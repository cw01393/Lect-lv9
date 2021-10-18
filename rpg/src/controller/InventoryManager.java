package controller;

import models.Item;
import models.Player;
import models.Unit;

public class InventoryManager {
	
	private Player pl = Player.instance;
	private GuildManager gm = GuildManager.instance;
	
	public static InventoryManager instance = new InventoryManager();
	private InventoryManager() {}
	
	public void wearing() {
		if(pl.inven.getInvenSize() > 0) {
			int guildIdx = selectUnit();
			
			if(guildIdx != -1) {
				Unit unit = pl.guild.getGuild(guildIdx);
				while(true) {
					if(pl.inven.getInvenSize() > 0) {
						System.out.println("==========================");
						unit.printStatue();
						unit.printItemStatue();
						int itemIdx = selectItem();
						if(itemIdx == -100) {
							break;
						}
						else if(itemIdx != -1) {
							Item item = pl.inven.getInven(itemIdx);
							
							String kind = item.getKind();
							
							boolean check = false;
							if(kind.equals("weapon")) {
								if(unit.getWeapon() == null) {
									unit.setWeaponItem(item);
									check = true;
								}
								else if(unit.getWeapon() != item){
									pl.inven.addInven(unit.getWeapon());
									unit.setWeaponItem(item);
									check = true;
								}
							}
							else if(kind.equals("armor")) {
								if(unit.getArmor() == null) {
									unit.setArmorItem(item);
									check = true;
								}
								else if(unit.getArmor() != item) {
									pl.inven.addInven(unit.getArmor());
									unit.setArmorItem(item);
									check = true;
								}
							}
							else if(kind.equals("ring")) {
								if(unit.getRing() == null) {
									unit.setRingItem(item);
									unit.setHp(unit.getHp()+item.getAbility());
									check = true;
								}
								else if(unit.getRing() != item) {
									pl.inven.addInven(unit.getRing());
									if(unit.getHp() > unit.getMaxHp()) {
										unit.setHp(unit.getMaxHp());
									}
									unit.setRingItem(item);
									unit.setHp(unit.getHp()+item.getAbility());
									check = true;
								}
							}
							if(kind.equals("potion")) {
								if(unit.getPotion() == null) {
									unit.setPotionItem(item);
									check = true;
								}
								else if(unit.getPotion() != item){
									pl.inven.addInven(unit.getPotion());
									unit.setPotionItem(item);
									check = true;
								}
							}
							if(check) {
								pl.inven.removeInven(itemIdx);
							}
						}
					}
					else {
						System.out.println("인벤토리가 비어있습니다");
						break;
					}
				}
			}
		}
		else {
			System.out.println("인벤토리가 비어있습니다");
		}
	}
	
	
	private int selectUnit() {
		gm.printAllGuild();
		System.out.println("아이템을 착용할 길드원을 선택하세요");
		String unit = Player.sc.next();
		
		try {
			int idx = Integer.parseInt(unit)-1;
			
			if(idx >= 0 && idx < pl.guild.getGuildSize()) {
				return idx;
			}
		} catch (Exception e) {
		}
		
		return -1;
	}
	
	private int selectItem() {
		printAllInven();
		System.out.println("아이템을 선택하세요(0.뒤로가기)");
		String sel = Player.sc.next();
		
		try {
			int itemIdx = Integer.parseInt(sel)-1;
			if(itemIdx >= 0 && itemIdx < pl.inven.getInvenSize()) {
				return itemIdx;
			}
			else if(itemIdx == -1){
				return -100;
			}
				
		} catch (Exception e) {
		}
		
		return -1;
	}
	
	private void printAllInven() {
		System.out.println("==========[ItemList]========");
		for(int i=0; i<pl.inven.getInvenSize(); i++) {
			System.out.print("[" + (i+1) + "]");
			pl.inven.getInven(i).printItem();
		}
	}
	
	public void sellItem() {
		while(true) {
			if(pl.inven.getInvenSize() > 0) {
				int sellIdx = selectItem();
				
				if(sellIdx == -100) {
					break;
				}
				else if(sellIdx != -1) {
					Item item = pl.inven.getInven(sellIdx);
					while(true) {
						item.printItem();
						System.out.printf("정말 판매하시겠습니까?\n",item.getName());
						System.out.println("(세금 50%) 1)YES 2)NO");
						String sel = Player.sc.next();
						
						if(sel.equals("1")){
							int sellMoney = item.getPrice();
							Player.money += sellMoney / 2;
							pl.inven.removeInven(sellIdx);
							System.out.println(sellMoney/2 + "골드로 판매완료, 현재 골드: " + Player.money);
							break;
						}
						else if(sel.equals("2")){
							break;
						}
					}
				}
			}
			else {
				System.out.println("인벤토리가 비어있습니다");
				break;
			}
		}
	}
	
}
