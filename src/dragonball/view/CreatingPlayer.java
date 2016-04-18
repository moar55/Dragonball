package dragonball.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class CreatingPlayer extends JPanel {
	
	private JPanel below;
	private JButton ok;
	private JButton cancel;
	private JTextArea playerName; 
	private WorldFrame world;
	
	public CreatingPlayer() {
		setLayout(new GridLayout(3,1));
		  JLabel order = new JLabel("Please enter your player's name");
		  order.setHorizontalAlignment(SwingConstants.CENTER);
		  add(order);
		   playerName =new  JTextArea();
		  add(playerName);
		  below = new JPanel();
		  below.setLayout(new GridLayout(1, 2));
		     ok = new JButton("OK");
		   cancel = new JButton("Cancel");
		  
		  below.add(ok);
		  below.add(cancel);
		  add(below);
	
	}
	
	public void initializeButton(){
		 ok.addActionListener(world);
		 cancel.addActionListener(world);


	}
	
	
	
	public static void main(String[] args) {
		JFrame test = new JFrame();
		//test.setDefaultCloseOperation();
		CreatingPlayer testing = new CreatingPlayer();
		test.add(testing);
		test.setSize(600, 400);
		test.setVisible(true);
		
	}

	public JPanel getBelow() {
		return below;
	}

	public JButton getOk() {
		return ok;
	}

	public JButton getCancel() {
		return cancel;
	}

	public JTextArea getPlayerName() {
		return playerName;
	}

	public void setWorld(WorldFrame world) {
		this.world = world;
	}
	
	
}
