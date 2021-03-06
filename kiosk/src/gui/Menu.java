package gui;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Menu {

	private int x;
	private int y;
	private String kind;
	private int num;
	private String name;
	private int price;
	private int maxCnt;
	private int cnt;
	private ImageIcon image;
	
	public Menu(int x, int y,String kind, int num, String name, int price) {
		this.x = x;
		this.y = y;
		this.kind = kind;
		this.num = num;
		this.name = name;
		this.price = price;
		this.maxCnt = 10;
		
		String path = String.format("images/%s%d.png", this.kind, this.num+1);
		Image temp = new ImageIcon(path).getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH);
		this.image = new ImageIcon(temp);
	}
	

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getKind() {
		return kind;
	}

	public int getNum() {
		return num;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
	
	public int getCnt() {
		return this.cnt;
	}
	
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	public int getMaxCnt() {
		return this.maxCnt;
	}
	
	public void setMaxCnt(int maxCnt) {
		this.maxCnt = maxCnt;
	}

	public ImageIcon getImage() {
		return image;
	}
	
	public void puchase() {
		this.maxCnt -= this.cnt;
		this.cnt = 0;
	}

}
