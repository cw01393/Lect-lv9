package controller;

import java.util.Random;
import java.util.Scanner;

// 시작 18:07 (21.11.01)
// 종료 
// 소요 

public class Main {

	public static Scanner sc = new Scanner(System.in);
	public static Random rn = new Random();

	public static void main(String[] args) {
		 
		GameController.getInstance().run();
		
	}

}
