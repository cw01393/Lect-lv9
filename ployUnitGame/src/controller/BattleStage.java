package controller;

import java.util.ArrayList;

import model.Unit;

public class BattleStage extends Stage {

	private final int monsterCnt = 4;
	private ArrayList<Unit> tempPlayer = new ArrayList<Unit>();
	private ArrayList<Unit> tempMonster = new ArrayList<Unit>();
	
	private UnitManager um = UnitManager.getInstance();
	
	@Override
	public void setting() {
		um.setMonClear();
		um.randomMonster(this.monsterCnt);
		setArray();
	}
	
	private void setArray() {
		this.tempPlayer.clear();
		this.tempMonster.clear();
		for(int i=0; i<um.getPlayerSize(); i++) {
			this.tempPlayer.add(um.getPlayer(i));
		}
		for(int i=0; i<um.getMonsterSize(); i++) {
			this.tempMonster.add(um.getMonster(i));
		}
	}

	@Override
	public boolean update() {
		System.out.println("===== [BATTLE] =====");
		printBattle();
		for(int i=0; i<tempPlayer.size(); i++) {
			playerAttack(i);
		}
		System.out.println("=====================");
		for(int i=0; i<tempMonster.size(); i++) {
			monsterAttack(i);
		}
		if(checkEnd()) {
			GameManager.nextStage = "LOBBY";
			return false;
		}
		else {
			return true;
		}
	}
	
	private boolean checkEnd() {
		if(this.tempMonster.size() == 0) {
			System.out.println("BATTLE에서 승리하였습니다!");
			return true;
		}
		else if(this.tempPlayer.size() == 0) {
			System.out.println("BATTLE에서 패배하였습니다");
			return true;
		}
		return false;
	}
	
	private void printBattle() {
		System.out.println("===== [PLAYER] =====");
		for(int i=0; i<um.getPlayerSize(); i++) {
			um.getPlayer(i).printInfo();
		}
		System.out.println("===== [MONSTER] =====");
		for(int i=0; i<um.getMonsterSize(); i++) {
			um.getMonster(i).printInfo();
		}
	}
	
	private void playerAttack(int index) {
		if(this.tempMonster.size() == 0) return;
		Unit player = this.tempPlayer.get(index);
		if(!player.getState().equals("노말")) {
			player.setState("노말");
			return;
		}
		while(true) {
			System.out.println("=====================");
			System.out.printf("[%s] [1.어택] [2.스킬]\n",player.getName());
			String sel = GameManager.sc.next();
			if(sel.equals("1")) {
				Unit target = this.tempMonster.get(Unit.rn.nextInt(this.tempMonster.size()));
				player.fight(target);
				if(target.getHp() <= 0) {
					this.tempMonster.remove(target);
				}
				break;
			}
			else if(sel.equals("2")) {
				player.skill(tempMonster);
				break;
			}
		}
	}
	private void monsterAttack(int index) {
		if(this.tempPlayer.size() == 0) return;
		Unit monster = this.tempMonster.get(index);
		int rNum = Unit.rn.nextInt(100);
		
		if(rNum > 74) {
			monster.skill(this.tempPlayer);
		}
		else {
			Unit target = this.tempPlayer.get(Unit.rn.nextInt(this.tempPlayer.size()));
			monster.fight(target);
			if(target.getHp() <= 0) {
				this.tempPlayer.remove(target);
			}
		}
	}

}
