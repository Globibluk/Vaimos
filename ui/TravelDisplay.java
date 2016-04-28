package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import boat.Boat;
import sim.World;

public class TravelDisplay extends JPanel {

	private static final long serialVersionUID = 1L;

	private World world;
	private int height;
	private int width;
	
	private JFrame frame;
	
	private boolean state = false;
	
	private Image spriteRaw;
	private Image spriteScan;
	private Image spriteBoat;
	private Image spriteRock;
	
	private int xSpriteLength = 8;
	private int ySpriteLength = 8;
	
	private Boat boat;
	
	public TravelDisplay(World world) 
	{	
		this.world = world;
		height = world.getWorldHeight();
		width = world.getWorldLength();
		
		frame = new JFrame();		
	    frame.setTitle("Travel");
	    frame.setSize(height * xSpriteLength, width * ySpriteLength);
	    
	    frame.add(this);
	    
	    frame.setFocusable(true);
	    frame.setVisible(state);
	    boat = world.getBoat();
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
		try
		{
			spriteBoat = ImageIO.read(new File("./src/ressources/boat.png"));
		} catch (Exception e)
		{
			System.out.println("Sprite non loaded :" + "boat");
		}
		try
		{
			spriteRock = ImageIO.read(new File("./src/ressources/rock.png"));
		} catch (Exception e)
		{
			System.out.println("Sprite non loaded :" + "rock");
		}
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		int tempHeight = frame.getHeight();
		int tempWidth = frame.getWidth();
		
		xSpriteLength = tempWidth / width;
		ySpriteLength = tempHeight / height;
		
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				if(world.getGrid()[i][j].getDecouvert())
				{
				g2.drawImage(spriteScan, xSpriteLength * i, ySpriteLength * j,
						xSpriteLength, ySpriteLength, this);
				}
				else
				{
				g2.drawImage(spriteRaw, xSpriteLength * i, ySpriteLength
						* j, xSpriteLength, ySpriteLength, this);
				}
				if(world.getGrid()[i][j].getProfondeur()<=0)
				{
					g2.drawImage(spriteRock, xSpriteLength * i, ySpriteLength
							* j, xSpriteLength, ySpriteLength, this);
				}
			}
		}
		g2.drawImage(spriteBoat, xSpriteLength * (boat.getIPosX()-1), ySpriteLength * (boat.getIPosY()-1), xSpriteLength+16, ySpriteLength+16, this);		
	}
	
	public void switchState()
	{
		state = !state;
		frame.setVisible(state);
	}	
}