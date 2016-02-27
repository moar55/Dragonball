package dragonball.model.cell;

import dragonball.model.character.fighter.NonPlayableFighter;

public class FoeCell  extends Cell{
 
	private NonPlayableFighter foe;
	
	public FoeCell(NonPlayableFighter foe){
		this.foe=foe;
	}
	
		@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(foe.isStrong())
			return "[b]";
		
		else
			return "[w]";
	}

		public NonPlayableFighter getFoe() {
			return foe;
		}



		
	
}
