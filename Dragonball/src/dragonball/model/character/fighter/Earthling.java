package dragonball.model.character.fighter;
import java.util.ArrayList;

import dragonball.model.attack.*;

public class Earthling extends PlayableFighter {
	
	static ArrayList<SuperAttack> superAttacks=new ArrayList<SuperAttack>();
	static ArrayList<UltimateAttack> ultimateAttacks= new ArrayList<UltimateAttack>();
	
	public Earthling(String name){
		
		/*constructing attributes in the form:name,1,0,10,maxHealthPoints,blastDamage,physicalDamage,0,maxKi,maxStamina,superAttacks,ultimateAttacks
		This line initializes this class with then 2nd Constructor of the PlayableFighter
				*/
		
		super(name,1,0,10,1250,50,50,0,4,4,superAttacks,ultimateAttacks); 
	}
}
