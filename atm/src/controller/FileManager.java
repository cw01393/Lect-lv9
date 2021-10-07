package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import models.Account;
import models.User;

public class FileManager {

	private UserManager um = UserManager.instance;
	
	public static FileManager instance = new FileManager();
	private FileManager() {}
	
	private final String fileName = "atm.txt";
	File file = new File(this.fileName);
	FileWriter fw;
	FileReader fr;
	BufferedReader br;
	
	private String infoFile() {
		String str = "";
		
		for(int i=0; i<um.getUserSize(); i++) {
			str += um.getUser(i).getUserCode();
			str += "/" + um.getUser(i).getId();
			str += "/" + um.getUser(i).getPw();
			str += "/" + um.getUser(i).getName();
			for(int j=0; j<um.getUser(i).getAccCnt(); j++) {
				str += "/" + um.getUser(i).getAcc(j).getAccNum();
				str += "/" + um.getUser(i).getAcc(j).getMoney();
			}
			str += "\n";
		}
		str = str.substring(0, str.length()-1);
		
		return str;
	}
	
	public void save() {
		try {
			fw = new FileWriter(file);
			fw.write(infoFile());
			fw.close();
			System.out.println("저장완료");
			
		} catch (IOException e) {
			System.out.println("저장실패");
		}
	}
	
	public void load() {
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			while(um.getUserSize() > 0) {
				um.remove(0);
			}
			
			String line = br.readLine();
			while(line != null) {
				String temp[] = line.split("/");
				
				int code = Integer.parseInt(temp[0]);
				String id = temp[1];
				String pw = temp[2];
				String name = temp[3];
				
				User user = new User(code,id,pw,name);
				
				for(int i=4; i<temp.length; i+=2) {
					int accNum = Integer.parseInt(temp[i]);
					int money = Integer.parseInt(temp[i+1]);
					Account acc = new Account(accNum,money);
					user.addAcc(acc);
					user.setAccCnt(user.getAccCnt()+1);
				}
				um.add(user);
				
				line = br.readLine();
			}
			fr.close();
			br.close();
			System.out.println("로드 성공");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("로드실패");
		}
	}
	
}
