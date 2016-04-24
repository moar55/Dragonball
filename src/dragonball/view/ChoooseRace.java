package dragonball.view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dragonball.controller.GGEvent;
import dragonball.view.FightersList;
public class ChoooseRace extends JPanel implements ActionListener{
	
	private WorldFrame world;
	private ArrayList<JButton>  races;
	
	
	public ChoooseRace() {
		setLayout(new GridLayout(1, 5));
		races = new ArrayList<JButton>();
	}
	
	
	public void populate(ArrayList<JButton> buttons){
		for(JButton button: buttons){
			add(button);
			races.add(button);
			button.addActionListener(this);
		}
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		add(cancel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		world.onEvent(new GGEvent(this, (((JButton)(e.getSource())).getText()),FightersList.findIndex((JButton)e.getSource(),races)));
		
	}
	
	


	public static void main(String[] args) {
		JFrame jf =new JFrame("YO");
		ChoooseRace cr = new ChoooseRace();
		jf.add(cr);
		jf.setSize(1208, 720);
		jf.setVisible(true);
	}
	
	public void setWorld(WorldFrame world) {
		this.world = world;
	}
	
	
	
}
