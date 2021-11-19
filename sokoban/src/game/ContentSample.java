package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JButton;

public class ContentSample extends MyUtil{

	private Random rn = new Random();
	
	private final int XSIZE = 8;
	private final int YSIZE = 9;
	private final int WIDTH = 60;
	private final int HEIGHT = 60;
	private final int GOALCOUNT = 7;
	
	private Box[][] map = new Box[YSIZE][XSIZE];
	private JButton reset = new JButton("reset");

	private Box player;
	private int pX;
	private int pY;
	
	private int dir;
	private final int UP = 1;
	private final int DOWN = 2;
	private final int LEFT = 3;
	private final int RIGHT = 4;
	
	public ContentSample() {
		setLayout(null);
		setBounds(0, 0, 580, 600);
		addKeyListener(this);
		setMap();
		setGoal();
		setBox();
		setPlayer();
		setButton();
	}
	private void setButton() {
		this.reset.setBounds(490,20,80,30);
		this.reset.setBackground(Color.white);
		this.reset.addActionListener(this);
		add(this.reset);
	}
	
	private void setPlayer() {
		this.pX = 2;
		this.pY = 2;
		Box b = this.map[pX][pY];
		this.player = new Box(b.getX(), b.getY(), b.getW(), b.getH(), b.PLAYER);
//		b.setState(b.PLAYER);
	}
	
	private void setMap() {
		int x = 0;
		int y = 0;
		
		for(int i=0; i<YSIZE; i++) {
			for(int j=0; j<XSIZE; j++) {
				this.map[i][j] = new Box(x,y,WIDTH,HEIGHT,1);
				x += WIDTH;
			}
			x = 0;
			y += HEIGHT;
		}
		
		int[][] stone = {{0,2},{0,3},{0,4},{0,5},{0,6},{1,0},{1,1},{1,2},{1,6},
				{2,0},{2,6},{3,0},{3,1},{3,2},{3,6},{4,0},{4,2},{4,3},{4,6},
				{5,0},{5,2},{5,6},{5,7},{6,0},{6,7},{7,0},{7,7},
				{8,0},{8,1},{8,2},{8,3},{8,4},{8,5},{8,6},{8,7}};
		
		for(int i=0; i<stone.length; i++) {
			y = stone[i][0];
			x = stone[i][1];
			Box b = this.map[y][x];
			this.map[y][x] = new Box(b.getX(),b.getY(),WIDTH,HEIGHT,b.STONE);
		}
	}
	
	private void setGoal() {
		int[][] goal = {{2,1},{3,5},{4,1},{5,4},{6,3},{6,6},{7,4}};
		
		for(int i=0; i<GOALCOUNT; i++) {
			int y = goal[i][0];
			int x = goal[i][1];
			Box b = this.map[y][x];
			this.map[y][x] = new Box(b.getX(),b.getY(),WIDTH,HEIGHT,b.GOAL);
		}
	}
	
	private void setBox() {
		int[][] boxx = {{2,3},{3,4},{4,4},{6,1},{6,3},{6,4},{6,5}};
		
		for(int i=0; i<GOALCOUNT; i++) {
			int y = boxx[i][0];
			int x = boxx[i][1];
			Box b = this.map[y][x];
			if(b.getState() == b.GOAL) {
				b.setState(b.GOALBOX);
			}
			else {
				b.setState(b.BOX);
			}
		}
	}
	
	private void reset() {
		setMap();
		setGoal();
		setBox();
		setPlayer();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			if(target == this.reset) {
				reset();
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) this.dir = UP;
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) this.dir = DOWN;
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) this.dir = LEFT;
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) this.dir = RIGHT;
		
		update();
	}
	
	private void update() {
		int xx = this.pX;
		int yy = this.pY;
		
		if(this.dir == UP) {
			yy--;
		}
		else if(this.dir == DOWN) {
			yy++;
		}
		else if(this.dir == LEFT) {
			xx--;
		}
		else if(this.dir == RIGHT) {
			xx++;
		}
		
		Box m = this.map[yy][xx];
		if(xx < 0 || xx >= XSIZE || yy < 0 || yy >= YSIZE || m.getState() == m.STONE) {
			return;
		}
		else {
			if(m.getState() == m.BOX || m.getState() == m.GOALBOX) {
				int bX = xx;
				int bY = yy;
				
				if(this.dir == UP) {
					bY--;
				}
				else if(this.dir == DOWN) {
					bY++;
				}
				else if(this.dir == LEFT) {
					bX--;
				}
				else if(this.dir == RIGHT) {
					bX++;
				}
				Box mm = this.map[bY][bX];
				if(bX < 0 || bX >= XSIZE || bY < 0 || bY >= YSIZE
						|| mm.getState() == mm.STONE || mm.getState() == mm.BOX) {
					return;
				}
				else {
					if(m.getState() == m.BOX) {
						m.setState(m.ROAD);
					}
					else if(m.getState() == m.GOALBOX) {
						m.setState(m.GOAL);
					}
					if(mm.getState() == mm.GOAL) {
						mm.setState(mm.GOALBOX);
					}
					else {
						mm.setState(mm.BOX);
					}
				}
			}
			this.player.setX(m.getX());
			this.player.setY(m.getY());
			this.pY = yy;
			this.pX = xx;
		}
		checkWin();
	}
	private void checkWin() {
		int cnt = 0;
		for(int i=0; i<YSIZE; i++) {
			for(int j=0; j<XSIZE; j++) {
				if(this.map[i][j].getState() == this.map[i][j].GOALBOX) cnt ++;
			}
		}
		if(cnt == GOALCOUNT) {
			new Result();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i=0; i<YSIZE; i++) {
			for(int j=0; j<XSIZE; j++) {
				Box b = this.map[i][j];
				g.drawImage(b.getImage().getImage(), b.getX(), b.getY(), null);
			}
		}
		Box b = this.player;
		g.drawImage(b.getImage().getImage(), b.getX(), b.getY(), null);
		
		requestFocusInWindow();
		repaint();
	}
}
