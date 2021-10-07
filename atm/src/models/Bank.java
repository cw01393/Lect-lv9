package models;

import java.util.Scanner;

public class Bank {

	public static Scanner sc = new Scanner(System.in);
	
	public static int log = -1; // 현재 로그인 한 state를 참조
	private static String name;
	
	private Bank() {} // new를 통한 Bank생성 불가. 유일하게 Bank 하나만 존재함
	
	public static String getName() {
		return Bank.name;
	}
	
	public static void setName(String name) {
		Bank.name = name;
	}
	
}
