package controller;

import java.util.ArrayList;

import model.Unit;
import model.UnitPlayer;

public class UnitManager {

	private ArrayList<Unit> player = new ArrayList<>();
	private ArrayList<Unit> monster = new ArrayList<>();
	private String path = "model.";
	private String[] mon = {"UnitBat","UnitOrc","UnitWolf"};
	
	private UnitManager(){
		player.add(new UnitPlayer("전사" , 1000, 45));
		player.add(new UnitPlayer("마법사" , 800, 60));
		player.add(new UnitPlayer("힐러" , 500, 75));
	}
	
	private static UnitManager instance = new UnitManager();
	public static UnitManager getInstance() {
		return instance;
	}
	
	public Unit getPlayer(int index) {
		return this.player.get(index);
	}
	public Unit getMonster(int index) {
		return this.monster.get(index);
	}
	public int getPlayerSize() {
		return this.player.size();
	}
	public int getMonsterSize() {
		return this.monster.size();
	}
//	public void setPlClear() {
//		this.player.clear();
//	}
	public void setMonClear() {
		this.monster.clear();
	}
	
	public void randomMonster(int size) {
		for(int i=0; i<size; i++) {
			int num = Unit.rn.nextInt(this.mon.length);
			try {
				Class<?> clazz = Class.forName(this.path + this.mon[num]);
				Object obj = clazz.newInstance();
				Unit unit = (Unit) obj;
				int hp = Unit.rn.nextInt(100) + 100;
				int power = Unit.rn.nextInt(10) + 10;

				unit.allSet(hp, power);
				this.monster.add(unit);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
