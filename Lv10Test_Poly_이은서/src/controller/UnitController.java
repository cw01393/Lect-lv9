package controller;

import java.util.ArrayList;

import models.UnitPlayer;
import models.Unit;

public class UnitController {

	private final int monsterCnt = 4;
	private ArrayList<Unit> players = new ArrayList<>();
	private ArrayList<Unit> monsters = new ArrayList<>();
	
	private String path = "models.";
	private String[] mon = {"UnitBat","UnitOrc","UnitWolf"};
	
	private static UnitController instance = new UnitController();
	private UnitController() {
		this.players.add(new UnitPlayer("전사",1000,80,30));
		this.players.add(new UnitPlayer("법사",500,90,20));
		this.players.add(new UnitPlayer("힐러",700,50,20));
	}
	public static UnitController getInstance() {
		return instance;
	}
	
	public int getPlayerSize() {
		return this.players.size();
	}
	public Unit getPlayer(int idx) {
		return this.players.get(idx);
	}
	public int getMonsterSize() {
		return this.monsterCnt;
	}
	public Unit getMonster(int idx) {
		return this.monsters.get(idx);
	}
	public void ClearMon() {
		this.monsters.clear();
	}
	
	public void setMonster() {
		for(int i=0; i<this.monsterCnt; i++) {
			int idx = Main.rn.nextInt(this.mon.length);
			try {
				Class<?> cl = Class.forName(this.path + this.mon[idx]);
				Object obj = cl.newInstance();
				Unit unit = (Unit) obj;
				int hp = Main.rn.nextInt(300) + 300;
				int att = Main.rn.nextInt(30) + 30;
				int def = Main.rn.nextInt(10) + 10;
				
				unit.setAll(hp, att, def);
				this.monsters.add(unit);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
