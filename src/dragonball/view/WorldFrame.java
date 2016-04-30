package dragonball.view;	

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Window;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.sun.prism.Image;

import java.awt.Rectangle;

import java.awt.Toolkit;
import dragonball.controller.GGEvent;
import dragonball.controller.GameGUI;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.fighter.PlayableFighter;


public class WorldFrame extends JFrame {
   
	private GameGUI controller;
	private Map map;
	private MenuScreen menu; 
	private CreatingPlayer cp;
	private JLayeredPane combo ;
	private String currentButton;

	private CreatingPlayer creatingPlayer;
	private FightersList fightersList;
	private CreatingFighter creatingFighter;
	private ChoooseRace choooseRace;
	private SuperAndUltimateAttacks superAndUltimateAttacks;
	private boolean selected;
	Dimension sizeofScreen = Toolkit.getDefaultToolkit().getScreenSize();
	int width  = (int)(Math.round(sizeofScreen.getWidth()));
	int height = (int)(Math.round(sizeofScreen.getHeight()))-(int)(Math.round(sizeofScreen.getHeight()/14.4));
	private BufferedImage  pic;
	
	public WorldFrame() throws IOException {
			
		
		combo = new JLayeredPane();		
		//combo.setBounds(0,0,1280,720);
		
	//	Rectangle windowSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		
		
		setLayout(new BorderLayout());
		combo = new JLayeredPane();		
		//combo.setBounds(0,0,width,height);

		//add(combo); 
		
//		map =new Map(9,9);
//		map.setBounds(0,0,width,height);
		menu = new MenuScreen();
		menu.setWorld(this);
		menu.setBounds(width/3,height/4,(int)(Math.round(width/3.2)),(int)(Math.round(height/1.8)));

	//	combo.add(map, 0,0);
		
		pic = ImageIO.read(new File("IntroScreen.png"));
		
		setContentPane(new JLabel(new ImageIcon(pic)));
		add(menu);
		menu.setVisible(true);
		combo.add(menu,0,0);
		
		creatingPlayer = new CreatingPlayer();
		creatingPlayer.setBounds(width/(width/440),height/(height/150),(int)(Math.round(width/3.2)),(int)(Math.round(height/1.8)));
		creatingPlayer.setWorld(this);
		
		fightersList = new FightersList();
		fightersList.setBounds(0,0,width,height);
		fightersList.setWorld(this);

		
		creatingFighter = new CreatingFighter();
		creatingFighter.setBounds(width/(width/440),height/(height/150),(int)(Math.round(width/3.2)),(int)(Math.round(height/1.8)));
		creatingFighter.setWorld(this);
		
		choooseRace = new ChoooseRace();
		choooseRace.setBounds(0,0,width,height);
		choooseRace.setWorld(this);
		

		setTitle("DragonBall");
		add(combo);
		validate();
		setVisible(true);
		
		setSize(width, height);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	

	
	public void addMenu(){
		combo.add(new JLabel(new ImageIcon("Introscreen.jpg")),0,0);
		combo.add(menu,1,0);
	}
	
	public void addCreatingPlayer(){
		combo.add(creatingPlayer);
	}
	
	public void addCreatingFighter(){
		combo.add(creatingFighter);
	}
	
	
	public void addFightersList(){
		
		combo.add(fightersList);
	}
	
	
	
	public void addChooseRace(){
		combo.add(choooseRace);
	}
	
	public void addSuperandUltimateAttacks(PlayableFighter fighter){
		superAndUltimateAttacks = new SuperAndUltimateAttacks(fighter);
		superAndUltimateAttacks.setWorld(this);
		superAndUltimateAttacks.setBounds((int)Math.round(width/6),(int)Math.round(height/4.5),(int)(Math.round(width/1.5)),(int)(Math.round(height/1.6)));
		
	}
	
	
	
	
	
	
	
	
	
	
	public CreatingPlayer getCp() {
		return cp;
	}








	public JLayeredPane getCombo() {
		return combo;
	}

	
	
//	public  String createPlayer() {
//		combo.add(creatingPlayer, 2,0);
//		creatingPlayer
//	}


	public static void main(String[] args) throws IOException {
		WorldFrame wf = new WorldFrame();
		
	}
	


	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public MenuScreen getMenu() {
		return menu;
	}

	public void setMenu(MenuScreen menu) {
		this.menu = menu;
	}



	
	public void setAttacks(PlayableFighter fighter){
		System.out.println("Hi");
		ArrayList <SuperAttack> superAttacks = fighter.getSuperAttacks();
		superAttacks.clear();
		System.out.println(superAndUltimateAttacks);
		for(JButton button : superAndUltimateAttacks.getListofSuper())
			if(button.getBackground()==Color.gray)
				superAttacks.add(controller.getGameEngine().getPlayer().getSuperAttacks().get(FightersList.findIndex(button, superAndUltimateAttacks.getListofSuper())));
			
		ArrayList <UltimateAttack> ultimateAttacks = fighter.getUltimateAttacks();
		ultimateAttacks.clear();
		for(JButton button : superAndUltimateAttacks.getListofUltimate())
			if(button.getBackground()==Color.gray  )
				ultimateAttacks.add(controller.getGameEngine().getPlayer().getUltimateAttacks().get(FightersList.findIndex(button, superAndUltimateAttacks.getListofUltimate())));
		
		combo.remove(superAndUltimateAttacks);
		
		for (SuperAttack attack:fighter.getSuperAttacks())
			System.out.println(attack.getName());
		repaint();
		validate();
	}			
	
	public void onEvent(GGEvent e)  {
			controller.onEvent(e);

	}

	

	




	public String getCurrentButton() {
		return currentButton;
	}



	public GameGUI getController() {
		return controller;
	}



	public void setController(GameGUI controller) {
		this.controller = controller;
	}

	public CreatingPlayer getCreatingPlayer() {
		return creatingPlayer;
	}





	public CreatingFighter getCreatingFighter() {
		return creatingFighter;
	}





	public FightersList getFightersList() {
		return fightersList;
	}





	public ChoooseRace getChoooseRace() {
		return choooseRace;
	}





	public SuperAndUltimateAttacks getSuperAndUltimateAttacks() {
		return superAndUltimateAttacks;
	}



	public boolean isSelected() {
		return selected;
	}



	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
	
}
	