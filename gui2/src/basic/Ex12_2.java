package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

class GrimRect{
	int x,y,w,h;
	Color c;

	public GrimRect(int x, int y, int w, int h, Color c) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.c = c;
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
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}
}

class GrimBoard extends MyUtil{
	
	private ArrayList<GrimRect> rects = new ArrayList<GrimRect>();
	private ArrayList<GrimRect> circle = new ArrayList<GrimRect>();
	private ArrayList<GrimRect> triangle = new ArrayList<GrimRect>();
	
	private GrimRect rect = null;
	private int startX, startY;
	private boolean shift;
	private int shape = -1;
	
	private final int REC = 0;
	private final int CIR = 1;
	private final int TRI = 2;
	
	String[] btnText = {"ㅁ","ㅇ","ㅅ"};
	JButton[] btn = new JButton[3];
	
	public GrimBoard() {
		setLayout(null);
		setBounds(0, 0, 700, 700);
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		setButton();
	}
	
	private void setButton() {
		int x = 10;
		int y = 10;
		for(int i=0; i<this.btn.length; i++) {
			JButton b = new JButton();
			b.setText(this.btnText[i]);
			b.setBounds(x,y,50,50);
			b.setBackground(Color.white);
			b.addMouseListener(this);
			this.btn[i] = b;
			add(this.btn[i]);
			x += 50 + 2;
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		

		// sample triangle
		// g.drawPolygon(int[], int[], int)
		// 1. x좌표의 배열
		// 2. y좌표의 배열
		// 3. 꼭지점 개수
		
//		int[] xx = new int[3];
//		int[] yy = new int[3];
//		xx[0] = 100;
//		yy[0] = 100;
//		xx[1] = 150;
//		yy[1] = 200;
//		xx[2] = 50;
//		yy[2] = 200;
//		
//		g.setColor(Color.green);
//		g.drawPolygon(xx, yy, 3);
		
		if(this.rect != null) {
			GrimRect r = this.rect;
			
			g.setColor(r.getC());
			if(this.shape == REC) {
				g.drawRect(r.getX(), r.getY(), r.getW(), r.getH());
			}
			else if(this.shape == CIR) {
				g.drawRoundRect(r.getX(), r.getY(), r.getW(), r.getH(), r.getW(), r.getH());
			}
			else if(this.shape == TRI) {
				int[] xx = {r.getX(), r.getX()+r.getW(), r.getX()+r.getW()/2};
				int[] yy = {r.getY()+r.getH(), r.getY()+r.getH(), r.getY()};
				g.drawPolygon(xx,yy,3);
			}
		}
		
		//rects
		for(int i=0; i<this.rects.size(); i++) {
			GrimRect r = this.rects.get(i);
			g.setColor(r.getC());
			g.drawRect(r.getX(), r.getY(), r.getW(), r.getH());
		}
		//circle
		for(int i=0; i<this.circle.size(); i++) {
			GrimRect r = this.circle.get(i);
			g.setColor(r.getC());
			g.drawRoundRect(r.getX(), r.getY(), r.getW(), r.getH(),r.getW(), r.getH());
		}
		for(int i=0; i<this.triangle.size(); i++) {
			GrimRect r = this.triangle.get(i);
			g.setColor(r.getC());
			int[] xx = {r.getX(), r.getX()+r.getW(), r.getX()+r.getW()/2};
			int[] yy = {r.getY()+r.getH(), r.getY()+r.getH(), r.getY()};
			g.drawPolygon(xx,yy,3);
		}
		
		requestFocusInWindow();
		repaint();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			this.shift = true;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		this.shift = false;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		this.startX = e.getX();
		this.startY = e.getY();
		
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			for(int i=0; i<this.btn.length; i++) {
				if(target == this.btn[i]) {
					this.shape = i;
				}
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(this.rect != null) {
			this.rect.setC(Color.blue);
			
			if(this.shape == REC) {
				this.rects.add(this.rect);
			}
			else if(this.shape == CIR) {
				this.circle.add(this.rect);
			}
			else if(this.shape == TRI) {
				this.triangle.add(this.rect);
			}
			this.rect = null;
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(this.shape != -1) {
			int x = e.getX();
			int y = e.getY();
			
			int w = Math.abs(x - startX); // 절대값으로 초기화
			int h = Math.abs(y - startY);
			
			if(this.shift) {
				w = h;
			}
			
			int rX = startX;
			int rY = startY;
			
			if(x < startX) rX = startX - w;
			if(y < startY) rY = startY - h;
			
			this.rect = new GrimRect(rX, rY, w, h, Color.red);
		}
	}
	
}

class DrawBoard extends JFrame{
	public DrawBoard() {
		super("DRAW");
		setLayout(null);
		setBounds(50, 50, 700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new GrimBoard());
		setVisible(true);
		revalidate();
	}
}

public class Ex12_2 {

	public static void main(String[] args) {
		DrawBoard drawRect = new DrawBoard();
	}

}

