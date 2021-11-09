package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Block {
	private int x,y,width,height;
	private Color c;
	
	public Block(int x, int y, int width, int height, Color c){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.c = c;
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

class PushPanel extends JPanel implements MouseListener{
	
	private JButton[] dirButton = new JButton[4];
	private Block[] block = new Block[2];
	private boolean isMoving;
	private int dir;
	
	private final int WIDTH = 500;
	private final int HEIGHT = 400;
	
	private final int UP = 0;
	private final int LEFT = 1;
	private final int DOWN = 2;
	private final int RIGHT = 3;
	
	public PushPanel() {
		setLayout(null);
		setBounds(0,0,600,600);
		setButton();
		setBlock();
		this.dir = -1;
	}
	
	private void setButton() {
		JButton temp = new JButton();
		temp.setBounds(400,450,50,50);
		temp.setBackground(Color.white);
		temp.setText("↑");
		temp.addMouseListener(this);
		this.dirButton[0] = temp;
		add(this.dirButton[0]);
		
		int x = 350;
		int y = 500;
		for(int i=1; i<this.dirButton.length; i++) {
			temp = new JButton();
			temp.setBounds(x,y,50,50);
			temp.setBackground(Color.white);
			if(i == 1) temp.setText("←");
			else if(i == 2) temp.setText("↓");
			else temp.setText("→");
			temp.addMouseListener(this);
			this.dirButton[i] = temp;
			add(this.dirButton[i]);
			
			x += 50;
		}
		
	}
	
	private void setBlock() {
		Random rn = new Random();
		
		for(int i=0; i<this.block.length; i++) {
			int rX = rn.nextInt(WIDTH-50) + 50;
			int rY = rn.nextInt(HEIGHT-50) + 25;
			
			if(!checkXY(i,rX,rY)) {
				this.block[i] = new Block(rX,rY,50,50,Color.gray);
			}
			else i--;
		}
		this.block[1].setC(Color.red);
	}
	
	private boolean checkXY(int idx, int x, int y) {
		for(int i=0; i<idx; i++) {
			Block b = this.block[i];
			if(x >= b.getX()-50 && x < b.getX()+50
					&& y >= b.getY()-50 && y < b.getY()+50) {
				return true;
			}
		}
		return false;
	}
	
	private void goUp(){
		Block p = this.block[0]; // player
		Block b = this.block[1]; // block
		if(p.getY() > 25) {
			if(b.getX() > p.getX()-50 && b.getX() < p.getX()+50 &&
					p.getY() > b.getY() && p.getY() <= b.getY()+50) {
				if(b.getY() > 25) {
					b.setY(b.getY()-1);
				}
				else {
					b.setY(25);
				}
				p.setY(b.getY()+50);
			}
			else {
				p.setY(p.getY()-1);
			}
		}
		else {
			p.setY(25);
		}
	}
	
	private void goDown() {
		Block p = this.block[0];
		Block b = this.block[1]; // block
		if(p.getY() < 25 + HEIGHT - 50) {
			if(b.getX() > p.getX()-50 && b.getX() < p.getX()+50 &&
					p.getY()+50 >= b.getY() && p.getY()+50 < b.getY()+50) {
				if(b.getY() < 25+ HEIGHT- 50) {
					b.setY(b.getY()+1);
				}
				else {
					b.setY(25+HEIGHT-50);
				}
				p.setY(b.getY()-50);
			}
			else {
				p.setY(p.getY()+1);
			}
		}
		else {
			p.setY(25+HEIGHT-50);
		}
	}
	private void goLeft() {
		Block p = this.block[0];
		Block b = this.block[1]; // block
		if(p.getX() > 50) {
			if(b.getY() > p.getY()-50 && b.getY() < p.getY()+50 &&
					p.getX() > b.getX() && p.getX() <= b.getX()+50) {
				if(b.getX() > 50) {
					b.setX(b.getX()-1);
				}
				else {
					b.setX(50);
				}
				p.setX(b.getX()+50);
			}
			else {
				p.setX(p.getX()-1);
			}
		}
		else {
			p.setX(50);
		}
	}
	private void goRight() {
		Block p = this.block[0];
		Block b = this.block[1]; // block
		if(p.getX() < 50 + WIDTH - 50) {
			if(b.getY() > p.getY()-50 && b.getY() < p.getY()+50 &&
					p.getX()+50 >= b.getX() && p.getX()+50 < b.getX()+50) {
				if(b.getX() < 50 + WIDTH - 50) {
					b.setX(b.getX()+1);
				}
				else {
					b.setX(50+WIDTH-50);
				}
				p.setX(b.getX()-50);
			}
			else {
				p.setX(p.getX()+1);
			}
		}
		else {
			p.setX(50 + WIDTH -50);
		}
	}
	
	private void update() {
		if(this.dir == UP) goUp(); // 위
		else if(this.dir == LEFT) goLeft(); // 왼
		else if(this.dir == DOWN) goDown(); // 아래
		else if(this.dir == RIGHT) goRight(); // 오
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// frame
		g.setColor(Color.black);
		g.drawRect(50, 25, WIDTH, HEIGHT);
		
		// block
		for(int i=0; i<this.block.length; i++) {
			Block b = this.block[i];
			g.setColor(b.getC());
			g.drawRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
		}
		if(this.isMoving) {
			update();
		}
		repaint();
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
		
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			
			for(int i=0; i<this.dirButton.length; i++) {
				if(target == this.dirButton[i]) {
					this.dir = i;
				}
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		this.isMoving = false;
	}

}

class PushGame extends JFrame{
	private PushPanel panel = new PushPanel();
	
	public PushGame() {
		super("PushGame");
		setLayout(null);
		setBounds(50,50,600,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(this.panel);
		setVisible(true);
		revalidate();
	}
	
}

public class Ex09 {

	public static void main(String[] args) {
		
		PushGame game = new PushGame();
	}

}
