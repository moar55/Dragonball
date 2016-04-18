package dragonball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import dragonball.model.game.Game;
import dragonball.model.player.Player;
import dragonball.view.GGEvent;
import dragonball.view.WorldFrame;
import dragonball.view.GGEvent;

public class GameGUI implements ActionListener {

	private Game gameEngine;
	private WorldFrame worldGUI;
		
	public void menuEvent(String c){
		switch(c){
		case "New Game": gameEngine = new Game();
							System.out.println("Clicked");
					//	gameEngine.setPlayer(new Player(worldGUI.createPlayer()));break;
						
		case "Exit" : System.exit(0);
		
		case "OK" : 
		}
	}
	
	public GameGUI() throws IOException {
		
		gameEngine= new Game();
		worldGUI = new WorldFrame();
		worldGUI.setController(this);
	}
	public static void main(String[] args) throws IOException {
		new GameGUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton){
			
//		switch( ((GGEvent) e).getNameOfEvent()){
	//	case "New Game": gameEngine = new Game();
//							System.out.println("Clicked");
//					//	gameEngine.setPlayer(new Player(worldGUI.createPlayer()));break;
//						
//		case "Exit" : System.exit(0);
//		
//		case "OK" : 
		}
		
	}
			

}
