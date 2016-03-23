package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;

public class PhysicalAttack extends Attack  {

	public PhysicalAttack() {
		super("PhysicalAttack", 50);
		
	}

	@Override
	int getAppliedDamage(BattleOpponent attacker) {
		// TODO Auto-generated method stub
		
		return 50+attacker.getPhysicalDamage();
	}

	@Override
	void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) {
		super.onUse(attacker,defender,defenderBlocking);
		attacker.setKi(attacker.getKi()+1);
	}
	
		

}
