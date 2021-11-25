package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 숙제
// 화면에 라벨제목 붙이기
// 밑에 리셋버튼 달기

class ResultFrame extends JFrame{
	
	private JLabel text = new JLabel();
	
	public ResultFrame(String winText) {
		super("Game Clear");
		setLayout(null);
		setBounds(TicFrame.width/2-200/2,TicFrame.height/2-100/2,200,100);
		
		text.setBounds(0,0,200,100);
		text.setText(winText);
		text.setHorizontalAlignment(JLabel.CENTER);
		add(text);
		
		setVisible(true);
		revalidate();
	}
}

class TicGame extends JPanel implements ActionListener{
	
	private JLabel title = new JLabel("Tic Tac Toe");
	private JButton reset = new JButton("start");
	private JButton[] map = new JButton[9];
	private int[] mark = new int[9];
	
	private int turn = 1;
	private int winner;
	
	public TicGame() {
		setLayout(null);
		setBounds(0,0,TicFrame.SIZE,TicFrame.SIZE);
		
		setTitle();
		setResetButton();
		
		setMap();
	}
	
	private void setResetButton() {
		this.reset.setBounds(TicFrame.SIZE/2-40, TicFrame.SIZE-80, 80, 50);
		this.reset.setBackground(Color.white);
		this.reset.addActionListener(this);
		add(this.reset);
	}
	
	private void resetGame() {
		this.mark = new int[9];
		this.turn = 1;
		this.winner = 0;
		for(int i=0; i<this.map.length; i++) {
			this.map[i].setBackground(new Color(254, 241, 230));
		}
	}

	private void setTitle() {
		this.title.setBounds(0,20,TicFrame.SIZE,50);
		this.title.setFont(new Font("", Font.BOLD, 20));
		this.title.setHorizontalAlignment(JLabel.CENTER);
		this.title.setVerticalAlignment(JLabel.BOTTOM);
		add(this.title);
	}

	private void setMap() {
		int x = TicFrame.SIZE/2-100*3/2;
		int y = TicFrame.SIZE/2-100*3/2;
		for(int i=0; i<this.map.length; i++) {
			this.map[i] = new JButton();
			this.map[i].setBounds(x,y,100,100);
			this.map[i].setBackground(new Color(254, 241, 230));
			this.map[i].addActionListener(this);
			add(this.map[i]);
			
			x += 100 + 3;
			if(i % 3 == 2) {
				x = TicFrame.SIZE/2-100*3/2;
				y += 100 + 3;
			}
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton) e.getSource();
		if(target == this.reset) {
			resetGame();
			return;
		}
		for(int i=0; i<this.map.length; i++) {
			if(target == this.map[i] && this.mark[i] == 0) {
				if(this.reset.getText().equals("start")) {
					this.reset.setText("reset");
				}
				
				if(this.turn == 1) {
					target.setBackground(new Color(255, 176, 133));
				}
				else {
					target.setBackground(new Color(144, 170, 203));
				}
				
				this.mark[i] = this.turn;
				checkWin();
				this.turn = this.turn == 1 ? 2 : 1;
			}
		}
	}

	private void checkWin() {
		this.winner = this.winner == 0 ? checkHori() : this.winner;
		this.winner = this.winner == 0 ? checkSero() : this.winner;
		this.winner = this.winner == 0 ? checkDia() : this.winner;
		this.winner = this.winner == 0 ? checkRev() : this.winner;
		
		if(this.winner != 0) {
//			System.out.printf("p%d 승리!\n",this.winner);
			new ResultFrame(String.format("p%d 승리!\n",this.winner));
		}
	}

	private int checkRev() {
		int cnt = 0;
		for(int i=2; i<=6; i+=2) {
			if(this.mark[i] == this.turn) cnt ++;
		}
		if(cnt == 3) return this.turn;
		return 0;
	}

	private int checkDia() {
		int cnt = 0;
		for(int i=0; i<this.mark.length; i+=4) {
			if(this.mark[i] == this.turn) cnt ++;
		}
		if(cnt == 3) return this.turn;
		
		return 0;
	}

	private int checkSero() {
		for(int i=0; i<3; i++) {
			int cnt = 0;
			for(int j=0; j<=6; j+=3) {
				if(this.mark[i+j] == this.turn) cnt ++;
			}
			if(cnt == 3) return this.turn;
		}
		return 0;
	}

	private int checkHori() {
		for(int i=0; i<this.mark.length; i+=3) {
			int cnt = 0;
			for(int j=0; j<3; j++) {
				if(this.mark[i+j] == this.turn) cnt ++;
			}
			if(cnt == 3) return this.turn;
		}
		return 0;
	}
}


class TicFrame extends JFrame{
	
	public static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	public static int width = dm.width;
	public static int height = dm.height;
	public static final int SIZE = 500;
	
	public TicFrame() {
		super("TIC TAC TOE");
		setLayout(null);
		setBounds(this.width/2-this.SIZE/2, this.height/2-this.SIZE/2, this.SIZE, this.SIZE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new TicGame());
		setVisible(true);
		revalidate();
	}
}


public class Ex02_1 {

	public static void main(String[] args) {

		TicFrame game = new TicFrame();
	}

}
