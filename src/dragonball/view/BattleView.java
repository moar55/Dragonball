package dragonball.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.sql.Time;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import javax.swing.Timer;
import javax.swing.JProgressBar;
import javafx.scene.layout.Background;
import sun.net.ProgressSource;

public class BattleView extends JFrame  implements PropertyChangeListener ,ActionListener {

	private JProgressBar playerHealth;
	private  JProgressBar foeHealth;
	Timer tm ;
	int progress = 100;
	
	public BattleView(){
	tm =  new Timer(150,this);
	this.setLayout(null);
	Dimension sizeofScreen = Toolkit.getDefaultToolkit().getScreenSize();		
	int width  = (int)(Math.round(sizeofScreen.getWidth()));
	int height = (int)(Math.round(sizeofScreen.getHeight()))-(int)(Math.round(sizeofScreen.getHeight()/14.4));

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
	setVisible(true);
	setSize(width, height);
	setExtendedState(JFrame.MAXIMIZED_BOTH);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	playerHealth= new JProgressBar();
	playerHealth.setStringPainted(true);
	playerHealth.setBackground(Color.red);
	playerHealth.setString("100");
	playerHealth.addPropertyChangeListener(this);
	tm.start();
	
//	playerHealth.setBackground(new Background(Color.blue));
	//UIManager.put(playerHealth, Color.red);
	playerHealth.setBounds(0,100,50,20);
	add(playerHealth);
	}
	
//	@Override
//	public void paintComponents(Graphics g) {
//		// TODO Auto-generated method stub
//		super.paintComponents(g);
//	}
	
	public static void main(String[] args) {
		new BattleView();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("HELLO");
		playerHealth.setValue(progress);
		playerHealth.setString(Integer.toString(progress));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		progress-=20;
		
	}
} 
