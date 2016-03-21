package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;

public class UltimateAttack extends Attack  {
	public UltimateAttack (String name, int damage){
		super (name, damage);
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
