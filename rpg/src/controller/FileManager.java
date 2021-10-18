package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import models.Guild;
import models.Inventory;
import models.Item;
import models.Player;
import models.Unit;

public class FileManager {
	
	private String guildFileName = "rpgGuild.txt";
	private String invenFileName = "rpgInventory.txt";
	File file;
	FileWriter fw;
	FileReader fr;
	BufferedReader br;
	
	public static FileManager instance = new FileManager();
	private FileManager() {};
	
	private GuildManager gm = GuildManager.instance;
	private InventoryManager im = InventoryManager.instance;
	private Player pl = Player.instance;
	
	public void save() {
		try {
			file = new File(this.guildFileName);
			fw = new FileWriter(file);
			String data = "";
			data += Player.money + "\n";
			data += makeGuildData();
			fw.write(data);
			fw.close();
			
			file = new File(this.invenFileName);
			fw = new FileWriter(file);
			data = makeInvenData();
			fw.write(data);
			fw.close();
			System.out.println("저장 성공");
		} catch (IOException e) {
			System.out.println("저장 실패");
		}
	}
	private String makeGuildData() {
		String str = "";
		for(int i=0; i<pl.guild.getGuildSize(); i++) {
			Unit unit = pl.guild.getGuild(i);
			str += unit.getName();
			str += "/" + unit.getLevel();
			str += "/" + unit.getHp();
			str += "/" + unit.getMaxHp();
			str += "/" + unit.getAtt();
			str += "/" + unit.getDef();
			str += "/" + unit.getExp();
			str += "/" + unit.getParty();
			str += "\n";
			str += unit.getWeapon();
			str += "/" + unit.getArmor();
			str += "/" + unit.getRing();
			str += "\n";
		}
		str = str.substring(0, str.length()-1);
		return str;
	}
	
	private String makeInvenData() {
		String str = "";
		for(int i=0; i<pl.inven.getInvenSize(); i++) {
			Item item = pl.inven.getInven(i);
			str += item + "\n";
		}
		str = str.substring(0, str.length()-1);
		return str;
	}
	
	public void load() {
		try {
			pl.guild = new Guild();
			pl.inven = new Inventory();
			
			file = new File(this.guildFileName);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			String str = br.readLine();
			Player.money = Integer.parseInt(str);
			str = br.readLine();
			while(str != null) {
				guildLoad(str);
				str = br.readLine();
				itemLoad(str);
				str = br.readLine();
			}
			fr.close();
			br.close();
			
			file = new File(this.invenFileName);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			str = br.readLine();
			while(str != null) {
				invenLoad(str);
				str = br.readLine();
			}
			fr.close();
			br.close();
			System.out.println("로드 성공");
		} catch (Exception e) {
			System.out.println("로드 실패");
		}
	}
	
	private void guildLoad(String str) {
//		public Unit(String name, int level, int hp, int maxHp, 
//		int att, int def, int exp, boolean party)
		String temp[] = str.split("/");
		String name = temp[0];
		int level = Integer.parseInt(temp[1]);
		int hp = Integer.parseInt(temp[2]);
		int maxHp = Integer.parseInt(temp[3]);
		int att = Integer.parseInt(temp[4]);
		int def = Integer.parseInt(temp[5]);
		int exp = Integer.parseInt(temp[6]);
		boolean party;
		if(temp[7].equals("true")) {
			party = true;
		}
		else {
			party = false;
		}
		Unit unit = new Unit(name,level,hp,maxHp,att,def,exp,party);
		pl.guild.addGuild(unit);
	}
	private void itemLoad(String str) {
		String temp[] = str.split("/");
		if(!temp[0].equals("null")) {
			String weapon[] = temp[0].split(",");
			Item item = makeItem(weapon);
			int lastIndex = pl.guild.getGuildSize()-1;
			pl.guild.getGuild(lastIndex).setWeaponItem(item);
		}
		if(!temp[1].equals("null")) {
			String armor[] = temp[0].split(",");
			Item item = makeItem(armor);
			int lastIndex = pl.guild.getGuildSize()-1;
			pl.guild.getGuild(lastIndex).setArmorItem(item);
		}
		if(!temp[2].equals("null")) {
			String ring[] = temp[0].split(",");
			Item item = makeItem(ring);
			int lastIndex = pl.guild.getGuildSize()-1;
			pl.guild.getGuild(lastIndex).setRingItem(item);
		}
	}
	private Item makeItem(String temp[]) {
		//public Item(String kind, String name, int ability, int price)
		String kind = temp[0];
		String name = temp[1];
		int ability = Integer.parseInt(temp[2]);
		int price = Integer.parseInt(temp[3]);
		
		return new Item(kind,name,ability,price);
	}
	
	private void invenLoad(String str) {
		String temp[] = str.split(",");
		Item item = makeItem(temp);
		pl.inven.addInven(item);
	}
	
}
