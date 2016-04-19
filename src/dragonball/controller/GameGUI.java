package dragonball.controller;


import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
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
							//worldGUI.addCreatingFighter(gameEngine.getPlayer().getActiveFighter());
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
				for (PlayableFighter fighter : fighters) {
					JPanel temp = new JPanel();
					temp.setLayout(new GridLayout(0, 4));
					JLabel temp2 = new JLabel();
					temp2.setLayout(new GridLayout(2, 0));
					JLabel name = new JLabel("Name: " + fighter.getName());
					JLabel race = new JLabel("Race: " + getRace(fighter));
					temp2.add(name);
					temp2.add(race);
					temp.add(temp2);
					temp.add(getPic(getRace(fighter)));
		}
		}
	}
	
	public String getRace (PlayableFighter fighter) {
		
		if(fighter instanceof Earthling ) return "Earthling";
		 if(fighter instanceof Frieza)  return "Frieza" ; 
		if( fighter instanceof Majin)  return "Majinn";
		if(fighter instanceof Namekian) return "Namekian" ;
		if(fighter instanceof Saiyan) return "Saiyan" ; 
		 return null;
		}
	
	public JLabel getPic (String st){
		switch(st){
		case "Earthling": return new JLabel(new ImageIcon("Earthling.jpg"));
		case "Saiyan" : return new JLabel(new ImageIcon("Goku.png"));
		
		}
	}
	
	}
	
				
