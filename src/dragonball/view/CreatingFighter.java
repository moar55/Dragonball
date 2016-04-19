package dragonball.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class CreatingFighter extends JPanel{
	
	private ArrayList<JPanel> fighters;
	
	
	public CreatingFighter() {
		setLayout(new GridLayout(0, 1));
		
		
	}


	public ArrayList<JPanel> getFighters() {
		return fighters;
	}
	
	
}
