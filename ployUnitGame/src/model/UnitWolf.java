package model;

import java.util.ArrayList;

public class UnitWolf extends Unit{

	public UnitWolf() {
		super.setName("늑대");
	}
	
	@Override
	public void skill(ArrayList<Unit> target) {
		System.out.printf("[%s]의 스킬발동!\n",getName());
		System.out.println(" >> 적 전체에게 공격력의 절반 데미지");
		
		int power = getPower();
		setPower(power/2);
		for(Unit u : target) {
			super.fight(u);
		}
		for(int i=0; i<target.size(); i++) {
			if(target.get(i).getHp() <= 0) {
				target.remove(i);
				i=0;
			}
		}
//		for(Unit u : target) {
//			if(u.getHp() <= 0) {
//				target.remove(u);
//			}
//		}
		setPower(power);
	}

}
