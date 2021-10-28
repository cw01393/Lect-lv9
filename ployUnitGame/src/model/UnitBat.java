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
		System.out.printf(" >> [%s]에게 침묵 시전\n",select.getName());
		
		super.fight(select);
		select.setState("침묵");
		
		if(select.getHp() <= 0) {
			select.setState("노말");
			target.remove(select);
		}
	}
	
}
