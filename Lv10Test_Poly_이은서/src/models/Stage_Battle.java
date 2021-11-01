package models;

import java.util.ArrayList;

import controller.GameController;
import controller.Main;
import controller.UnitController;

public class Stage_Battle extends Stage{

	private ArrayList<Unit> tempPlayer = new ArrayList<Unit>();
	private ArrayList<Unit> tempMonster = new ArrayList<Unit>();
	private UnitController uc = UnitController.getInstance();
	
	@Override
	public void setting() {
		uc.ClearMon();
		uc.setMonster();
		this.tempPlayer.clear();
		this.tempMonster.clear();
		for(int i=0; i<uc.getPlayerSize(); i++) {
			if(uc.getPlayer(i).getHp() > 0)
				this.tempPlayer.add(uc.getPlayer(i));
		}
		for(int i=0; i<uc.getMonsterSize(); i++) {
			this.tempMonster.add(uc.getMonster(i));
		}
	}

	@Override
	public boolean update() {
		printBattle();
		playerAttack();
		System.out.println("===================");
		monsterAttack();
		if(this.tempMonster.size() == 0) {
			System.out.println("BATTLE WIN!");
			GameController.next = "LOBBY";
			return false;
		}
		if(this.tempPlayer.size() == 0) {
			System.out.println("BATTLE LOSE");
			GameController.next = "";
			return false;
		}
		return true;
	}

	private void printBattle() {
		System.out.println("===== PLAYER =====");
		for(int i=0; i<uc.getPlayerSize(); i++) {
			uc.getPlayer(i).printInfo();
		}
		System.out.println("===== MONSTER =====");
		for(int i=0; i<uc.getMonsterSize(); i++) {
			uc.getMonster(i).printInfo();
		}
	}
	
	private void playerAttack() {
		System.out.println("==== PLAYER ATTACK ====");
		for(Unit unit : this.tempPlayer) {
			if(this.tempMonster.size() > 0) {
				while(!setBack(unit)) {
					System.out.printf("[%s] [1.공격] [2.스킬]\n",unit.getName());
					String sel = Main.sc.next();
					
					if(sel.equals("1")) {
						int idx = Main.rn.nextInt(this.tempMonster.size());
						unit.attack(this.tempMonster.get(idx));
						if(this.tempMonster.get(idx).getHp() == 0) {
							this.tempMonster.remove(idx);
						}
						break;
					}
					else if(sel.equals("2")) {
						if(unit.getName().equals("힐러")) {
							unit.skill(tempPlayer);
						}
						else {
							unit.skill(tempMonster);
						}
						break;
					}
				}
			}
		}
	}
	private boolean setBack(Unit unit) {
		if(unit.getState().equals("노말")) {
			return false;
		}
		else {
			System.out.printf("[%s] %s중!\n",unit.getName(),unit.getState());
			unit.setState("노말");
			return true;
		}
	}
	
	private void monsterAttack() {
		for(Unit unit : this.tempMonster) {
			if(this.tempPlayer.size() > 0) {
				if(!setBack(unit)) {
					int rNum = Main.rn.nextInt(100);
					if(rNum > 74) {
						unit.skill(tempPlayer);
					}
					else {
						int rIdx = Main.rn.nextInt(this.tempPlayer.size());
						unit.attack(this.tempPlayer.get(rIdx));
						if(this.tempPlayer.get(rIdx).getHp() == 0) {
							this.tempPlayer.remove(rIdx);
						}
					}
				}
			}
		}
	}
	
}
