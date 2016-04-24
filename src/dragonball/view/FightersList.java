package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Panel;
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
	private ArrayList<JLabel> upgrade;
	private ArrayList<JPanel> stats;
	private JPanel list ;
	public FightersList() {
		
		setLayout(new GridLayout(0, 1));
		JLabel title = new JLabel("List of Fighters");
		title.setFont(new Font("Times New Roman",23, 23));
		title.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		add(title);
		select = new ArrayList<JButton>();

		upgrade = new ArrayList<JLabel>();
		
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
		
		if(getComponentCount()==0){
		JLabel title = new JLabel("List of Fighters");
		title.setFont(new Font("Times New Roman",23, 23));
		title.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		add(title);
		}
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
	
	public void resetSelectAndUpgrade (){
		upgrade = new ArrayList<JLabel>();
		select = new ArrayList<JButton>();
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
		
		else if(((JButton)e.getSource()).getText().equals("Back to game") || ((JButton)e.getSource()).getText().equals("Create a Fighter"))
			world.onEvent(new GGEvent(this, ((JButton)e.getSource()).getText()));

			
		else
			world.onEvent(new GGEvent(this, ((JButton)e.getSource()).getText(),findIndex((JLabel)((JButton)e.getSource()).getParent(),upgrade)));

			
	}	
	
	public static int findIndex (JButton x , ArrayList <JButton> arrayList) {
		for(int i = 0 ;i<arrayList.size();i++ ){
			if(x==arrayList.get(i)){
				//System.out.println(i);
				return i ;
			}
		}
		return -1;
	}
	
	public static int findIndex (JLabel x , ArrayList <JLabel> arrayList) {
		for(int i = 0 ;i<arrayList.size();i++ ){
			if(x==arrayList.get(i)){
				//System.out.println(i);
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

	public void setUpgrade(ArrayList<JLabel> upgrade) {
		this.upgrade = upgrade;
	}

	public ArrayList<JLabel> getUpgrade() {
		return upgrade;
	}
	
	
	
	
	
	
}
