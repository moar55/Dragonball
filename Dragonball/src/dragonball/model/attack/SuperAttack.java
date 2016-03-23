package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;

public class SuperAttack extends Attack {
	public SuperAttack (String name, int damage){
		super (name, damage);
	}

	@Override
	int getAppliedDamage(BattleOpponent attacker) {
		
		return this.getDamage()+attacker.getBlastDamage();
	}

	@Override
	void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) {
		
		if(attacker.getKi()>=1){
			super.onUse(attacker, defender, defenderBlocking);
			attacker.setKi(attacker.getKi()-1);
		}
					
	}

}
