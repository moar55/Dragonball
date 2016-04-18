package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.player.Player;

public class PhysicalAttack extends Attack  {

	public PhysicalAttack() {
		super("PhysicalAttack", 50);
		
	}

	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
	
		int damage = 50+attacker.getPhysicalDamage();
		if(attacker instanceof Saiyan && ((Saiyan)attacker).isTransformed())
		return damage+ (int)(0.25*damage);
		
		else
			return damage;
	}

	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) {
		super.onUse(attacker,defender,defenderBlocking);
		attacker.setKi(attacker.getKi()+1);
	}
	
	public static void main(String[] args) {
		Player p = new Player("7amada");
		NonPlayableFighter strong = new NonPlayableFighter("Goku", 10, 3000,
				100, 100, 5, 6, true, null, null);
		Frieza e = new Frieza("frieza");
		p.getFighters().add(e);
		PhysicalAttack a = new PhysicalAttack();
		a.onUse(e, strong, true);
		System.out.println(strong.getHealthPoints());
	}
	
		

}
