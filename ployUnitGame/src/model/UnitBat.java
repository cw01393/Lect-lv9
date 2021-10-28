package model;

import java.util.ArrayList;

public class UnitBat extends Unit {

	public UnitBat() {
		super.setName("박쥐");
	}

	@Override
	public void skill(ArrayList<Unit> target) {
		System.out.printf("[%s]의 스킬발동!\n",getName());
		
		int rIdx = Unit.rn.nextInt(target.size());
		Unit select = target.get(rIdx);
		
		super.fight(select);
		select.setState("침묵");
		System.out.printf(" >> [%s]에게 침묵 시전\n",select.getName());
		
		if(select.getHp() <= 0) {
			target.remove(select);
		}
	}
	
//	@Override
//	public void fight(Unit target) {
//		int num = Unit.rn.nextInt(100);
//		
//		if(num > 74) {
//			skill(target);
//		}
//		else {
//			super.fight(target);
//		}
//	}
	
}
