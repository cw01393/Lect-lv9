package race;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Content extends MyUtil implements Runnable{

	private final int SIZE = 5;
	private final int WIDTH = 120;
	private final int HEIGHT = 80;
	private Horse[] horse = new Horse[SIZE];
	private JButton start = new JButton();
	private JLabel timer = new JLabel();
	private JLabel[] record = new JLabel[SIZE];
	
	private int ms;
	private boolean timeRun;
	private boolean finish;
	private int rank = 1;
	
	public Content() {
		setLayout(null);
		setBounds(0, 0, 1000, 600);
		setHorse();
		setButton();
		setTimer();
		setLabel();
	}
	
	private void setLabel() {
		
	}
	
	private void setTimer() {
		this.timer.setBounds(200, 10, 100, 50);
		this.timer.setText("0.00");
		this.timer.setHorizontalAlignment(JLabel.LEFT);
		add(this.timer);
	}
	
	private void setButton() {
		this.start.setBounds(100,10,80,50);
		this.start.setBackground(Color.white);
		this.start.setText("start");
		this.start.setFont(new Font("",Font.BOLD,15));
		this.start.addMouseListener(this);
		add(this.start);
	}

	private void setHorse() {
		int x = 0;
		int y = 80;
		for(int i=0; i<SIZE; i++) {
			this.horse[i] = new Horse(i+1,x,y,WIDTH,HEIGHT);
			y += 100;
		}
	}
	
	private void race() {
		Random rn = new Random();
		
		for(int i=0; i<SIZE; i++) {
			Horse h = this.horse[i];
			if(h.getState() == 2) continue;
			int rN = rn.nextInt(2);
			int xx = h.getX() + rN;
			if(xx >= 1000-WIDTH) {
				String record = String.format("%3d.%3d", this.ms/1000, this.ms%1000);
				
				if(checkDup(i,record)) {
					i--;
					continue;
				}
				else {
					xx = 1000-WIDTH;
					h.setRank(this.rank);
					h.setState(2);
					h.setRecord(String.format("%3d.%3d", this.ms/1000, this.ms%1000));
					this.rank ++;
				}
			}
			h.setX(xx);
		}
		checkFinish();
	}
	
	private boolean checkDup(int index, String record) {
		for(int i=0; i<index; i++) {
			if(this.horse[i].getState() == 2 
					&& this.horse[i].getRecord().equals(record)) return true;
		}
		return false;
	}
	
	private void checkFinish() {
		int cnt = 0;
		for(int i=0; i<SIZE; i++) {
			if(this.horse[i].getState() == 2) cnt ++;
		}
		if(cnt == SIZE) {
			this.timeRun = false;
			this.finish = true;
			this.start.setText("reset");
		}
	}
	
	private void reset() {
		setHorse();
		this.finish = false;
		this.timeRun = false;
		this.ms = 0;
		this.rank = 1;
		this.timer.setText("0.00");
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			
			if(target == this.start) {
				if(!this.finish) {
					this.timeRun = true;
					for(int i=0; i<SIZE; i++) {
						this.horse[i].setState(1);
					}
				}
				else {
					this.start.setText("start");
					reset();
				}
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i=0; i<SIZE; i++) {
			Horse h = this.horse[i];
			g.drawLine(0, h.getY()+HEIGHT, 1000, h.getY()+HEIGHT);
			g.drawImage(h.getImage().getImage(), h.getX(), h.getY(), null);
		}
		for(int i=0; i<SIZE; i++) {
			Horse h = this.horse[i];
			if(h.getRank() != 0) {
				g.drawString(String.format("%d등, %s", h.getRank(), h.getRecord()), h.getX()-100, h.getY()+40);
			}
		}
		repaint();
	}

	@Override
	public void run() {
		while(true) {
			if(this.timeRun) {
				race();
				this.ms ++;
				this.timer.setText(String.format("%3d,%3d", this.ms/1000, this.ms%1000));
				
			}
			try {
				Thread.sleep(1);
			} catch (Exception e) {
			}
		}
	}
	
}
