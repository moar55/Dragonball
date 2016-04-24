package dragonball.controller;

import java.util.EventObject;

public class GGEvent extends  EventObject{
	
	public String nameOfEvent;
	private int index; //This variable is for items repeated in a JPanel like JButtons with similar names for example
	
	public GGEvent(Object source,String nameOfEvent){
		super(source);
		this.nameOfEvent=nameOfEvent;
	}
	public GGEvent(Object source,String nameOfEvent,int index){
		this(source,nameOfEvent);
		this.index= index;
	}
	

	public String getNameOfEvent() {
		return nameOfEvent;
	}
	public int getIndex() {
		return index;
	}
	
	
	
	
	
}
