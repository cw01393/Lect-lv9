//package basic;
//
//import java.awt.Color;
//import java.awt.Frame;
//import java.awt.Graphics;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//// paintComponent() 메소드 활용한 사각형 그리기
//class Nemo{
//	private int x, y, width, height;
//	private Color c;
//	
//	public int getX() {
//		return x;
//	}
//	public void setX(int x) {
//		this.x = x;
//	}
//	public int getY() {
//		return y;
//	}
//	public void setY(int y) {
//		this.y = y;
//	}
//	public int getWidth() {
//		return width;
//	}
//	public void setWidth(int width) {
//		this.width = width;
//	}
//	public int getHeight() {
//		return height;
//	}
//	public void setHeight(int height) {
//		this.height = height;
//	}
//	public Color getC() {
//		return c;
//	}
//	public void setC(Color c) {
//		this.c = c;
//	}
//	
//}
//
//class TestPanel extends JPanel implements MouseListener{
//	
//	private Nemo nemo = new Nemo();
//	
//	public TestPanel() {
//		setLayout(null);
//		setBounds(0, 0, 500, 400);
//		setBackground(Color.orange);
//		
//		nemo.setX(100);
//		nemo.setY(100);
//		nemo.setWidth(200);
//		nemo.setHeight(200);
//		nemo.setC(Color.blue);
//		
//		// 패널에 혹은 특정하는 컴포넌트 -> 마우스리스너 달 수 있음
//		addMouseListener(this);
//		
//		
//	}
//	
//	@Override
//	protected void paintComponent(Graphics g) { // 스레드
//		super.paintComponent(g); // 그래픽을 지움
//		
//		// 수정불가능
////		g.setColor(Color.red);
////		g.drawRect(100, 100, 100, 100);
////		g.fillRect(100, 100, 100, 100);
//		
//		// 수정가능
//		g.setColor(this.nemo.getC());
//		g.drawRect(this.nemo.getX(), this.nemo.getY(), this.nemo.getWidth(), this.nemo.getHeight());
//		
//		// 다시 그리기
//		repaint();
//	}
//
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		System.out.println("click");
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		System.out.println("enter");
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//		System.out.println("exit");
//		
//	}
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//		System.out.println("press - 클");
//		
//		int x = e.getX();
//		int y = e.getY();
//		
//		System.out.println(x + "/" + y);
//		
//		if(x >= this.nemo.getX() && x <= this.nemo.getX()+this.nemo.getWidth()
//			&& y >= this.nemo.getY() && y <= this.nemo.getY()+this.nemo.getHeight()) {
//			this.nemo.setC(Color.red);
//		}
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		System.out.println("release - 릭");
//		
//	}
//}
//
//class TestFrame extends JFrame{
//	public TestFrame() {
//		setLayout(null);
//		setTitle("MyFrame");
//		setBounds(50, 50, 500, 400);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		add(new TestPanel());
//		setVisible(true);
//		revalidate();
//	}
//}
//
//public class Ex06 {
//
//	public static void main(String[] args) {
//
//		TestFrame frame = new TestFrame();
//	}
//
//}
