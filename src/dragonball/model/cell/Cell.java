package dragonball.model.cell;

public abstract class Cell implements java.io.Serializable {
	private CellListener world;
	
	@Override
	public abstract String toString();

	public CellListener getWorld() {
		return world;
	}
	
	abstract public void onStep();

	public void setWorld(CellListener world) {
		this.world = world;
	}
	
	
	
	
}
