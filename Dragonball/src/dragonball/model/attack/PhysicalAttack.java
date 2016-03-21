package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;

public class PhysicalAttack extends Attack  {

	public PhysicalAttack() {
		super("PhysicalAttack", 50);
		
	}

	@Override
	int getAppliedDamage(BattleOpponent attacker) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) {
		// TODO Auto-generated method stub
		
	}
	
		

}
