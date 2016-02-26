package dragonball.model.attack;

public class UltimateAttack extends Attack  {
	public UltimateAttack (String name, int damage){
		super (name, damage);
}
	public class SuperSaiyan extends UltimateAttack {
		public SuperSaiyan(String name,int damage){
			super ("SuperSaiyan",0);
		}
	}
}
