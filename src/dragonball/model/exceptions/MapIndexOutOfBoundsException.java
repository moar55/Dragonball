package dragonball.model.exceptions;

public class MapIndexOutOfBoundsException extends IndexOutOfBoundsException{
	
	private int row;
	private int column;
	
	
	public MapIndexOutOfBoundsException(int row, int column){
		super("You can't exceed the boundaries of the map! Co-ordinates: "+row+ " "+column);
		this.row=row;
		this.column=column;
	}


	public int getRow() {
		return row;
	}


	public int getColumn() {
		return column;
	}
	
	
	
}
