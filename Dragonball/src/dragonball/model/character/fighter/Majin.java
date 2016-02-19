package dragonball.model.character.fighter;

import java.util.ArrayList;

public class Majin extends PlayableFighter{
	
	public Majin(String name, ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack>
	ultimateAttacks){
		this(name,1,0,10,1500,50,50,0,3,6,superAttacks,ultimateAttacks);

	}
}
