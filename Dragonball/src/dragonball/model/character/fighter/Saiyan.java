package dragonball.model.character.fighter;
import dragonball.model.attack.*;
import java.util.ArrayList;

public class Saiyan extends  PlayableFighter{
	
	static ArrayList<SuperAttack> superAttacks=new ArrayList<SuperAttack>();
	static ArrayList<UltimateAttack> ultimateAttacks= new ArrayList<UltimateAttack>();
	private boolean transformed;
	
	public Saiyan(String name){

		/*constructing attributes in the form:name,1,0,10,maxHealthPoints,blastDamage,physicalDamage,0,maxKi,maxStamina,superAttacks,ultimateAttacks
		This line initializes this class with then 2nd Constructor of the PlayableFighter
		*/
		super(name,1,0,10,1000,150,100,0,5,3,superAttacks,ultimateAttacks);
	}

	public boolean isTransformed() {
		return transformed;
	}

	public void setTransformed(boolean transformed) {
		this.transformed = transformed;
	}
	
		
}
