package dragonball.model.character.fighter;
import dragonball.model.attack.*;
import java.util.ArrayList;

public class Saiyan extends  PlayableFighter{
	
	static ArrayList<SuperAttack> superAttacks=new ArrayList<SuperAttack>();
	static ArrayList<UltimateAttack> ultimateAttacks= new ArrayList<UltimateAttack>();
	private boolean transformed;
	
	
	public Saiyan(String name){

		/*constructing attributes in the form:name,maxHealthPoints,blastDamage,physicalDamage,maxKi,maxStamina,superAttacks,ultimateAttacks
		This line initializes this class with then 2nd Constructor of the PlayableFighter
		*/
		super(name,1000,150,100,5,3,superAttacks,ultimateAttacks);
		transformed=false;
	}
	
	public Saiyan(String name, int level, int xp, int targetXp, int maxHealthPoints,
			int blastDamage, int physicalDamage, int abilityPoints, int maxKi, int maxStamina,
			ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks){
			super(name,level,xp,targetXp,maxHealthPoints,blastDamage,physicalDamage,abilityPoints,maxKi,maxStamina,superAttacks,ultimateAttacks);
			transformed=false;
		
	}
	
	

	public boolean isTransformed() {
		return transformed;
	}

	public void setTransformed(boolean transformed) {
		this.transformed = transformed;
	}

	@Override
	public void onAttackerTurn() {
		
		setStamina(getStamina()+1);
		if(isTransformed()){
			setKi(getKi()-1);
			if(getKi()<=0){
				setKi(0);
				setStamina(0);
				setTransformed(false);
			}
				
		}
		
	}

	@Override
	public void onDefenderTurn() {
		
		setStamina(getStamina()+1);
		if(isTransformed()){
			setKi(getKi()-1);
			if(getKi()<=0){
				setKi(0);
				setStamina(0);
				setTransformed(false);
			}
				
		}
		
	}
	
		
}
