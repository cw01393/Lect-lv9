package model;

public class ZombieKing extends Unit{
	private int shield;
	public ZombieKing(String name, int maxHp, int att, int def, int pos,int shield) {
		super(name, maxHp, att, def, pos);
		this.shield = shield;
	}
	public int getshield() {
		return shield;
	}
	public void setshield(int shield) {
		this.shield = shield;
	}
	
	@Override
	public void attack(Unit target) {
		if(Unit.rn.nextInt(100) >= 74) {
			int damage = (this.getAtt() - target.getDef())*(Unit.rn.nextInt(150)+50)/100;
			if(damage <= 0) damage = 1;
			damage*=2;
			System.out.println("[" + this.getName() + "의 필살기!]");
			System.out.printf("[%d]의 데미지를 입었습니다!\n",damage);
			target.setHp(target.getHp()-damage);
			System.out.printf("[%s]의 남은 [HP: %d]\n",target.getName(), target.getHp());
		}
		else {
			super.attack(target);
		}
	}
	
	@Override
	public void print() {
		System.out.print("[이름] " + this.getName() + " [HP] " + this.getHp());
		if(this.shield > 0) {
			System.out.print(" [쉴드] " + this.shield);
		}
		System.out.println("\n[공격력] " + this.getAtt() + " [방어력] " + this.getDef() + " [위치] " + this.getPos());
	}
	
	
}
