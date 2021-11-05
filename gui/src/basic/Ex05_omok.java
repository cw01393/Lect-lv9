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


class OmokResult extends JFrame{
	private JLabel resultText = new JLabel();
	
	public OmokResult(String str) {
		super("Result");
		setLayout(null);
		setBounds(Omok.width/2-200/2, Omok.height/2-100/2, 200, 100);
		this.resultText.setText(str);
		this.resultText.setBounds(0, 0, 200, 100);
		this.resultText.setHorizontalAlignment(JLabel.CENTER);
		this.resultText.setVerticalAlignment(JLabel.CENTER);
		add(this.resultText);
		setVisible(true);
		revalidate();
	}
	
}

class OmokPanel extends JPanel implements ActionListener{
	
	private final int mapSize = 10;
	private JLabel title = new JLabel();
	private JButton reset = new JButton("reset");
	private JButton[][] map = new JButton[this.mapSize][this.mapSize];
	private int[][] check = new int[this.mapSize][this.mapSize];
	private int turn = 1;
	private int winner;
	
	public OmokPanel() {
		setLayout(null);
		setBounds(0, 0, Omok.SIZE, Omok.SIZE);
		setTitle();
		setMap();
		setReset();
	}
	
	private void setReset(){
		this.reset.setBounds(Omok.SIZE/2-50*this.mapSize/2, 60, 70, 30);
		this.reset.setBackground(Color.white);
		this.reset.addActionListener(this);
		add(this.reset);
	}
	
	private void setTitle() {
		this.title.setBounds(0,0,Omok.SIZE,60);
		this.title.setText("오목게임");
		this.title.setFont(new Font("",Font.BOLD,25));
		this.title.setHorizontalAlignment(JLabel.CENTER);
		this.title.setVerticalAlignment(JLabel.BOTTOM);
		add(this.title);
	}
	
	private void setMap() {
		int x = Omok.SIZE/2-50*this.mapSize/2;
		int y = Omok.SIZE/2-50*this.mapSize/2;
		
		for(int i=0; i<this.mapSize; i++) {
			for(int j=0; j<this.mapSize; j++) {
				this.map[i][j] = new JButton();
				this.map[i][j].setBounds(x, y, 50, 50);
				this.map[i][j].setBackground(Color.gray);
				this.map[i][j].addActionListener(this);
				add(this.map[i][j]);
				x += 50+2;
			}
			x = Omok.SIZE/2-50*this.mapSize/2;
			y += 50+2;
		}
	}
	
	private void resetGame() {
		this.turn = 1;
		this.winner = 0;
		this.check = new int[this.mapSize][this.mapSize];
		for(int i=0; i<this.mapSize; i++) {
			for(int j=0; j<this.mapSize; j++) {
				this.map[i][j].setBackground(Color.gray);
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
		else {
			for(int i=0; i<this.mapSize; i++) {
				for(int j=0; j<this.mapSize; j++) {
					if(this.winner == 0 &&
							target == this.map[i][j] && this.check[i][j] == 0) {
						this.check[i][j] = this.turn;
						Color color;
						if(this.turn == 1) color = Color.pink;
						else color = Color.orange;
						this.map[i][j].setBackground(color);
						
						checkWin();
						this.turn = this.turn == 1 ? 2 : 1;
					}
				}
			}
		}
	}
	
	private void checkWin() {
		this.winner = this.winner == 0 ? checkGaro() : this.winner;
		this.winner = this.winner == 0 ? checkSero() : this.winner;
		this.winner = this.winner == 0 ? checkUpDiag() : this.winner;
		this.winner = this.winner == 0 ? checkDownDiag() : this.winner;
		
		if(this.winner != 0) {
			new OmokResult(String.format("p%d의 승리!", this.winner));
		}
	}
	
	private int checkGaro() {
		for(int i=0; i<this.mapSize; i++) {
			for(int j=0; j<this.mapSize; j++) {
				int cnt = 0;
				if(this.check[i][j] == this.turn && j<= this.mapSize-5) {
					for(int k=0; k<5; k++) {
						if(this.check[i][j+k] == this.turn) cnt ++;
					}
					if(cnt >= 5) {
						return this.turn;
					}
				}
			}
		}
		return 0;
	}
	private int checkSero() {
		for(int i=0; i<this.mapSize; i++) {
			for(int j=0; j<this.mapSize; j++) {
				int cnt = 0;
				if(this.check[i][j] == this.turn && i <= this.mapSize-5) {
					for(int k=0; k<5; k++) {
						if(this.check[i+k][j] == this.turn) cnt ++;
					}
					if(cnt >= 5) {
						return this.turn;
					}
				}
			}
		}
		return 0;
	}
	private int checkUpDiag() {
		for(int i=0; i<this.mapSize; i++) {
			for(int j=0; j<this.mapSize; j++) {
				int cnt = 0;
				if(this.check[i][j] == this.turn && j >= 4 && i <= this.mapSize-5) {
					for(int k=0; k<5; k++) {
						if(this.check[i+k][j-k] == this.turn) cnt ++;
					}
					if(cnt >= 5) {
						return this.turn;
					}
				}
			}
		}
		
		return 0;
	}
	private int checkDownDiag() {
		for(int i=0; i<this.mapSize; i++) {
			for(int j=0; j<this.mapSize; j++) {
				int cnt = 0;
				if(this.check[i][j] == this.turn && i <= this.mapSize-5 && j <= this.mapSize-5) {
					for(int k=0; k<5; k++) {
						if(this.check[i+k][j+k] == this.turn) cnt ++;
					}
					if(cnt >= 5) {
						return this.turn;
					}
				}
			}
		}
		return 0;
	}
	
}

class Omok extends JFrame{
	
	public static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	public static int width = dm.width;
	public static int height = dm.height;
	public static final int SIZE = 700;
	private OmokPanel panel = new OmokPanel();
	
	public Omok() {
		super("OMOK GAME");
		setLayout(null);
		setBounds(width/2-SIZE/2, height/2-SIZE/2, SIZE, SIZE);
		setBackground(Color.white);
		add(this.panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		revalidate();
	}
}

public class Ex05_omok {

	public static void main(String[] args) {
		
		Omok game = new Omok();
	}

}
