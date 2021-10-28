package model;

import java.util.ArrayList;

public class UnitOrc extends Unit{

	public UnitOrc() {
		super.setName("오크");
	}
	
	@Override
	public void skill(ArrayList<Unit> target) {
		System.out.printf("[%s]의 스킬발동!\n",getName());
		
		int rIdx = Unit.rn.nextInt(target.size());
		Unit select = target.get(rIdx);
		System.out.printf(" >> [%s]에게 2배의 데미지 + 기절\n",select.getName());
		
		int power = getPower();
		setPower(power*2);
		super.fight(select);
		setPower(power);
		select.setState("기절");
		
		if(select.getHp() <= 0) {
			select.setState("노말");
			target.remove(select);
		}
	}

}
