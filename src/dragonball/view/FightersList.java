package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.sun.corba.se.spi.orbutil.fsm.Action;

import dragonball.controller.GGEvent;
import dragonball.controller.GameGUI;
import dragonball.model.world.World;

public class FightersList extends JPanel implements ActionListener{
	
	private ArrayList<JPanel> fighters;
	private WorldFrame world;
	private ArrayList<JButton> select;
	private ArrayList<JButton> upgrade;
	private JPanel list ;
	public FightersList() {
		
		setLayout(new GridLayout(0, 1));
		JLabel title = new JLabel("List of Fighters");
		title.setFont(new Font("Times New Roman",23, 23));
		title.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		add(title);
		select = new ArrayList<JButton>();

		upgrade = new ArrayList<JButton>();
		
		
	}
	
	public void populate(ArrayList<JPanel> fightersPanels) {
		
		for(JPanel panel : fightersPanels){
			panel.setBorder(BorderFactory.createBevelBorder(50));
			add(panel);
		}
		
		
		
		JButton createFighter = new JButton("Create a Fighter");
		JButton back = new JButton("Back to game");
		createFighter.addActionListener(this);
		back.addActionListener(this);
		JPanel last = new JPanel();
		last.setLayout(new GridLayout(1, 2));
		last.add(createFighter);
		last.add (back);
		add(last);
		validate();
		repaint();
	}
	
	public void addFighter (JPanel fighter){
		remove(getComponentCount()-1);
		add(fighter);
		JButton createFighter = new JButton("Create a Fighter");
		JButton back = new JButton("Back to game");
		createFighter.addActionListener(this);
		back.addActionListener(this);
		JPanel last = new JPanel();
		last.setLayout(new GridLayout(1, 2));
		last.add(createFighter);
		last.add (back);
		add(last);
	}


	public ArrayList<JPanel> getFighters() {
		return fighters;
	}


	public WorldFrame getWorld() {
		return world;
	}


	public void setWorld(WorldFrame world) {
		this.world = world;
	}


	public void setFighters(ArrayList<JPanel> fighters) {
		this.fighters = fighters;
	}
	
	public static void main(String[] args) throws IOException {
		JFrame jf = new JFrame();
		jf.setSize(1280,720);
		FightersList cf = new FightersList();
		jf.add(cf);
		jf.setVisible(true);
//		GameGUI controller = new GameGUI();
//		cf.setController(controller);
	//	jf.setDefaultCloseOperation(exit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("HI");
		
		if(((JButton)e.getSource()).getText().equals("Select"))
		world.onEvent(new GGEvent(this, ((JButton)e.getSource()).getText(),findIndex((JButton)e.getSource(),select)));
		
		else if(((JButton)e.getSource()).getText().equals("Upgrde"))
			world.onEvent(new GGEvent(this, ((JButton)e.getSource()).getText(),findIndex((JButton)e.getSource(),upgrade)));
		
		else
			world.onEvent(new GGEvent(this, ((JButton)e.getSource()).getText()));
			
	}	
	
	public static int findIndex (JButton x , ArrayList <JButton> arrayList) {
		for(int i = 0 ;i<arrayList.size();i++ ){
			if(x==arrayList.get(i)){
				System.out.println(i);
				return i ;
			}
		}
		return -1;
	}
	
	public ArrayList<JButton> getSelect() {
		return select;
	}
	
	

	public void setSelect(ArrayList<JButton> select) {
		this.select = select;
	}

	public void setUpgrade(ArrayList<JButton> upgrade) {
		this.upgrade = upgrade;
	}

	public ArrayList<JButton> getUpgrade() {
		return upgrade;
	}
	
	
	
	
	
	
}
