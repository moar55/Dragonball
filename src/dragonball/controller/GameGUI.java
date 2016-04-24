package dragonball.controller;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
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
import dragonball.view.ChoooseRace;
import dragonball.view.CreatingFighter;
import dragonball.view.CreatingPlayer;
import dragonball.view.FightersList;
import dragonball.view.MenuScreen;
import dragonball.view.WorldFrame;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GameGUI implements KeyListener {

	private Game gameEngine;
	private WorldFrame worldGUI;
	private JFrame introScreen;
	private MediaPlayer player ;
	private MediaPlayer transition ;
	
	public GameGUI() throws IOException {
		
		gameEngine= new Game();
		Dimension sizeofScreen = Toolkit.getDefaultToolkit().getScreenSize();
		 introScreen = new JFrame("Intro");
		 BufferedImage pic = ImageIO.read(new File("IntroScreen.png"));
//		 BufferedReader dbi = new BufferedImage(dWidth, dHeight, imageType);
//	        Graphics2D g = dbi.createGraphics();
//	        AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
//	        g.drawRenderedImage(sbi, at);
		pic.getScaledInstance(sizeofScreen.width, sizeofScreen.height-(int)Math.round(sizeofScreen.getHeight()/14.4), BufferedImage.TYPE_INT_ARGB);
		introScreen.setContentPane(new JLabel(new ImageIcon(pic)));
		JFXPanel jp = new JFXPanel();
		player = new MediaPlayer(new Media(Paths.get("IntroMusic.mp3").toUri().toString()));
		player.play();
		
		introScreen.addKeyListener(this);
		introScreen.setVisible(true);
		introScreen.setSize(1280, 720);
		introScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		worldGUI = new WorldFrame();
		worldGUI.setVisible(false);
		worldGUI.setController(this);
		worldGUI.getChoooseRace().populate(getRaces());
	}
	public static void main(String[] args) throws IOException {
		new GameGUI();
		getPic("Earthling");
	//	System.out.println(getCorresponding(2));
	}

	
	
	public void onEvent(GGEvent e) {
		if(e.getSource() instanceof MenuScreen)
			onMenuScreen(e);
			
			else if(e.getSource() instanceof CreatingPlayer)
				onCreatingPlayer(e);
		
			else  if ( e.getSource() instanceof FightersList)
				onFightersList(e);
		
			else if (e.getSource() instanceof CreatingFighter)
			   onCreatingFighter(e);
		
			else if (e.getSource() instanceof ChoooseRace)
				onChooseRace(e);
		}
		
		public void onMenuScreen(GGEvent e){
			switch( e.getNameOfEvent()){
			
			case "New Game": gameEngine = new Game();
								worldGUI.addCreatingPlayer();
								break;
								
			case "Save" : save();break;
			
			case "Load": load(); break;
			case "Exit" : System.exit(0);break;
			}
		}
		
		public void onCreatingPlayer(GGEvent e){
			//System.out.println(worldGUI.getCreatingPlayer().getPlayerName());
			switch(e.getNameOfEvent()){
			
			case "OK" : if(!worldGUI.getCreatingPlayer().getJtextPlayerName().equals("Please enter your player's name: \n")){ 
							
				//Supposedly the user has entered a name and the game should start!
							worldGUI.getCombo().remove(worldGUI.getCreatingPlayer());
							worldGUI.getCombo().remove(worldGUI.getMenu());
							worldGUI.repaint();
							worldGUI.validate(); 
							worldGUI.addFightersList();
							worldGUI.getFightersList().populate(getFighterPanels());
							JOptionPane.showMessageDialog(worldGUI, "You need to create a fighter to start playing ");
							 		

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
		
		public void onFightersList(GGEvent e) {
			System.out.println(e.getNameOfEvent());
			
			switch (e.getNameOfEvent()){
			case "Select" : 
							gameEngine.getPlayer().setActiveFighter(gameEngine.getPlayer().getFighters().get(e.getIndex()));
							break;
			
			case "Upgrade" :  
							//worldGUI.addUpgradingPlayer(); worldGUI.getUpgradingPlayer.setFighter(gameEngine.getPlayer().getFighters().get(e.getIndex()));
							break;
							
							
			case "Create a Fighter" : worldGUI.addCreatingFighter();break;	
			
			case "Back to game"  : System.out.println("lol");worldGUI.getCombo().remove(worldGUI.getFightersList());
			worldGUI.repaint();
			worldGUI.validate();
			break;
			}
		}
		
		public void onCreatingFighter(GGEvent e ){
			switch(e.getNameOfEvent()){
			case "Save" : if(worldGUI.getCreatingFighter().getFighter()==null)
							JOptionPane.showMessageDialog(worldGUI,"Please select a Fighter!");
			
			else{
				save();
				worldGUI.getCombo().remove(worldGUI.getCreatingFighter());  	
				ArrayList<JPanel> fighters = getFighterPanels();
				worldGUI.getFightersList().addFighter(fighters.get(fighters.size()-1));
				worldGUI.repaint();
				worldGUI.validate();
			}
			
			break;
			case "Fighter's Race:" : worldGUI.addChooseRace();JOptionPane.showMessageDialog(worldGUI,"Alright Player; choose a class!");break;
			case "Cancel" : worldGUI.getCombo().remove(worldGUI.getCreatingFighter());
					worldGUI.repaint();
					worldGUI.validate();
					break;
		}
		}
		
		public void onChooseRace(GGEvent e ){
			switch(e.getIndex()){
			case 0 : gameEngine.getPlayer().getFighters().add(new Earthling(worldGUI.getCreatingFighter().getNameofPlayer().getText()));break;
			case 1 :  gameEngine.getPlayer().getFighters().add(new Frieza(worldGUI.getCreatingFighter().getNameofPlayer().getText()));break;
			case 2 :  gameEngine.getPlayer().getFighters().add(new Saiyan(worldGUI.getCreatingFighter().getNameofPlayer().getText()));break;
			case 3 :  gameEngine.getPlayer().getFighters().add(new Majin(worldGUI.getCreatingFighter().getNameofPlayer().getText()));break;
			case 4 :  gameEngine.getPlayer().getFighters().add(new Namekian(worldGUI.getCreatingFighter().getNameofPlayer().getText()));break;
			default : System.out.println(e.getIndex());
			}
			System.out.println(gameEngine.getPlayer().getFighters().size());
			worldGUI.getCreatingFighter().setFighter(gameEngine.getPlayer().getFighters().get(gameEngine.getPlayer().getFighters().size()-1));
			worldGUI.getCombo().remove(worldGUI.getChoooseRace());
			worldGUI.validate();
			worldGUI.repaint();
	}
	

	
	public ArrayList<JButton> getRaces(){
		ArrayList<JButton> races = new ArrayList<JButton>();
		for(int i = 0 ;i <5 ;i ++){
		JButton temp = new JButton();
		temp.setLayout(new GridLayout(0, 1));
		JLabel race = new JLabel();
		race.setLayout(new GridLayout(1, 2));
		race.add(new JLabel("Race: "+getCorresponding(i)));
		race.add(getPic(getCorresponding(i)));
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
		stats.setLayout(new GridLayout(5,0));
		stats.add(new JLabel("Max Health Points: " + fighter.getMaxHealthPoints()));
		stats.add(new JLabel("Blast Damage: " + fighter.getBlastDamage()));
		stats.add(new JLabel("Physical Damge: "+ fighter.getPhysicalDamage()));
		stats.add(new JLabel("Max Ki: "+ fighter.getMaxKi()));
		stats.add(new JLabel("Max Stamina: " + fighter.getMaxStamina()));
		temp.add(stats);
		
		races.add(temp);
		}
		return races;
	}
	
	public  String getCorresponding (int i){
		switch(i){
		case  0 : return"Earthling";
		case 1 :return "Frieza" ;
		case 2 :return "Saiyan" ; 
		case 3 :return "Majin";
		case 4 : return "Namekian";
		}
		return null;
	}
	
	public static JLabel getPic (String st){
		switch(st){
		case "Earthling": return new JLabel(new ImageIcon("Earthling.jpg"));
		case "Saiyan" : return new JLabel(new ImageIcon("Goku.png"));
		case "Frieza" : return new JLabel(new ImageIcon("Frieza.png"));
		case "Majin" : return new JLabel(new ImageIcon("Majin.png"));
		case "Namekian" : return new JLabel(new ImageIcon("Namekian.jpg"));
		default : System.out.println("oops");return null;
		}
	}
	
	
	public ArrayList<JPanel> getFighterPanels(){
		ArrayList<PlayableFighter> fighters = gameEngine.getPlayer().getFighters();
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
					select.addActionListener(worldGUI.getFightersList());
					JButton upgrade = new JButton("Upgrade");
					upgrade.addActionListener(worldGUI.getFightersList());
					temp3.add(select);
					worldGUI.getFightersList().getSelect().add(select);
					temp3.add(upgrade);
					worldGUI.getFightersList().getUpgrade().add(upgrade);
					System.out.println(worldGUI.getFightersList().getUpgrade().size());
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
		if( fighter instanceof Majin)  return "Majin";
		if(fighter instanceof Namekian) return "Namekian" ;
		if(fighter instanceof Saiyan) return "Saiyan" ; 
		 return null;
		}
	

	
	public void save(){
		if (gameEngine.getSavePath().equals(""))
			
			gameEngine.save("SaveData.ser");
		
		else 
			gameEngine.save(gameEngine.getSavePath());
			
			JOptionPane.showMessageDialog(worldGUI,"Game Saved!(if no bugs occured lol)");
	}
	
	public void load(){
		File f  = new File("SaveData.ser");
		if(f.exists()){
			JOptionPane.showMessageDialog(worldGUI, "Done!");
			worldGUI.getCombo().remove(worldGUI.getMenu());
		//	worldGUI.getMap().populate();
			worldGUI.validate();
			worldGUI.validate();
		}
		else
			JOptionPane.showMessageDialog(worldGUI, "No previous save data");
	
	}
	
	
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {

		transition= new MediaPlayer(new Media(Paths.get("selected.mp3").toUri().toString()));
		transition.play();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		transition.pause();
		introScreen.setVisible(false);
		transition=null;
		worldGUI.setVisible(true);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	}
	
				
