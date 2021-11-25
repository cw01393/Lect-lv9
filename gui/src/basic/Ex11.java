package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

class MovingRect{
	int x,y,w,h;
	
	public MovingRect(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
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
	public int getW() {
		return w;
	}
	public int getH() {
		return h;
	}
}

class MovingPanel extends JPanel implements MouseMotionListener,MouseListener{
	private final int SIZE = 50; 
	private MovingRect nemo;
	private boolean drag;
	private int x = -1;
	private int y = -1;
	
	public MovingPanel() {
		setLayout(null);
		setBounds(0, 0, 600, 600);
		setBackground(Color.white);
		addMouseMotionListener(this);
		addMouseListener(this);
		setNemo();
	}
	
	private void setNemo() {
		Random rn = new Random();
		int rX = rn.nextInt(600-SIZE);
		int rY = rn.nextInt(600-SIZE);
		this.nemo = new MovingRect(rX,rY,SIZE,SIZE);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(this.drag) 
			g.setColor(Color.red);
		else 
			g.setColor(Color.black);
		g.drawRect(this.nemo.getX(), this.nemo.getY(), this.nemo.getW(), this.nemo.getH());
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
//		if(this.x >= this.nemo.getX() && this.x < this.nemo.getX()+SIZE
//				&& this.y >= this.nemo.getY() && this.y < this.nemo.getY()+SIZE) {
		if(this.drag) {
			this.nemo.setX(this.nemo.getX()-(this.x-e.getX()));
			this.nemo.setY(this.nemo.getY()-(this.y-e.getY()));
			this.x = e.getX();
			this.y = e.getY();
			this.drag = true;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getX() >= this.nemo.getX() && e.getX() < this.nemo.getX()+SIZE
				&& e.getY() >= this.nemo.getY() && e.getY() < this.nemo.getY()+SIZE) {
			this.x = e.getX();
			this.y = e.getY();
			this.drag = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.drag = false;
	}
}

class MovingFrame extends JFrame{
	public MovingFrame() {
		super("MOVE MOVE");
		setLayout(null);
		setBounds(50,50,600,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new MovingPanel());
		setVisible(true);
		revalidate();
	}
}

public class Ex11 {

	public static void main(String[] args) {
		MovingFrame move = new MovingFrame();
	}

}
