package dragonball.controller;

import java.util.EventObject;

public class GGEvent extends  EventObject{
	
	public String nameOfEvent;
	
	public GGEvent(Object source,String nameOfEvent){
		super(source);
		this.nameOfEvent=nameOfEvent;
	}

	public String getNameOfEvent() {
		return nameOfEvent;
	}
	
	
	
}
