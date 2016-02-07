package sim;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends JPanel{

	private World world;
	private int height;
	private int length;
	
	private JFrame frame;
	
	private Image spriteRaw;
	private Image spriteScan;
	private int spriteLength = 16;
	
	public Display(World world) 
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
			spriteRaw = ImageIO.read(new File("./src/ressources/water_raw.png"));
		} catch (Exception e)
		{
			System.out.println("Sprite non loaded :" + "water_raw");
		}
		
		try
		{
			spriteScan = ImageIO.read(new File("./src/ressources/water_scan.png"));
		} catch (Exception e)
		{
			System.out.println("Sprite non loaded :" + "water_scan");
		}
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<length;j++)
			{
				if(world.getGrid()[i][j].getDecouvert())
				{
				g2.drawImage(spriteScan, spriteLength * i, spriteLength * j,
						spriteLength, spriteLength, frame);
				}
				else
				{
				g2.drawImage(spriteRaw, spriteLength * i, spriteLength
						* j, spriteLength, spriteLength, frame);
				}
			}
		}
		
	}
	
}