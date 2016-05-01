package dragonball.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dragonball.controller.GGEvent;
import dragonball.controller.GameGUI;

public class DragonFrame extends JFrame implements ActionListener {
	private JButton senzubeans; 
	private JButton abilitypoints;
	private JButton newsuperattack;
	private JButton newultimateattack;
	private GameGUI controller;
	
public DragonFrame () throws IOException{
	setVisible(true);
	this.setLayout(null);
	
	
	Dimension sizeofScreen = Toolkit.getDefaultToolkit().getScreenSize();		
	int width  = (int)(Math.round(sizeofScreen.getWidth()));
	int height = (int)(Math.round(sizeofScreen.getHeight()))-(int)(Math.round(sizeofScreen.getHeight()/14.4));

BufferedImage pic = ImageIO.read(new File("Dragon.png"));
pic.getScaledInstance(sizeofScreen.width, sizeofScreen.height-(int)Math.round(sizeofScreen.getHeight()/14.4), BufferedImage.TYPE_INT_ARGB);
BufferedImage resizedImage = new BufferedImage(width, height, 	BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(pic, 0, 0, width, height, null);
		g.dispose();		
		setContentPane(new JLabel(new ImageIcon(resizedImage)));
	
	
		senzubeans= new JButton("Senzu Beans");
		senzubeans.setBounds(22, 300, 220, 70);
		senzubeans.addActionListener(this);
	  add(senzubeans);
	  
	  abilitypoints = new JButton("Ability Points");
	  abilitypoints.setBounds(22,375,220,70);
	  abilitypoints.addActionListener(this);
	 add (abilitypoints);
	 
	 newsuperattack = new JButton("New Super Attack");
	 newsuperattack.setBounds(22,450,220,70);
	 newsuperattack.addActionListener(this);
	 add(newsuperattack);
	 
	 newultimateattack = new JButton("New Ultimate Attack");
	 newultimateattack.setBounds(22,525,220,70);
	 newultimateattack.addActionListener(this);
	add (newultimateattack);
	setSize(width,height);
	
	setExtendedState(JFrame.MAXIMIZED_BOTH);
	setVisible(true);
	 
	  setDefaultCloseOperation(EXIT_ON_CLOSE);
	  repaint();
	  validate();

	}

@Override
public void actionPerformed(ActionEvent e) {
	controller.onEvent(new GGEvent(this,((JButton)e.getSource()).getText()));
}

public static void main(String[] args) throws IOException {
	Dimension sizeofScreen = Toolkit.getDefaultToolkit().getScreenSize();		
		int width  = (int)(Math.round(sizeofScreen.getWidth()));
		int height = (int)(Math.round(sizeofScreen.getHeight()))-(int)(Math.round(sizeofScreen.getHeight()/14.4));
	DragonFrame D = new DragonFrame();
	D.setSize(width,height);
	D.setExtendedState(JFrame.MAXIMIZED_BOTH);
	D.setVisible(true);
}

public GameGUI getController() {
	return controller;
}

public void setController(GameGUI controller) {
	this.controller = controller;
}


}