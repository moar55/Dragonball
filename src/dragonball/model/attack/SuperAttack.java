package dragonball.model.attack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;

public class SuperAttack extends Attack {
	public SuperAttack(String name, int damage) {
		super(name, damage);
	}

	
	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
			return getDamage()+((Fighter)attacker).getBlastDamage();
	}
	
	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) throws NotEnoughKiException {
		if(((Fighter)attacker).getKi()>=1 ||(attacker instanceof Saiyan && ((Saiyan)attacker).isTransformed())){
		super.onUse(attacker, defender, defenderBlocking);
		((Fighter)attacker).setKi(((Fighter)attacker).getKi()-1);
		}
		
		else
			throw new NotEnoughKiException(1, ((Fighter)attacker).getKi());
	}
	
	public static void main(String[] args) throws NotEnoughKiException {
		
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
		System.out.println(b.isMeBlocking());
		int prevHP = e.getHealthPoints();
		int prevStamina = e.getStamina();
		System.out.println(e.getStamina());
		System.out.println(b.getAttacker());
		b.attack(new PhysicalAttack());
		System.out.println(e.getStamina());
		assertEquals(
				"if foe attacks me while me was blocking ,me's Stamina should be reduced according to the game rules",
				1, e.getStamina());
	}
}
