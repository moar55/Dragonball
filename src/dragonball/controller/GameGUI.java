package dragonball.controller;


import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.game.Game;
import dragonball.view.CreatingPlayer;
import dragonball.view.MenuScreen;
import dragonball.view.WorldFrame;

public class GameGUI {

	private Game gameEngine;
	private WorldFrame worldGUI;
		

	
	public GameGUI() throws IOException {
		
		gameEngine= new Game();
		worldGUI = new WorldFrame();
		worldGUI.setController(this);
	}
	public static void main(String[] args) throws IOException {
		new GameGUI();
	}

	public void onEvent(GGEvent e) {
		if(e.getSource() instanceof MenuScreen){
	
		switch( e.getNameOfEvent()){
	
		case "New Game": gameEngine = new Game();
							worldGUI.addCreatingPlayer();
							break;
		case "Exit" : System.exit(0);
		}
		}
		
		if(e.getSource() instanceof CreatingPlayer){
			System.out.println(worldGUI.getCreatingPlayer().getPlayerName());
			switch(e.getNameOfEvent()){
			
			case "OK" : if(!worldGUI.getCreatingPlayer().getJtextPlayerName().equals("Please enter your player's name: \n")){
							
				//Supposedly the user has entered a name and the game should start!
							worldGUI.getCombo().remove(worldGUI.getCreatingPlayer());
							worldGUI.getCombo().remove(worldGUI.getMenu());
							worldGUI.repaint();
							worldGUI.validate(); 
							worldGUI.addCreatingFighter(gameEngine.getPlayer().getActiveFighter());
							JOptionPane.showMessageDialog(worldGUI, "You need to create a fighter to start playing " );

							}
							else 
							JOptionPane.showMessageDialog(worldGUI, "You can't set a Player with out a name! " );
			break;
			
			case "Cancel" : worldGUI.getCombo().remove(worldGUI.getCreatingPlayer());
			worldGUI.repaint();
			worldGUI.validate();
			break;
			}
		}
		
	}
	
	public ArrayList<JPanel> getFighterPanels(){
		ArrayList<PlayableFighter> fighters = gameEngine.getPlayer().getFighters();
		ArrayList<JPanel> fightersPanels = new ArrayList<JPanel>();
		if(fightersPanels.size()==0)return fightersPanels;
		
		else
		{
			
			//To Be continued Insha'Allah
		for (PlayableFighter fighter : fighters) {
			JPanel temp = new JPanel();
			
		}
		}
	}
}
				
