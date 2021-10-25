package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.Hero;
import model.Unit;
import model.Zombie;
import model.ZombieKing;

public class Game {
	public Scanner sc = new Scanner(System.in);
	
	private int pos;
	private boolean isRun;
	private Hero player;
	private ArrayList<Unit> zombie = new ArrayList<>();
	
	private static Game instance = new Game();
	private Game() {}
	public static Game getInstance() {
		return instance;
	}
	
	public void run() {
		setGame();
		while(isRun) {
			map();
		}
	}
	
	private void setGame() {
		this.pos = 1;
		this.isRun = true;
		this.player = new Hero("용사", 100, 5, 1, 2);
		zombie.add(new Zombie("그냥좀비", 25, 5, 1, 4));
		zombie.add(new Zombie("힘쌘좀비", 45, 10, 2, 7));
		zombie.add(new Zombie("정예좀비", 65, 15, 3, 10));
		zombie.add(new ZombieKing("좀비왕",100,20,4,12,50));
	}
	
	private void map() {
		System.out.printf("[현재 층: %d층]\n",this.pos);
		if(check() == -1) {
			boolean select = false;
			System.out.println("아무 것도 없다");
			while(true) {
				System.out.println("[1] 올라간다");
				if(!select) {
					System.out.println("[2] 체력을 회복한다");
					System.out.println("[3] 무기를 강화한다");
				}
				String sel = sc.next();
				
				if(sel.equals("1")) {
					this.pos ++;
					break;
				}
				else if(sel.equals("2") && !select) {
					heal();
					select = true;
				}
				else if(sel.equals("3") && !select) {
					strengthen();
					select = true;
				}
			}
		}
		else {
			fight(check());
			this.pos ++;
		}
	}
	
	private int check() {
		for(int i=0; i<this.zombie.size(); i++) {
			if(zombie.get(i).getPos() == this.pos) return i;
		}
		return -1;
	}
	
	private void fight(int index) {
		while(true) {
			this.player.print();
			System.out.println("-------------------------------");
			this.zombie.get(index).print();
			System.out.println("===============================");
			
			System.out.printf("[1]공격 [2]물약(%d개 남음)\n",this.player.getPotion());
			String sel = sc.next();
			if(sel.equals("1")) {
				
			}
			else if(sel.equals("2")) {
				
			}
		}
	}
	
	private void heal() {
		int heal = Unit.rn.nextInt(40)+20;
		if(heal + this.player.getHp() > this.player.getMaxHp()) {
			heal = this.player.getMaxHp()-this.player.getHp(); 
		}
		this.player.setHp(this.player.getHp() + heal);
		System.out.printf("HP가 %d만큼 회복되었습니다!\n",heal);
		System.out.printf("남은 HP: %d\n",this.player.getHp());
	}
	private void strengthen() {
		int sel = Unit.rn.nextInt(2);
		if(sel == 0) {
			int r = Unit.rn.nextInt(3)+2;
			this.player.setAtt(this.player.getAtt() + r);
			System.out.printf("공격력이 %d만큼 증가했습니다!\n",r);
		}
		else {
			int r = Unit.rn.nextInt(3)+2;
			this.player.setDef(this.player.getDef() + r);
			System.out.printf("공격력이 %d만큼 증가했습니다!\n",r);
		}
	}
}
