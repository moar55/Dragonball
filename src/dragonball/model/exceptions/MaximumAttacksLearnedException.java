package dragonball.model.exceptions;

public class MaximumAttacksLearnedException extends InvalidAssignAttackException{
	
	public MaximumAttacksLearnedException(){
	super("Maximum number of attacks to be assigned!");	
	}
}
