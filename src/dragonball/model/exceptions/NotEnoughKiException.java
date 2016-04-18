package dragonball.model.exceptions;

public class NotEnoughKiException extends NotEnoughResourcesException{

	private int availableKi;
	private int requiredKi;
	
	public NotEnoughKiException(int requiredKi, int availableKi) {
		super("This attack requires "+ requiredKi+" ki points but you only have "+ availableKi);
		this.requiredKi=requiredKi;
		this.availableKi=availableKi;
	}
	
	
	public int getAvailableKi() {
		return availableKi;
	}
	public int getRequiredKi() {
		return requiredKi;
	}
	
	
	
	
}
