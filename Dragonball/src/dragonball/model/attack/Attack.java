package dragonball.model.attack;

public class Attack {
	private  String name;
	 private int damage;
	public Attack(String name, int damage){
		this.name =name;
		this.damage=damage;
	}
	public String getAttackname(){
		return name;
	}
	public int getAttackdamage(){
		return damage;
	}
}
