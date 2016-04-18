package dragonball.model.exceptions;

public class NotASaiyanException extends InvalidAssignAttackException{

	public NotASaiyanException() {
		super("You can't assign a SuperSaiyan attack to a non-Saiyan fighter!");
	}

	
}
