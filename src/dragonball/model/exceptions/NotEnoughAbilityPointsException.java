package dragonball.model.exceptions;

public class NotEnoughAbilityPointsException extends NotEnoughResourcesException{

	
	 public NotEnoughAbilityPointsException() {
		super("Not enough ability points to upgrade fighter!");
	}

}
