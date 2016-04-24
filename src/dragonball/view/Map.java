package dragonball.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class Map extends JPanel {

	/**
	 * The actual map where you move around 
	 */
	private static final long serialVersionUID = 1L;
	private Image currentFighter ;
	private Image boss;

	public Map() throws IOException {
		// img=ImageIO.read(new File("Grass.jpg"))
//		this.add(j);
//		this.setPreferredSize(new Dimension(800,600));
		
		
		setLayout(new GridLayout(10, 10));
		
		JLabel tiles [] = new JLabel[100];
		for (int i = 0; i < 100; i++) {
			tiles[i]= new JLabel();
			tiles[i].setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.white));
			tiles[i].setIcon(new ImageIcon("Grass.jpg"));
			add(tiles[i]);
		}
		currentFighter= ImageIO.read(new File("goku_sprite.png"));
	}
//	 @Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		g.drawImage(ImageIO.read(new File("Grass.jpg")), 20, 20, null)
//		g.drawImage(currentFighter, 20, 20, null);
//	}

	public Image getCurrentFighter() {
		return currentFighter;
	}

	public Image getBoss() {
		return boss;
	}
	
	public static void main(String[] args) throws IOException {
		JFrame jf = new JFrame("YO");
		jf.setVisible(true);
		jf.setSize(1280, 720);
		Map m = new Map();
		m.setVisible(true);
		jf.add(m);
		jf.repaint();
		jf.validate();
		//jf.setDefaultCloseOperation();
	}
	
	
	
	
	
	
}
