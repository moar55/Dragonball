	package dragonball.model.battle;

import java.lang.reflect.Array;
import java.util.ArrayList;

import dragonball.model.attack.Attack;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.*;
import dragonball.model.player.Player;

public class Battle {
	private BattleOpponent me;
	private BattleOpponent foe;
	private BattleOpponent attacker;
	private boolean meBlocking;
	private boolean foeBlocking;
	private BattleListener game;
	
	
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
	
	public void attack (Attack attack){
	  
		Fighter attackerr = (Fighter)attacker;
		Fighter mee=(Fighter)me;
	
		
		if(attackerr.getName().equals(mee.getName()))
		attack.onUse(attacker,foe ,foeBlocking);
		
		else
			attack.onUse(attacker, me, meBlocking);
		
	 BattleEvent e = new BattleEvent(this,BattleEventType.ATTACK,attack);
	 	game.onBattleEvent(e);
	 	endTurn();
	 	
	}
	
	public void block(){
		
		meBlocking=true;
		BattleEvent e = new BattleEvent(this,BattleEventType.BLOCK);
		game.onBattleEvent(e);
		endTurn();
	}
	
	public void use(Player player, Collectible collectible){
		
		if(player.getSenzuBeans()>=1)
		{player.getActiveFighter().setHealthPoints(player.getActiveFighter().getMaxHealthPoints());
		 player.getActiveFighter().setStamina(player.getActiveFighter().getMaxStamina());
		}
		
		BattleEvent e = new BattleEvent(this, BattleEventType.USE);
		game.onBattleEvent(e);
	}
	
	public BattleOpponent getDefender(){
		Fighter attackerr = (Fighter)attacker;
		Fighter mee=(Fighter)me;
		
		BattleOpponent defender= (attackerr.getName().equals(mee.getName()))?foe:me;
		return defender;
	}
	
	public  void play(){
		
		int rand = (int)(Math.random()*2);
		if(rand ==0)
			foeBlocking = true;
		
		else
		{
			 rand = (int)(Math.random()*3);
			 if(rand==0)
				 attack(new PhysicalAttack());
			 
			 else if (rand==1){
				 ArrayList<SuperAttack> superAttacks = foe.getSuperAttacks();
				 SuperAttack temp= superAttacks.get((int)(Math.random()*superAttacks.size()));
				 attack(temp);
			 }
			else{
				ArrayList<UltimateAttack> ultimateAttacks = foe.getUltimateAttacks();
				 UltimateAttack temp= ultimateAttacks.get((int)(Math.random()*ultimateAttacks.size()));
				 attack(temp);	 
			 }
			 
			 }
	}
	
	public void start (){
		BattleEvent e = new BattleEvent(this, BattleEventType.STARTED);
		game.onBattleEvent(e);
	}
	
	void endTurn(){
		
	}
	
	
}
