package game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Content extends MyUtil{
	
	private Random rn = new Random();
	
	private final int SIZE = 8;
	private final int WIDTH = 70;
	private final int HEIGHT = 70;
	private final int GOALCOUNT = 5;
	
	private Box[][] map = new Box[SIZE][SIZE];
	private Box[] box = new Box[GOALCOUNT];
//	private Box[] goal = new Box[GOALCOUNT];
	private int[][] boxXY = new int[GOALCOUNT][2];
	private int[][] goalXY = new int[GOALCOUNT][2];

	private Box player;
	private int pX;
	private int pY;
	
	public Content() {
		setLayout(null);
		setBounds(0, 0, 580, 600);
		addKeyListener(this);
		setMap();
		setGoal();
		setBox();
		setPlayer();
	}
	
	private void setPlayer() {
		while(true) {
			int rX = rn.nextInt(SIZE);
			int rY = rn.nextInt(SIZE);
			
			Box b = this.map[rX][rY];
			if(b.getState() == b.ROAD) {
				this.map[rX][rY] = new Box(b.getX(),b.getY(),WIDTH,HEIGHT,b.PLAYER);
				b = this.map[rX][rY];
				this.player = new Box(b.getX(),b.getY(),WIDTH,HEIGHT,b.PLAYER);
				this.pX = rX;
				this.pY = rY;
				break;
			}
		}
	}
	
	private void setMap() {
		int x = 0;
		int y = 0;
		
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				this.map[i][j] = new Box(x,y,WIDTH,HEIGHT,1);
				x += WIDTH;
			}
			x = 0;
			y += HEIGHT;
		}
		
		int stoneCnt = 15;
		
		for(int i=0; i<stoneCnt; i++) {
			int rX = rn.nextInt(SIZE);
			int rY = rn.nextInt(SIZE);
			
			Box b = this.map[rX][rY];
			if(b.getState() == b.STONE) {
				i--;
			}
			else {
				this.map[rX][rY] = new Box(b.getX(),b.getY(),WIDTH,HEIGHT,b.STONE);
			}
		}
	}
	
	private void setGoal() {
		for(int i=0; i<GOALCOUNT; i++) {
			int rX = rn.nextInt(SIZE);
			int rY = rn.nextInt(SIZE);
			
			Box b = this.map[rX][rY];
			if(b.getState() == b.ROAD) {
				this.map[rX][rY] = new Box(b.getX(),b.getY(),WIDTH,HEIGHT,b.GOAL);
			}
			else {
				i--;
			}
		}
	}
	
	private void setBox() {
		for(int i=0; i<GOALCOUNT; i++) {
			int rX = rn.nextInt(SIZE);
			int rY = rn.nextInt(SIZE);
			
			Box b = this.map[rX][rY];
			if(b.getState() == b.ROAD && !checkSide()) {
				this.map[rX][rY] = new Box(b.getX(),b.getY(),WIDTH,HEIGHT,b.BOX);
				b = this.map[rX][rY];
				this.box[i] = new Box(b.getX(),b.getY(),WIDTH,HEIGHT,b.BOX);
			}
			else {
				i--;
			}
		}
	}
	
	private boolean checkSide() {
		
		return false;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				Box b = this.map[i][j];
				g.drawImage(b.getImage().getImage(), b.getX(), b.getY(), null);
			}
		}
		repaint();
	}
	
	
}
