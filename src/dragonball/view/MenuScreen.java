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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import dragonball.controller.GGEvent;

public class MenuScreen extends JPanel  implements ActionListener{ 

	
	private JButton newGame; 
	private JButton save;
	private JButton load;
	private JButton exit;
	private WorldFrame world;
	private JPanel  creatingPlayer;
	
	public MenuScreen() {
	
		setLayout(null);
		
		Dimension sizeofScreen = Toolkit.getDefaultToolkit().getScreenSize();		
		int width  = (int)(Math.round(sizeofScreen.getWidth()));
		int height = (int)(Math.round(sizeofScreen.getHeight()))-(int)(Math.round(sizeofScreen.getHeight()/14.4));
		
	
		
		JLabel list = new JLabel();
		 list.setBounds((int)Math.round(width*0.36),(int)Math.round(height/3.5), (int)Math.round(width*0.3),height/2);
		 list.setLayout(new GridLayout(4, 0));
		  newGame= new JButton("New Game");
		  System.out.println(newGame.getX()  +" "+ newGame.getY()+" "+ newGame.getWidth()+" "+ newGame.getHeight());
		  list.add(newGame);
		 
		  newGame.addActionListener(this);
		 save = new JButton("Save");
		 save.addActionListener(this);
		 list.add(save);
		  load = new JButton("Load");
		  load.addActionListener(this);
		  list.add(load);
		  exit = new JButton("Exit");
		  exit.addActionListener(this);
		 list.add(exit);
		 list.validate();
		 list.repaint();
		 add(list);
		 
			BufferedImage pic=null;
			try {
				pic = ImageIO.read(new File("Pics\\MenuBackground.png"));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Something Went Wrong!");
			}
			Image img = pic.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			JLabel dude = new JLabel(new ImageIcon(img));
			dude.setBounds(0, 0, width, height);
			add(dude);
			
		 repaint();
		 validate();
		 
		 
		  	}
	
	
	public static void main(String[] args) {
		MenuScreen menu = new MenuScreen();
		JFrame jf = new JFrame();
		jf.add(menu);
		jf.repaint();
		jf.validate();
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
