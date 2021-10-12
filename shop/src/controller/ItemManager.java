package controller;

import java.util.ArrayList;

import models.Cart;
import models.Item;
import models.User;

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
				User nowUser = um.getUser(Shop.log);
				
				if(itemIdx != -1) {
					String id = nowUser.getId();
					String itemName = this.items.get(itemIdx).getName();
					int cartIdx = searchCartIdx(itemName);
					if(cartIdx == -1) {
						nowUser.addCart(new Cart(id, itemName));
					}
					else {
						int count = nowUser.getCart(cartIdx).getCount() + 1;
						nowUser.getCart(cartIdx).setCount(count);
					}
				}
			}
			else if(sel == -2) {
				break;
			}
		}
	}
	
	private int selectCate() {
		for(int i=0; i<this.cate.size(); i++) {
			System.out.printf("[%d] %s\n",i+1,this.cate.get(i));
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
	
	private int searchCartIdx(String itemName) {
		User nowUser = um.getUser(Shop.log);
		for(int i=0; i<nowUser.getCartSize(); i++) {
			if(um.getUser(Shop.log).getCart(i).getItemName().equals(itemName)) {
				return i;
			}
		}
		return -1;
	}
	
	public void printCartMenu() {
		while(true) {
			System.out.println("1.내 장바구니\n2.삭제\n3.구입\n0.뒤로가기");
			String sel = Shop.sc.next();
			
			if(sel.equals("1")) {
				printCart();
			}
			else if(sel.equals("2")) {
				deleteCart();
			}
			else if(sel.equals("3")) {}
			else if(sel.equals("0")) {
				break;
			}
		}
	}
	
	private void printCart() {
		User nowUser = um.getUser(Shop.log);
		for(int i=0; i<nowUser.getCartSize(); i++) {
			System.out.printf("[%d][%s][%d개]\n",
					i+1,nowUser.getCart(i).getItemName(),nowUser.getCart(i).getCount());
		}
	}
	private void deleteCart() {
		printCart();
		System.out.print("삭제할 item선택: ");
		String sel = Shop.sc.next();
		
		try {
			int idx = Integer.parseInt(sel)-1;
			User nowUser = um.getUser(Shop.log);

			if(idx >= 0 && idx < nowUser.getCartSize()) {
				nowUser.removeCart(idx);
			}
			
		} catch (Exception e) {
		}
	}
	
}
