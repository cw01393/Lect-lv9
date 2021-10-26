package model;

public class Subject {
	
	private String major;
	private String title;
	private int score;
	
	public Subject(String major, String title) {
		this.major = major;
		this.title = title;
	}
	
	public String getMajor() {
		return this.major;
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
