package controller;

import java.util.HashMap;
import java.util.Map;

import models.Stage;
import models.Stage_Battle;
import models.Stage_Lobby;
import models.Stage_Title;

public class GameController {

	public static String next;
//	private String current;
	private Map<String, Stage> stageList = new HashMap<String, Stage>();
	
	private static GameController instance = new GameController();
	private GameController() {
		this.stageList.put("TITLE", new Stage_Title());
		this.stageList.put("LOBBY", new Stage_Lobby());
		this.stageList.put("BATTLE", new Stage_Battle());
		next = "TITLE";
	}
	public static GameController getInstance() {
		return instance;
	}
	
	
	public void run() {
		while(!checkEnd()) {
			Stage current = this.stageList.get(next);
			current.setting();
			while(current.update()) {}
		}
		System.out.println("GAME OVER");
	}
	
	private boolean checkEnd() {
		if(next.equals("")) return true;
		return false;
	}
	
}
