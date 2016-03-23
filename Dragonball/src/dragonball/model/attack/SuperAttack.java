package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Saiyan;

public class SuperAttack extends Attack {
	public SuperAttack (String name, int damage){
		super (name, damage);
	}

	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
		
		int damage = getDamage()+attacker.getBlastDamage();
		
		if(attacker instanceof Saiyan && ((Saiyan)attacker).isTransformed())
		return damage+ (int)(0.25*damage);
		
		else
			return damage;
	}

	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) {
		
		if(attacker.getKi()>=1){
			super.onUse(attacker, defender, defenderBlocking);
			attacker.setKi(attacker.getKi()-1);
		}
					
	}

}
