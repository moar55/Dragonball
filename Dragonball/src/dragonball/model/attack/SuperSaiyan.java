package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Saiyan;

public class SuperSaiyan extends UltimateAttack {
	public SuperSaiyan(){
		super ("Super Saiyan",0);
	}
	
	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) {
		
		
		Saiyan s= (Saiyan)attacker;
		if(s.getKi()>=3)
		s.setTransformed(true);
	
	}

}
