package dragonball.model.cell;

public class CollectibleCell extends Cell  {
	
	private Collectible collectible;
	private CellListener world;
	public CollectibleCell(Collectible collectible){
		this.collectible=collectible;
		
	}
	@Override
	public String toString() {
		
		if(collectible==Collectible.SENZU_BEAN)
			return "[s]";
		
		else 
			return "[d]";
	}
	
	

	public Collectible getCollectible() {
		return collectible;
	}
	
	
	
	public void setWorld(CellListener world) {
		this.world = world;
	}
	
	@Override
	public void onStep() {
	
		if(world!=null)
			world.onCollectibleFound(collectible);
		
	}
	
	
}
