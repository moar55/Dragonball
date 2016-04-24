package dragonball.controller;


import java.awt.Dimension;
import java.awt.Graphics2D;
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
		int width  = (int)(Math.round(sizeofScreen.getWidth()));
		int height = (int)(Math.round(sizeofScreen.getHeight()))-(int)(Math.round(sizeofScreen.getHeight()/14.4));
		 introScreen = new JFrame("Intro");
		 BufferedImage pic = ImageIO.read(new File("IntroScreen.png"));
//		 BufferedReader dbi = new BufferedImage(dWidth, dHeight, imageType);
//	        Graphics2D g = dbi.createGraphics();
//	        AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
//	        g.drawRenderedImage(sbi, at);
		pic.getScaledInstance(sizeofScreen.width, sizeofScreen.height-(int)Math.round(sizeofScreen.getHeight()/14.4), BufferedImage.TYPE_INT_ARGB);
	//	introScreen.setContentPane(new JLabel(new ImageIcon(pic)));
		
		JFXPanel jp = new JFXPanel();
		player = new MediaPlayer(new Media(Paths.get("IntroMusic.mp3").toUri().toString()));
		player.play();	
		
		introScreen.addKeyListener(this);
		introScreen.setVisible(true);
		introScreen.setSize(width, height);
		introScreen.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		BufferedImage resizedImage = new BufferedImage(width, height, 	BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(pic, 0, 0, width, height, null);
		g.dispose();		
		introScreen.setContentPane(new JLabel(new ImageIcon(resizedImage)));
		introScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		for(PlayableFighter fighter :gameEngine.getPlayer().getFighters())
			worldGUI.getFightersList().addFighter(getFighterPanel(fighter));
			
			
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
		//	System.out.println(e.getNameOfEvent());
			
			switch (e.getNameOfEvent()){
			case "Select" : 
							System.out.println(e.getIndex());
							System.out.println(worldGUI.getFightersList().getSelect().size());
							gameEngine.getPlayer().setActiveFighter(gameEngine.getPlayer().getFighters().get(e.getIndex()));
							break;
			
			case "Upgrade" :  
							//worldGUI.addUpgradingPlayer(); worldGUI.getUpgradingPlayer.setFighter(gameEngine.getPlayer().getFighters().get(e.getIndex()));
							break;
							
							
			case "Create a Fighter" : worldGUI.addCreatingFighter();break;	
			
			case "Back to game"  : System.out.println("lol");worldGUI.getCombo().remove(worldGUI.getFightersList());break;
			
			
			case "<html><center>"+"Upgrade"+"<br>"+"Max"+"<br>Health</center></html>": 
				
				
				PlayableFighter fighter = gameEngine.getPlayer().getFighters().get(e.getIndex());
				if(fighter.getAbilityPoints()>=1){
				fighter.setMaxHealthPoints(fighter.getMaxHealthPoints()+50);
				refreshFightersList();}
				
				else 
					JOptionPane.showMessageDialog(worldGUI, "You don't have enough ability points ! ");
				break;
			
			case "<html><center>"+"Upgrade"+"<br>"+"Physical"+"<br>Damage</center></html>": 
				 fighter = gameEngine.getPlayer().getFighters().get(e.getIndex());
				if(fighter.getAbilityPoints()>=1){
				fighter.setPhysicalDamage(fighter.getPhysicalDamage()+50);
				refreshFightersList();
				}
				else
					JOptionPane.showMessageDialog(worldGUI, "You don't have enough ability points ! ");
				break;
			
				
			case "<html><center>"+"Upgrade"+"<br>"+"Blast"+"<br>Damage</center></html>":
				fighter = gameEngine.getPlayer().getFighters().get(e.getIndex());
				
				if(fighter.getAbilityPoints()>=1){
				fighter.setBlastDamage(fighter.getBlastDamage()+50);
				refreshFightersList();
				}
				
				else
					JOptionPane.showMessageDialog(worldGUI, "You don't have enough ability points ! ");

				break;
				
				
			case "<html><center>"+"Upgrade"+"<br>"+"Max"+"<br>KI</center></html>":
				fighter = gameEngine.getPlayer().getFighters().get(e.getIndex());
			
			if(fighter.getAbilityPoints()>=1){

				fighter.setMaxKi(fighter.getMaxKi()+1);
				refreshFightersList();
			}
			else
				JOptionPane.showMessageDialog(worldGUI, "You don't have enough ability points ! ");

					break;
			
			case "<html><center>"+"Upgrade"+"<br>"+"Max"+"<br>Stamina</center></html>":
				fighter = gameEngine.getPlayer().getFighters().get(e.getIndex());
				
				if(fighter.getAbilityPoints()>=1){
				fighter.setStamina(fighter.getMaxStamina()+1);
				refreshFightersList();
				}
				else
					JOptionPane.showMessageDialog(worldGUI, "You don't have enough ability points ! ");
				break;
			}
			worldGUI.repaint();
			worldGUI.validate();
			
		}
		
		
		
		public void refreshFightersList(){
			worldGUI.getFightersList().removeAll();
			worldGUI.getFightersList().updateUI();
			ArrayList<JButton> x =  worldGUI.getFightersList().getSelect();
			x = new ArrayList<JButton>();
			ArrayList<JLabel> y = worldGUI.getFightersList().getUpgrade();
			y = new ArrayList<JLabel>() ;
			worldGUI.getFightersList().resetSelectAndUpgrade();
			worldGUI.getFightersList().add(new JLabel("..."));
			for(PlayableFighter fighter :gameEngine.getPlayer().getFighters())
				worldGUI.getFightersList().addFighter(getFighterPanel(fighter));
			
			worldGUI.repaint();
			worldGUI.validate();
			
		}
		public void onCreatingFighter(GGEvent e ){
			switch(e.getNameOfEvent()){
			case "Save" : if(worldGUI.getCreatingFighter().getFighter()==null)
							JOptionPane.showMessageDialog(worldGUI,"Please select a Fighter!");
			
			else{
				save();
				worldGUI.getCombo().remove(worldGUI.getCreatingFighter());  	
				worldGUI.getFightersList().addFighter(getFighterPanel(worldGUI.getCreatingFighter().getFighter()));
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
			default :System.out.println("What?!");
			}
			System.out.println("Now it is " + gameEngine.getPlayer().getFighters().size());
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
	
	
	public JPanel getFighterPanel(PlayableFighter fighter){
		
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
					stats.setLayout(new GridLayout(6,0));
					stats.add(new JLabel("Max Health Points: " + fighter.getMaxHealthPoints()));
					stats.add(new JLabel("Blast Damage: " + fighter.getBlastDamage()));
					stats.add(new JLabel("Physical Damge: "+ fighter.getPhysicalDamage()));
					stats.add(new JLabel("Max Ki: "+ fighter.getMaxKi()));
					stats.add(new JLabel("Max Stamina: " + fighter.getMaxStamina()));
					stats.add(new JLabel("Ability Points: " + fighter.getAbilityPoints()));
					temp.add(stats);
					JLabel temp3 = new JLabel();
					temp3.setLayout(new GridLayout(2, 0));
					JButton select = new JButton("Select");
					select.addActionListener(worldGUI.getFightersList());
					JLabel upgrade = new JLabel();
					upgrade.setLayout(new GridLayout(1,5));
					JButton maxHealth = new JButton();
					maxHealth.setText("<html><center>"+"Upgrade"+"<br>"+"Max"+"<br>Health</center></html>");
					JButton physicalDamage = new JButton();
					physicalDamage.setText("<html><center>"+"Upgrade"+"<br>"+"Physical"+"<br>Damage</center></html>");
					JButton blastDamage = new JButton();
					blastDamage.setText("<html><center>"+"Upgrade"+"<br>"+"Blast"+"<br>Damage</center></html>");
					JButton maxKi = new JButton();
					maxKi.setText("<html><center>"+"Upgrade"+"<br>"+"Max"+"<br>KI</center></html>");
					JButton maxStamina = new JButton();
					maxStamina.setText("<html><center>"+"Upgrade"+"<br>"+"Max"+"<br>Stamina</center></html>");
					System.out.println(maxHealth.getText());
					maxHealth.addActionListener(worldGUI.getFightersList());
					physicalDamage.addActionListener(worldGUI.getFightersList());
					blastDamage.addActionListener(worldGUI.getFightersList());
					maxKi.addActionListener(worldGUI.getFightersList());
					maxStamina.addActionListener(worldGUI.getFightersList());
					upgrade.add(maxHealth);upgrade.add(physicalDamage);upgrade.add(blastDamage);upgrade.add(maxKi);
					upgrade.add(maxStamina);
					temp3.add(select);
					worldGUI.getFightersList().getSelect().add(select);
					temp3.add(upgrade);
					worldGUI.getFightersList().getUpgrade().add(upgrade);
					System.out.println("Upgrade size" + worldGUI.getFightersList().getUpgrade().size());
					temp.add(temp3);
					//temp.setBorder();					
					return temp;
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
			worldGUI.getCombo().repaint();
			worldGUI.getCombo().validate();
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
			
