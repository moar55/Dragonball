package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;

public class UltimateAttack extends Attack  {
	public UltimateAttack (String name, int damage){
		super (name, damage);
}

	@Override
	int getAppliedDamage(BattleOpponent attacker) {

		return this.getDamage()+attacker.getBlastDamage();
	}

	@Override
	void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) {

		if(attacker.getKi()>=3){
			super.onUse(attacker, defender, defenderBlocking);
			attacker.setKi(attacker.getKi()-3);
		}
	}
		
		
		
	
	
}
