package dragonball.model.battle;

import java.util.ArrayList;

import dragonball.model.character.fighter.SuperAttacks;
import dragonball.model.character.fighter.UltimateAttack;

public interface BattleOpponent {
	public int getLevel();

	public void setLevel(int level);

	public int getBlastDamage();

	public void setBlastDamage(int blastDamage);

	public int getPhsyicalDamage();

	public void setPhsyicalDamage(int phsyicalDamage);

	public int getHealthPoints();

	public void setHealthPoints(int healthPoints);

	public int getMaxHealthPoints();

	public void setMaxHealthPoints(int maxHealthPoints);

	public int getKi();

	public void setKi(int ki);

	public int getMaxKi();

	public void setMaxKi(int maxKi);

	public int getStamina();

	public void setStamina(int stamina);

	public ArrayList<SuperAttacks> getSuperAttacks();
	

	

	public ArrayList<UltimateAttack> getUltimateAttacks();

	

	public int getMaxStamina();

	public void setMaxStamina(int maxStamina);
}
