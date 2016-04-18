package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
<<<<<<< HEAD
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
=======
>>>>>>> c3eb60443824c30816bce8b83446f260ca2d3881
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

<<<<<<< HEAD
import dragonball.controller.GGEvent;

public class MenuScreen extends JPanel  implements ActionListener{ 
=======
public class MenuScreen extends JPanel { 
>>>>>>> c3eb60443824c30816bce8b83446f260ca2d3881
	
	private JButton newGame; 
	private JButton save;
	private JButton load;
	private JButton exit;
	private WorldFrame world;
	private JPanel  creatingPlayer;
	
	public MenuScreen() {
	
		setVisible(true);
		setLayout(new GridLayout(4, 1));
		  
		  newGame= new JButton("New Game");
		  add(newGame);
<<<<<<< HEAD
		  newGame.addActionListener(this);
		 save = new JButton("Save");
		 save.addActionListener(this);
		 add(save);
		  load = new JButton("Load");
		  load.addActionListener(this);
		  add(load);
		  exit = new JButton("Exit");
		  exit.addActionListener(this);
=======
		 save = new JButton("Save");
		 add(save);
		  load = new JButton("Load");
		  add(load);
		  exit = new JButton("Exit");
>>>>>>> c3eb60443824c30816bce8b83446f260ca2d3881
		 add(exit);
//		  JLabel order = new JLabel("Please enter your player's name");
	//	  JTextArea  playerName =new  JTextArea();
		//  creatingPlayer.add(playerName,BorderLayout.CENTER);
		//  JButton ok = new JButton("OK");
		  //JButton cancel = new JButton("Cancel");
		  //this.add(creatingPlayer);
		 repaint();
		 validate();
		 
		 
		  	}
	
	
	
<<<<<<< HEAD
	
=======
	public void initializeButton(){
		 newGame.addActionListener(world);
		 save.addActionListener(world);
		  load.addActionListener(world);
		  exit.addActionListener(world);


	}
>>>>>>> c3eb60443824c30816bce8b83446f260ca2d3881
	
	public static void main(String[] args) {
		MenuScreen menu = new MenuScreen();
		JFrame jf = new JFrame();
		jf.add(menu);
		jf.setVisible(true);
		jf.setSize(600, 400);
	
	}
	
	
	

	public WorldFrame getWorld() {
		return world;
	}

	public void setWorld(WorldFrame world) {
		this.world = world;
	}
<<<<<<< HEAD





	@Override
	public void actionPerformed(ActionEvent e) {
		world.onEvent(new GGEvent(this, ((JButton)e.getSource()).getText()));
		
	}
=======
>>>>>>> c3eb60443824c30816bce8b83446f260ca2d3881
	
	
}
