package dragonball.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
	public CreatingFighter() {
		setLayout(new GridLayout(3, 0));
		JLabel first = new JLabel();
		first.setLayout(new BorderLayout());
		JLabel name = new JLabel("Name");
		first.add(name,BorderLayout.WEST);
		JTextArea nameofPlayer = new JTextArea();
		first.add(nameofPlayer,BorderLayout.EAST); 
		JButton race = new JButton("Fighter's Race:");
		add(first);
		add(race);
		JLabel last = new JLabel();
		last.setLayout(new GridLayout(1, 2));
		JButton ok = new JButton("OK");
		JButton cancel = new JButton("Cancel");
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
	
	
	
}
