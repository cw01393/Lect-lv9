package basic;

// thread

class PlayGame extends Thread{
	public boolean play;
	
	public PlayGame() {
		
	}
	
	@Override
	public void run() {
		play = true;
		while(play) {
			System.out.println("신나게 게임을 하는중 >>");
			try {
				sleep(100);
			} catch (Exception e) {
			}
		}
	}
}

class PlayMusic implements Runnable{

	public boolean play;
	
	@Override
	public void run() {
		play = true;
		while(play) {
			System.out.println("음악이 흐르고...");
			try {
				Thread.sleep(100);
			} catch (Exception e) {
			}
		}
	}
	
}

public class Ex04 {

	public static void main(String[] args) {

		PlayGame game = new PlayGame();
//		game.run(); // 이렇게도 할 수 있지만 보통 start 메소드로 런함
		game.start();
		
		for(int n=0; n<10; n++) {
			System.out.println("n : " + n);
			if(n == 8) {
				System.out.println("앗, 엄마가 등장했다!");
//				game.stop(); // 쓸순있지만 예전에 없어진 메소드
				game.play = false;
			}
			
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
//		PlayMusic music = new PlayMusic();
//		music.run();
		Runnable music = new PlayMusic();
		Thread thread = new Thread(music); // 러너블 인터페이스에 있는 클래스를 쓰레드에 태워서 실행가능
		thread.start();
		
		for(int n=0; n<10; n++) {
			System.out.println("n: " + n);
			
			if(n == 7) {
				System.out.println("선생님 등장!");
				if(music instanceof PlayMusic) { // 다운캐스팅을 위한 예외처리
					PlayMusic mu = (PlayMusic) music;
					mu.play = false;
				}
			}
		}
	}

}
