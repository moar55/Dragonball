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
	
	abstract int getAppliedDamage(BattleOpponent attacker );
	
	 void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking){
		  int damage= getAppliedDamage(attacker);
		  int remDamage=damage;
		  
		  if(defenderBlocking){
			  int deductStamina=damage/100;
			   remDamage=damage%100;
		  }
		  
		  defender.setHealthPoints(defender.getHealthPoints()-remDamage);
		  
		  
	 }
	
}
