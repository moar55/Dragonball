package dragonball.model.battle;

import java.util.ArrayList;

import dragonball.model.attack.*;



public interface BattleOpponent {

	public int getLevel();

	public int getBlastDamage();

	public int getPhysicalDamage();
	
	public int getHealthPoints();

	public void setHealthPoints(int hp);

	public int getMaxHealthPoints();
	
	public int getKi();

	public void setKi(int ki);

	public int getMaxKi();

	public int getStamina();

	public void setStamina(int stamina);

	public ArrayList<SuperAttack> getSuperAttacks();
	
	public ArrayList<UltimateAttack> getUltimateAttacks();

	public int getMaxStamina();
	
	public void onAttackerTurn();
	
	public void onDefenderTurn();
	
}
