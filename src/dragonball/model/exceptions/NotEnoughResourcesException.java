package dragonball.model.exceptions;

abstract public class NotEnoughResourcesException extends DragonBallException {
		
	public NotEnoughResourcesException() {
		super();
	}
	
	public NotEnoughResourcesException(String message){
		super(message);
	}
	
}
