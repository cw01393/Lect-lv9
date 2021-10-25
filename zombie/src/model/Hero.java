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
			this.potion --;
			System.out.println("ü���� 100 ȸ���Ǿ����ϴ�!");
			int hp = this.getHp() + 100;
			if(hp > this.getMaxHp()) hp = this.getMaxHp();
			this.setHp(hp);
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
				System.out.println("���� -1 !");
				((ZombieKing) target).setshield(((ZombieKing) target).getshield()-1);
				
				if(((ZombieKing) target).getshield() == 0) {
					System.out.println("���尡 ��� ������!");
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
