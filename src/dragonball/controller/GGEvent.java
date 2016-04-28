package dragonball.controller;

import java.util.EventObject;

import com.sun.xml.internal.ws.api.server.Container;

import dragonball.model.attack.Attack;

public class GGEvent extends  EventObject{
	
	public String nameOfEvent;
	private int index; //This variable is for items repeated in a JPanel like JButtons with similar names for example
	private Attack attack;
	private Container container;
	
	public GGEvent(Object source,String nameOfEvent){
		super(source);
		this.nameOfEvent=nameOfEvent;
	}
	
	public GGEvent(Object source,String nameOfEvent,int index){
		this(source,nameOfEvent);
		this.index= index;
	}
	
	public GGEvent(Object source, Container continer,int index){
		super(source);
		this.container=continer;
		this.index= index;
	}
	

	public String getNameOfEvent() {
		return nameOfEvent;
	}
	public int getIndex() {
		return index;
	}

	public Container getContainer() {
		return container;
	}


	
	
}
