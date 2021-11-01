package models;

import controller.GameController;
import controller.Main;

public class Stage_Lobby extends Stage{

	@Override
	public void setting() {
		
	}

	@Override
	public boolean update() {
		System.out.println("===== LOBBY =====");
		System.out.println("[1.전투]   [2.종료]");
		String menu = Main.sc.next();
		
		if(menu.equals("1")) {
			GameController.next = "BATTLE";
			return false;
		}
		else if(menu.equals("2")) {
			GameController.next = "";
			return false;
		}
		return true;
		
	}

}
