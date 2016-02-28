package dragonball.model.attack;

abstract public class Attack {
	private  String name;
	 private int damage;
	public Attack(String name, int damage){
		this.name =name;
		this.damage=damage;
	}
	public String getName(){
		return name;
	}
	public int getDamage(){
		return damage;
	}
}
