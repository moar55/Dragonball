package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Saiyan;

public class UltimateAttack extends Attack  {
	public UltimateAttack (String name, int damage){
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

		if(attacker.getKi()>=3){
			super.onUse(attacker, defender, defenderBlocking);
			attacker.setKi(attacker.getKi()-3);
		}
	}
		
		
		
	
	
}
