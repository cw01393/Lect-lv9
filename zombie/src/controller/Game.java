package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.Hero;
import model.Unit;
import model.Zombie;
import model.ZombieKing;

public class Game {
	public Scanner sc = new Scanner(System.in);
	
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
			checkEnd();
		}
	}
	
	private void setGame() {
		this.isRun = true;
		this.player = new Hero("용사", 100, 5, 1, 1);
		zombie.add(new Zombie("그냥좀비", 25, 5, 1, 3));
		zombie.add(new Zombie("힘쌘좀비", 45, 10, 2, 6));
		zombie.add(new Zombie("정예좀비", 65, 15, 3, 9));
		zombie.add(new ZombieKing("좀비왕",100,20,4,12,50));
	}
	
	private void map() {
		System.out.printf("[현재 층: %d층]\n",this.player.getPos());
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
					this.player.setPos(this.player.getPos()+1);
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
			this.player.setPos(this.player.getPos()+1);
		}
	}
	
	private int check() {
		for(int i=0; i<this.zombie.size(); i++) {
			if(zombie.get(i).getPos() == this.player.getPos()) return i;
		}
		return -1;
	}
	
	private void fight(int index) {
		System.out.println("좀비가 나타났다!");
		while(true) {
			System.out.println("-------------------------------");
			Unit target = this.zombie.get(index);
			this.player.print();
			System.out.println("-------------------------------");
			target.print();
			System.out.println("===============================");
			
			System.out.printf("[1]공격 [2]물약(%d개 남음)\n",this.player.getPotion());
			String sel = sc.next();
			if(sel.equals("1")) {
				this.player.attack(target);
				System.out.println("-------------------------------");
				if(target.getHp() > 0) {
					target.attack(player);
					if(this.player.getHp() < 0) {
						System.out.println("PLAYER의 죽음...");
						this.isRun = false;
						break;
					}
				}
				else {
					System.out.printf("%s를 물리쳤습니다!\n",target.getName());
					zombie.remove(index);
					break;
				}
			}
			else if(sel.equals("2")) {
				this.player.drinkPotion();
				System.out.println("-------------------------------");
				target.attack(player);
				if(this.player.getHp() < 0) {
					System.out.println("PLAYER의 죽음...");
					this.isRun = false;
					break;
				}
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
			System.out.printf("방어력이 %d만큼 증가했습니다!\n",r);
		}
	}
	
	private void checkEnd() {
		if(this.zombie.size() == 0) {
			System.out.println("좀비를 모두 물리쳤습니다!");
			System.out.println("GAME CLEAR!");
			this.isRun = false;
		}
	}
}
