package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import membership.ChoicePanel;

public class FileManager {

	private ChoicePanel cp = ChoicePanel.getInstance();
	
	private static FileManager instance = new FileManager();
	private FileManager() {}
	
	private File file = new File("mms.text");
	private FileWriter fw;
	private FileReader fr;
	private BufferedReader br;
	
	public static FileManager getInstacne() {
		return instance;
	}
	
//	private String dataText() {
//		String str = "";
//		for(int i=0; i<)
//		return str;
//	}
	
	
	
}
