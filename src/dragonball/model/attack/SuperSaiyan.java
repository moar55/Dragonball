package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.*;
import dragonball.model.exceptions.NotEnoughKiException;

public class SuperSaiyan extends UltimateAttack {
	public SuperSaiyan() {
		super("Super Saiyan", 0);
	}
	
	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) throws NotEnoughKiException {
		if(((Fighter)attacker).getKi()>=3)
			((Saiyan)attacker).setTransformed(true);
		
		else
			throw new NotEnoughKiException(3, ((Fighter)attacker).getKi());
		
	}
	public int getAppliedDamage(BattleOpponent attacker){
		 return 0;
	}
}
