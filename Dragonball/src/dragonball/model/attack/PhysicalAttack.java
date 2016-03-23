package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Saiyan;

public class PhysicalAttack extends Attack  {

	public PhysicalAttack() {
		super("PhysicalAttack", 50);
		
	}

	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
	
		int damage = 50+attacker.getPhysicalDamage();
		
		if(attacker instanceof Saiyan && ((Saiyan)attacker).isTransformed())
		return damage+ (int)(0.25*damage);
		
		else
			return damage;
	}

	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) {
		super.onUse(attacker,defender,defenderBlocking);
		attacker.setKi(attacker.getKi()+1);
	}
	
		

}
