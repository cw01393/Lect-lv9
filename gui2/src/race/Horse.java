package race;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Horse {
	
	private int num;
	private int x, y, w, h;
	private String fileName;
	private ImageIcon image;
	
	private int state; // 0 ready 1 run 2 goal
	private int rank;
	private String record;
	
	public Horse(int num, int x, int y, int w, int h) {
		this.num = num;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.fileName = String.format("images/horse%d.png", this.num);
		this.image = new ImageIcon(new ImageIcon(this.fileName).getImage().getScaledInstance(this.w, this.h, Image.SCALE_SMOOTH));
	}
	
	public int getNum() {
		return num;
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
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
		// 이미지 수정
		this.image = new ImageIcon(new ImageIcon(this.fileName).getImage().getScaledInstance(this.w, this.h, Image.SCALE_SMOOTH));
	}
	public ImageIcon getImage() {
		return image;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	
	
}
