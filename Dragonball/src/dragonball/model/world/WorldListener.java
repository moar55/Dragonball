package dragonball.model.world;

import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.NonPlayableFighter;

public interface WorldListener {
	public  void onFoeEncountered(NonPlayableFighter foe);
	
	public void onCollectibleFound(Collectible collectible);
}
