//package basic;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.event.KeyEvent;
//import java.awt.event.MouseEvent;
//import java.util.ArrayList;
//
//import javax.swing.JFrame;
//
//class GrimRect{
//	int x,y,w,h;
//	Color c;
//
//	public GrimRect(int x, int y, int w, int h, Color c) {
//		this.x = x;
//		this.y = y;
//		this.w = w;
//		this.h = h;
//		this.c = c;
//	}
//	public int getX() {
//		return x;
//	}
//	public int getY() {
//		return y;
//	}
//	public int getW() {
//		return w;
//	}
//	public int getH() {
//		return h;
//	}
//	public Color getC() {
//		return c;
//	}
//	public void setC(Color c) {
//		this.c = c;
//	}
//}
//
//class GrimBoard extends MyUtil{
//	
//	private ArrayList<GrimRect> rects = new ArrayList<GrimRect>();
//	
//	private GrimRect rect = null;
//	private int startX, startY;
//	private boolean shift;
//	
//	public GrimBoard() {
//		setLayout(null);
//		setBounds(0, 0, 700, 700);
//		addMouseMotionListener(this);
//		addMouseListener(this);
//	}
//	
//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		
//		if(this.rect != null) {
//			g.setColor(this.rect.getC());
//			g.drawRect(this.rect.getX(), this.rect.getY(), this.rect.getW(), this.rect.getH());
//		}
//		
//		//rects
//		for(int i=0; i<this.rects.size(); i++) {
//			GrimRect r = this.rects.get(i);
//			g.setColor(r.getC());
//			g.drawRect(r.getX(), r.getY(), r.getW(), r.getH());
//		}
//		
//		requestFocusInWindow();
//		repaint();
//	}
//	@Override
//	public void keyPressed(KeyEvent e) {
//		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
//			this.shift = true;
//		}
//	}
//	@Override
//	public void keyReleased(KeyEvent e) {
//		this.shift = false;
//	}
//	
//	@Override
//	public void mousePressed(MouseEvent e) {
//		this.startX = e.getX();
//		this.startY = e.getY();
//	}
//	
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		this.rect.setC(Color.blue);
//		this.rects.add(this.rect);
//		this.rect = null;
//	}
//	
//	@Override
//	public void mouseDragged(MouseEvent e) {
//		int x = e.getX();
//		int y = e.getY();
//		
//		int w = Math.abs(x - startX); // 절대값으로 초기화
//		int h = Math.abs(y - startY);
//		
//		if(this.shift) {
//			w = h;
//		}
//		
//		int rX = startX;
//		int rY = startY;
//		
//		if(x < startX) rX = startX - w;
//		if(y < startY) rY = startY - h;
//		
//		this.rect = new GrimRect(rX, rY, w, h, Color.red);
//	}
//	
//}
//
//class DrawBoard extends JFrame{
//	public DrawBoard() {
//		super("DRAW");
//		setLayout(null);
//		setBounds(50, 50, 700, 700);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		add(new GrimBoard());
//		setVisible(true);
//		revalidate();
//	}
//}
//
//public class Ex12_1 {
//
//	public static void main(String[] args) {
//		DrawBoard drawRect = new DrawBoard();
//	}
//
//}
