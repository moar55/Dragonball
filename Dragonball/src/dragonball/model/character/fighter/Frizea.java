package dragonball.model.character.fighter;

import java.util.ArrayList;

public class Frizea extends PlayableFighter	{

	public Frizea(String name, ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack>
	ultimateAttacks){

		/*constructing attributes in the form:name,1,0,10,maxHealthPoints,blastDamage,physicalDamage,0,maxKi,maxStamina,superAttacks,ultimateAttacks
		This line initializes this class with then 2nd Constructor of the PlayableFighter
				*/
		super(name,1,0,10,1100,75,75,0,4,4,superAttacks,ultimateAttacks); 

	}
}
