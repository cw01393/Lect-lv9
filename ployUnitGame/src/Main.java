import controller.GameManager;

public class Main {

	public static void main(String[] args) {

		while(GameManager.getInstance().changeStage()) {}
		System.out.println("GAME OVER");
		
	}

}
