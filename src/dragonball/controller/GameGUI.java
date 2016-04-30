package dragonball.controller;


import java.awt.Dimension;
import java.awt.Font;
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

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import dragonball.model.attack.Attack;
import dragonball.model.attack.MaximumCharge;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.BattleEvent;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.dragon.Dragon;
import dragonball.model.exceptions.MapIndexOutOfBoundsException;
import dragonball.model.game.Game;
import dragonball.model.game.GameListener;
import dragonball.model.player.Player;
import dragonball.view.ChoooseRace;
import dragonball.view.CreatingFighter;
import dragonball.view.CreatingPlayer;
import dragonball.view.DragonFrame;
import dragonball.view.FightersList;
import dragonball.view.Map;
import dragonball.view.MenuScreen;
import dragonball.view.SuperAndUltimateAttacks;
import dragonball.view.WorldFrame;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class GameGUI implements KeyListener ,GameListener {

	private Game gameEngine;
	private WorldFrame worldGUI;
	private JFrame introScreen;
	private MediaPlayer player ;
	private MediaPlayer transition ;
	private Map map;
	private DragonFrame dragonframe;
	
	public GameGUI() throws IOException {
		
		gameEngine = new Game();
		System.out.println("I am in the constructor");
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
		
		
		
			
			
		worldGUI = new WorldFrame();
		worldGUI.addKeyListener(this);
		worldGUI.setVisible(false);
		worldGUI.setController(this);
		worldGUI.getChoooseRace().populate(getRaces());
		
		 map = new Map(9,9,this);
		 map.setController(this);
		map.setVisible(false);
		System.out.println("map in constructor is "+ map);
	}
	public static void main(String[] args) throws IOException {
		new GameGUI();
	//	System.out.println(getCorresponding(2));
	}

	
	
	public void onEvent(GGEvent e)  {
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
		
			else if ( e.getSource() instanceof Map)
				onMap(e);
		
			
			}
		
		
		public void onMenuScreen(GGEvent e) {
			switch( e.getNameOfEvent()){
			
			case "New Game": gameEngine = new Game();
							  gameEngine.setListener(this);
								clean();
								worldGUI.addCreatingPlayer();
								worldGUI.repaint();
								worldGUI.validate();
								break;
								
			case "Save" : save();break;
			
			
			case "Load": try{load();}catch(IOException IO){System.out.println("oops");}
			break;
			case "Exit" : System.exit(0);break;
			}
		}
		
		public void onCreatingPlayer(GGEvent e){
			//System.out.println(worldGUI.getCreatingPlayer().getPlayerName());
			switch(e.getNameOfEvent()){
			
			case "OK" : if(!worldGUI.getCreatingPlayer().getJtextPlayerName().equals("Please enter your player's name: \n")){ 
							
				//Supposedly the user has entered a name and the game should start!
							
							clean();
							worldGUI.addFightersList();
							String name = worldGUI.getCreatingPlayer().getJtextPlayerName();
							gameEngine.setPlayer(new Player(name.substring(34)));
							JOptionPane.showMessageDialog(worldGUI, "You need to create a fighter to start playing ");
							gameEngine.getPlayer().getSuperAttacks().add(new SuperAttack("Killer", 50));
							gameEngine.getPlayer().getSuperAttacks().add(new SuperAttack("Destroyer", 100));
							gameEngine.getPlayer().getUltimateAttacks().add(new UltimateAttack("WOW", 200));
							gameEngine.getPlayer().getUltimateAttacks().add(new UltimateAttack("PEW", 150)); 		
							gameEngine.getPlayer().getUltimateAttacks().add(new SuperSaiyan());
							gameEngine.getPlayer().getSuperAttacks().add(new MaximumCharge());
							}
							else 
							JOptionPane.showMessageDialog(worldGUI, "You can't set a Player with out a name! " );
			break;
			
			case "Cancel" : worldGUI.getCombo().remove(worldGUI.getCreatingPlayer());
			worldGUI.getCombo().add(worldGUI.getMenu());
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
			
			case "<html><center>"+"Super/ Ultimate"+"<br> Attacks</center></html>" :  
				
				System.out.println("size is " + worldGUI.getFightersList().getAttacks().size());
				PlayableFighter fighter = gameEngine.getPlayer().getFighters().get(e.getIndex());
//				fighter.getSuperAttacks().add(gameEngine.getPlayer().getSuperAttacks().get(0));
//				fighter.getSuperAttacks().add(gameEngine.getPlayer().getSuperAttacks().get(1));
				worldGUI.addSuperandUltimateAttacks(fighter);
				
				
				for(SuperAttack attack : gameEngine.getPlayer().getSuperAttacks()){
					Boolean used = false;
					if(fighter.getSuperAttacks().contains(attack))
						used=true;
					worldGUI.getSuperAndUltimateAttacks().addSuperAttack(getSuperAttackButton(attack), used);
				}
				
				for(UltimateAttack attack : gameEngine.getPlayer().getUltimateAttacks()){
					Boolean used = false;
					if(fighter.getUltimateAttacks().contains(attack))
						used=true;
					worldGUI.getSuperAndUltimateAttacks().addUltimateAttack(getUltimateAttackButton(attack), used);
				}
				
				clean();
				System.out.println("HIee");
				worldGUI.getCombo().add(worldGUI.getFightersList(),0,0);
				worldGUI.getCombo().add(worldGUI.getSuperAndUltimateAttacks(), 1,0);
				break;
							
							
			case "Create a Fighter" : worldGUI.getCombo().add(worldGUI.getCreatingFighter(), 1,0);worldGUI.validate();worldGUI.repaint();break;	
			
			case "Back to game"  : if(gameEngine.getPlayer().getActiveFighter()==null)
									JOptionPane.showMessageDialog(worldGUI, "Please select a fighter");
			
			else{
				
				worldGUI.setFocusable(false);
				map.setFocusable(true);
				map.update();
				System.out.println("map in creating fighter is "+ map);
				worldGUI.setVisible(false);
				
				map.setVisible(true);
				mapMusic();
				map.repaint();
				map.validate();
			}
			break;
			
			
			case "<html><center>"+"Upgrade"+"<br>"+"Max"+"<br>Health</center></html>": 
				
				
				PlayableFighter fighter1 = gameEngine.getPlayer().getFighters().get(e.getIndex());
				if(fighter1.getAbilityPoints()>=1){
				fighter1.setMaxHealthPoints(fighter1.getMaxHealthPoints()+50);
				refreshFightersList();}
				
				else 
					JOptionPane.showMessageDialog(worldGUI, "You don't have enough ability points ! ");
				break;
			
			case "<html><center>"+"Upgrade"+"<br>"+"Physical"+"<br>Damage</center></html>": 
				 fighter1 = gameEngine.getPlayer().getFighters().get(e.getIndex());
				if(fighter1.getAbilityPoints()>=1){
				fighter1.setPhysicalDamage(fighter1.getPhysicalDamage()+50);
				refreshFightersList();
				}
				else
					JOptionPane.showMessageDialog(worldGUI, "You don't have enough ability points ! ");
				break;
			
				
			case "<html><center>"+"Upgrade"+"<br>"+"Blast"+"<br>Damage</center></html>":
				fighter1 = gameEngine.getPlayer().getFighters().get(e.getIndex());
				
				if(fighter1.getAbilityPoints()>=1){
				fighter1.setBlastDamage(fighter1.getBlastDamage()+50);
				refreshFightersList();
				}
				
				else
					JOptionPane.showMessageDialog(worldGUI, "You don't have enough ability points ! ");

				break;
				
				
			case "<html><center>"+"Upgrade"+"<br>"+"Max"+"<br>KI</center></html>":
				fighter1 = gameEngine.getPlayer().getFighters().get(e.getIndex());
			
			if(fighter1.getAbilityPoints()>=1){

				fighter1.setMaxKi(fighter1.getMaxKi()+1);
				refreshFightersList();
			}
			else
				JOptionPane.showMessageDialog(worldGUI, "You don't have enough ability points ! ");

					break;
			
			case "<html><center>"+"Upgrade"+"<br>"+"Max"+"<br>Stamina</center></html>":
				fighter1 = gameEngine.getPlayer().getFighters().get(e.getIndex());
				
				if(fighter1.getAbilityPoints()>=1){
				fighter1.setStamina(fighter1.getMaxStamina()+1);
				refreshFightersList();
				}
				else
					JOptionPane.showMessageDialog(worldGUI, "You don't have enough ability points ! ");
				break;
			}
			worldGUI.repaint();
			worldGUI.validate();
			
		}
		
		
		public void clean(){
			worldGUI.getCombo().removeAll();
			
		}
		public void onMap(GGEvent e){
			clean();
			refreshFightersList();
			worldGUI.addFightersList();
			map.setVisible(false);
			
		//	worldGUI.addFightersList();
			
			worldGUI.setVisible(true);
			
		}
		
		public void refreshFightersList(){
			worldGUI.getFightersList().removeAll();
			worldGUI.getFightersList().updateUI();
			 worldGUI.getFightersList().getSelect().clear();
			worldGUI.getFightersList().getUpgrade().clear();;
		
			worldGUI.getFightersList().getAttacks().clear();
			
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
				
				worldGUI.getFightersList().addFighter(getFighterPanel(worldGUI.getCreatingFighter().getFighter()));
				//worldGUI.get
				clean();
				worldGUI.addFightersList();
				worldGUI.repaint();
				worldGUI.validate();
			}
			
			break;
			case "Fighter's Race:" :worldGUI.getCombo().add(worldGUI.getChoooseRace(),2,0);JOptionPane.showMessageDialog(worldGUI,"Alright Player; choose a class!");break;
			case "Cancel" : 
				
					clean();
					worldGUI.addFightersList();
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
			if(gameEngine.getPlayer().getFighters().size() ==1)
				gameEngine.getPlayer().setActiveFighter(gameEngine.getPlayer().getFighters().get(0));
			System.out.println("Now it is " + gameEngine.getPlayer().getFighters().size());
			worldGUI.getCreatingFighter().setFighter(gameEngine.getPlayer().getFighters().get(gameEngine.getPlayer().getFighters().size()-1));
			worldGUI.getCombo().remove(worldGUI.getChoooseRace());
//			worldGUI.add(worldGUI.getFightersList());
//			worldGUI.add(worldGUI.getCreatingFighter());
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
		race.add(new JLabel("Race: "+getCorerspondingRace(i)));
		race.add(getPic(getCorerspondingRace(i)));
		temp.add(race);
		PlayableFighter fighter=null;
		
		switch(getCorerspondingRace(i)){
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
	
	public  String getCorerspondingRace (int i){
		switch(i){
		case  0 : return"Earthling";
		case 1 :return "Frieza" ;
		case 2 :return "Saiyan" ; 
		case 3 :return "Majin";
		case 4 : return "Namekian";
		}
		return null;
	}
	
//	public Attack getCorrespondingAttack(int i ){
//		switch(i){
//		// s
//		}
	//}
	
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
					JButton SUAttacks = new JButton("<html><center>"+"Super/ Ultimate"+"<br> Attacks</center></html>");
					worldGUI.getFightersList().getAttacks().add(SUAttacks);
					System.out.println("AWESOOOOOME");
					SUAttacks.addActionListener(worldGUI.getFightersList());
					stats.add(SUAttacks);
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
	
	public JButton getSuperAttackButton(SuperAttack attack) {
		
	
			JButton temp;
			if(attack instanceof MaximumCharge){
			  temp = new JButton("<html><center>"+"Maxmimum Charge"+"<br>Requires 0 KI"+"<br>Afflicts 0 Damage"+"<br>Charges 3 KI</center></html>");
			  temp.setFont(new Font("Times New Roman",15,15));
			}
			
			else
				temp= new JButton("<html><center>"+ attack.getName() + "<br>Damage: "+attack.getDamage()+"</ceneter></html>");
			  
			return temp;  
		
	}
	
	public JButton getUltimateAttackButton(UltimateAttack attack){
	
			JButton temp;
			if(attack instanceof SuperSaiyan){
				 temp = new JButton("<html><center>"+"Super Saiyan"+"<br>Exclusive for Saiyan"+"<br>Requires 3 KI bars but consumes 1 per turn"+"<br>Afflicted Damage: 0" + "<br> Increases damage by 25% until KI is 0</center></html>");
				 temp.setFont(new Font("Times New Roman",15,15));
			}
				 else
					 temp =new JButton("<html><center>"+ attack.getName() + "<br> Damage: "+attack.getDamage()+"</ceneter></html>");

				return temp;
			
	}
	
	public void load() throws IOException {
		File f  = new File("SaveData.ser");
		if(f.exists()){
			JOptionPane.showMessageDialog(worldGUI, "Done!");
		
			worldGUI.getCombo().repaint();
			worldGUI.getCombo().validate();
			worldGUI.addFightersList();
			for(PlayableFighter fighter :gameEngine.getPlayer().getFighters()){
				worldGUI.getFightersList().addFighter(getFighterPanel(fighter));
				
			}
			

				
				
			//	}
				//worldGUI.getSuperAndUltimateAttacks().addUltimateAttack(getAttackButtons("Ultimate"));
			
			
		//	worldGUI.getMap().populate();
			Dimension sizeofScreen = Toolkit.getDefaultToolkit().getScreenSize();
			int width  = (int)(Math.round(sizeofScreen.getWidth()));
			int height = (int)(Math.round(sizeofScreen.getHeight()))-(int)(Math.round(sizeofScreen.getHeight()/14.4));
			gameEngine.load("SaveData.ser");
			gameEngine.setListener(this);
	
			
			map= new  Map(gameEngine.getWorld().getPlayerRow(), gameEngine.getWorld().getPlayerColumn(),this);
			map.populate();
			map.setController(this);
			map.setFocusable(true);
			worldGUI.setFocusable(false);
			map.setExtendedState(JFrame.MAXIMIZED_BOTH);
			worldGUI.setVisible(false);
			map.setVisible(true);
			mapMusic();
		}
		else
			JOptionPane.showMessageDialog(worldGUI, "No previous save data");
	
	}
	
	
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
		System.out.println(e.getKeyCode());
		if(e.getSource() == introScreen){
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
		
		else if (e.getSource() == worldGUI){
			switch(e.getKeyCode()){
			
			case KeyEvent.VK_ESCAPE : worldGUI.getCombo().remove(worldGUI.getMenu());worldGUI.setVisible(false);
				map.setVisible(true);mapMusic();worldGUI.setFocusable(false);map.setFocusable(true);break;
			}
		}
		
		
		else if (e.getSource() == map){
			 
			switch(e.getKeyCode()){
			case KeyEvent.VK_ESCAPE: 
					System.out.println("HeeeI");
					map.setVisible(false);
					
					clean();
					worldGUI.addMenu();
					worldGUI.setVisible(true);
					map.setFocusable(false);
				worldGUI.setFocusable(true);
				break;
				
			case KeyEvent.VK_LEFT :
					moveLeft();
					break;
			case  KeyEvent.VK_A: moveLeft();
			 break;
				
			case KeyEvent.VK_RIGHT : moveRight();break;
			case KeyEvent.VK_D  : moveRight();break;
			
			case KeyEvent.VK_UP : moveUp();break;
			case KeyEvent.VK_W : moveUp(); break;
			
			case KeyEvent.VK_DOWN : moveDown();break;
			case KeyEvent.VK_S : moveDown();break;
		}
	}
	
	}
	
	
	
	
	
	public Game getGameEngine() {
		return gameEngine;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onDragonCalled(Dragon dragon) {
		try{new DragonFrame();}
		catch(Exception e){
			JOptionPane.showMessageDialog(dragonframe, "Something went wrong!");
		}
		
	}
	@Override
	public void onCollectibleFound(Collectible collectible) {
		player.pause();
		transition = new MediaPlayer(new Media(Paths.get("Sounds\\pickup.wav").toUri().toString()));
		transition.setStartTime(Duration.seconds(1));
		transition.play();
		try{Thread.sleep(900);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(map, "Something went wrong!");
		}
		player.play();
		String st = (collectible==Collectible.DRAGON_BALL)? "dragon ball": "senzu bean";
		JOptionPane.showMessageDialog(map, "You have collected a "+ st+"!");
		
	}
	@Override
	public void onBattleEvent(BattleEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void moveLeft()  {
	
		gameEngine.getWorld().moveLeft();	
		System.out.println(gameEngine.getWorld().getPlayerColumn());
		map.setPlayerRow(gameEngine.getWorld().getPlayerRow());
		map.setPlayerColumn(gameEngine.getWorld().getPlayerColumn());
		map.update();
		map.validate();
		map.repaint();
	}
	
	public void moveRight(){
		try{
			gameEngine.getWorld().moveRight();
			}
			catch(IndexOutOfBoundsException e){
				JOptionPane.showMessageDialog(map, "You can't move there!");
			}
		map.setPlayerRow(gameEngine.getWorld().getPlayerRow());
		map.setPlayerColumn(gameEngine.getWorld().getPlayerColumn());
		map.update();
		map.validate();
		map.repaint();
		
		
		}
	
	public void moveUp(){
		try{
			gameEngine.getWorld().moveUp();
			}
			catch(IndexOutOfBoundsException e){
				JOptionPane.showMessageDialog(map, "You can't move there!");
			}
		
		map.setPlayerRow(gameEngine.getWorld().getPlayerRow());
		map.setPlayerColumn(gameEngine.getWorld().getPlayerColumn());
		map.update();
		map.validate();
		map.repaint();
	}
	public void moveDown(){
		try{
			gameEngine.getWorld().moveDown();
			}
			catch(IndexOutOfBoundsException e){
				JOptionPane.showMessageDialog(map, "You can't move there!");
			}
		map.setPlayerRow(gameEngine.getWorld().getPlayerRow());
		map.setPlayerColumn(gameEngine.getWorld().getPlayerColumn());
		map.update();
		map.validate();
		map.repaint();
	}
	
	
	public void mapMusic(){
		player.pause();
		player =new MediaPlayer(new Media(Paths.get("Sounds\\map.mp3").toUri().toString()));
	    player.setOnEndOfMedia(new Runnable() {
			
			@Override
			
			public void run() {
				// TODO Auto-generated method stub
				player.seek(Duration.ZERO);
			}
		});
	    player.play();
	}
	
	
}
			
