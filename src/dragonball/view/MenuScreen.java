package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dragonball.controller.GGEvent;

public class MenuScreen extends JPanel  implements ActionListener{ 

	
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
		  newGame.addActionListener(this);
		 save = new JButton("Save");
		 save.addActionListener(this);
		 add(save);
		  load = new JButton("Load");
		  load.addActionListener(this);
		  add(load);
		  exit = new JButton("Exit");
		  exit.addActionListener(this);
		 add(exit);

		 repaint();
		 validate();
		 
		 
		  	}
	
	
	public static void main(String[] args) {
		MenuScreen menu = new MenuScreen();
		JFrame jf = new JFrame();
		jf.add(menu);
		jf.setVisible(true);
		jf.setSize(1280, 720);
	
	}
	
	
	public WorldFrame getWorld() {
		return world;
	}

	public void setWorld(WorldFrame world) {
		this.world = world;
	}





	@Override
	public void actionPerformed(ActionEvent e) {
		world.onEvent(new GGEvent(this, ((JButton)e.getSource()).getText()));
		
	}
	
	
}
