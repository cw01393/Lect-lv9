package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import models.Item;
import models.Player;
import models.Unit;

public class FileManager {
	
	private String guildFileName = "rpgGuild.text";
	private String invenFileName = "rpgInventory.text";
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
			file = new File(guildFileName);
			fw = new FileWriter(file);
			String data = "";
			data += Player.money + "\n";
			data += makeGuildData();
			fw.write(data);
			fw.close();
			
			file = new File(invenFileName);
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
	
}
