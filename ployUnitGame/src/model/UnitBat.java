package model;

public class UnitBat extends Unit {

	public UnitBat() {
		super.setName("박쥐");
	}

	@Override
	public void skill() {
		System.out.println("적 한명에게 침묵 시전");
	}
	
	@Override
	public void fight(Unit target) {
		int num = Unit.rn.nextInt(100);
		
		if(num > 74) {
			skill();
		}
		else {
			super.fight(target);
		}
	}
	
}
