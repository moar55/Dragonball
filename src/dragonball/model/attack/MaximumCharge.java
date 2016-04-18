package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.*;
public class MaximumCharge extends SuperAttack {
	public MaximumCharge() {
		super("Maximum Charge", 0);
	}
	
	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) {
		((Fighter)attacker).setKi(((Fighter)attacker).getKi()+3);}
		 public int getAppliedDamage(BattleOpponent attacker){
			 return 0;
		}
		
	}

