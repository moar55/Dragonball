package dragonball.model.player;

import java.util.ArrayList;

import dragonball.model.attack.*;
import dragonball.model.character.fighter.PlayableFighter;

public class Player {
	
	private String name; //name of the player
	private ArrayList<PlayableFighter> fighters; //number of fighters you have
	private ArrayList<SuperAttack> 	superAttacks; //unlocked super-attacks
	private ArrayList<UltimateAttack> ultimateAttacks; //unlocked ultimate-attacks
	private int senzuBeans;  
	private int dragonBalls; 
	private PlayableFighter activeFighter; //current fighter
	private int exploredMaps; //number of maps explored
	
	public Player(String name){
		this.name=name;
	}
	
	public Player(String name, ArrayList<PlayableFighter> fighters, ArrayList<SuperAttack>
	superAttacks, ArrayList<UltimateAttack> ultimateAttacks, int senzuBeans, int dragonBalls,
	PlayableFighter activeFighter, int exploredMaps){
		this.name=name;
		this.fighters=fighters;
		this.superAttacks=superAttacks;
		this.ultimateAttacks=ultimateAttacks;
		this.senzuBeans=senzuBeans;
		this.dragonBalls=dragonBalls;
		this.activeFighter=activeFighter;
		this.exploredMaps=exploredMaps;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<PlayableFighter> getFighters() {
		return fighters;
	}

	public void setFighters(ArrayList<PlayableFighter> fighters) {
		this.fighters = fighters;
	}

	public ArrayList<SuperAttack> getSuperAttacks() {
		return superAttacks;
	}

	public void setSuperAttacks(ArrayList<SuperAttack> superAttacks) {
		this.superAttacks = superAttacks;
	}

	public ArrayList<UltimateAttack> getUltimateAttacks() {
		return ultimateAttacks;
	}

	public void setUltimateAttacks(ArrayList<UltimateAttack> ultimateAttacks) {
		this.ultimateAttacks = ultimateAttacks;
	}

	public int getSenzuBeans() {
		return senzuBeans;
	}

	public void setSenzuBeans(int senzuBeans) {
		this.senzuBeans = senzuBeans;
	}

	public int getDragonBalls() {
		return dragonBalls;
	}

	public void setDragonBalls(int dragonBalls) {
		this.dragonBalls = dragonBalls;
	}

	public PlayableFighter getActiveFighter() {
		return activeFighter;
	}

	public void setActiveFighter(PlayableFighter activeFighter) {
		this.activeFighter = activeFighter;
	}

	public int getExploredMaps() {
		return exploredMaps;
	}

	public void setExploredMaps(int exploredMaps) {
		this.exploredMaps = exploredMaps;
	}
	



}
