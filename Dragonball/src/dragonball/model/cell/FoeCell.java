package dragonball.model.cell;

import dragonball.model.character.fighter.NonPlayableFighter;

public class FoeCell  extends Cell{
 
	private NonPlayableFighter foe;
	private CellListener world; 
	public FoeCell(NonPlayableFighter foe){
		this.foe=foe;
	}
	
		@Override
	public String toString() {
		
		if(foe.isStrong())
			return "[b]";
		
		else
			return "[w]";
	}

		public NonPlayableFighter getFoe() {
			return foe;
		}
		
	

		public void setWorld(CellListener world) {
			this.world = world;
		}

		@Override
		public void onStep() {
			if(world!=null)
				world.onFoeEncountered(foe);
			
			
		}


		
		
	
}
