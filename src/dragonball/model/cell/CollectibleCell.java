package dragonball.model.cell;

public class CollectibleCell extends Cell {
	private Collectible collectible;
	
	public CollectibleCell(Collectible collectible) {
		this.collectible = collectible;
	}

	public Collectible getCollectible() {
		return collectible;
	}

	@Override
	public String toString() {
		switch (collectible) {
		case SENZU_BEAN:
			return "[s]";
		case DRAGON_BALL:
			return "[d]";
		}

		return "[" + Character.toLowerCase(collectible.name().charAt(0)) + "]";
	}

	@Override
	public void onStep() {
		if(super.getWorld()!=null)
			super.getWorld().onCollectibleFound(collectible);
		
	}



	
}
