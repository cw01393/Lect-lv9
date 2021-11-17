package game;

import javax.swing.ImageIcon;

public class Box {

	private int x,y,w,h;
	private boolean goal;
	private ImageIcon image;

	public Box(int x, int y, int w, int h, ImageIcon image) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.image = image;
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

	public boolean isGoal() {
		return goal;
	}

	public void setGoal(boolean goal) {
		this.goal = goal;
	}

	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}
}
