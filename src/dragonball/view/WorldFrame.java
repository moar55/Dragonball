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


import dragonball.controller.GameGUI;


public class WorldFrame extends JFrame implements ActionListener{
   
	private GameGUI controller;
	private Map map;
	private MenuScreen menu; 
	private CreatingPlayer cp;
	private JLayeredPane combo ;
	private String currentButton;
	
	public WorldFrame() throws IOException {
			
//		setPreferredSize(new Dimension(1280, 720));
		setLayout(new BorderLayout());
		combo = new JLayeredPane();		
		combo.setBounds(0,0,600,400);
		add(combo); 
		
		map =new Map();
		map.setBounds(0,0,1280,720);
		menu = new MenuScreen();
		menu.setWorld(this);
		menu.setBounds(400,150,400,400);
		menu.initializeButton();
		combo.add(map, 0,0);
		combo.add(menu, 1,0);
//		cp = new CreatingPlayer();
//		cp.setBounds(200,100,200,200);
//		cp.setVisible(true);
//		cp.setWorld(this);
//		cp.initializeButton();
//		cp.setOpaque(true);
//		combo.add(cp);
		setTitle("World Mode");
		menu.initializeButton();
		add(combo);
		//combo.setVisible(true);
//		menu.setOpaque(true);
		//menu.setVisible(false);
		//setSize(600, 400);
		repaint();
		validate();
		setVisible(true);
		
		//.setVisible(false);
		setSize(1280, 720);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void createPlayer () {
		
	}
	
	
	
	
	
	
	
	
	public CreatingPlayer getCp() {
		return cp;
	}








	public JLayeredPane getCombo() {
		return combo;
	}








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



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton ){
			System.out.println("HI");
			String input= ((JButton)e.getSource()).getText();
			if(input.equals("Ok"));
			controller.menuEvent(input);
		}
		
		
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
	
}
	

		 
	 
