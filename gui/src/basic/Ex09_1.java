package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Block {
	private int x,y,width,height;
	private Color c;
	
	public Block(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}
}

class PushPanel extends JPanel implements ActionListener,MouseListener{
	
	private JButton[] btn = new JButton[4];
	
	private Block b1 = null;
	private Block b2 = null;
	
	private final int SIZE = 700;
	private final int LEFT = 0;
	private final int DOWN = 1;
	private final int RIGHT = 2;
	private final int UP = 3;
	
	private int dir;
	private boolean isMoving;
	private boolean check;
	
	public PushPanel() {
		setLayout(null);
		setBounds(0, 0, 700, 700);
		this.dir = -1;
		setBtn();
		setBlock();
	}
	
	private void setBlock() {
		Random rn = new Random();
		int rX = rn.nextInt(SIZE-100);
		int rY = rn.nextInt(SIZE-100);
		
		this.b1 = new Block(rX, rY, 100, 100);
		
		while(true) {
			rX = rn.nextInt(SIZE-100-100);
			rY = rn.nextInt(SIZE-100-100);
			
			// 검증
			if(rX < this.b1.getX()-this.b1.getWidth() || rX > this.b1.getX()+this.b1.getWidth()
				|| rY < this.b1.getY( )-this.b1.getHeight()|| rY > this.b1.getY()+this.b1.getHeight()) {
				break;
			}
			
			// 
		}
		this.b2 = new Block(rX,rY,100,100);
	}

	private void setBtn() {
		String[] text = {"←","↓","→","↑"};
		
		int x = 500;
		int y = 550;
		for(int i=0; i<4; i++) {
			JButton bt = new JButton();
			bt.setBounds(x, y, 50, 50);
			bt.setText(text[i]);
//			bt.addActionListener(this);
			bt.addMouseListener(this);
			add(bt);
			
			this.btn[i] = bt;
			
			x += 50;
			
			if(i == this.btn.length-1-1) {
				x -= 100;
				y -= 50;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.isMoving = true;
		// check Button
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			
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

	private void update() {
		checkSecond();
		if(this.dir == LEFT) {
			if((!check && this.b1.getX() > 0) || (check && this.b2.getX() > 0)) {
				this.b1.setX(this.b1.getX()-1);
			}
		}
		else if(this.dir == DOWN) {
			if((!check && this.b1.getY() < SIZE-this.b1.getHeight()) || (check && this.b2.getY() < SIZE - this.b2.getHeight())) {
				this.b1.setY(this.b1.getY()+1);
			}
		}
		else if(this.dir == RIGHT) {
			if((!check && this.b1.getX() < SIZE-this.b1.getWidth()) || (check && this.b2.getX() < SIZE - this.b2.getWidth())) {
				this.b1.setX(this.b1.getX()+1);
			}
		}
		else if(this.dir == UP) {
			if((!check && this.b1.getY() > 0) || (check && this.b2.getY() > 0)) {
				this.b1.setY(this.b1.getY()-1);
			}
		}
		this.check = false;
	}
	private void checkSecond() {
		if(this.dir == LEFT) {
			if(this.b2.getX() + this.b2.getWidth() == this.b1.getX()
					&& this.b2.getY() > this.b1.getY() - this.b1.getHeight()
					&& this.b2.getY() < this.b1.getY() + this.b1.getHeight()) {
				if(this.b2.getX() > 0)
					this.b2.setX(this.b2.getX()-1);
				this.check = true;
			}
		}
		else if(this.dir == DOWN) {
			if(this.b2.getY() == this.b1.getY() + this.b1.getHeight() 
					&& this.b2.getX() > this.b1.getX() - this.b1.getWidth()
					&& this.b2.getX() < this.b1.getX() + this.b1.getWidth()) {
				if(this.b2.getY() < SIZE - this.b2.getHeight())
					this.b2.setY(this.b2.getY()+1);
				this.check = true;
			}
		}
		else if(this.dir == RIGHT) {
			if(this.b2.getX() == this.b1.getX() + this.b1.getWidth()
					&& this.b2.getY() > this.b1.getY() - this.b1.getHeight()
					&& this.b2.getY() < this.b1.getY() + this.b1.getHeight()) {
				if(this.b2.getX() < SIZE - this.b2.getWidth())
					this.b2.setX(this.b2.getX()+1);
				this.check = true;
			}
		}
		else if(this.dir == UP) {
			if(this.b2.getY() + this.b2.getHeight() == this.b1.getY()
					&& this.b2.getX() > this.b1.getX() - this.b1.getWidth()
					&& this.b2.getX() < this.b1.getX() + this.b1.getWidth()) {
				if(this.b2.getY() > 0)
					this.b2.setY(this.b2.getY()-1);
				this.check = true;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		this.isMoving = false;
//		this.check = false;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// draw Rect
		if(this.b1 != null && this.b2 != null) {
			g.setColor(Color.black);
			g.drawRect(this.b1.getX(), this.b1.getY(), this.b1.getWidth(), this.b1.getHeight());
			g.setColor(Color.red);
			g.drawRect(this.b2.getX(), this.b2.getY(), this.b2.getWidth(), this.b2.getHeight());
		}
		
		if(this.isMoving) update();
		repaint();
	}
}

class PushGame extends JFrame{
	private PushPanel panel = new PushPanel();
	
	public PushGame() {
		super("PushGame");
		setLayout(null);
		setBounds(50,50,700,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(this.panel);
		setVisible(true);
		revalidate();
	}
	
}

public class Ex09_1 {

	public static void main(String[] args) {
		PushGame game = new PushGame();
	}

}
