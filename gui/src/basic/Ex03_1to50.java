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

class Panel1to50 extends JPanel implements ActionListener, Runnable{

	private JButton[] map = new JButton[25];
	private JButton reset = new JButton("reset");
	private JLabel timer = new JLabel();
	private JLabel title = new JLabel();
	private int[] back;
	private int gameNum = 1;
	private int ms;
	private boolean timeRun;
	
	public Panel1to50() {
		setLayout(null);
		setBounds(0, 0, Frame1to50.SIZE, Frame1to50.SIZE);
		setTitle();
		setMap();
		setNum();
		setReset();
		setTimer();
	}
	
	private void setTitle() {
		this.title.setBounds(0,0,Frame1to50.SIZE,100);
		this.title.setText("1 to 50");
		this.title.setFont(new Font("", Font.BOLD, 25));
		this.title.setHorizontalAlignment(JLabel.CENTER);
		this.title.setVerticalAlignment(JLabel.BOTTOM);
		add(this.title);
	}
	
	private void setTimer() {
		this.timer.setBounds(0,0,200,100);
		this.timer.setText("start");
		this.timer.setHorizontalAlignment(JLabel.CENTER);
		this.timer.setVerticalAlignment(JLabel.CENTER);
		add(this.timer);
	}
	
	public void setTimeText(int n) {
		this.timer.setText(String.format("%3d.%3d",n/1000,n%1000));
	}
	
	private void setReset() {
		this.reset.setBounds(Frame1to50.SIZE/2-100/2+4, Frame1to50.SIZE-100, 100, 30);
		this.reset.setBackground(Color.white);
		this.reset.addActionListener(this);
		add(this.reset);
	}
	
	private void reset() {
		this.timeRun = false;
		this.gameNum = 1;
		this.ms = 0;
		this.timer.setText("start");
		
		setNum();
		for(int i=0; i<this.map.length; i++) {
			this.map[i].setBackground(new Color(201, 150, 204));
		}
	}
	
	private void setNum() {
		int num[] = new int[25];
		this.back = new int[25];
		for(int i=1; i<=50; i++) {
			if(i <= 25) {
				num[i-1] = i;
			}
			else {
				this.back[i-26] = i;
			}
		}
		shuffle(num);
		shuffle(this.back);
		
		for(int i=0; i<this.map.length; i++) {
			this.map[i].setText(num[i]+"");
		}
	}
	
	private void shuffle(int arr[]) {
		Random rn = new Random();
		
		for(int i=0; i<1000; i++) {
			int rIdx = rn.nextInt(arr.length);
			int temp = arr[0];
			arr[0] = arr[rIdx];
			arr[rIdx] = temp;
		}
	}
	
	private void setMap() {
		int x = Frame1to50.SIZE/2-(50*5)/2;
		int y = Frame1to50.SIZE/2-(50*5)/2;
		
		for(int i=0; i<this.map.length; i++) {
			this.map[i] = new JButton();
			this.map[i].setBounds(x,y,50,50);
			this.map[i].setBackground(new Color(201, 150, 204));
			this.map[i].addActionListener(this);
			add(this.map[i]);
			
			x += 50+2;
			if(i % 5 == 4) {
				y += 50+2;
				x = Frame1to50.SIZE/2-(50*5)/2;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton) e.getSource();
		if(target == this.reset) {
			reset();
			return;
		}
		for(int i=0; i<this.map.length; i++) {
			if(target == this.map[i] && this.map[i].getText().equals(this.gameNum+"")) {
				if(this.gameNum <= this.map.length) {
					if(this.gameNum == 1) timeRun = true;
					this.map[i].setText(this.back[i] + "");
					this.map[i].setBackground(new Color(145, 107, 191));
				}
				else {
					this.map[i].setText("");
					this.map[i].setBackground(Color.white);
				}
				this.gameNum ++;
				checkEnd();
			}
		}
	}
	
	private void checkEnd() {
		if(this.gameNum > 50) {
			this.timeRun = false;
			new ResultFrame1to50(this.ms);
		}
	}

	@Override
	public void run() {
		while(true) {
			if(this.timeRun) {
				ms ++;
				setTimeText(this.ms);
			}
			try {
				Thread.sleep(1);
			} catch (Exception e) {
			}
		}
	}
}

class ResultFrame1to50 extends JFrame{
	private JLabel text = new JLabel();
	
	public ResultFrame1to50(int ms) {
		super("Game Clear");
		setLayout(null);
		setBounds(Frame1to50.width/2-200/2, Frame1to50.height/2-100/2, 200, 100);
		setBackground(Color.white);
		this.text.setBounds(0,0,200,100);
		this.text.setText(String.format("%3d.%3d초 소요!", ms/1000, ms%1000));
		this.text.setHorizontalAlignment(JLabel.CENTER);
		add(this.text);
		setVisible(true);
		revalidate();
	}
}

class Frame1to50 extends JFrame{
	
	public static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	public static int width = dm.width;
	public static int height = dm.height;
	public static final int SIZE = 500;
	private Panel1to50 panel = new Panel1to50();
	
	public Frame1to50() {
		super("1 to 50");
		setLayout(null);
		setBounds(width/2-SIZE/2, height/2-SIZE/2, SIZE, SIZE);
		setBackground(Color.white);
		add(this.panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		revalidate();
		panel.run();
	}
	
}

public class Ex03_1to50 {

	public static void main(String[] args) {
		Frame1to50 game = new Frame1to50();
		
	}
}
