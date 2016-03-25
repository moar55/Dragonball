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
		attacker=me;
		
		BattleEvent e = new BattleEvent(this, BattleEventType.STARTED);
		if(game!=null)
		game.onBattleEvent(e);
	}

	public BattleOpponent getMe() {
		return me;
	}

	public BattleOpponent getFoe() {
		return foe;
	}


	
	public ArrayList<Attack> getAssignedAttacks(){
		ArrayList<Attack> output= new ArrayList<>();
		output.add(new PhysicalAttack());
		output.addAll(me.getSuperAttacks());
		output.addAll(me.getUltimateAttacks());
		
		return output;
	}
	
	public void attack (Attack attack){
	  
		Fighter attackerr = (Fighter)attacker;
		Fighter mee=(Fighter)me;
	
		
		if(attackerr.getName().equals(mee.getName()))
		attack.onUse(me,foe ,foeBlocking);
		
		else
			attack.onUse(foe, me, meBlocking);
		
	 BattleEvent e = new BattleEvent(this,BattleEventType.ATTACK,attack);
	 	if(game!=null)
	 		game.onBattleEvent(e);
	 	endTurn();
	 	
	}
	
	public void block(){
		
		meBlocking=true;
		BattleEvent e = new BattleEvent(this,BattleEventType.BLOCK);
		if(game!=null)
		game.onBattleEvent(e);
		endTurn();
	}
	
	public void use(Player player, Collectible collectible){
		
		if(player.getSenzuBeans()>=1)
		{player.getActiveFighter().setHealthPoints(player.getActiveFighter().getMaxHealthPoints());
		 player.getActiveFighter().setStamina(player.getActiveFighter().getMaxStamina());
		 player.setSenzuBeans(player.getSenzuBeans()-1);
		 BattleEvent e = new BattleEvent(this, BattleEventType.USE);
			if(game!=null)
			game.onBattleEvent(e);
			endTurn();
		}
		
		
	}
	
	public BattleOpponent getDefender(){
		Fighter attackerr = (Fighter)attacker;
		Fighter mee=(Fighter)me;
		
		BattleOpponent defender= (attackerr.getName().equals(mee.getName()))?foe:me;
		return defender;
	}
	
	public  void play(){
		
		int rand = (int)(Math.random()*2);
		if(rand ==0){
			foeBlocking = true;
			endTurn();
		}
		else
		{
			 rand = (int)(Math.random()*3);
			 if(rand==0){
				 attack(new PhysicalAttack());
			 }
			 else if (rand==1){
				 ArrayList<SuperAttack> superAttacks = attacker.getSuperAttacks();
				 SuperAttack temp= superAttacks.get((int)(Math.random()*superAttacks.size()));
				 attack(temp);
			 }
			else{
				ArrayList<UltimateAttack> ultimateAttacks = attacker.getUltimateAttacks();
				 UltimateAttack temp= ultimateAttacks.get((int)(Math.random()*ultimateAttacks.size()));
				 attack(temp);	 
			 }
			 
			 }
	}
	
	public void start (){
		BattleEvent e = new BattleEvent(this, BattleEventType.STARTED);
		if(game!=null)game.onBattleEvent(e);
	}
	
	public void endTurn(){
		
		 
		if(foe.getHealthPoints()==0 || me.getHealthPoints()==0){
			if(foe.getHealthPoints()==0)
			{
				BattleEvent e = new BattleEvent(this, BattleEventType.ENDED, me);
				if(game!=null)
				game.onBattleEvent(e);
			}
			
			else
			{
				BattleEvent e = new BattleEvent(this, BattleEventType.ENDED, foe);
				if(game!=null)
				game.onBattleEvent(e);
				
			}
		}
		
		else
		{
			Fighter attackerr = (Fighter)attacker;
			Fighter mee=(Fighter)me;
			Fighter foee= (Fighter)foe;
			
			if(attackerr.getName().equals(mee.getName()))
			{
				me.onAttackerTurn();
				foe.onDefenderTurn();
				switchTurn();
			}
			
			else
			{
				foe.onAttackerTurn();
				me.onDefenderTurn();
				switchTurn();
			}
		}
	}
	
	public void switchTurn(){
		Fighter attackerr = (Fighter)attacker;
		Fighter mee=(Fighter)me;
		
		if(attackerr.getName().equals(mee.getName()))
			attacker=foe;
		
		else
			attacker=me;
			
		
	}

	public void setGame(BattleListener game) {
		this.game = game;
	}

	public BattleOpponent getAttacker() {
		return attacker;
	}

	public boolean isMeBlocking() {
		return meBlocking;
	}

	public boolean isFoeBlocking() {
		return foeBlocking;
	}
	
	
	
	public static void main(String[] args) {
		
	}
	
	
	
	
	
	
	
	
}
