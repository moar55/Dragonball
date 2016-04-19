package dragonball.view;	

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import com.sun.javafx.tk.Toolkit;

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

	
	public WorldFrame() throws IOException {
			

		setLayout(new BorderLayout());
		combo = new JLayeredPane();		
		combo.setBounds(0,0,1280,720);

		setLayout(new BorderLayout());
		combo = new JLayeredPane();		
		combo.setBounds(0,0,600,400);

		add(combo); 
		
		map =new Map();
		map.setBounds(0,0,1280,720);
		menu = new MenuScreen();
		menu.setWorld(this);
		menu.setBounds(400,150,400,400);

		combo.add(map, 0,0);
		combo.add(menu, 1,0);

		creatingPlayer = new CreatingPlayer();
		creatingPlayer.setBounds(400,150,400,400);
		creatingPlayer.setWorld(this);

		setTitle("World Mode");
		add(combo);
		validate();
		setVisible(true);
		
		setSize(1280, 720);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	

	
	
	
	public void addCreatingPlayer(){
		combo.add(creatingPlayer,2,0);
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
	
	
	

	
}
	

		 
	 
