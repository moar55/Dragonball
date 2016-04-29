package dragonball.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dragonball.controller.GGEvent;
import dragonball.model.attack.SuperAttack;
import dragonball.model.character.fighter.PlayableFighter;


public class SuperAndUltimateAttacks extends JPanel implements ActionListener{
	
	private	JLabel superAttacks ;
	private JLabel ultimateAttacks;
	private int chosenSuperAttacks=0 ;
	private ArrayList<JButton> listofSuper;
	private ArrayList<JButton> listofUltimate;
	private int chosenUltimateAttacks=0 ;
	private WorldFrame world;
	private PlayableFighter fighter;
	
	public SuperAndUltimateAttacks(PlayableFighter fighter) {
		this.fighter=fighter;
		setLayout(new GridLayout(4,0));
		JLabel heading  = new JLabel("List of Super and Ultimate Attacks")	;
		heading.setFont(new Font("Times New Roman", 30, 30));
		heading.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		add(heading);
		superAttacks = new JLabel();
		superAttacks.setLayout(new FlowLayout());
		superAttacks.add(new JLabel("<html><center>"+"Super Attacks:"+"<br>"+"(Consumes 1 KI bar)"+"</center></html>"));
		ultimateAttacks = new JLabel();
		ultimateAttacks.setLayout(new FlowLayout());
		ultimateAttacks.add(new JLabel("<html><center>"+"Ultimate Attacks:"+"<br>"+"(Consumes 3 KI bars)"+"</center></html>"));
		add(superAttacks);
		add(ultimateAttacks);
		JButton close = new JButton("close");
		close.addActionListener(this);
		add(close);
		listofSuper = new ArrayList<>();
		listofUltimate = new ArrayList<>();
	}
	
	public void addSuperAttack( JButton attack,boolean used){
		attack.addActionListener(this);
		
		if(used){
		attack.setBackground(Color.gray);
		chosenSuperAttacks++;
		}
		
		else
			attack.setBackground(Color.blue);
		
		superAttacks.add(attack);
		listofSuper.add(attack);
	}
	
	public void addUltimateAttack(JButton attack,boolean used) {
		attack.addActionListener(this);
		if(used){
		attack.setBackground(Color.gray);
		chosenSuperAttacks--;
		}
		
		else
			attack.setBackground(Color.blue);
		
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
		
		
			
		
		if(((JButton)e.getSource()).getText().equals("close"))
			world.setAttacks(fighter);
		
		
		else if(((JButton)(JButton)e.getSource()).getParent() == superAttacks){
			System.out.println("It enters");
		if(!((JButton)e.getSource()).getBackground().equals(Color.gray)){
			if(chosenSuperAttacks<4){
			((JButton)e.getSource()).setBackground(Color.gray);
			 chosenSuperAttacks++;
			 repaint();
			 validate();
			}
			else JOptionPane.showMessageDialog(this, "You can't Select more than 4 super attacks!");
		}
			 else{
				 ((JButton)e.getSource()).setBackground(Color.blue);
				 chosenSuperAttacks--;
			 }
		
		}
		
		
		else if(((JButton)(JButton)e.getSource()).getParent() == ultimateAttacks){
			if(!((JButton)e.getSource()).getBackground().equals(Color.gray)){
				if(chosenUltimateAttacks<2){
				((JButton)e.getSource()).setBackground(Color.gray);
				 chosenUltimateAttacks++;
				 repaint();
				 validate();

				}
				else JOptionPane.showMessageDialog(this, "You can't Select more than 2 ultimate attacks!");
			}
				 else{
						((JButton)e.getSource()).setBackground(Color.blue);				 
					 chosenUltimateAttacks--;
				 }
			}
		
	}


	public void setWorld(WorldFrame world) {
		this.world = world;
	}

	public JLabel getSuperAttacks() {
		return superAttacks;
	}

	public JLabel getUltimateAttacks() {
		return ultimateAttacks;
	}

	public ArrayList<JButton> getListofSuper() {
		return listofSuper;
	}

	public ArrayList<JButton> getListofUltimate() {
		return listofUltimate;
	}

	public WorldFrame getWorld() {
		return world;
	}

	public PlayableFighter getFighter() {
		return fighter;
	}

	public void setFighter(PlayableFighter fighter) {
		this.fighter = fighter;
	}
	
	
	
}
