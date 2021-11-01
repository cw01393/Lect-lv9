package models;

import controller.GameController;
import controller.Main;

public class Stage_Title extends Stage{

	@Override
	public void setting() {
		
	}

	@Override
	public boolean update() {
		System.out.println("===== RPG =====");
		System.out.println("[시작]을 입력하세요");
		String str = Main.sc.next();
		if(str.equals("시작")) {
			GameController.next = "LOBBY";
			return false;
		}
		else {
			return true;
		}
	}

}
