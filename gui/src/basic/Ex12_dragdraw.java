package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Nemo2 {
	int x,y,w,h;
	
	public Nemo2() {}
	public Nemo2(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getW() {
		return w;
	}
	public int getH() {
		return h;
	}
}

class DrawPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener{
	
	private ArrayList<Nemo2> rectGroup = new ArrayList<Nemo2>();
	private Nemo2 rect = new Nemo2();
	private JButton reset = new JButton("reset");
	
	private int startX;
	private int startY;
	private int nowX;
	private int nowY;
	private boolean isDrawing;
	private boolean shiftKey;
	
	public DrawPanel() {
		setLayout(null);
		setBounds(0, 0, 800, 600);
		setBackground(Color.white);
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setResetButton();
	}
	
	private void setResetButton() {
		this.reset.setBounds(650,500,100,50);
		this.reset.setBackground(Color.gray);
		this.reset.addMouseListener(this);
		add(this.reset);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(this.rectGroup.size() > 0) {
			g.setColor(Color.blue);
			for(int i=0; i<this.rectGroup.size(); i++) {
				Nemo2 r = this.rectGroup.get(i);
				g.drawRect(r.getX(), r.getY(), r.getW(), r.getH());
			}
		}
		
		if(this.isDrawing) g.setColor(Color.gray);
		else g.setColor(Color.blue);
		g.drawRect(this.rect.getX(), this.rect.getY(), this.rect.getW(), this.rect.getH());
		
		requestFocusInWindow();
		repaint();
	}
	
	private void drawDownRight() { //this.nowX > this.startX && this.nowY > this.startY
		int w = this.nowX - this.startX;
		int h = this.nowY - this.startY;
		if(this.shiftKey) {
			w = w >= h ? w : h;
			h = w >= h ? w : h;
		}
		int x = this.startX;
		int y = this.startY;
			this.rect = new Nemo2(x, y, w, h);
	}
	private void drawUpRight() { // this.nowX > this.startX && this.nowY < this.startY
		int w = this.nowX - this.startX;
		int h = this.startY - this.nowY;
		if(this.shiftKey) {
			w = w >= h ? w : h;
			h = w >= h ? w : h;
		}
		int x = this.startX;
		int y = this.startY - h;
		this.rect = new Nemo2(x, y, w, h);
	}
	private void drawDownLeft() { // this.nowX < this.startX && this.nowY > this.startY
		int w = this.startX - this.nowX;
		int h = this.nowY - this.startY;
		if(this.shiftKey) {
			w = w >= h ? w : h;
			h = w >= h ? w : h;
		}
		int x = this.startX - w;
		int y = this.startY;
		this.rect = new Nemo2(x, y, w, h);
	}
	private void drawUpLeft() { // this.nowX < this.startX && this.nowY < this.startY
		int w = this.startX - this.nowX;
		int h = this.startY - this.nowY;
		if(this.shiftKey) {
			w = w >= h ? w : h;
			h = w >= h ? w : h;
		}
		int x = this.startX - w;
		int y = this.startY - h;
		this.rect = new Nemo2(x, y, w, h);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.nowX = e.getX();
		this.nowY = e.getY();
		if(this.isDrawing) {
			if(this.nowX > this.startX && this.nowY > this.startY) {
				drawDownRight();
			}
			else if(this.nowX > this.startX && this.nowY < this.startY) {
				drawUpRight();
			}
			else if(this.nowX < this.startX && this.nowY > this.startY) {
				drawDownLeft();
			}
			else if(this.nowX < this.startX && this.nowY < this.startY) {
				drawUpLeft();
			}
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
		if(e.getSource() == this.reset) {
			resetDraw();
		}
		else {
			this.startX = e.getX();
			this.startY = e.getY();
			this.isDrawing = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.isDrawing = false;
		this.rectGroup.add(this.rect);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			this.shiftKey = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.shiftKey = this.shiftKey ? false : this.shiftKey;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	private void resetDraw() {
		this.rect = new Nemo2();
		this.rectGroup.clear();
		this.isDrawing = false;
		this.shiftKey = false;
	}
}

class DrawFrame extends JFrame{
	public DrawFrame() {
		super("DRAW");
		setLayout(null);
		setBounds(50, 50, 800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new DrawPanel());
		setVisible(true);
		revalidate();
	}
}

public class Ex12_dragdraw {

	public static void main(String[] args) {
		DrawFrame drawRect = new DrawFrame();
	}

}
