package controller;

public class LobbyStage extends Stage {

	@Override
	public void setting() {
		
	}

	@Override
	public boolean update() {
		while(true) {
			System.out.println("===== [LOBBY] =====");
			System.out.println("[1.전투] [2.종료]");
			String menu = GameManager.sc.next();
			
			if(menu.equals("1")) {
				GameManager.nextStage = "BATTLE";
				return false;
			}
			else if(menu.equals("2")) {
				GameManager.nextStage = "";
				return false;
			}
		}
	}

}
