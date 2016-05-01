package dragonball.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
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


import java.awt.Image;

import dragonball.controller.GGEvent;
import dragonball.controller.GameGUI;
import dragonball.controller.TypeofAttack;
import dragonball.model.attack.MaximumCharge;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

import javax.swing.Timer;
import javax.swing.JProgressBar;
import javafx.scene.layout.Background;

public class BattleView extends JFrame  implements ActionListener {

	private JProgressBar playerHealth;
	private  JProgressBar foeHealth;
	Timer tm ;
	int progress = 0;
	
	private JProgressBar playerStamina;
	private JProgressBar foeStamina;
	private JLabel playerKi;
	private JLabel foeKi;
	final String health = "Health";
	final String stamina = "Stamina";
	private JLabel superAttacks;
	private JLabel ultimateAttacks;
	private Image kiDrop ;
	private JButton sA;
	private JButton uA;
	private JButton physicalAttack;
	private  GameGUI controller;
	private boolean playerTurn;
	
	public BattleView(){
		
	this.setLayout(null);
	setTitle("Battle");
	Dimension sizeofScreen = Toolkit.getDefaultToolkit().getScreenSize();		
	int width  = (int)(Math.round(sizeofScreen.getWidth()));
	int height = (int)(Math.round(sizeofScreen.getHeight()))-(int)(Math.round(sizeofScreen.getHeight()/14.4));
//
	BufferedImage pic=null;
	try {
		pic = ImageIO.read(new File("Pics\\arena.jpg"));
	} catch (IOException e) {
		JOptionPane.showMessageDialog(this, "Something Went wrong!");
	}
	pic.getScaledInstance(sizeofScreen.width, sizeofScreen.height-(int)Math.round(sizeofScreen.getHeight()/14.4), BufferedImage.TYPE_INT_ARGB);
	BufferedImage resizedImage = new BufferedImage(width, height, 	BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(pic, 0, 0, width, height, null);
		g.dispose();		
		setContentPane(new JLabel(new ImageIcon(resizedImage)));
	
	foeHealth= new JProgressBar();
	foeHealth.setStringPainted(true);
	foeHealth.setForeground(Color.red);
	foeHealth.setBounds(0,(int)Math.round(height*0.08),(int)Math.round(width*0.2),(int)Math.round(height/25));
	foeHealth.setMinimum(0);
	add(foeHealth);
	
	playerHealth= new JProgressBar();
	playerHealth.setStringPainted(true);
	playerHealth.setForeground(Color.red);
	playerHealth.setBounds((int)Math.round(width*0.80),(int)Math.round(height*0.08),(int)Math.round(width*0.2),(int)Math.round(height/25));
	playerHealth.setMinimum(0);
	add(playerHealth);
	
	foeStamina = new JProgressBar();
	foeStamina.setStringPainted(true);
	foeStamina.setForeground(Color.green);
	foeStamina.setBounds(0,playerHealth.getY()+playerHealth.getHeight(),(int)Math.round(width*0.2),(int)Math.round(height/25));
	foeStamina.setMinimum(0);
	add(foeStamina);
	
	
	playerStamina = new JProgressBar();
 	playerStamina.setStringPainted(true);
	playerStamina.setForeground(Color.green);
	playerStamina.setBounds((int)Math.round(width*0.80),playerHealth.getY()+playerHealth.getHeight(),(int)Math.round(width*0.2),(int)Math.round(height/25));
	playerStamina.setMinimum(0);
	add(playerStamina);
	
	
	BufferedImage ki = null;
	try{
		 ki = ImageIO.read(new File("Pics\\KI.png"));
		}
		catch(Exception e){
			System.out.println("HMMM");
		}
	
	
	 kiDrop = ki.getScaledInstance(width/30, height/25, Image.SCALE_DEFAULT);
	

	
	JLabel playerKiLabel = new JLabel("Ki Bars");
	playerKiLabel.setBounds((int)Math.round(width*0.75),playerStamina.getY()+height/17,(int)Math.round(width*0.2),(int)Math.round(height/25));
	playerKiLabel.setFont(new Font("Arial", 20, 20));
	playerKiLabel.setForeground(Color.blue);
	add(playerKiLabel);
	playerKi = new JLabel();

	playerKi.setBounds((int)Math.round(width*0.80),playerStamina.getY()+height/18,(int)Math.round(width*0.2),(int)Math.round(height/25));
	playerKi.setLayout(new FlowLayout());
	add(playerKi);
	
	
	JLabel foeKiLabel = new JLabel("Ki Bars");
	foeKiLabel.setBounds(0,playerStamina.getY()+height/17,(int)Math.round(width*0.2),(int)Math.round(height/25));
	foeKiLabel.setFont(new Font("Arial", 20, 20));
	foeKiLabel.setForeground(Color.blue);
	add(foeKiLabel);
	
	foeKi = new JLabel();

	foeKi.setBounds((int)Math.round(width*0.05),playerStamina.getY()+height/18,(int)Math.round(width*0.2),(int)Math.round(height/25));
	foeKi.setLayout(new FlowLayout());
	add(foeKi);
																																										
	
	try{
	 pic = ImageIO.read(new File("Pics\\Goku.png"));
	}
	catch(Exception e){
		System.out.println("HMMM");
	}
	
	
	JLabel playerFighter =new JLabel(new ImageIcon(pic));
	playerFighter.setBounds((int)Math.round(width*0.80), playerStamina.getY()+(width/8),pic.getWidth(),pic.getHeight());
	playerFighter.setVisible(true);
	add(playerFighter);
	
	
	try{
		 pic = ImageIO.read(new File("Pics\\Vegeta.png"));
		}
		catch(Exception e){
			System.out.println("HMMM");
		}
	
	
	BufferedImage resizedImage2 = new BufferedImage(width/3,height/3, BufferedImage.TYPE_INT_ARGB);
	Graphics2D g2 = resizedImage.createGraphics();
	g2.drawImage(pic, 0, playerStamina.getY()+(width/6), width/3, height/3, null);
	g2.dispose();		

	
	JLabel foeFighter =new JLabel(new ImageIcon(resizedImage2));
	foeFighter.setBounds(0, 500,resizedImage2.getWidth(),resizedImage.getHeight());
	foeFighter.setVisible(true);
	add(foeFighter);
	
	
	JButton attack = new JButton("Attack");
	attack.addActionListener(this);
	sA= new JButton("Super Attacks");
	sA.addActionListener(this);
	 uA = new JButton("Ultimate Attacks");
	 uA.addActionListener(this);
	JButton use = new JButton("Use");
	use.addActionListener(this);
	JButton block = new JButton("Block");
	block.addActionListener(this);
	attack.setBounds((int)Math.round(width*0.66), playerFighter.getY()+playerFighter.getHeight(),(int)Math.round(width*0.1),(int)Math.round(height/20));
	add(attack);
	block.setBounds(attack.getX()+attack.getWidth()+width/60, playerFighter.getY()+playerFighter.getHeight(),(int)Math.round(width*0.1),(int)Math.round(height/20));
	add(block);
	use.setBounds(block.getX()+block.getWidth()+width/60, playerFighter.getY()+playerFighter.getHeight(),(int)Math.round(width*0.1),(int)Math.round(height/20));
	add(use);
	sA.setBounds(attack.getX()-(int)Math.round(attack.getWidth()/2)*2,attack.getY()-attack.getHeight()*2,(int)Math.round(width*0.1),(int)Math.round(height/20));
	add(sA);
	
	uA.setBounds(attack.getX()+(int)Math.round(attack.getWidth()/2),attack.getY()-attack.getHeight()*2,(int)Math.round(width*0.1),(int)Math.round(height/20));
	add(uA);
	
	 physicalAttack = new JButton("Physical Attack");
	 physicalAttack.addActionListener(this);
	physicalAttack.setBounds(uA.getX()+uA.getWidth()+width/50,attack.getY()-attack.getHeight()*2,(int)Math.round(width*0.1),(int)Math.round(height/20));
	add(physicalAttack);
	
	superAttacks = new JLabel();
	superAttacks.setBounds(sA.getX()-sA.getWidth()/2,playerKiLabel.getY()+height/50, sA.getX()+sA.getWidth()+sA.getWidth()/2-(sA.getX()-sA.getWidth()/2), (int)Math.round(height/2.8));
	superAttacks.setLayout(new GridLayout(4, 0));
	add(superAttacks);
	
	
	ultimateAttacks = new JLabel();
	ultimateAttacks.setBounds((int)Math.round(uA.getX()+uA.getWidth()/2),playerKiLabel.getY()+height/50, sA.getX()+sA.getWidth()+sA.getWidth()/2-(sA.getX()-sA.getWidth()/2), (int)Math.round(height/2.8));
	ultimateAttacks.setLayout(new GridLayout(4, 0));
	add(ultimateAttacks);
	
	
	
	hide();
	
	setVisible(true);
	setSize(width, height);
	setExtendedState(JFrame.MAXIMIZED_BOTH); 
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}

	
	public static void main(String[] args) {
		new BattleView();
	}
	
	public void hide(){
		sA.setVisible(false);
		uA.setVisible(false);
		superAttacks.setVisible(false);
		ultimateAttacks.setVisible(false);
		physicalAttack.setVisible(false);
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button =(JButton) e.getSource();
	
		switch(button.getText()){
		case "Attack": sA.setVisible(true);uA.setVisible(true);physicalAttack.setVisible(true);break;
		
		case "Super Attacks": superAttacks.setVisible(true);if(superAttacks.getComponentCount()==0)
			JOptionPane.showMessageDialog(this, "You don't have any super attacks assigned");break;
		case "Ultimate Attacks": ultimateAttacks.setVisible(true);
		if(ultimateAttacks.getComponentCount()==0)
			JOptionPane.showMessageDialog(this, "You don't have any ultimate attacks assigned");break;	
		
		
		default :
			if(button.getText().equals("Block") || button.getText().equals("Use") ||button.getText().equals("Physical Attack")){
				controller.onEvent(new GGEvent(this, button.getText()));
				hide();
			}
				
			else if(isSuper(button)){
				System.out.println("HEH");
				int index;
					System.out.println("SUPPPPER!");
					for(int i=0;i<controller.getGameEngine().getPlayer().getActiveFighter().getSuperAttacks().size();i++){
						if(button.getText().equals(controller.getGameEngine().getPlayer().getActiveFighter().getSuperAttacks().get(i)))
							index = i;
					}
					index = -1;
					controller.onEvent(new GGEvent(this, TypeofAttack.SUPER,findIndex(button, superAttacks)));
					hide();
			}
			else if(isUltimate(button)){
				
				int index;
				for(int i=0;i<controller.getGameEngine().getPlayer().getActiveFighter().getUltimateAttacks().size();i++){
					if(button.getText().equals(controller.getGameEngine().getPlayer().getActiveFighter().getUltimateAttacks().get(i)))
						index = i;
				}
				index = -1;
				System.out.println("ULTIMATE!");
				controller.onEvent(new GGEvent(this, TypeofAttack.UlTIMATE,findIndex(button, ultimateAttacks)));
				hide();
				}
		break;
		}
	}
	
	public int findIndex(JButton button ,JLabel label){
		System.out.println("count is "+ label.getComponentCount());
		for(int i =0 ;i<label.getComponentCount();i++){
			if(label.getComponent(i)==button)
				return i;
		}
		return -1;
	}
	
	
	public boolean isSuper(JButton b){
		for(SuperAttack a : controller.getGameEngine().getPlayer().getActiveFighter().getSuperAttacks()){
			String name = a.getName();
			if(b.getName().equals(name))
			return true;
		}
		return false;
	}
	
	public boolean isUltimate(JButton b){
		for(UltimateAttack  a : controller.getGameEngine().getPlayer().getActiveFighter().getUltimateAttacks()){
			String name = a.getName();
			if(b.getName().equals(name))
			return true;
		}
		return false;
	}
	
	public void update(int playerHealthVal, int foeHealthVal, int playerStaminaVal,int foeStaminaVal,
	int playerKiVal,int foeKiVal){
	
		
		System.out.println("da health val" + playerHealthVal);
		
		playerHealth.setValue(playerHealthVal);
		playerHealth.setString(Integer.toString(playerHealthVal));
		
		foeHealth.setValue(foeHealthVal);
		foeHealth.setString(Integer.toString(foeHealthVal));
		
		playerStamina.setValue(playerStaminaVal);
		playerStamina.setString(Integer.toString(playerStaminaVal));
		
		foeStamina.setValue(foeStaminaVal);
		foeStamina.setString(Integer.toString(foeStaminaVal));
		
		
		playerKi.removeAll();
		for(int i =0;i<playerKiVal;i++)
			playerKi.add(new JLabel(new ImageIcon(kiDrop)));
		
		foeKi.removeAll();
		for(int i =0;i<foeKiVal;i++)
			foeKi.add(new JLabel(new ImageIcon(kiDrop)));
		
		repaint();
		validate();
		
		if(playerTurn)
			JOptionPane.showMessageDialog(this, "It is now your turn");
	}


	public GameGUI getController() {
		return controller;
	}


	public void setController(GameGUI controller) {
		this.controller = controller;
	}


	public boolean isPlayerTurn() {
		return playerTurn;
	}


	public void setPlayerTurn(boolean playerTurn) {
		this.playerTurn = playerTurn;
	}


	public JProgressBar getPlayerHealth() {
		return playerHealth;
	}


	public JProgressBar getFoeHealth() {
		return foeHealth;
	}


	public JProgressBar getPlayerStamina() {
		return playerStamina;
	}


	public JProgressBar getFoeStamina() {
		return foeStamina;
	}


	public JLabel getSuperAttacks() {
		return superAttacks;
	}


	public void setSuperAttacks(JLabel superAttacks) {
		this.superAttacks = superAttacks;
	}


	public JLabel getUltimateAttacks() {
		return ultimateAttacks;
	}


	public void setUltimateAttacks(JLabel ultimateAttacks) {
		this.ultimateAttacks = ultimateAttacks;
	}
	
	
	
} 
