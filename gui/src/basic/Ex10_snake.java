//package basic;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.util.ArrayList;
//import java.util.Random;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
//class SnakeRect{
//	private int x, y, w, h;
//	private Color c;
//	
//	public int getX() {
//		return x;
//	}
//
//	public void setX(int x) {
//		this.x = x;
//	}
//
//	public int getY() {
//		return y;
//	}
//
//	public void setY(int y) {
//		this.y = y;
//	}
//
//	public int getW() {
//		return w;
//	}
//
//	public int getH() {
//		return h;
//	}
//
//	public Color getC() {
//		return c;
//	}
//
//	public void setC(Color c) {
//		this.c = c;
//	}
//
//	public SnakeRect(int x, int y, int w, int h) {
//		this.x = x;
//		this.y = y;
//		this.w = w;
//		this.h = h;
//	}
//}
//
//class SnakeGame extends JPanel implements KeyListener,ActionListener{
//	
//	private ArrayList<SnakeRect> snake = new ArrayList<>();
//	private SnakeRect[][] map = new SnakeRect[10][10];
//	private ArrayList<SnakeRect> item = new ArrayList<>();
//	private JLabel title = new JLabel("SNAKE GAME");
//	private JButton reset = new JButton("Reset");
//	
//	private final int frameX = 150;
//	private final int frameY = 70;
//	private final int SIZE = 40;
//	private final int ITEMCOUNT = 8;
//	
//	private int itemIdx = -1;
//	
//	public SnakeGame() {
//		setLayout(null);
//		setBounds(0, 0, 700, 500);
//		setFocusable(true);
//		addKeyListener(this);
//		
//		setTitle();
//		setResetButton();
//		setMap();
//		setSnake();
//		setItem();
//	}
//	
//	private void setTitle() {
//		this.title.setBounds(0,0,700,50);
//		this.title.setFont(new Font("",Font.BOLD,30));
//		this.title.setHorizontalAlignment(JLabel.CENTER);
//		this.title.setVerticalAlignment(JLabel.CENTER);
//		add(this.title);
//	}
//	private void setResetButton() {
//		this.reset.setBounds(600,100,80,40);
//		this.reset.setBackground(Color.white);
//		this.reset.addActionListener(this);
//		add(this.reset);
//	}
//	
//	private void setMap() {
//		int x = frameX;
//		int y = frameY;
//				
//		for(int i=0; i<10; i++) {
//			for(int j=0; j<10; j++) {
//				this.map[i][j]= new SnakeRect(x,y,SIZE,SIZE);
//				x += SIZE;
//			}
//			x = frameX;
//			y += SIZE;
//		}
//	}
//	
//	private void setSnake() {
//		for(int i=0; i<4; i++) {
//			SnakeRect m = this.map[0][i+3];
//			this.snake.add(new SnakeRect(m.getX(),m.getY(),m.getW(),m.getH()));
//			Color c = i == 0 ? Color.gray : Color.pink;
//			this.snake.get(i).setC(c);
//		}
//	}
//	
//	private void setItem() {
//		Random rn = new Random();
//		
//		for(int i=0; i<ITEMCOUNT; i++) {
//			int rY = rn.nextInt(this.map.length);
//			int rX = rn.nextInt(this.map[0].length);
//			
//			boolean check = false;
//			for(int j=0; j<i-1; j++) {
//				if(this.item.get(j) == this.map[rY][rX]) {
//					check = true;
//				}
//				for(int k=0; k<this.snake.size(); k++) {
//					if(this.snake.get(k).getX() == this.map[rY][rX].getX() 
//						&& this.snake.get(k).getY() == this.map[rY][rX].getY()) {
//						check = true;
//					}
//				}
//			}
//			if(!check) this.item.add(this.map[rY][rX]);
//			else i--;
//		}
//	}
//	
//	private void checkItem(int x, int y) {
//		for(int i=0; i<this.item.size(); i++) {
//			SnakeRect item = this.item.get(i);
//			if(item.getX() == x && item.getY() == y) {
//				this.itemIdx = i;
//			}
//		}
//	}
//	
//	private void moving() {
//		for(int i=this.snake.size()-1; i>0; i--) {
//			SnakeRect now = this.snake.get(i);
//			SnakeRect past = this.snake.get(i-1);
//			now.setX(past.getX());
//			now.setY(past.getY());
//		}
//	}
//	private void goLeft() {
//		moving();
//		SnakeRect s1 = this.snake.get(0);
//		checkItem(s1.getX()-SIZE,s1.getY());
//		s1.setX(s1.getX()-SIZE);
//	}
//	
//	private void goDown() {
//		moving();
//		SnakeRect s1 = this.snake.get(0);
//		checkItem(s1.getX(),s1.getY()+SIZE);
//		s1.setY(s1.getY()+SIZE);
//	}
//	
//	private void goRight() {
//		moving();
//		SnakeRect s1 = this.snake.get(0);
//		checkItem(s1.getX()+SIZE,s1.getY());
//		s1.setX(s1.getX()+SIZE);
//	}
//	
//	private void goUp() {
//		moving();
//		SnakeRect s1 = this.snake.get(0);
//		checkItem(s1.getX(),s1.getY()-SIZE);
//		s1.setY(s1.getY()-SIZE);
//	}
//	
//	private boolean check(int num) {
//		SnakeRect head = this.snake.get(0);
//		if(num == KeyEvent.VK_LEFT) {
//			for(int i=1; i<this.snake.size(); i++) {
//				SnakeRect body = this.snake.get(i);
//				if(body.getX() == head.getX() - SIZE && body.getY() == head.getY())
//					return true;
//			}
//		}
//		else if(num == KeyEvent.VK_DOWN) {
//			for(int i=1; i<this.snake.size(); i++) {
//				SnakeRect body = this.snake.get(i);
//				if(body.getX() == head.getX() && body.getY() == head.getY() + SIZE)
//					return true;
//			}
//		}
//		else if(num == KeyEvent.VK_RIGHT) {
//			for(int i=1; i<this.snake.size(); i++) {
//				SnakeRect body = this.snake.get(i);
//				if(body.getX() == head.getX() + SIZE && body.getY() == head.getY())
//					return true;
//			}
//		}
//		else if(num == KeyEvent.VK_UP) {
//			for(int i=1; i<this.snake.size(); i++) {
//				SnakeRect body = this.snake.get(i);
//				if(body.getX() == head.getX() && body.getY() == head.getY()-SIZE)
//					return true;
//			}
//		}
//		return false;
//	}
//
//	@Override
//	public void keyPressed(KeyEvent e) {
//		SnakeRect tail = this.snake.get(this.snake.size()-1);
//		SnakeRect temp = new SnakeRect(tail.getX(),tail.getY(),tail.getW(),tail.getH());
//		temp.setC(tail.getC());
//		
//		if(e.getKeyCode() == e.VK_LEFT && !check(e.getKeyCode())
//				&& this.snake.get(0).getX() > frameX) {
//			goLeft();
//		}
//		else if(e.getKeyCode() == e.VK_DOWN && !check(e.getKeyCode())
//				&& this.snake.get(0).getY() < frameY + SIZE*9) {
//			goDown();
//		}
//		else if(e.getKeyCode() == e.VK_RIGHT && !check(e.getKeyCode())
//				&& this.snake.get(0).getX() < frameX + SIZE*9) {
//			goRight();
//		}
//		else if(e.getKeyCode() == e.VK_UP && !check(e.getKeyCode())
//				&& this.snake.get(0).getY() > frameY) {
//			goUp();
//		}
//		
//		if(this.itemIdx != -1) {
//			this.snake.add(temp);
//			this.item.remove(itemIdx);
//			this.itemIdx = -1;
//		}
//	}
//
//	@Override
//	public void keyReleased(KeyEvent e) {
//	}
//
//	@Override
//	public void keyTyped(KeyEvent e) {
//	}
//	
//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		
//		// map
//		g.setColor(Color.gray);
//		for(int i=0; i<this.map.length; i++) {
//			for(int j=0; j<this.map[i].length; j++) {
//				SnakeRect m = this.map[i][j];
//				g.drawRect(m.getX(), m.getY(), m.getW(), m.getH());
//			}
//		}
//		
//		// item
//		g.setColor(Color.red);
//		for(int i=0; i<this.item.size(); i++) {
//			SnakeRect it = this.item.get(i);
//			g.fillRoundRect(it.getX()+15, it.getY()+15, 10, 10, 10, 10);
//		}
//		
//		
//		// snake
//		for(int i=0; i<this.snake.size(); i++) {
//			SnakeRect s = this.snake.get(i);
//			g.setColor(s.getC());
//			g.fillRect(s.getX(),s.getY(),s.getW(),s.getH());
//			g.setColor(Color.black);
//			g.drawRect(s.getX(),s.getY(),s.getW(),s.getH());
//		}
//		
//		requestFocusInWindow();
//		repaint();
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if(e.getSource() instanceof JButton) {
//			JButton target = (JButton) e.getSource();
//			if(target == this.reset) {
//				reset();
//			}
//		}
//	}
//	
//	private void reset() {
//		this.snake.clear();
//		this.item.clear();
//		setSnake();
//		setItem();
//	}
//}
//
//public class Ex10_snake extends JFrame{
//
//	public Ex10_snake(){
//		super("Snake");
//		setLayout(null);
//		setBounds(100,100,700,500);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		add(new SnakeGame());
//		setVisible(true);
//		revalidate();
//	}
//	
//	
//	public static void main(String[] args) {
//		Ex10_snake game = new Ex10_snake();
//	}
//
//}
