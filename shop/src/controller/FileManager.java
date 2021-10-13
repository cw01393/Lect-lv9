package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import models.Cart;
import models.Item;
import models.User;

public class FileManager {
	
	private String userFileName = "user.txt";
	private String itemFileName = "item.txt";
	private File file;
	private FileWriter fw;
	private FileReader fr;
	private BufferedReader br;
	
	private UserManager um = UserManager.instance;
	private ItemManager im = ItemManager.instance;
	
	public static FileManager instance = new FileManager();
	private FileManager() {}
	
	
	public void save() {
		if(um.getUserSize() > 0) {
			try {
				String data = makeUserData();
				file = new File(this.userFileName);
				fw = new FileWriter(this.file);
				fw.write(data);
				fw.close();
				
				data = makeItemData();
				
				file = new File(this.itemFileName);
				fw = new FileWriter(file);
				fw.write(data);
				fw.close();
				
				System.out.println("저장 완료");
			} catch (IOException e) {
				System.out.println("저장 실패");
			}
		}
		else {
			System.out.println("저장할 데이터가 없습니다");
		}
	}
	
	private String makeUserData() {
		String str = "";
		str += Shop.totalSales + "\n";
		for(int i=0; i<um.getUserSize(); i++) {
			str += um.getUser(i).getId();
			str += "/" + um.getUser(i).getPw();
			str += "/" + um.getUser(i).getTotalSales();
			for(int j=0; j<um.getUser(i).getCartSize(); j++) {
				str += "/" + um.getUser(i).getCart(j).getItemName();
				str += "/" + um.getUser(i).getCart(j).getPrice();
				str += "/" + um.getUser(i).getCart(j).getCount();
			}
			str += "\n";
		}
		str = str.substring(0, str.length()-1);
		return str;
	}
	
	private String makeItemData() {
		String str = "";
		for(int i=0; i<im.getItemsSize(); i++) {
			str += im.getItem(i).getName();
			str += "/" + im.getItem(i).getCategory();
			str += "/" + im.getItem(i).getPrice();
			str += "\n";
		}
		str = str.substring(0, str.length()-1);
		return str;
	}
	
	public void load() {
		try {
			fr = new FileReader(this.itemFileName);
			br = new BufferedReader(fr);
			
			im.setNewItemArray();
			im.setNewCateArray();
			String str = br.readLine();
			while(str != null) {
				String temp[] = str.split("/");
				
				String itemName = temp[0];
				String cate = temp[1];
				int price = Integer.parseInt(temp[2]);
				im.addItems(new Item(itemName,cate,price));
				
				str = br.readLine();
			}
			setCategory();
			
			fr.close();
			br.close();
			
			fr = new FileReader(this.userFileName);
			br = new BufferedReader(fr);
			
			um.setNewUser();
			str = br.readLine();
			Shop.totalSales = Integer.parseInt(str);
			str = br.readLine();
			while(str != null) {
				String temp[] = str.split("/");
				String id = temp[0];
				String pw = temp[1];
				int totalSales = Integer.parseInt(temp[2]);
				
				User user = new User(id,pw,totalSales);
				
				for(int i=3; i<temp.length; i+=3) {
					String name = temp[i];
					int price = Integer.parseInt(temp[i+1]);
					int count = Integer.parseInt(temp[i+2]);
					Cart cart = new Cart(id,name,price,count);
					user.addCart(cart);
				}
				um.addUsers(user);
				
				str = br.readLine();
			}
			
			fr.close();
			br.close();
			System.out.println("로드 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("로드 실패");
		}
		
	}
	private void setCategory() {
		for(int i=0; i<im.getItemsSize(); i++) {
			String cate = im.getItem(i).getCategory();
			boolean check = false;
			for(int j=0; j<i; j++) {
				if(im.getItem(j).getCategory().equals(cate)) {
					check = true;
					break;
				}
			}
			if(!check) {
				im.addCate(cate);
			}
		}
	}
	
}
