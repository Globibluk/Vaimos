package ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import boat.Boat;

public class AverageDepthDisplay extends JPanel {

	private static final long serialVersionUID = 1L;

	private int height;
	private int width;
	private Boat vaimos;
	
	private JFrame frame;
	
	private int xSpriteLength;
	private int ySpriteLength;
	
	private float hpas;
	private float lpas;
	
	private boolean state = false;
	
	
	public AverageDepthDisplay(Boat vai, double hpas, double lpas) 
	{	
		vaimos = vai;
		height = vaimos.getWorld().getWorldHeight();
		width = vaimos.getWorld().getWorldLength();
		xSpriteLength = (int)hpas;
		ySpriteLength = (int)lpas;
		this.hpas=(float) hpas;
		this.lpas=(float) lpas;
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
		int i = 10;
		while(i<height)
		{
			int j = 10;
			while(j<width)
			{
				if(vaimos.getGridXY(i, j) != null)
				{
					double prof=0;
					int cpt=0;
					for(int u=(int) -hpas; u <= hpas; u++)
					{
						for(int v=(int) -lpas; v <= lpas; v++)
						{
							if(vaimos.getGridXY(i+u, j+u) != null)
							{
								prof = (int) vaimos.getGridXY(i+u, j+v).getProfondeur();
								cpt++;
							}	
						}
					}
					if(cpt!=0)
					{
						prof=prof/cpt;
					}
					else
					{
						prof=0;
					}
					if(prof==0)
					{
						g.setColor(new Color(0, 0, 0));
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
					g.setColor(new Color(0, 0, 0));
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
