package dragonball.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import dragonball.controller.GGEvent;

public class CreatingPlayer extends JPanel implements ActionListener {
	
	private JPanel below;
	private JButton ok;
	private JButton cancel;
	private JTextArea playerName; 
	private WorldFrame world;
	
	public CreatingPlayer() {
		setLayout(new GridLayout(3,1));
		  JLabel order = new JLabel("New Player");
		  order.setHorizontalAlignment(SwingConstants.CENTER);
		  add(order);
		   playerName =new  JTextArea(1,50);
		   playerName.setText("Please enter your player's name: \n");
		   playerName.setLineWrap(true);
		   
		  add(playerName);
		  
		  below = new JPanel();
		  below.setLayout(new GridLayout(1, 2));
		     ok = new JButton("OK");
		   cancel = new JButton("Cancel");
		  ok.addActionListener(this);
		  cancel.addActionListener(this);
		  below.add(ok);
		  below.add(cancel);
		  add(below);
	
	} 
	

	public static void main(String[] args) {
		JFrame test = new JFrame();
		//test.setDefaultCloseOperation();
		CreatingPlayer testing = new CreatingPlayer();
		test.add(testing);
		test.setSize(600, 400);
		test.setVisible(true);
		test.pack();
		
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

	public String getPlayerName() {
		return playerName.getText().substring(playerName.getText().lastIndexOf("\n"));
	}
	
	public String getJtextPlayerName() {
		return playerName.getText();
	}

	public void setWorld(WorldFrame world) {
		this.world = world;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//new GGEvent(this, ((JButton)e.getSource()).getText());
		world.onEvent(new GGEvent(this, ((JButton)e.getSource()).getText()));
		
	}


	public WorldFrame getWorld() {
		return world;
	}
	
	
	
	
}
