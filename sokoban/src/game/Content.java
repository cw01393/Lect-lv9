package game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Content extends MyUtil{
	
	private final int SIZE = 8;
	private final int WIDTH = 75;
	private final int HEIGHT = 75;
	private final int GOALCOUNT = 7;
	
	private int[][] goal = new int[GOALCOUNT][2];
	private Box[][] map = new Box[SIZE][SIZE];
	private Box[] box = new Box[GOALCOUNT];

	public Content() {
		setLayout(null);
		setBounds(0, 0, 600, 600);
		
		setMap();
	}
	private void setMap() {
		int x = 0;
		int y = 0;
		Image im = new ImageIcon("images/tile1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(im);
		
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				this.map[i][j] = new Box(x,y,WIDTH,HEIGHT,icon);
				x += 75;
			}
			x = 0;
			y += 75;
		}
//		for(int i=0; i<this.map.length; i++) {
//			for(int j=0; j<this.map[i].length; j++) {
//			}
//		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				Box b = this.map[i][j];
//				g.drawRect(b.getX(), b.getY(), WIDTH, HEIGHT);
				g.drawImage(b.getImage().getImage(), b.getX(), b.getY(), null);
			}
		}
		repaint();
	}
	
	
}
