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
			System.out.println("[" + this.getName() + "�� �ʻ��!]");
			System.out.printf("[%d]�� �������� �Ծ����ϴ�!\n",damage);
			target.setHp(target.getHp()-damage);
			System.out.printf("[%s]�� ���� [HP: %d]\n",target.getName(), target.getHp());
		}
		else {
			super.attack(target);
		}
	}
	
	@Override
	public void print() {
		System.out.print("[�̸�] " + this.getName() + " [HP] " + this.getHp());
		if(this.shield > 0) {
			System.out.print(" [����] " + this.shield);
		}
		System.out.println("\n[���ݷ�] " + this.getAtt() + " [����] " + this.getDef() + " [��ġ] " + this.getPos());
	}
	
	
}
