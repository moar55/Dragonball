package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MenuScreen extends JPanel { 
	
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
		 save = new JButton("Save");
		 add(save);
		  load = new JButton("Load");
		  add(load);
		  exit = new JButton("Exit");
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
	
	
	
	public void initializeButton(){
		 newGame.addActionListener(world);
		 save.addActionListener(world);
		  load.addActionListener(world);
		  exit.addActionListener(world);


	}
	
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
	
	
}
