package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class SnakeRect{
	private int x, y, w, h;
	private Color c;
	
	public SnakeRect(int x, int y, int w, int h, Color c) {
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

class SnakeGame extends JPanel implements KeyListener, ActionListener{
	
	private final int SIZE = 10;
	private SnakeRect[][] map = new SnakeRect[SIZE][SIZE];
	private ArrayList<SnakeRect> snake = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> yx = new ArrayList<ArrayList<Integer>>();
	private ArrayList<SnakeRect> item = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> itemYx = new ArrayList<ArrayList<Integer>>();
	
	private final int LEFT = 0;
	private final int DOWN = 1;
	private final int RIGHT = 2;
	private final int UP = 3;
	private final int STOP = 4;
	
	private JButton[] btn = new JButton[4];
	private JButton reset = new JButton();
	
	private int dir;
	private boolean death;
	
	public SnakeGame() {
		setLayout(null);
		setBounds(0, 0, 700, 500);
		setFocusable(true);
		addKeyListener(this);
		
		setMap();
		setSnake();
		setButton();
		setItem();
	}
	
	private void setItem() {
		Random rn = new Random();
		int r = rn.nextInt(10)+5;
		
		for(int i=0; i<r; i++) {
			int rY = rn.nextInt(SIZE);
			int rX = rn.nextInt(SIZE);
			
			boolean check = false;
			for(int j=0; j<this.yx.size(); j++) {
				if(rY == this.yx.get(j).get(0) && rX == this.yx.get(j).get(1))
					check = true;
				if(check) {
					i--;
					break;
				}
			}
			if(check) continue;
			
			for(int j=0; j<this.itemYx.size(); j++) {
				if(rY == this.itemYx.get(j).get(0) && rX == this.itemYx.get(j).get(1)) {
					check = true;
				}
				if(check) {
					i--;
					break;
				}
			}
			
			if(!check) {
				SnakeRect t = this.map[rY][rX];
				this.item.add(new SnakeRect(t.getX(),t.getY(),t.getW(),t.getH(), Color.blue));
				
				ArrayList<Integer> pair = new ArrayList<Integer>();
				pair.add(rY);
				pair.add(rX);
				this.itemYx.add(pair);
			}
		}
	}
	
	private void setMap() {
		this.dir = STOP;
		
		int x = (700-200)/2 - 40*SIZE/2;
		int y = (700-200)/2 - 40*SIZE/2;
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				this.map[i][j] = new SnakeRect(x,y,40,40,Color.gray);
				x+=40;
			}
			x = (700-200)/2 - 40*SIZE/2;
			y += 40;
		}
	}
	
	private void setSnake() {
		for(int i=0; i<4; i++) {
			SnakeRect temp = this.map[0][i];
			int x = temp.getX();
			int y = temp.getY();
			int w = temp.getW();
			int h = temp.getH();
			Color c = i == 0 ? Color.red : Color.pink;
			this.snake.add(new SnakeRect(x,y,w,h,c));
			
			ArrayList<Integer> pair = new ArrayList<>();
			pair.add(0);
			pair.add(i);
			this.yx.add(pair);
		}
	}
	
	private void setButton() {
		String[] text = {"←","↓","→","↑"};
		
		int x = 700-200;
		int y = 500-200;
		for(int i=0; i<this.btn.length; i++) {
			this.btn[i] = new JButton();
			this.btn[i].setBounds(x, y, 50, 50);
			this.btn[i].setText(text[i]);
			this.btn[i].addActionListener(this);
			add(this.btn[i]);
			
			x+=50;
			if(i == this.btn.length-2) {
				x -= 100;
				y -= 50;
			}
		}
		
		this.reset.setBounds(700-200, y+110, 150, 50);
		this.reset.setText("reset");
		this.reset.addActionListener(this);
		add(this.reset);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_LEFT) {
			this.dir = LEFT;
		}
		else if(e.getKeyCode() == e.VK_DOWN) {
			this.dir = DOWN;
		}
		else if(e.getKeyCode() == e.VK_RIGHT) {
			this.dir = RIGHT;
		}
		else if(e.getKeyCode() == e.VK_UP) {
			this.dir = UP;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.dir = STOP;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(this.dir != STOP)
			update();
		//snake
		for(int i=0; i<this.snake.size(); i++) {
			SnakeRect r = this.snake.get(i);
			Color c = r.getC();
			if(this.death) c = Color.red;
			g.setColor(c);
			g.fillRect(r.getX(), r.getY(),r.getW(), r.getH());
		}
		//item
		for(int i=0; i<this.item.size(); i++) {
			SnakeRect r = this.item.get(i);
			g.setColor(r.getC());
			g.fillRoundRect(r.getX()+10, r.getY()+10,20,20,20,20);
		}
		
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				SnakeRect r = this.map[i][j];
				g.setColor(r.getC());
				g.drawRect(r.getX(), r.getY(), r.getW(), r.getH());
			}
		}
		
		requestFocusInWindow();
		repaint();
	}
	
	private void update() {
		int yy = this.yx.get(0).get(0);
		int xx = this.yx.get(0).get(1);
		
		if(this.dir == LEFT) {
			xx --;
		}
		else if(this.dir == DOWN) {
			yy ++;
		}
		else if(this.dir == RIGHT) {
			xx ++;
		}
		else if(this.dir == UP) {
			yy --;
		}
		if(xx < 0 || xx >= SIZE || yy < 0 || yy >= SIZE)
			return;
		
		for(int i=1; i<this.yx.size(); i++) {
			if(yy == this.yx.get(i).get(0) && xx == this.yx.get(i).get(1)) {
				this.death = true;
			}
		}
		// item
		boolean isGrow = false;
		for(int i=0; i<this.itemYx.size(); i++) {
			if(yy == this.itemYx.get(i).get(0) && xx == this.itemYx.get(i).get(1)) {
				isGrow = true;
				this.item.remove(i);
				this.itemYx.remove(i);
			}
		}
		
		// move
		if(!death) {
			SnakeRect tail = this.snake.get(this.snake.size()-1);
			ArrayList<Integer> tailYx = this.yx.get(this.yx.size()-1);
			
			for(int i = this.snake.size()-1; i>0; i--) {
				this.snake.set(i, this.snake.get(i-1));
				this.snake.get(i).setC(Color.pink);
				
//				ArrayList<Integer> pair = new ArrayList<Integer>();
//				pair.add(this.yx.get(i-1).get(0));
//				pair.add(this.yx.get(i-1).get(1));
				this.yx.set(i, this.yx.get(i-1));
			}
			
			SnakeRect t = this.map[yy][xx];
			this.snake.set(0, new SnakeRect(t.getX(), t.getY(), t.getW(), t.getH(), Color.red));
			
			ArrayList<Integer> pair = new ArrayList<Integer>();
			pair.add(yy);
			pair.add(xx);
			this.yx.set(0, pair);
			
			if(isGrow) {
				this.snake.add(tail);
				this.yx.add(tailYx);
			}
			
			this.dir = STOP;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			
			if(target == this.reset) {
				resetMap();
			}
			else {
				if(target == this.btn[LEFT]) {
					this.dir = LEFT;
				}
				else if(target == this.btn[DOWN]) {
					this.dir = DOWN;
				}
				else if(target == this.btn[RIGHT]) {
					this.dir = RIGHT;
				}
				else if(target == this.btn[UP]) {
					this.dir = UP;
				}
			}
		}
	}

	private void resetMap() {
		
	}
}

public class Ex10_1 extends JFrame{
	
	public Ex10_1(){
		super("Snake");
		setLayout(null);
		setBounds(100,100,700,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new SnakeGame());
		setVisible(true);
		revalidate();
	}

	public static void main(String[] args) {
		Ex10_1 game = new Ex10_1();
	}

}
