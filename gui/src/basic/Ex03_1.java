package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class AlertResult extends JFrame{
	private JLabel text = new JLabel();
	
	public AlertResult(int ms) {
		super("GAME CLEAR!");
		setLayout(null);
		setBounds(100, 100, 300, 200);
		this.text.setText(String.format("성적: %5d.%3d초 소요", ms/1000, ms%1000));
		this.text.setBounds(0,0,300,200);
		this.text.setHorizontalAlignment(JLabel.CENTER);
		add(this.text);
		setVisible(true);
		revalidate();
	}
}

class GamePanel extends JPanel implements ActionListener,Runnable{
	
	private JLabel title = new JLabel("1 to 50");
	private JLabel timer = new JLabel("READY");
	private int ms;
	private boolean isRun;
	private int gameNum = 1;
	
	private final int SIZE = 5;
	private JButton reset = new JButton("reset");
	private JButton[][] map = new JButton[SIZE][SIZE];
	private int[][] front = new int[SIZE][SIZE];
	private int[][] back = new int[SIZE][SIZE];
	
	public GamePanel() {
		setLayout(null);
		setBounds(0,0,700,700);
		
		setTitle();
		setTimer();
		setData();
		setMap();
		setReset();
	}
	private void setReset() {
		this.reset.setBounds(550,30,100,50);
		this.reset.setBackground(Color.white);
		this.reset.addActionListener(this);
		add(this.reset);
	}
	
	private void resetGame() {
		setData();
		this.ms = 0;
		this.isRun = false;
		this.timer.setText("READY");
		
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				this.map[i][j].setText(this.front[i][j]+"");
				this.map[i][j].setBackground(Color.gray);
			}
		}
	}
	
	private void setTimer() {
		this.timer.setBounds(0, 0, 200, 50);
		this.timer.setBackground(Color.green);
		this.timer.setHorizontalAlignment(JLabel.LEFT);
		add(this.timer);
	}
	private void setTitle() {
		this.title.setBounds(0,0,700,100);
		this.title.setHorizontalAlignment(JLabel.CENTER);
		this.title.setFont(new Font("", Font.BOLD, 30));
		add(this.title);
	}
	
	private void setData() {
		int n = 1;
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				this.front[i][j] = n;
				this.back[i][j] = n + SIZE*SIZE;
				n ++;
			}
		}
		shuffle();
	}
	
	private void shuffle() {
		Random rn = new Random();
		
		for(int i=0; i<1000; i++) {
			int r1 = rn.nextInt(SIZE);
			int r2 = rn.nextInt(SIZE);
			
			int temp = this.front[0][0];
			this.front[0][0] = this.front[r1][r2];
			this.front[r1][r2] = temp;
			
			r1 = rn.nextInt(SIZE);
			r2 = rn.nextInt(SIZE);
			
			temp = this.back[0][0];
			this.back[0][0] = this.back[r1][r2];
			this.back[r1][r2] = temp;
		}
	}
	
	private void setMap() {
		int x = 700/2-100*SIZE/2;
		int y = 700/2-100*SIZE/2;
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				this.map[i][j] = new JButton();
				// 버튼의 속성설정
				this.map[i][j].setBounds(x,y,100,100);
				this.map[i][j].setBackground(Color.gray);
				this.map[i][j].setText(this.front[i][j] + "");
				this.map[i][j].addActionListener(this);
				add(this.map[i][j]);
				x+= 100+2;
			}
			x = 700/2-100*SIZE/2;
			y += 100 + 2;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			
			if(target == reset) {
				resetGame();
				return;
			}
			
			for(int i=0; i<SIZE; i++) {
				for(int j=0; j<SIZE; j++) {
					if(target == this.map[i][j]
							&& this.map[i][j].getText().equals(this.gameNum+"")) {
						if(this.gameNum == 1) isRun = true;
						this.front[i][j] = this.back[i][j];
						this.back[i][j] = 0;
						
						if(this.front[i][j] == 0) {
							this.map[i][j].setBackground(Color.white);
							this.map[i][j].setText("");
						}
						else {
							this.map[i][j].setBackground(Color.pink);
							this.map[i][j].setText(this.front[i][j]+"");
						}
						this.gameNum ++;
						winCheck();
					}
				}
			}
		}
	}

	private void winCheck() {
		if(this.gameNum > 50) {
			this.isRun = false;
			new AlertResult(this.ms); // 성적출력
		}
	}

	@Override
	public void run() {
		while(true) {
			if(this.isRun) {
				this.ms ++;
				this.timer.setText(String.format("%5d.%3d", this.ms/1000, this.ms%1000));
				
			}
			try {
				Thread.sleep(1);
			} catch (Exception e) {
			}
		}
	}
}


class Game extends JFrame{
	public static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	public static int width = dm.width;
	public static int height = dm.height;
	public static final int SIZE = 700;
	private GamePanel panel = new GamePanel();
	
	public Game() {
		super("1 to 50");
		setLayout(null);
		setBounds(width/2-SIZE/2, height/2-SIZE/2, SIZE, SIZE);
		setBackground(Color.white);
		add(this.panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		revalidate();
		
		this.panel.run();
	}
	
}

public class Ex03_1 {

	public static void main(String[] args) {

		Game game = new Game();
	}

}
