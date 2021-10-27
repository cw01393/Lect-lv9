package controller;

public class BattleStage extends Stage {

	private UnitManager um = UnitManager.getInstance();
	
	@Override
	public void setting() {
		um.setMonClear();
		um.randomMonster(4);
	}

	@Override
	public boolean update() {
		System.out.println("===== [BATTLE] =====");
		printBattle();
		
		
		GameManager.nextStage = "LOBBY";
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

}
