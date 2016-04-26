package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import boat.Boat;
import sim.World;

public class TravelDisplay extends JFrame {

	private static final long serialVersionUID = 1L;

	private World world;
	private int height;
	private int length;
	
	private boolean state = false;
	
	private Image spriteRaw;
	private Image spriteScan;
	private Image spriteBoat;
	private Image spriteRock;
	private int spriteLength = 8;
	
	private Boat boat;
	
	public TravelDisplay(World world) 
	{	
		this.world = world;
		height = world.getWorldHeight();
		length = world.getWorldLength();
		
	    setTitle("Travel");
	    setSize(height * spriteLength, length	* spriteLength);
	    setVisible(state);
		setFocusable(true);
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
		
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<length;j++)
			{
				if(world.getGrid()[i][j].getDecouvert())
				{
				g2.drawImage(spriteScan, spriteLength * i, spriteLength * j,
						spriteLength, spriteLength, this);
				}
				else
				{
				g2.drawImage(spriteRaw, spriteLength * i, spriteLength
						* j, spriteLength, spriteLength, this);
				}
				if(world.getGrid()[i][j].getProfondeur()<=0)
				{
					g2.drawImage(spriteRock, spriteLength * i, spriteLength
							* j, spriteLength, spriteLength, this);
				}
			}
		}
		g2.drawImage(spriteBoat, (int) (spriteLength * boat.getFPosX()), (int) (spriteLength * boat.getFPosY()), spriteLength + 16, spriteLength + 16, this);		
	}
	
	public void switchState()
	{
		state = !state;
		setVisible(state);
	}
	
	public void addNotify() {
        super.addNotify();
        requestFocus();
    }
	
}
