package controller;

import java.util.Random;
import java.util.Scanner;

// 시작  18:07
// 종료  21:41
// 소요  03:34

public class Main {

	public static Scanner sc = new Scanner(System.in);
	public static Random rn = new Random();

	public static void main(String[] args) {
		 
		GameController.getInstance().run();
		
	}

}
