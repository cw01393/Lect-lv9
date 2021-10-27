package controller;

public class TitleStage extends Stage {

	@Override
	public void setting() {
	}

	@Override
	public boolean update() {
		System.out.println("=== TEXT RPG ===");
		System.out.println("[시작]을 입력하세요");
		String start = GameManager.sc.next();
		
		if(start.equals("시작")) {
			GameManager.nextStage = "LOBBY";
			return false;
		}
		return true;
	}

}
