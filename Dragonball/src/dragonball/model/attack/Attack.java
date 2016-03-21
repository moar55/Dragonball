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
	
	abstract void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking);
	
}
