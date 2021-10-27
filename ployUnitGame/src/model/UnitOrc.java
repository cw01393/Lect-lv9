package model;

public class UnitOrc extends Unit{

	public UnitOrc() {
		super.setName("오크");
	}
	
	@Override
	public void skill() {
		System.out.println("한명에게 2배의 데미지 + 기절 ");		
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
