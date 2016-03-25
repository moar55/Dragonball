package dragonball.model.cell;

abstract public class Cell {
	
	private  CellListener world;
	abstract public String toString();
	
	abstract public void onStep();

	public void setWorld(CellListener world) {
		this.world = world;
	}
	
	
	
	
	
}
