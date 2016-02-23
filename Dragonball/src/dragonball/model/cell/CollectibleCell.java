package dragonball.model.cell;

public class CollectibleCell extends Cell {
	
	private Collectible collectible;
	
	public CollectibleCell(Collectible collectible){
		this.collectible=collectible;
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(collectible==Collectible.SENZU_BEANS)
			return "[s]";
		
		else 
			return "[d]";
	}

	public Collectible getCollectibleType() {
		return collectible;
	}
	
}
