package dragonball.model.character.fighter;
import java.util.ArrayList;

import dragonball.model.attack.*;

public class Earthling extends PlayableFighter {
	
	static ArrayList<SuperAttack> superAttacks=new ArrayList<SuperAttack>();
	static ArrayList<UltimateAttack> ultimateAttacks= new ArrayList<UltimateAttack>();
	
	public Earthling(String name, int level, int xp, int targetXp, int maxHealthPoints,
			int blastDamage, int physicalDamage, int abilityPoints, int maxKi, int maxStamina,
			ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks){
			super(name,level,xp,targetXp,maxHealthPoints,blastDamage,physicalDamage,abilityPoints,maxKi,maxStamina,superAttacks,ultimateAttacks);
			
		
	}
	
	public Earthling(String name){
		
		/*constructing attributes in the form:name,maxHealthPoints,blastDamage,physicalDamage,maxKi,maxStamina,superAttacks,ultimateAttacks
		This line initializes this class with then 2nd Constructor of the PlayableFighter
				*/
		
		super(name,1250,50,50,4,4,superAttacks,ultimateAttacks); 
	}
}
