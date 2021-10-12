package controller;

import java.util.ArrayList;

import models.Item;

public class ItemManager {

	private ArrayList<Item> items = new ArrayList<>();
	private ArrayList<String> cate = new ArrayList<>();
	private UserManager um = UserManager.instance;
	
	public static ItemManager instance = new ItemManager();
	private ItemManager() {}
	
	public void setCate() {
		this.cate.add("과일");
		this.cate.add("육류");
		this.cate.add("과자");
		this.cate.add("생필품");
	}
	
	public void setItems() {
		this.items.add(new Item("포도","과일",3200));
		this.items.add(new Item("사과","과일",1500));
		this.items.add(new Item("양지","육류",12000));
		this.items.add(new Item("삼겹살","육류",9900));
		this.items.add(new Item("빼빼로","과자",1200));
		this.items.add(new Item("포스틱","과자",1800));
		this.items.add(new Item("샴푸","생필품",6800));
		this.items.add(new Item("세제","생필품",5700));
	}
	
	public void shopping() {
		while(true) {
			int sel = selectCate();
			
			if(sel >= 0 && sel < this.cate.size()) {
				String cate = this.cate.get(sel);
				int itemIdx = selectItem(cate);
				if(itemIdx != -1) {
					um.getUser(Shop.log)
				}
			}
			else if(sel == -2) {
				break;
			}
		}
	}
	
	private int selectCate() {
		for(int i=0; i<this.cate.size(); i++) {
			System.out.printf("[&d] %s\n",i+1,this.cate.get(i));
		}
		System.out.print("카테고리 선택(-1: 뒤로가기): ");
		String sel = Shop.sc.next();
		try {
			int idx = Integer.parseInt(sel)-1;
			
			return idx;
		} catch (Exception e) {
		}
		return -1;
	}
	
	private int selectItem(String cate) {
		int n = 1;
		for(int i=0; i<this.items.size(); i++) {
			if(this.items.get(i).getCategory().equals(cate)) {
				Item item = this.items.get(i);
				System.out.printf("[%d][%s][%d]\n",n,item.getName(),item.getPrice());
				n++;
			}
		}
		System.out.print("아이템 선택: ");
		String sel = Shop.sc.next();
		try {
			int cnt = Integer.parseInt(sel);
			n = 0;
			for(int i=0; i<this.items.size(); i++) {
				if(this.items.get(i).getCategory().equals(cate)) {
					n++;
					if(n == cnt) {
						return i;
					}
				}
			}
			
		} catch (Exception e) {
		}
		return -1;
	}
	
}
