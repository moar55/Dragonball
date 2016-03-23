	package dragonball.model.battle;

import java.lang.reflect.Array;
import java.util.ArrayList;

import dragonball.model.attack.Attack;
import dragonball.model.character.fighter.Saiyan;

public class Battle {
	private BattleOpponent me;
	private BattleOpponent foe;
	private BattleOpponent attacker;
	private boolean meBlocking;
	private boolean foeBlocking;
	private BattleListener battlelistener;
	
	
	public Battle(BattleOpponent me, BattleOpponent foe){
	
		if(me instanceof Saiyan )
			((Saiyan)me).setTransformed(false);
		
		this.me=me;
		this.foe=foe;
		me.setHealthPoints(me.getMaxHealthPoints());
		foe.setHealthPoints(foe.getMaxHealthPoints());
		me.setStamina(me.getMaxStamina());
		foe.setStamina(foe.getMaxStamina());
		me.setKi(0);
		foe.setKi(0);
	}

	public BattleOpponent getMe() {
		return me;
	}

	public BattleOpponent getFoe() {
		return foe;
	}

	public BattleOpponent getCurrentOpponent() {
		return attacker;
	}
	
	
	ArrayList<Attack> getAssignedAttacks(){
		ArrayList<Attack> output= new ArrayList<>();
		
		output.addAll(me.getSuperAttacks());
		output.addAll(me.getUltimateAttacks());
		
		return output;
	}
	
	
}
