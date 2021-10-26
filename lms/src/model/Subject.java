package model;

public class Subject {
	
	private String title;
	private int score;
	
	public Subject(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	public int getScore() {
		return this.score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
