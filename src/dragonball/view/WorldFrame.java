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

<<<<<<< HEAD
import com.sun.javafx.tk.Toolkit;

import dragonball.controller.GGEvent;
=======

>>>>>>> c3eb60443824c30816bce8b83446f260ca2d3881
import dragonball.controller.GameGUI;


public class WorldFrame extends JFrame implements ActionListener{
   
	private GameGUI controller;
	private Map map;
	private MenuScreen menu; 
	private CreatingPlayer cp;
	private JLayeredPane combo ;
	private String currentButton;
<<<<<<< HEAD
	private CreatingPlayer creatingPlayer;
=======
>>>>>>> c3eb60443824c30816bce8b83446f260ca2d3881
	
	public WorldFrame() throws IOException {
			
//		setPreferredSize(new Dimension(1280, 720));
<<<<<<< HEAD
		
		setLayout(new BorderLayout());
		combo = new JLayeredPane();		
		combo.setBounds(0,0,1280,720);
=======
		setLayout(new BorderLayout());
		combo = new JLayeredPane();		
		combo.setBounds(0,0,600,400);
>>>>>>> c3eb60443824c30816bce8b83446f260ca2d3881
		add(combo); 
		
		map =new Map();
		map.setBounds(0,0,1280,720);
		menu = new MenuScreen();
		menu.setWorld(this);
		menu.setBounds(400,150,400,400);
<<<<<<< HEAD
		combo.add(map, 0,0);
		combo.add(menu, 1,0);

		creatingPlayer = new CreatingPlayer();
		creatingPlayer.setBounds(400,150,400,400);
		creatingPlayer.setWorld(this);
=======
		menu.initializeButton();
		combo.add(map, 0,0);
		combo.add(menu, 1,0);
>>>>>>> c3eb60443824c30816bce8b83446f260ca2d3881
//		cp = new CreatingPlayer();
//		cp.setBounds(200,100,200,200);
//		cp.setVisible(true);
//		cp.setWorld(this);
//		cp.initializeButton();
//		cp.setOpaque(true);
//		combo.add(cp);
		setTitle("World Mode");
<<<<<<< HEAD
=======
		menu.initializeButton();
>>>>>>> c3eb60443824c30816bce8b83446f260ca2d3881
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
	
<<<<<<< HEAD
	
	
	
	public void addCreatingPlayer(){
		combo.add(creatingPlayer,2,0);
	}
=======
	public void createPlayer () {
		
	}
	
	
	
	
>>>>>>> c3eb60443824c30816bce8b83446f260ca2d3881
	
	
	
	
	public CreatingPlayer getCp() {
		return cp;
	}








	public JLayeredPane getCombo() {
		return combo;
	}
<<<<<<< HEAD
	
	
//	public  String createPlayer() {
//		combo.add(creatingPlayer, 2,0);
//		creatingPlayer
//	}
=======
>>>>>>> c3eb60443824c30816bce8b83446f260ca2d3881








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


<<<<<<< HEAD
	public void onEvent(GGEvent e) {
		controller.onEvent(e);
=======

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton ){
			System.out.println("HI");
			String input= ((JButton)e.getSource()).getText();
			if(input.equals("Ok"));
			controller.menuEvent(input);
		}
		
		
>>>>>>> c3eb60443824c30816bce8b83446f260ca2d3881
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
<<<<<<< HEAD









	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	public CreatingPlayer getCreatingPlayer() {
		return creatingPlayer;
	}
	
	
	
=======
>>>>>>> c3eb60443824c30816bce8b83446f260ca2d3881
	
}
	

		 
	 
