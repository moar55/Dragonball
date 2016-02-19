package dragonball.model.character.fighter;

public class Namekian extends PlayableFighter {
	
	public Namekian(String name, ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack>
	ultimateAttacks){

/*constructing attributes in the form:name,1,0,10,maxHealthPoints,blastDamage,physicalDamage,0,maxKi,maxStamina,superAttacks,ultimateAttacks
This line initializes this class with then 2nd Constructor of the PlayableFighter
		*/
		
		super(name,1,0,10,1350,0,50,0,3,5,superAttacks,ultimateAttacks); 
}
}
