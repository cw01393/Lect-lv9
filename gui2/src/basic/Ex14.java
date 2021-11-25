package basic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JPanel;
import javax.swing.JLabel;

class Horse {
	private int x;
	private int y;
	private int win;
	private ImageIcon icon;
	
	public Horse(int x, int y, ImageIcon icon) {
		this.x = x;
		this.y = y;
		this.icon = icon;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getWin() {
		return win;
	}
	
	public void setWin(int win) {
		this.win = win;
	}
	
	public ImageIcon getIcon() {
		return icon;
	}
}


class HorsePanel extends MyUtil implements Runnable{
	private final int HORSECOUNT = 5;
	private Horse[] horse = new Horse[HORSECOUNT];
	private JButton start = new JButton();
	private JLabel timer = new JLabel();
	
	private int ms;
	private boolean timeRun;
	private boolean finish;
	
	public HorsePanel() {
		setLayout(null);
		setBounds(0, 0, 800, 600);
		setHorse();
		setButton();
		setTimer();
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
		this.start.setFont(new Font("",Font.BOLD,20));
		this.start.addMouseListener(this);
		add(this.start);
	}

	private void setHorse() {
		int x = 10;
		int y = 80;
		for(int i=0; i<HORSECOUNT; i++) {
			String path = String.format("images/horse%d.png", i+1);
			Image im = new ImageIcon(path).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			ImageIcon c = new ImageIcon(im);
			this.horse[i] = new Horse(x,y,c);
			y += 100;
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			
			if(target == this.start) {
				this.timeRun = true;
				System.out.println("run");
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i=0; i<HORSECOUNT; i++) {
			Horse h = this.horse[i];
			g.drawImage(h.getIcon().getImage(), h.getX(), h.getY(), null);
		}
		
		repaint();
	}

	@Override
	public void run() {
		while(true) {
			if(this.timeRun) {
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

class HorseRacing extends JFrame{
	private HorsePanel panel = new HorsePanel();
	
	public HorseRacing() {
		super("HORSE RACING");
		setLayout(null);
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(this.panel);
		setVisible(true);
		revalidate();
		
		this.panel.run();
	}
}

public class Ex14 {

	public static void main(String[] args) {
		new HorseRacing();
	}

}
