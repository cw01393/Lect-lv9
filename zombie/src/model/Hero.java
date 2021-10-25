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
			System.out.println("체력이 100 회복되었습니다!");
			int hp = this.getHp() + 100;
			if(hp > this.getMaxHp()) hp = this.getMaxHp();
			this.setHp(hp);
			this.potion --;
		}
		else {
			System.out.println("물약이 없습니다");
		}
	}

	@Override
	public void attack(Unit target) {
		if(target instanceof ZombieKing) {
			if(((ZombieKing) target).getshield() > 0) {
				System.out.printf("%s의 공격!\n",this.getName());
				int damage = (this.getAtt() - target.getDef())*(Unit.rn.nextInt(150)+50)/100;
				if(damage < 0) damage = 0;
				System.out.println("쉴드 공격!");
				System.out.printf("[%d]의 데미지를 입었습니다!\n",damage);
				((ZombieKing) target).setshield(((ZombieKing) target).getshield()-damage);
				
				if(((ZombieKing) target).getshield() <= 0) {
					System.out.println("쉴드가 모두 깨졌다!");
					int hp = ((ZombieKing) target).getshield() + target.getHp();
					target.setHp(hp);
					System.out.printf("[%s의 남은 HP: %d]\n",target.getName(),target.getHp());
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
