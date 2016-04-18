package dragonball.model.dragon;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

public class Dragon implements java.io.Serializable	 {
	private String name;
	private ArrayList<SuperAttack> superAttacks;
	private ArrayList<UltimateAttack> ultimateAttacks;
	private int senzuBeans;
	private int abilityPoints;

	public Dragon(String name, ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks,
			int senzuBeans, int abilityPoints) {
		this.name = name;
		this.superAttacks = superAttacks;
		this.ultimateAttacks = ultimateAttacks;
		this.senzuBeans = senzuBeans;
		this.abilityPoints = abilityPoints;
	}

	public String getName() {
		return name;
	}

	public ArrayList<SuperAttack> getSuperAttacks() {
		return superAttacks;
	}

	public ArrayList<UltimateAttack> getUltimateAttacks() {
		return ultimateAttacks;
	}

	public int getSenzuBeans() {
		return senzuBeans;
	}

	public int getAbilityPoints() {
		return abilityPoints;
	}
	
	public DragonWish[] getWishes(){
		ArrayList<DragonWish> wishesList= new ArrayList<>();
		
		if(senzuBeans>0)wishesList.add(new DragonWish(this, DragonWishType.SENZU_BEANS,senzuBeans));
		if(abilityPoints>0)wishesList.add(new DragonWish(this,DragonWishType.ABILITY_POINTS,abilityPoints));
		if(superAttacks.size()>0)
		wishesList.add(new DragonWish(this, DragonWishType.SUPER_ATTACK, superAttacks.get((int)(Math.random()*superAttacks.size()))));
		if(ultimateAttacks.size()>0)
			wishesList.add(new DragonWish(this, DragonWishType.ULTIMATE_ATTACK, ultimateAttacks.get((int)(Math.random()*ultimateAttacks.size()))));
		DragonWish [] wishes= new DragonWish[wishesList.size()];
		wishes=wishesList.toArray(wishes);
		return wishes;
	}
}
