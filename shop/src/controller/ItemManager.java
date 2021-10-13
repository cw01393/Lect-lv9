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
					int price = this.items.get(itemIdx).getPrice();
					int cartIdx = searchCartIdx(itemName);
					if(cartIdx == -1) {
						nowUser.addCart(new Cart(id, itemName, price));
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
	
	public void printCate() {
		for(int i=0; i<this.cate.size(); i++) {
			System.out.printf("[%d] %s\n",i+1,this.cate.get(i));
		}
	}
	
	private int selectCate() {
		printCate();
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
			return -1;
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
	
	public void printCart() {
		User nowUser = um.getUser(Shop.log);
		for(int i=0; i<nowUser.getCartSize(); i++) {
			System.out.printf("[%d][%s][%d개]\n",
					i+1,nowUser.getCart(i).getItemName(),nowUser.getCart(i).getCount());
		}
	}
	public void deleteCart() {
		printCart();
		System.out.print("삭제할 item선택: ");
		String sel = Shop.sc.next();
		
		try {
			int idx = Integer.parseInt(sel)-1;
			User nowUser = um.getUser(Shop.log);

			if(idx >= 0 && idx < nowUser.getCartSize()) {
				if(nowUser.getCart(idx).getCount() > 1) {
					int count = nowUser.getCart(idx).getCount()-1;
					nowUser.getCart(idx).setCount(count);
				}
				else {
					nowUser.removeCart(idx);
				}
			}
			
		} catch (Exception e) {
		}
	}
	
	public void purchase() {
		User nowUser = um.getUser(Shop.log);
		
		int total = 0;
		for(int i=0; i<nowUser.getCartSize(); i++) {
			total += nowUser.getCart(i).getCount() * nowUser.getCart(i).getPrice();
		}
		System.out.println("총 금액: " + total + "원");
		System.out.println("구매하시겠습니까? 1)YES 2)NO");
		String sel = Shop.sc.next();
		
		if(sel.equals("1")) {
			int totalSales = nowUser.getTotalSales() + total;
			nowUser.setTotalSales(totalSales);
			nowUser.setNewCart();
			System.out.println("구매완료! 구매해주셔서 감사합니다");
		}
	}
	
	public void printAllItems() {
		for(int i=0; i<this.items.size(); i++) {
			System.out.println("[" + (i+1) + "] " + this.items.get(i));
		}
	}
	
	public void addItem() {
		int cateIdx = selectCate();
		String cate = this.cate.get(cateIdx);
		
		if(cateIdx != -1) {
			System.out.print("아이템이름: ");
			String name = Shop.sc.next();
			
			if(!checkItemDup(name, cate)) {
				System.out.print("가격설정: ");
				int price = Shop.sc.nextInt();
				
				if(price > 0) {
					Item newItem = new Item(name, cate, price);
					this.items.add(newItem);
					System.out.println("아이템 추가 완료");
				}
			}
			else {
				System.out.println("아이템이름 중복");
			}
		}
		else if(cateIdx == -1) {
			System.out.println("카테고리를 정확히 입력해주세요");
		}
	}
	
	public void removeItem() {
		printAllItems();
		System.out.print("삭제할 아이템 선택: ");
		String sel = Shop.sc.next();
		
		try {
			int delIdx = Integer.parseInt(sel)-1;
			
			if(delIdx >= 0 && delIdx < this.items.size()) {
				this.items.remove(delIdx);
				System.out.println("아이템 삭제 완료");
			}
			
		} catch (Exception e) {
		}
	}
	
	public void addCategory() {
		System.out.print("추가할 카테고리: ");
		String cate = Shop.sc.next();
		
		if(!checkCateDup(cate)) {
			this.cate.add(cate);
			System.out.println("카테고리 추가 완료");
		}
		else {
			System.out.println("카테고리명 중복");
		}
	}
	
	public void removeCate() {
		printCate();
		System.out.print("삭제할 카테고리 선택: ");
		String sel = Shop.sc.next();
		
		try {
			int delIdx = Integer.parseInt(sel)-1;
			
			if(delIdx >= 0 && delIdx < this.cate.size()) {
				this.cate.remove(delIdx);
				System.out.println("카테고리 삭제 완료");
			}
			
		} catch (Exception e) {
		}
	}
	
	private boolean checkItemDup(String name, String cate) {
		for(Item item : this.items) {
			if(item.getName().equals(name) && item.getCategory().equals(cate))
				return true;
		}
		
		return false;
	}
	private boolean checkCateDup(String cate) {
		for(String str : this.cate) {
			if(str.equals(cate))
				return true;
		}
		
		return false;
	}
	
	public void printAllCart() {
		for(int i=0; i<um.getUserSize(); i++) {
			String userId = um.getUser(i).getId();
			System.out.printf("유저 %s의 장바구니\n",userId);
			for(int j=0; j<um.getUser(i).getCartSize(); j++) {
				System.out.println("[" + j + "]" + um.getUser(i).getCart(j));
			}
			System.out.println("------------------------");
		}
	}
	
}
