package model;

import java.util.ArrayList;

public class UnitPlayer extends Unit{

	public UnitPlayer(String name, int max, int power) {
		super(name, max, power);
	}
	
	@Override
	public void skill(ArrayList<Unit> target) {
		if(getName().equals("전사")) {
			criticalAttack(target);
		}
		else if(getName().equals("마법사")) {
			allAttack(target);
		}
		else if(getName().equals("힐러")) {
			heal(target);
		}
	}
	
	private void criticalAttack(ArrayList<Unit> target) {
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
		select.printInfo();
	}
	
	private void allAttack(ArrayList<Unit> target) {
		System.out.printf("[%s]의 스킬발동!\n",getName());
		System.out.println(" >> 적 전체에게 공격력의 절반 데미지");
		
		int power = getPower();
		setPower(power/2);
		for(Unit u : target) {
			super.fight(u);
			u.printInfo();
		}
		for(int i=0; i<target.size(); i++) {
			if(target.get(i).getHp() <= 0) {
				target.remove(i);
				i=0;
			}
		}
		setPower(power);
	}
	
	private void heal(ArrayList<Unit> target) {
		System.out.printf("[%s]의 스킬발동!\n",getName());
		System.out.println(" >> 아군 전체에 100만큼 HP회복");
		
		for(Unit u : target) {
			int hp = u.getHp() + 100;
			if(hp > u.getMaxHp()) hp = u.getMaxHp();
			u.setHp(hp);
			u.printInfo();
		}
	}

}
