package dragonball.model.attack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.*;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.Saiyan;

public class SuperAttack extends Attack {
	public SuperAttack (String name, int damage){
		super (name, damage);
	}

	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
		
		int damage = getDamage()+attacker.getBlastDamage();
		
		if(attacker instanceof Saiyan && ((Saiyan)attacker).isTransformed())
		return damage+ (int)(0.25*damage);
		
		else
			return damage;
	}

	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) {
		
		if(attacker.getKi()>=1){
			super.onUse(attacker, defender, defenderBlocking);
			attacker.setKi(attacker.getKi()-1);
		}
					
	}
	
	public static void main(String[] args) {
		NonPlayableFighter strong = new NonPlayableFighter("Goku", 10, 3000,
				350, 400, 5, 3, true, null, null);
		ArrayList<SuperAttack> superAttacks = new ArrayList<SuperAttack>();
		superAttacks.add(new SuperAttack("Kamehameha", 250));
		Frieza e = new Frieza("frieza");
		e.setSuperAttacks(superAttacks);
		strong.setSuperAttacks(superAttacks);
		Battle b = new Battle(e, strong);
		b.start();
		b.attack(new PhysicalAttack());

		b.attack(new PhysicalAttack());
		b.block();

		int prevHP = e.getHealthPoints();
		int prevStamina = e.getStamina();

		b.attack(strong.getSuperAttacks().get(0));
		assertEquals(
				"if foe attacks me while me was blocking ,me's Stamina should be reduced according to the game rules",
				1, e.getStamina());
		
		assertEquals(
				"When foe attacks me while me is blocking with Super attack and me's stamina reached zero , the health points of me should be reduced according to the game rules",
				prevHP - (600-prevStamina*100), e.getHealthPoints());

		System.out.println(strong.getHealthPoints());
		b.attack(new PhysicalAttack());
		System.out.println(b.isMeBlocking());

		b.block();
		System.out.println(b.isMeBlocking());
	
		prevHP = strong.getHealthPoints();
		prevStamina = strong.getStamina();
		System.out.println(strong.getHealthPoints());
		System.out.println(strong.getStamina());
		b.attack(e.getSuperAttacks().get(0));
		System.out.println(strong.getStamina());
	}

}
