package dragonball.model.attack;


import java.util.ArrayList;

import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;

public class UltimateAttack extends Attack {
	public UltimateAttack(String name, int damage) {
		super(name, damage);
	}

	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
		return (getDamage()+((Fighter)attacker).getBlastDamage());
	}
	
	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) throws NotEnoughKiException {
		
		if(((Fighter)attacker).getKi()>=3 ||(attacker instanceof Saiyan && ((Saiyan)attacker).isTransformed())){
		super.onUse(attacker, defender, defenderBlocking);
		((Fighter)attacker).setKi(((Fighter)attacker).getKi()-3);
		}
		else
			throw new NotEnoughKiException(3, ((Fighter)attacker).getKi());
 
	}
	
	public static void main(String[] args) throws NotEnoughKiException {
		NonPlayableFighter strong = new NonPlayableFighter("Goku", 10, 3000,
				350, 100, 5, 6, true, null, null);
		ArrayList<UltimateAttack> ultimateAttacks = new ArrayList<UltimateAttack>();
		ultimateAttacks.add(new UltimateAttack("Spirit Bomb", 500));
		Namekian e = new Namekian("namekian");
		e.setUltimateAttacks(ultimateAttacks);
		ArrayList<UltimateAttack> ultimateAttacks2 = new ArrayList<UltimateAttack>();
		ultimateAttacks2.add(new UltimateAttack("Super Kamehameha", 450));
		strong.setUltimateAttacks(ultimateAttacks2);
		Battle b = new Battle(e, strong);
		b.start();
		b.attack(new PhysicalAttack());

		b.attack(new PhysicalAttack());

		b.attack(new PhysicalAttack());

		b.attack(new PhysicalAttack());

		b.attack(new PhysicalAttack());

		b.attack(new PhysicalAttack());

		System.out.println(e.getKi());
		b.attack(e.getUltimateAttacks().get(0));
		System.out.println(e.getKi());
	}
}
