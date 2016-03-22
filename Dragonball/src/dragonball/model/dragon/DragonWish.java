package dragonball.model.dragon;

import java.util.EventObject;

import dragonball.model.attack.*;

public class DragonWish extends EventObject{
	private DragonWishType type;
	private int senzuBeans;
	private int abilityPoints;
	private SuperAttack superAttack;
	private UltimateAttack ultimateAttack;
	
	
	DragonWish(Dragon dragon, DragonWishType type){
		super(dragon);
		this.type=type;
	}
	
	DragonWish(Dragon dragon, DragonWishType type, int senzuBeansOrAbilityPoints){
	this(dragon,type);
	if(type==DragonWishType.SENZU_BEANS)
		senzuBeans=senzuBeansOrAbilityPoints;
	else
		abilityPoints=senzuBeansOrAbilityPoints;
	}
	
	DragonWish(Dragon dragon, DragonWishType type, SuperAttack superAttack){
		this(dragon,type);
		this.superAttack=superAttack;
	}
	
	DragonWish(Dragon dragon, DragonWishType type, UltimateAttack ultimateAttack){
		this(dragon,type);
		this.ultimateAttack=ultimateAttack;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public DragonWishType getType() {
		return type;
	}
	public int getSenzuBeans() {
		return senzuBeans;
	}
	public int getAbilityPoints() {
		return abilityPoints;
	}
	public SuperAttack getSuperAttack() {
		return superAttack;
	}
	public UltimateAttack getUltimateAttack() {
		return ultimateAttack;
	}
	
	
	
	
	
	
	
	
	
	
	
}
