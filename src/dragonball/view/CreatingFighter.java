package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dragonball.controller.GGEvent;
import dragonball.model.character.fighter.PlayableFighter;

public class CreatingFighter extends JPanel  implements ActionListener{
	
	/**
	 * A panel for creating a Fighter
	 */
	
	private static final long serialVersionUID = 1L;
	private WorldFrame world;
	private PlayableFighter fighter;
	private JTextArea nameofPlayer;
	public CreatingFighter() {
		setLayout(new GridLayout(4, 0));
		JLabel heading = new JLabel("Create a New Fighter");
		heading.setFont(new Font("Times New Roman", 20, 20));
		heading.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		add(heading,CENTER_ALIGNMENT);
		JLabel first = new JLabel();
		first.setLayout(new GridLayout(1,2));
		JLabel name = new JLabel("Name");
		first.add(name);
		 nameofPlayer = new JTextArea(1,20);
		first.add(nameofPlayer); 
		JButton race = new JButton("Fighter's Race:");
		add(first);
		race.addActionListener(this);
		add(race);
		JLabel last = new JLabel();
		last.setLayout(new GridLayout(1, 2));
		JButton ok = new JButton("Save");
		ok.addActionListener(this);
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		last.add(ok);
		last.add(cancel);
		add(last);
	}

	public WorldFrame getWorld() {
		return world;
	}

	public void setWorld(WorldFrame world) {
		this.world = world;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		world.onEvent(new GGEvent(this, (((JButton)(e.getSource())).getText())));
		
	}

	public PlayableFighter getFighter() {
		return fighter;
	}
	
	
	public static void main(String[] args) {
		JFrame jf =new JFrame("YO");
		CreatingFighter cf = new CreatingFighter();
		jf.add(cf);
		jf.setSize(1208, 720);
		jf.setVisible(true);
	}

	public JTextArea getNameofPlayer() {
		return nameofPlayer;
	}

	public void setFighter(PlayableFighter fighter) {
		this.fighter = fighter;
	}
	
	
	
}
