package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.*;

public class Frieza extends PlayableFighter	{

	static ArrayList<SuperAttack> superAttacks=new ArrayList<SuperAttack>();
	static ArrayList<UltimateAttack> ultimateAttacks= new ArrayList<UltimateAttack>();
	
	public Frieza(String name, int level, int xp, int targetXp, int maxHealthPoints,
			int blastDamage, int physicalDamage, int abilityPoints, int maxKi, int maxStamina,
			ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks){
			super(name,level,xp,targetXp,maxHealthPoints,blastDamage,physicalDamage,abilityPoints,maxKi,maxStamina,superAttacks,ultimateAttacks);
			
		
	}
	public Frieza(String name){

		/*constructing attributes in the form:name,maxHealthPoints,blastDamage,physicalDamage,0,maxKi,maxStamina,superAttacks,ultimateAttacks
		This line initializes this class with then 2nd Constructor of the PlayableFighter
				*/
		super(name,1100,75,75,4,4,superAttacks,ultimateAttacks); 

	}
	@Override
	public void onAttackerTurn() {
		setStamina(getStamina()+1);
		
	}
	@Override
	public void onDefenderTurn() {
		setStamina(getStamina()+1);

		
	}
}
