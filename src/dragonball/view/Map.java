package dragonball.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

import dragonball.controller.GGEvent;
import dragonball.controller.GameGUI;
import javafx.scene.input.KeyCode;


public class Map extends JFrame implements ActionListener , KeyListener{

	/**
	 * The actual map where you move around 
	 */

	private Image currentFighter ;
	private Image boss;
	private int playerRow;
	private int playerColumn;
	private JLayeredPane combo;
	private GameGUI controller;
	private JPanel sideMenu;
	static Dimension sizeofScreen = Toolkit.getDefaultToolkit().getScreenSize();
	static int width  = (int)(Math.round(sizeofScreen.getWidth()));
	static int height = (int)(Math.round(sizeofScreen.getHeight()))-(int)(Math.round(sizeofScreen.getHeight()/14.4));
	private JPanel tiles ;
	
	public Map(int playerRow, int playerColumn,GameGUI controller) throws IOException {
		
			this.controller=controller;
		// img=ImageIO.read(new File("Grass.jpg"))
//		this.add(j);
//		this.setPreferredSize(new Dimension(800,600));
			

		
		addKeyListener(controller);
		 sizeofScreen = Toolkit.getDefaultToolkit().getScreenSize();
		width  = (int)(Math.round(sizeofScreen.getWidth()));
		height = (int)(Math.round(sizeofScreen.getHeight()))-(int)(Math.round(sizeofScreen.getHeight()/14.4));
		 BufferedImage pic = ImageIO.read(new File("Pics\\Grass.jpg"));
		 combo = new JLayeredPane();		
		
		this.playerRow= playerRow;
		this.playerColumn = playerColumn;
		
		 tiles = new JPanel();
		tiles.setLayout(new GridLayout(10, 10));
		 BufferedImage player =  ImageIO.read(new File("Pics\\Player.png"));
		 BufferedImage boss = ImageIO.read(new File("Pics\\Boss.png"));
		 tiles.add(new JLabel(new ImageIcon(boss)));
		for (int i = 0; i < 10; i++) {
			for(int j=0 ;j<10 ; j++){
				
				if(i==0 && j==0) j =1;
				
				JLabel tempTile = new JLabel();
				
				
				
				if(i==playerRow && j==playerColumn)
					tempTile = new JLabel(new ImageIcon(player));
				else
					tempTile= new JLabel(new ImageIcon(pic));
				
				tempTile.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.white));
				
			tiles.add(tempTile);
			
			}
		}
		
		
		
			tiles.setBounds(0, 0, (int)Math.round(width*0.85), height);
			
			combo.add(tiles, 0,0);
	
			setSize(width,height);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			add(combo);
		
		}
	
	public void populate() {
		JLabel name = new JLabel("Name " + controller.getGameEngine().getPlayer().getName());
		
		
		
		sideMenu = new JPanel();
		
		sideMenu.setBounds((int)Math.round(width*0.85), 0, width-(int)Math.round(width*0.85), height);
		sideMenu.setLayout(new GridLayout(0, 1));
		sideMenu.add(name);
		sideMenu.add(new JLabel("Active Fighter: " + controller.getGameEngine().getPlayer().getActiveFighter().getName()));
		sideMenu.add(new JLabel("senzu beans:" + controller.getGameEngine().getPlayer().getSenzuBeans()));
		sideMenu.add(new JLabel("dragon balls:" + controller.getGameEngine().getPlayer().getDragonBalls()));
		JButton fighters = new JButton("Fighters Options");
		fighters.addActionListener(this);
		sideMenu.add(fighters);
		sideMenu.setBackground(Color.orange);
		combo.add(sideMenu, 1,0);
	}
	public Image getCurrentFighter() {
		return currentFighter;
	}

	public Image getBoss() {
		return boss;
	}
	
	public static void main(String[] args) throws IOException {
	
		
		Map m = new Map(9,9,null);
		m.setVisible(true);
		 sizeofScreen = Toolkit.getDefaultToolkit().getScreenSize();
		 width  = (int)(Math.round(sizeofScreen.getWidth()));
		 height = (int)(Math.round(sizeofScreen.getHeight()))-(int)(Math.round(sizeofScreen.getHeight()/14.4));
		m.setSize(width,height);
		//jf.setDefaultCloseOperation();
	}


	
	public void update() {
		tiles.removeAll();
		BufferedImage pic = null;
		BufferedImage player =null;
		BufferedImage boss =null;
		try{
		  pic = ImageIO.read(new File("Pics\\Grass.jpg"));
		  player =  ImageIO.read(new File("Pics\\Player.png"));
		  boss = ImageIO.read(new File("Pics\\Boss.png"));
		}
		catch(IOException e){
			JOptionPane.showMessageDialog(this, "Something went wrong :(");
		}
		 tiles.add(new JLabel(new ImageIcon(boss)));
		for (int i = 0; i < 10; i++) {
			for(int j =0 ;j<10 ; j++){
//				
				JLabel tempTile = new JLabel();
				
				if(i==0 && j==0)j++;
				
				if(i==playerRow && j==playerColumn)
					tempTile = new JLabel(new ImageIcon(player));
				
				else{
					tempTile= new JLabel(new ImageIcon(pic));
				
					
				}
				tempTile.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.white));
				
			tiles.add(tempTile);
			
			
		}
		}
		
		
		
		//tiles.setBounds(0, 0, (int)Math.round(width*0.85), height);
		

			combo.removeAll();
			tiles.validate();
			tiles.repaint();
			combo.add(tiles, 0,0);
		
			populate();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		controller.onEvent(new GGEvent(this, ((JButton)e.getSource()).getText()));
		
	}


	public int getPlayerRow() {
		return playerRow;
	}


	public int getPlayerColumn() {
		return playerColumn;
	}




	public JLayeredPane getCombo() {
		return combo;
	}


	public GameGUI getController() {
		return controller;
	}


	public void setController(GameGUI controller) {
		this.controller = controller;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("Key released..");
		switch(e.getKeyCode()){
		case KeyEvent.VK_KP_LEFT :
			System.out.println("HEEI");
			
				controller.moveLeft();
				
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void setPlayerRow(int playerRow) {
		this.playerRow = playerRow;
	}

	public void setPlayerColumn(int playerColumn) {
		this.playerColumn = playerColumn;
	}


		
		
}
