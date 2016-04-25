package ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import boat.Boat;

public class DepthDisplay extends JPanel{

	private static final long serialVersionUID = 1L;

	private int height;
	private int length;
	private Boat vaimos;
	private JFrame frame;
	private int spriteLength = 8;
	
	private boolean state = true;
	
	
	public DepthDisplay(Boat vai) 
	{	
		vaimos = vai;
		height = vaimos.getWorld().getWorldHeight();
		length = vaimos.getWorld().getWorldLength();
		
		frame = new JFrame();
	    frame.setTitle("Projet VAIMOS");
	    frame.setSize(height * spriteLength, length	* spriteLength);
	    frame.add(this);
	    frame.setVisible(state);
		setFocusable(true);
	}

	public void paint(Graphics g)
	{
		
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<length;j++)
			{
				if(vaimos.getGridXY(i, j) != null)
				{
					double prof = (int) vaimos.getGridXY(i, j).getProfondeur();
					if(prof==0)
					{
						g.setColor(new Color(0, 255, 0));
						g.fillRect(i * spriteLength, j * spriteLength, spriteLength, spriteLength);
					}
					else
					{
						prof = (prof / maxprof) * 255;
						g.setColor(new Color((int)prof, 0,(int)(255 - prof)));
						g.fillRect(i * spriteLength, j * spriteLength, spriteLength, spriteLength);
					}
				}
				else
				{
					g.setColor(new Color(255, 255, 255));
					g.fillRect(i * spriteLength, j * spriteLength, spriteLength, spriteLength);
				}
				
			}
		}
		
	}
	
	public void switchState()
	{
		state = !state;
		frame.setVisible(state);
	}
	
	public void addNotify() {
        super.addNotify();
        requestFocus();
    }
	
}
