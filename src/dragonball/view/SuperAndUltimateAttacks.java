package dragonball.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dragonball.controller.GGEvent;


public class SuperAndUltimateAttacks extends JPanel implements ActionListener{
	
	private	JLabel superAttacks ;
	private JLabel ultimateAttacks;
	private int chosenSuperAttacks=0 ;
	private ArrayList<JButton> listofSuper;
	private ArrayList<JButton> listofUltimate;
	private int chosenUltimateAttacks=0 ;
	private WorldFrame world;
	
	public SuperAndUltimateAttacks() {
		
		setLayout(new GridLayout(3, 0));
		JLabel heading  = new JLabel("List of Super and Ultimate Attacks")	;
		heading.setFont(new Font("Times New Roman", 10, 10));
		add(heading);
		superAttacks = new JLabel();
		superAttacks.setLayout(new FlowLayout());
		superAttacks.add(new JLabel("<html><center>"+"Super Attacks:"+"<br>"+"(Consumes 1 KI bar)"+"</center></html>"));
		ultimateAttacks = new JLabel();
		ultimateAttacks.setLayout(new FlowLayout());
		ultimateAttacks.add(new JLabel("<html><center>"+"Ultimate Attacks:"+"<br>"+"(Consumes 3 KI bars)"+"</center></html>"));
		
	}
	
	public void addSuperAttack( JButton attack){
		superAttacks.add(attack);
		listofSuper.add(attack);
	}
	
	public void addUltimateAttack(JButton attack) {
		ultimateAttacks.add(attack);
		listofUltimate.add(attack);
	}


	public int getChosenSuperAttacks() {
		return chosenSuperAttacks;
	}


	public int getChosenUltimateAttacks() {
		return chosenUltimateAttacks;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JButton)(JButton)e.getSource()).getParent() == superAttacks){
		if(!((JButton)e.getSource()).getBackground().equals(Color.gray)){
			if(chosenSuperAttacks<4){
			((JButton)e.getSource()).setBackground(Color.gray);
			 chosenSuperAttacks++;
			 world.onEvent(new GGEvent(this,((JButton)(e.getSource())).getText(),FightersList.findIndex((JButton)e.getSource(), listofSuper)));
			}
			else JOptionPane.showMessageDialog(this, "You can't Select more than 4 super attacks!");
		}
			 else
				 chosenSuperAttacks--;
		
		}
		
		
		else if(((JButton)(JButton)e.getSource()).getParent() == ultimateAttacks){
			if(!((JButton)e.getSource()).getBackground().equals(Color.gray)){
				if(chosenUltimateAttacks<2){
				((JButton)e.getSource()).setBackground(Color.gray);
				 chosenUltimateAttacks++;
				}
				else JOptionPane.showMessageDialog(this, "You can't Select more than 2 ultimate attacks!");
			}
				 else
					 chosenUltimateAttacks--;
			
			}
		
	}


	public void setWorld(WorldFrame world) {
		this.world = world;
	}
	
	
	
}
