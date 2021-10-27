package model;

public class UnitWolf extends Unit{

	public UnitWolf() {
		super.setName("늑대");
	}
	
	@Override
	public void skill() {
		System.out.println("적 전체에게 공격력의 절반 데미지 ");
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
