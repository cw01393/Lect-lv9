package controller;

public class SubjectManager {

	private static SubjectManager instance = new SubjectManager();
	private SubjectManager() {}
	public static SubjectManager getInstance() {
		return instance;
	}
	
}
