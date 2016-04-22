package dragonball.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import dragonball.controller.GGEvent;

public class ChoooseRace extends JPanel implements ActionListener{
	
	private WorldFrame world;
	private ArrayList<JButton>  races;
	
	
	public ChoooseRace() {
		setLayout(new GridLayout(1, 5));
	}
	
	
	public void populate(ArrayList<JButton> buttons){
		for(JButton button: buttons)
			add(button);
		
		add(new JButton("Cancel"));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		world.onEvent(new GGEvent(this, (((JButton)(e.getSource())).getText())));
		
	}



	public void setWorld(WorldFrame world) {
		this.world = world;
	}
	
	
	
}
