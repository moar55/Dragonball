package dragonball.model.dragon;

import java.util.ArrayList;

import dragonball.model.attack.*;

public class Dragon {
	private String name;
	private ArrayList<SuperAttack> superAttacks;
	private ArrayList<UltimateAttack> ultimateAttacks;
	private int senzuBeans;
	private int abilityPoints;
	public Dragon(String name, ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack>
	ultimateAttacks, int senzuBeans, int abilityPoints){
		this.name=name;
		this.superAttacks=superAttacks;
		this.ultimateAttacks=ultimateAttacks;
		this.senzuBeans=senzuBeans;
		this.abilityPoints=abilityPoints;
		
	}
	
	public String getName() {
		return name;
	}
	public ArrayList<SuperAttack> getSuperAttacks() {
		return superAttacks;
	}
	public ArrayList<UltimateAttack> getUltimateAttacks() {
		return ultimateAttacks;
	}
	public int getSenzuBeans() {
		return senzuBeans;
	}
	public int getAbilityPoints() {
		return abilityPoints;
	}
	
	

}
