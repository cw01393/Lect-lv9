package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameManager {

	public static Scanner sc = new Scanner(System.in);
	public static String nextStage = "";
	String currentStage = "";
	Map<String , Stage> stageList = new HashMap<String , Stage>();
	
	private GameManager() {
		stageList.put("TITLE", new TitleStage());
		stageList.put("BATTLE", new BattleStage());
		stageList.put("LOBBY", new LobbyStage());
		
		nextStage = "TITLE";
	}
	
	private static GameManager instance = new GameManager();

	public static GameManager getInstance() {
		return instance;
	}
	
	public boolean changeStage() {
		if(currentStage.equals(nextStage) || nextStage == "") {
			return false;
		}
		
		Stage stage = stageList.get(nextStage);
		stage.setting();
		
		while(stage.update()) {}
		
		return true;
	}
	
}
