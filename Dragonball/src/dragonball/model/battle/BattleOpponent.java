package dragonball.model.battle;

import java.util.ArrayList;

import dragonball.model.character.fighter.SuperAttacks;
import dragonball.model.character.fighter.UltimateAttack;

public interface BattleOpponent {

	public int getLevel();

	public int getBlastDamage();

	public int getPhsyicalDamage();
	
	public int getHealthPoints();

	public void setHealthPoints(int hp);

	public int getMaxHealthPoints();
	
	public int getKi();

	public void setKi(int ki);

	public int getMaxKi();

	public int getStamina();

	public void setStamina(int stamina);

	public ArrayList<SuperAttacks> getSuperAttacks();
	
	public ArrayList<UltimateAttack> getUltimateAttacks();

	public int getMaxStamina();
	
}
