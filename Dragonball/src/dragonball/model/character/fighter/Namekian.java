package dragonball.model.character.fighter;
import java.util.ArrayList;

import dragonball.model.attack.*;

public class Namekian extends PlayableFighter {
	
	static ArrayList<SuperAttack> superAttacks=new ArrayList<SuperAttack>();
	static ArrayList<UltimateAttack> ultimateAttacks= new ArrayList<UltimateAttack>();
	
	public Namekian(String name, int level, int xp, int targetXp, int maxHealthPoints,
			int blastDamage, int physicalDamage, int abilityPoints, int maxKi, int maxStamina,
			ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks){
			super(name,level,xp,targetXp,maxHealthPoints,blastDamage,physicalDamage,abilityPoints,maxKi,maxStamina,superAttacks,ultimateAttacks);
			
		
	}
	
	public Namekian(String name){
		
/*constructing attributes in the form:name,1,0,10,maxHealthPoints,blastDamage,physicalDamage,0,maxKi,maxStamina,superAttacks,ultimateAttacks
This line initializes this class with then 2nd Constructor of the PlayableFighter
		*/
		
		super(name,1350,0,50,3,5,superAttacks,ultimateAttacks); 
}

	@Override
	public void onAttackerTurn() {
		setStamina(getStamina()+1);
		setHealthPoints(getHealthPoints()+1);
		
	}

	@Override
	public void onDefenderTurn() {
		setStamina(getStamina()+1);
		setHealthPoints(getHealthPoints()+1);
		
	}

}
