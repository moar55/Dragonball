package dragonball.model.exceptions;

import dragonball.model.attack.Attack;

public class DuplicateAttackException extends InvalidAssignAttackException{

	private Attack attack;
	
	public DuplicateAttackException(Attack attack) {
		super("Attack already assigned to figher!");
		this.attack=attack;
	}

	public Attack getAttack() {
		return attack;
	}
	
	
	
	
}
