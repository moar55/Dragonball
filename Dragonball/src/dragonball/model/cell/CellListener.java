package dragonball.model.cell;

import dragonball.model.character.fighter.NonPlayableFighter;

public interface CellListener {
	
	public void onFoeEncountered(NonPlayableFighter foe);
	
	public void onCollectibleFound(Collectible collectible);
	
	
}
