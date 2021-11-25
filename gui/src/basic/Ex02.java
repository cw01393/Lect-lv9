package basic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// gui 예제
// Tic Tac Toe 만들기
// 버튼 9개를 가진 배열 활용
// 마킹용 int[]배열 활용
// 턴에 따라 버튼의 색이 다르게 지정됨

class MyButton extends JButton{
	private boolean click;
	
	public boolean getClick() {
		return this.click;
	}
	public void setClick(boolean click) {
		this.click = click;
	}
}

class MyPanel2 extends JPanel implements ActionListener{
	private MyButton[] bt = new MyButton[9];
	private int[] mark = new int[9];
	private int turn = 1;
	private int winner;
	
	public MyPanel2() {
		setLayout(null);
		setBounds(0,0,300,300);

		int x = 0;
		int y = 0;
		for(int i=0; i<this.bt.length; i++) {
			this.bt[i] = new MyButton();
			this.bt[i].setText((i+1)+"");
			
			this.bt[i].setBounds(x,y,100,100);
			this.bt[i].setBackground(Color.white);
			this.bt[i].addActionListener(this);
			x += 100;
			if(x == 300) x = 0;
			if(i % 3 == 2) y += 100;
			add(this.bt[i]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.winner == 0) {
			Color color = null;
			if(this.turn == 1) color = Color.LIGHT_GRAY;
			else color = Color.pink;
			for(int i=0; i<this.bt.length; i++) {
				if(e.getSource() == this.bt[i] && !this.bt[i].getClick()) {
					this.bt[i].setClick(true);
					this.bt[i].setBackground(color);
					
					this.mark[i] = this.turn;
					
					checkWin();
					this.turn = this.turn == 1 ? 2 : 1;
				}
			}
		}
		else {
			System.out.printf("GAME OVER! WINNER is PLAYER %d\n",this.winner);
		}
	}
	
	private void checkWin() {
		checkGaro();
		checkSero();
		checkDownToUp();
		checkUpToDown();
		
		if(this.winner != 0) {
			System.out.printf("GAME OVER! WINNER is PLAYER %d\n",this.winner);
		}
	}
	
	private void checkGaro() {
		for(int i=0; i<this.mark.length; i+=3) {
			int cnt = 0;
			for(int j=0; j<3; j++) {
				if(this.mark[i+j] == this.turn) cnt ++;
			}
			if(cnt == 3) this.winner = this.turn;
		}
	}
	
	private void checkSero() {
		for(int i=0; i<3; i++) {
			int cnt = 0;
			for(int j=0; j<=6; j+=3) {
				if(this.mark[i+j] == this.turn) cnt ++;
			}
			if(cnt == 3) this.winner = this.turn;
		}
	}
	private void checkDownToUp() {
		int cnt = 0;
		for(int i=2; i<this.mark.length-1; i+=2) {
			if(this.mark[i] == this.turn) cnt ++;
		}
		if(cnt == 3) this.winner = this.turn;
	}
	private void checkUpToDown() {
		int cnt = 0;
		for(int i=0; i<this.mark.length; i+=4) {
			if(this.mark[i] == this.turn) cnt ++;
		}
		if(cnt == 3) this.winner = this.turn;
	}
}

class MyFrame2 extends JFrame{
	public MyFrame2() {
		setLayout(null);
		setTitle("TicTacToe");
		setBounds(50,50,300,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new MyPanel2());
		setVisible(true);
		revalidate();
	}
}

public class Ex02 {

	public static void main(String[] args) {

		MyFrame2 game = new MyFrame2();
	}

}
