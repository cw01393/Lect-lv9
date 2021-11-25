package race2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;

import race2.Horse;
import race2.MyUtil;

public class Content_1 extends MyUtil implements Runnable{
	
	Random rn = new Random();
	
	private final int SIZE = 5;
	private Horse[] horses = new Horse[SIZE];
	private JButton start = new JButton();

	private int startX = 30;
	private int endX = 1000-120;
	private int startY = 100;
	private boolean isRun;
	private int ms;
	private int rank;
	private JLabel timer = new JLabel();
	
	public Content_1() {
		setLayout(null);
		setBounds(0, 0, 1000, 600);
		setTimer();
		setHorse();
		setButton();
	}
	private void setButton() {
		this.start.setBounds(100, 50, 100, 30);
		this.start.setText("start");
		this.start.setBackground(Color.white);
		this.start.addActionListener(this);
		add(this.start);
	}
	
	private void resetGame() {
		setHorse();
		this.ms = 0;
		this.timer.setText("ready");
		this.rank = 1;
	}
	
	private void setHorse() {
		this.rank = 1;
		int x = this.startX;
		int y = this.startY;
		for(int i=0; i<SIZE; i++) {
			this.horses[i] = new Horse(i+1,x,y,120,80);
			y += 90;
		}
	}
	
	private void setTimer() {
		this.timer.setBounds(30, 50, 50, 30);
		this.timer.setText("ready");
		add(this.timer);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			
			if(target == this.start) {
				if(this.start.getText().equals("start")) {
					this.start.setText("reset");
					this.isRun = true;
				}
				else if(this.start.getText().equals("reset")) {
					this.start.setText("start");
					this.isRun = false;
					resetGame();
				}
			}
		}
	}
	
	private void update() {
		
		// 1번 update할때에 한마리만 골인할 수 있다
		boolean goal = false;
		
		for(int i=0; i<SIZE; i++) {
			Horse h = this.horses[i];
			int jump = rn.nextInt(10)*3;
			int xx = h.getX() + jump;
			
			if(h.getState() == h.RUN) {
				if(xx >= this.endX && !goal) {
					xx = this.endX;
					h.setState(h.GOAL);
					h.setRecord(String.format("%3d.%03d", this.ms/1000, this.ms%1000));
					h.setRank(this.rank);
					this.rank ++;
					goal = true;
				}
				else if(xx >= this.endX && goal){
					i--;
					continue;
				}
				h.setX(xx);
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i=0; i<SIZE; i++) {
			Horse h = this.horses[i];
			g.drawImage(h.getImage().getImage(), h.getX(), h.getY(), null);
			g.drawLine(this.startX, h.getY()+h.getH(), 1000-30, h.getY()+h.getH());
			
			if(h.getState() == h.GOAL) {
				g.setFont(new Font("", Font.BOLD, 20));
				g.drawString(h.getRank()+"등", this.endX-100, h.getY()+h.getH()/2);
				g.setFont(new Font("", Font.PLAIN, 10));
				g.drawString(h.getRecord(), this.endX-50, h.getY()+h.getH()/2);
			}
		}
		if(isRun) {
			try {
				Thread.sleep(50);
				update();
			} catch (Exception e) {
			}
		}
		repaint();
	}
	
	@Override
	public void run() {
		while(true) {
			if(isRun) {
				this.ms++;
				this.timer.setText(String.format("%3d.%03d", this.ms/1000, this.ms%1000));
			}
			try {
				Thread.sleep(1);
			} catch (Exception e) {
			}
		}
		
	}

}
