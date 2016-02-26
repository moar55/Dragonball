package dragonball.model.attack;

public class SuperAttack extends Attack {
	public SuperAttack (String name, int damage){
		super (name, damage);
	}
	public class MaximumCharge extends SuperAttack{
		public MaximumCharge (String name, int damage){
			super ("MaximumCharge", 0);
		}
	}
}
