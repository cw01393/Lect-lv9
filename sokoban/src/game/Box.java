package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Box {
	
	public final int ROAD = 1;
	public final int STONE = 2;
	public final int PLAYER = 3;
	public final int BOX = 4;
	public final int GOAL = 5;
	public final int GOALBOX = 6;

	private int x,y,w,h;
	private int state;
	private ImageIcon image;

	public Box(int x, int y, int w, int h, int state) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.state = state;
		Image im = new ImageIcon(String.format("images/tile%d.png", state)).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
		this.image = new ImageIcon(im);
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

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
		Image im = new ImageIcon(String.format("images/tile%d.png", state)).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
		this.image = new ImageIcon(im);
	}

	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}
}
