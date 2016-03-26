package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;

abstract public class Attack {
	private  String name;
	 private int damage;
	public Attack(String name, int damage){
		this.name =name;
		this.damage=damage;
	}
	public String getName(){
		return name;
	}
	public int getDamage(){
		return damage;
	}
	
	abstract public int getAppliedDamage(BattleOpponent attacker );
	
	 public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking){
		  int remDamage= getAppliedDamage(attacker);
		  
		  
		  if(defenderBlocking){
			  int deductStamina=0;
			  for(int i=defender.getStamina();i>0 && remDamage>0 ;i--,remDamage-=100,deductStamina++){
			  ;
			  }
			  
			  if(remDamage<0)remDamage=0;			  
			   defender.setStamina(defender.getStamina()-deductStamina);
			   
		  }
		  
		  defender.setHealthPoints(defender.getHealthPoints()-remDamage);
		  
		  
	 }
	
}
