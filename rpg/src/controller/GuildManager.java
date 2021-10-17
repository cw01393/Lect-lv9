package controller;

import java.util.Random;

import models.Player;
import models.Unit;

public class GuildManager {

	public static GuildManager instance = new GuildManager();
	private GuildManager(){}
	
	private Player pl = Player.instance;
	
	public void setGuild() {
		Unit temp = new Unit("호랑이", 1, 100, 10, 5, 0);
		pl.guild.addGuild(temp);
		temp = new Unit("강아지", 1, 80, 7, 3, 0);
		pl.guild.addGuild(temp);
		temp = new Unit("사슴", 1, 50, 3, 1, 0);
		pl.guild.addGuild(temp);
		temp = new Unit("두더지", 1, 70, 5, 2, 0);
		pl.guild.addGuild(temp);
		temp = new Unit("돼지", 1, 200, 4, 8, 0);
		pl.guild.addGuild(temp);
		temp = new Unit("사자", 1, 120, 11, 7, 0);
		pl.guild.addGuild(temp);
		for(int i=0; i<4; i++) {
			temp = pl.guild.getGuild(i);
			temp.setParty(true);
		}
	}
	
	
	public void printAllGuild() {
		System.out.println("==========[길드목록]==========");		
		for(int i=0; i<pl.guild.getGuildSize(); i++) {
			System.out.printf("[%d]",i+1);
			pl.guild.getGuild(i).printStatue();
		}
	}
	
	public void addGuild() {
		System.out.println("길드원 추가를 하시겠습니까?(5000골드)\n1)YES 2)NO");
		String sel = Player.sc.next();
		
		if(sel.equals("1")) {
			Unit temp = randomUnit();
			pl.guild.addGuild(temp);
			temp.printStatue();
			System.out.println("길드원 추가 완료!");
			Player.money -= 5000;
			System.out.println("잔여 골드: " + Player.money);
		}
		else if(!sel.equals("2")) {
			System.out.println("메뉴를 정확히 입력해주세요");
		}
	}
	
	private Unit randomUnit() {
		while(true) {
			Random rn = new Random();
			Unit temp = null;
			String[] n1 = { "박", "이", "김", "최", "유", "지", "오"};
			String[] n2 = { "명", "기", "종", "민", "재", "석", "광"};
			String[] n3 = { "수", "자", "민", "수", "석", "민", "철"};
			
			String name = "";
			name += n1[rn.nextInt(n1.length)];
			name += n2[rn.nextInt(n2.length)];
			name += n2[rn.nextInt(n3.length)];
			
			boolean check = checkNameDup(name);
			if(!check) {
				int level = 1;
				int maxHp = rn.nextInt(80) + 20;
				int att = rn.nextInt(10)+2;
				int def = rn.nextInt(10)+2;
				
				temp = new Unit(name, level, maxHp, att, def, 0);
				
				return temp;
			}
		}
	}
	
	private boolean checkNameDup(String name) {
		for(int i=0; i<pl.guild.getGuildSize(); i++) {
			if(pl.guild.getGuild(i).getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public void removeGuild() { // 추가하기 : 길드원 삭제시 착용하고 있는 아이템 다시 인벤토리로 보내기
		printAllGuild();
		System.out.print("삭제할 길드원 번호선택: ");
		String sel = Player.sc.next();
		
		try {
			int delIdx = Integer.parseInt(sel)-1;
			if(delIdx >= 0 && delIdx < pl.guild.getGuildSize()) {
				String delName = pl.guild.getGuild(delIdx).getName();
				pl.guild.removeGuild(delIdx);
				System.out.println("==========================");	
				System.out.printf("[%s]길드원 삭제 완료\n",delName);
				System.out.println("==========================");	
			}
			else {
				System.out.println("삭제할 길드원 번호를 다시 확인하세요");
			}
			
		} catch (Exception e) {
		}
	}
	
	public void changeParty() {
		int partyIdx = selectParty();
		if(partyIdx != -1) {
			int notPartyIdx = selectNotParty();
			if(notPartyIdx != -1) {
				pl.guild.getGuild(partyIdx).setParty(false);
				pl.guild.getGuild(notPartyIdx).setParty(true);
				System.out.println("==========================");	
				System.out.printf("[%s → %s]파티원 교체 완료\n"
						,pl.guild.getGuild(partyIdx).getName(),pl.guild.getGuild(notPartyIdx).getName());
				System.out.println("==========================");	
			}
		}
		
	}
	
	private int selectParty() {
		int n = 1;
		for(int i=0; i<pl.guild.getGuildSize(); i++) {
			if(pl.guild.getGuild(i).getParty()) {
				System.out.printf("[%d]",n);
				pl.guild.getGuild(i).printStatue();
				n++;
			}
		}
		System.out.print("교체할 파티원 선택: ");
		String sel = Player.sc.next();
		
		try {
			int change = Integer.parseInt(sel);
			
			int changeIdx = findPartyIdx(change);
			if(changeIdx != -1) {
				return changeIdx;
			}
			
		} catch (Exception e) {
			System.out.println("교체할 파티원이 선택되지 않았습니다");
		}
		
		return -1;
	}
	
	private int selectNotParty() {
		int n = 1;
		for(int i=0; i<pl.guild.getGuildSize(); i++) {
			if(!pl.guild.getGuild(i).getParty()) {
				System.out.printf("[%d]",n);
				pl.guild.getGuild(i).printStatue();
				n++;
			}
		}
		System.out.print("교체할 파티원 선택: ");
		String sel = Player.sc.next();
		
		try {
			int change = Integer.parseInt(sel);
			
			int changeIdx = findNotPartyIdx(change);
			if(changeIdx != -1) {
				return changeIdx;
			}
			
		} catch (Exception e) {
			System.out.println("교체할 파티원이 선택되지 않았습니다");
		}
		
		return -1;
	}
	
	private int findNotPartyIdx(int cnt) {
		int n = 0;
		for(int i=0; i<pl.guild.getGuildSize(); i++) {
			if(!pl.guild.getGuild(i).getParty()) {
				n++;
			}
			if(cnt == n) {
				return i;
			}
		}
		return -1;
	}
	
	private int findPartyIdx(int cnt) {
		int n = 0;
		for(int i=0; i<pl.guild.getGuildSize(); i++) {
			if(pl.guild.getGuild(i).getParty()) {
				n++;
			}
			if(cnt == n) {
				return i;
			}
		}
		return -1;
	}
	
	public void sort() {
		for(int i=0; i<pl.guild.getGuildSize(); i++) {
			String name = pl.guild.getGuild(i).getName();
			int idx = i;
			Unit e = pl.guild.getGuild(i);
			for(int j=i; j<pl.guild.getGuildSize(); j++) {
				if(pl.guild.getGuild(j).getName().compareTo(name) < 0) {
					name = pl.guild.getGuild(j).getName();
					idx = j;
					e = pl.guild.getGuild(j);
				}
			}
			Unit temp = pl.guild.getGuild(i);
			pl.guild.setGuile(i, e);
			pl.guild.setGuile(idx, temp);
		}
		System.out.println("정렬 완료");
	}
}
