package models;

import java.util.ArrayList;

import controller.Main;

public class UnitBat extends Unit{

	public UnitBat(){
		super.setName("박쥐");
	}
	
	@Override
	public void skill(ArrayList<Unit> enemy) {
		System.out.println("박쥐 스킬 시전>> 적 1명에게 공격 + 침묵");
		
		int rIdx = Main.rn.nextInt(enemy.size());
		Unit u = enemy.get(rIdx);
		super.attack(u);
		if(u.getHp() == 0) {
			enemy.remove(u);
		}
		else {
			System.out.printf(" >> [%s] 침묵\n",u.getName());
			u.setState("침묵");
		}
	}

}
