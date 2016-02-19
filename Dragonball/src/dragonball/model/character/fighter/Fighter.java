package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.character.Character;

public class Fighter extends Character implements BattleOponent  {
	
	private int level;
	private int blastDamage; 
	private int phsyicalDamage;
	private int healthPoints;
	private int maxHealthPoints;
	private int ki;	
	private int maxKi;
	private int stamina;
	int maxStamina;
	private ArrayList<SuperAttacks>superAttacks;
	private ArrayList<UltimateAttack>ultimateAttacks;
	
	public Fighter(String name, int level, int maxHealthPoints, int blastDamage,
		int physicalDamage, int maxKi, int maxStamina, ArrayList<SuperAttack> superAttacks,
		ArrayList<UltimateAttack> ultimateAttacks)  {
		
		 super(name);
	 }
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getBlastDamage() {
		return blastDamage;
	}

	public void setBlastDamage(int blastDamage) {
		this.blastDamage = blastDamage;
	}

	public int getPhsyicalDamage() {
		return phsyicalDamage;
	}

	public void setPhsyicalDamage(int phsyicalDamage) {
		this.phsyicalDamage = phsyicalDamage;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public int getMaxHealthPoints() {
		return maxHealthPoints;
	}

	public void setMaxHealthPoints(int maxHealthPoints) {
		this.maxHealthPoints = maxHealthPoints;
	}

	public int getKi() {
		return ki;
	}

	public void setKi(int ki) {
		this.ki = ki;
	}

	public int getMaxKi() {
		return maxKi;
	}

	public void setMaxKi(int maxKi) {
		this.maxKi = maxKi;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public ArrayList<SuperAttacks> getSuperAttacks() {
		return superAttacks;
	}

	public void setSuperAttacks(ArrayList<SuperAttacks> superAttacks) {
		this.superAttacks = superAttacks;
	}

	public ArrayList<UltimateAttack> getUltimateAttacks() {
		return ultimateAttacks;
	}

	public void setUltimateAttacks(ArrayList<UltimateAttack> ultimateAttacks) {
		this.ultimateAttacks = ultimateAttacks;
	}

	public int getMaxStamina() {
		return maxStamina;
	}

	public void setMaxStamina(int maxStamina) {
		this.maxStamina = maxStamina;
	}

	
	
	
}
