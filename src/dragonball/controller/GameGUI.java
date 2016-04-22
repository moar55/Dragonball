package dragonball.controller;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.game.Game;
import dragonball.view.CreatingFighter;
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
							
		case "Save" : save();break;
		
		case "Load": load(); break;
		case "Exit" : System.exit(0);break;
		}
		}
		
		else if(e.getSource() instanceof CreatingPlayer){
			//System.out.println(worldGUI.getCreatingPlayer().getPlayerName());
			switch(e.getNameOfEvent()){
			
			case "OK" : if(!worldGUI.getCreatingPlayer().getJtextPlayerName().equals("Please enter your player's name: \n")){ 
							
				//Supposedly the user has entered a name and the game should start!
							worldGUI.getCombo().remove(worldGUI.getCreatingPlayer());
							worldGUI.getCombo().remove(worldGUI.getMenu());
							worldGUI.repaint();
							worldGUI.validate(); 
							System.out.println("Hi");
							worldGUI.addFightersList();
							worldGUI.getFightersList().populate(getFighterPanels());
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
		
		else if (e.getSource() instanceof CreatingFighter){
			switch(e.getNameOfEvent()){
			case "Save" : if(worldGUI.getCreatingFighter().getFighter()==null)
							JOptionPane.showMessageDialog(worldGUI,"Please select a Fighter!");
			
			else{
				save();
			}
			
			break;
			case "Fighter's Race:" : worldGUI.addChooseRace();worldGUI.getChoooseRace().populate(getRaces());
			case "Cancel" : worldGUI.getCombo().remove(worldGUI.getCreatingFighter());break;
		}
		
	  }
	}
	
	public ArrayList<JButton> getRaces(){
		ArrayList<JButton> races = new ArrayList<JButton>();
		for(int i = 0 ;i <5 ;i ++){
		JButton temp = new JButton();
		temp.setLayout(new BorderLayout());
		JLabel race = new JLabel();
		race.setLayout(new GridLayout(1, 2));
		if(i==0){
		race.add(new JLabel(getCorresponding(i)));
		race.add(getPic(getCorresponding(i)));
		}
		temp.add(race);
		PlayableFighter fighter=null;
		switch(getCorresponding(i)){
		case "Earthling": fighter= new Earthling("random");break;
		case "Frieza" : fighter = new Frieza("random");break;
		case "Saiyan" : fighter = new Saiyan("random");break;
		case "Majin" : fighter = new Majin("random");break;
		case "Namekian" :fighter = new Namekian("random");break;
		}

		JLabel stats = new JLabel();
		stats.setLayout(new GridLayout(7,0));
		stats.add(new JLabel("Max Health Points: " + fighter.getMaxHealthPoints()));
		stats.add(new JLabel("Blast Damage: " + fighter.getBlastDamage()));
		stats.add(new JLabel("Physical Damge: "+ fighter.getPhysicalDamage()));
		stats.add(new JLabel("Max Ki: "+ fighter.getMaxKi()));
		stats.add(new JLabel("Max Stamina: " + fighter.getMaxStamina()));
		stats.add(new JButton("Available SuperAttacks"));
		stats.add(new JButton("Available UltimateAttacks"));
		temp.add(stats);
		
		races.add(temp);
		}
		return races;
	}
	
	public String getCorresponding (int i){
		switch(i){
		case  0 : return"Earthling";
		case 1 :return "Frieza" ;
		case 2 :return "Saiyan" ; 
		case 3 :return "Majin";
		case 4 : return "Namekian";
		}
		return null;
	}
	public ArrayList<JPanel> getFighterPanels(){
		ArrayList<PlayableFighter> fighters = gameEngine.getPlayer().getFighters();
		fighters.add(new Earthling("James")); 
		fighters.add(new Saiyan("Medhat"));
		ArrayList<JPanel> fightersPanels = new ArrayList<JPanel>(); 
		if(fighters.size()!=0){
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
					JLabel pic =  getPic(getRace(fighter));
					temp.add(pic);
					JLabel stats = new JLabel();
					stats.setLayout(new GridLayout(7,0));
					stats.add(new JLabel("Max Health Points: " + fighter.getMaxHealthPoints()));
					stats.add(new JLabel("Blast Damage: " + fighter.getBlastDamage()));
					stats.add(new JLabel("Physical Damge: "+ fighter.getPhysicalDamage()));
					stats.add(new JLabel("Max Ki: "+ fighter.getMaxKi()));
					stats.add(new JLabel("Max Stamina: " + fighter.getMaxStamina()));
					stats.add(new JButton("Available SuperAttacks"));
					stats.add(new JButton("Available UltimateAttacks"));
					temp.add(stats);
					JLabel temp3 = new JLabel();
					temp3.setLayout(new GridLayout(2, 0));
					JButton select = new JButton("Select");
					JButton upgrade = new JButton("Upgrade");
					temp3.add(select);
					worldGUI.getFightersList().getSelect().add(select);
					temp3.add(upgrade);
					worldGUI.getFightersList().getUpgrade().add(upgrade);

					temp.add(temp3);
					//temp.setBorder();
					fightersPanels.add(temp);
					
				}
		}
		return fightersPanels;
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
		case "Frieza" : return new JLabel(new ImageIcon("Frieza.png"));
		case "Majin" : return new JLabel(new ImageIcon("Majin.png"));
		case "Namekin" : return new JLabel(new ImageIcon("null"));
		default : return null;
		}
	}
	
	public void save(){
		if (gameEngine.getSavePath().equals(""))
			
			gameEngine.save("SaveData.ser");
		
		else 
			gameEngine.save(gameEngine.getSavePath());
			
			JOptionPane.showMessageDialog(worldGUI,"Game Saved!(if no bugs occured lol)");
	}
	
	public void load(){
		if(gameEngine.getSavePath().equals(""))
			JOptionPane.showMessageDialog(worldGUI, "No previous save data");
		
		else
			JOptionPane.showMessageDialog(worldGUI, "Done!(I think :D )");
	}
	
	}
	
				
