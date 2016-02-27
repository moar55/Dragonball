package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.*;

public class Majin extends PlayableFighter{
	
	static ArrayList<SuperAttack> superAttacks=new ArrayList<SuperAttack>();
	static ArrayList<UltimateAttack> ultimateAttacks= new ArrayList<UltimateAttack>();
	
	public Majin(String name){
		super(name,1,0,10,1500,50,50,0,3,6,superAttacks,ultimateAttacks);

	}
}
