package dragonball.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DragonFrame extends JFrame implements ActionListener {
	private JButton senzubeans; 
	private JButton abilitypoints;
	private JButton newsuperattack;
	private JButton newultimateattack;
	
public DragonFrame (){
	setVisible(true);
	this.setLayout(null);
	
	setContentPane( new JLabel( (new ImageIcon("Dragon.png"))));
	Dimension sizeofScreen = Toolkit.getDefaultToolkit().getScreenSize();		
	int width  = (int)(Math.round(sizeofScreen.getWidth()));
	int height = (int)(Math.round(sizeofScreen.getHeight()))-(int)(Math.round(sizeofScreen.getHeight()/14.4));
		senzubeans= new JButton("Senzu Beans");
		senzubeans.setBounds(22, 300, 220, 70);
	  add(senzubeans);
	  
	  abilitypoints = new JButton("Ability Points");
	  abilitypoints.setBounds(22,375,220,70);
	 add (abilitypoints);
	 
	 newsuperattack = new JButton("New SuperAttack");
	 newsuperattack.setBounds(22,450,220,70);
	 add (newsuperattack);
	 
	 newultimateattack = new JButton("New UltimateAttack");
	 newultimateattack.setBounds(22,525,220,70);
	add (newultimateattack);
	setSize(width,height);
	setExtendedState(JFrame.MAXIMIZED_BOTH);
	setVisible(true);
	 
	  setDefaultCloseOperation(EXIT_ON_CLOSE);
	  repaint();
	  validate();

	}

@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
}

public static void main(String[] args) {
	Dimension sizeofScreen = Toolkit.getDefaultToolkit().getScreenSize();		
		int width  = (int)(Math.round(sizeofScreen.getWidth()));
		int height = (int)(Math.round(sizeofScreen.getHeight()))-(int)(Math.round(sizeofScreen.getHeight()/14.4));
	DragonFrame D = new DragonFrame();
	D.setSize(width,height);
	D.setExtendedState(JFrame.MAXIMIZED_BOTH);
	D.setVisible(true);
}
}