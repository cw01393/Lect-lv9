package models;

import java.util.ArrayList;

import controller.Main;

public class UnitPlayer extends Unit{

	public UnitPlayer(String name, int hp, int att, int def){
		super(name, hp, att, def);
	}
	
	@Override
	public void skill(ArrayList<Unit> group) {
		if(super.getName().equals("전사")) {
			criticalAttack(group);
		}
		else if(super.getName().equals("법사")) {
			allAttack(group);
		}
		else if(super.getName().equals("힐러")) {
			heal(group);
		}
	}
	
	private void criticalAttack(ArrayList<Unit> enemy) {
		System.out.println("[전사] 스킬 시전>> 적 1명에게 공격력 2배 공격 + 기절");
		
		int rIdx = Main.rn.nextInt(enemy.size());
		Unit u = enemy.get(rIdx);
		
		int power = super.getAtt();
		super.setAtt(power*2);
		super.attack(u);
		if(u.getHp() == 0) {
			enemy.remove(u);
		}
		else {
			u.setState("기절");
		}
		super.setAtt(power);
	}
	private void allAttack(ArrayList<Unit> enemy) {
		System.out.println("[마법사] 스킬 시전>> 공격력 절반으로 전체공격");
		
		int power = super.getAtt();
		super.setAtt(power/2);
		for(Unit u : enemy) {
			super.attack(u);
		}
		for(int i=0; i<enemy.size(); i++) {
			if(enemy.get(i).getHp() == 0) {
				enemy.remove(i);
				i=0;
			}
		}
		super.setAtt(power);
	}
	private void heal(ArrayList<Unit> group) {
		System.out.println("[힐러] 스킬 시전>> 아군전체 100만큼 HP회복");
		
		for(Unit unit : group) {
			int healHp = unit.getHp()+100;
			if(healHp > unit.getMaxHp()) healHp = unit.getMaxHp();
			unit.setHp(healHp);
			System.out.printf("[%s] %d만큼 HP회복\n",unit.getName(), healHp);
		}
	}

}
