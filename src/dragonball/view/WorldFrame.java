package dragonball.view;	

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import java.awt.Rectangle;

import java.awt.Toolkit;
import dragonball.controller.GGEvent;
import dragonball.controller.GameGUI;


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
	

	
	public WorldFrame() throws IOException {
			
		
		combo = new JLayeredPane();		
		//combo.setBounds(0,0,1280,720);
		Dimension sizeofScreen = Toolkit.getDefaultToolkit().getScreenSize();
	//	Rectangle windowSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		
		int width  = (int)(Math.round(sizeofScreen.getWidth()));
		int height = (int)(Math.round(sizeofScreen.getHeight()))-(int)(Math.round(sizeofScreen.getHeight()/14.4));
		setLayout(new BorderLayout());
		combo = new JLayeredPane();		
		//combo.setBounds(0,0,width,height);

		add(combo); 
		
		map =new Map();
		map.setBounds(0,0,width,height);
		menu = new MenuScreen();
		menu.setWorld(this);
		menu.setBounds(width/3,height/4,(int)(Math.round(width/3.2)),(int)(Math.round(height/1.8)));

		combo.add(map, 0,0);
		combo.add(menu, 1,0);

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
	

	
	
	
	public void addCreatingPlayer(){
		combo.add(creatingPlayer,2,0);
	}
	
	public void addCreatingFighter(){
		combo.add(creatingFighter,2,0);
	}
	
	
	public void addFightersList(){
		
		combo.add(fightersList,1,0);
	}
	
	
	
	public void addChooseRace(){
		combo.add(choooseRace, 3,0);
	}
	
	public void createPlayer () {
		
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



	public void onEvent(GGEvent e) {
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
	
	
	
}
	