package dragonball.model.dragon;

public class Dragon {
	String name;
	ArrayList<SuperAttack> superAttacks;
	ArrayList<UltimateAttack> ultimateAttacks;
	int senzuBeans;
	int abilityPoints;
	public Dragon(String name, ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack>
	ultimateAttacks, int senzuBeans, int abilityPoints){
		this.name=name;
		this.senzuBeans=senzuBeans;
		this.abilityPoints=abilityPoints;
	}

}
