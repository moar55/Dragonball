package dragonball.model.exceptions;

abstract public class InvalidAssignAttackException extends DragonBallException {
	
	public InvalidAssignAttackException(){
		super();
	}
	
	public  InvalidAssignAttackException(String message){
		super(message);
	}
}

