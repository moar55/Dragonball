package dragonball.model.cell;

import dragonball.model.character.fighter.NonPlayableFighter;

public class FoeCell  extends Cell{
 
	private char foeType;
	private NonPlayableFighter foe;
	
	public FoeCell(NonPlayableFighter foe,char foeType){
		this.foeType=foeType;
		this.foe=foe;
	}
	
		@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(foeType=='w')
			return "[w]";
		
		else
			return "[b]";
	}

		public NonPlayableFighter getFoe() {
			return foe;
		}

		public char getFoeType() {
			return foeType;
		}

		
	
}
