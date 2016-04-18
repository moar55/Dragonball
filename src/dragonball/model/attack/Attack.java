package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;

public abstract class Attack implements java.io.Serializable {
	private String name;
	private int damage;

	public Attack(String name, int damage) {
		this.name = name;
		this.damage = damage;
	}

	public String getName() {
		return name;
	}

	public int getDamage() {
		return damage;
	}
	
	abstract public int getAppliedDamage(BattleOpponent attacker);
		
	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) throws NotEnoughKiException{
		int damage= getAppliedDamage(attacker);
		if(attacker instanceof Saiyan && ((Saiyan)attacker).isTransformed())
			damage+=(damage*.25);
		
		Fighter a=(Fighter)defender;
		if(defenderBlocking){
			while(a.getStamina()>0 && damage>0){
				a.setStamina(a.getStamina()-1);
				damage-=100;
		
			}
		}
		
		if(damage>0)
		a.setHealthPoints(a.getHealthPoints()-damage);
		
	}
}
