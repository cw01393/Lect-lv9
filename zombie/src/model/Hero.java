package model;

public class Hero extends Unit{
	private int potion = 3;
	public Hero(String name, int maxHp, int att, int def, int pos) {
		super(name, maxHp, att, def, pos);
	}
	
	public int getPotion() {
		return this.potion;
	}
	
	public void drinkPotion() {
		if(this.potion > 0) {
			System.out.println("ü���� 100 ȸ���Ǿ����ϴ�!");
			int hp = this.getHp() + 100;
			if(hp > this.getMaxHp()) hp = this.getMaxHp();
			this.setHp(hp);
			this.potion --;
		}
		else {
			System.out.println("������ �����ϴ�");
		}
	}

	@Override
	public void attack(Unit target) {
		if(target instanceof ZombieKing) {
			if(((ZombieKing) target).getshield() > 0) {
				System.out.printf("%s�� ����!\n",this.getName());
				int damage = (this.getAtt() - target.getDef())*(Unit.rn.nextInt(150)+50)/100;
				if(damage < 0) damage = 0;
				System.out.println("���� ����!");
				System.out.printf("[%d]�� �������� �Ծ����ϴ�!\n",damage);
				((ZombieKing) target).setshield(((ZombieKing) target).getshield()-damage);
				
				if(((ZombieKing) target).getshield() <= 0) {
					System.out.println("���尡 ��� ������!");
					int hp = ((ZombieKing) target).getshield() + target.getHp();
					target.setHp(hp);
					System.out.printf("[%s�� ���� HP: %d]\n",target.getName(),target.getHp());
				}
			}
			else {
				super.attack(target);
			}
		}
		else {
			super.attack(target);
		}
	}
}
