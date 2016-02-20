package sim;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AffichageVecteur extends JPanel{

	private static final long serialVersionUID = 1L;

	private World world;
	private int height;
	private int length;
	
	private JFrame frame;
	private Image Spritevecteur;
	private int spriteLength = 16;
	
	
	public AffichageVecteur(World world) 
	{	
		this.world = world;
		height = world.getWorldHeight();
		length = world.getWorldLength();
		
		frame = new JFrame();
	    frame.setTitle("Projet VAIMOS");
	    frame.setSize(height * spriteLength, length	* spriteLength);
	    frame.add(this);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    frame.setVisible(true);
	    loadSprites();	    
	}
	
	public void loadSprites()
	{
		try
		{
			Spritevecteur = ImageIO.read(new File("./src/ressources/vecteur.png"));
		} catch (Exception e)
		{
			System.out.println("Sprite non loaded");
		}
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<length;j++)
			{
				//g2.rotate(Math.toRadians(50), 100, 100);
				g2.drawImage(Spritevecteur, spriteLength * i, spriteLength * j,
						spriteLength, spriteLength, frame);
			}
		}
		
	}
	
}