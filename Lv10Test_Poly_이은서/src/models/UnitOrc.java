package models;

import java.util.ArrayList;

public class UnitOrc extends Unit{

	public UnitOrc() {
		super.setName("오크");
	}
	
	@Override
	public void skill(ArrayList<Unit> enemy) {
		System.out.println("오크 스킬 시전>> 공격력 절반으로 전체공격");
		
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

	
}
