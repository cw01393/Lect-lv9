package basic;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

class Clock extends JFrame{
	private JLabel timer = new JLabel();
	
	public Clock() {
		super("Clock");
		setLayout(null);
		setBounds(100,100,400,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setTimer();
		setVisible(true);
		revalidate();
	}
	
	private void setTimer() {
		this.timer.setBounds(0,0,400,400);
		this.timer.setText("ready");
		this.timer.setFont(new Font("",Font.BOLD, 50));
		this.timer.setHorizontalAlignment(JLabel.CENTER);
		this.timer.setVerticalAlignment(JLabel.CENTER);
		add(this.timer);
	}
	
	public void setTime(int time) {
		this.timer.setText(String.format("%3d.%3d", time/1000, time%1000));
	}
}

public class Ex03_hint {

	public static void main(String[] args) {

		Clock clock = new Clock();
		
		int n = 0;
		while(true) {
			n ++;
			clock.setTime(n);
			
			try {
				Thread.sleep(1);
			} catch (Exception e) {
			}
		}
	}

}
