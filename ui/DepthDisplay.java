package ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import boat.Boat;

public class DepthDisplay extends JPanel{

	private static final long serialVersionUID = 1L;

	private int height;
	private int width;
	private Boat vaimos;
	
	private JFrame frame;
	
	private int xSpriteLength = 8;
	private int ySpriteLength = 8;
	
	private boolean state = false;
	
	
	public DepthDisplay(Boat vai) 
	{	
		vaimos = vai;
		height = vaimos.getWorld().getWorldHeight();
		width = vaimos.getWorld().getWorldLength();
		
		frame = new JFrame();
	    frame.setTitle("Depth");
	    frame.setSize(height * xSpriteLength, width * ySpriteLength);
	    
	    frame.add(this);
	    
	    frame.setFocusable(true);
	    frame.setVisible(state);
		
	}

	public void paint(Graphics g)
	{
		int tempHeight = frame.getHeight();
		int tempWidth = frame.getWidth();
		
		xSpriteLength = tempWidth / width;
		ySpriteLength = tempHeight / height;
		
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				if(vaimos.getGridXY(i, j) != null)
				{
					double prof = (int) vaimos.getGridXY(i, j).getProfondeur();
					if(prof==0)
					{
						g.setColor(new Color(0, 255, 0));
						g.fillRect(i * xSpriteLength, j * ySpriteLength, xSpriteLength, ySpriteLength);
					}
					else
					{
						prof = (prof / vaimos.getWorld().getMaxWorldDepth()) * 255;
						if(prof < 0) prof = 0;
						if(prof > 255) prof = 255;
						g.setColor(new Color((int)prof, 0,(int)(255 - prof)));
						g.fillRect(i * xSpriteLength, j * ySpriteLength, xSpriteLength, ySpriteLength);
					}
				}
				else
				{
					g.setColor(new Color(255, 255, 255));
					g.fillRect(i * xSpriteLength, j * ySpriteLength, xSpriteLength, ySpriteLength);
				}
			}
		}
		
	}
	
	public void switchState()
	{
		state = !state;
		frame.setVisible(state);
	}	
}