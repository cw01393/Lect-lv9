package models;

import java.util.ArrayList;

import controller.Main;

public class UnitWolf extends Unit{

	public UnitWolf() {
		super.setName("늑대");
	}
	
	@Override
	public void skill(ArrayList<Unit> enemy) {
		System.out.println("늑대 스킬 시전>> 적 1명에게 공격력 2배 공격 + 기절");
		
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

}
