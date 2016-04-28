package ui;

import java.util.ArrayList;

import javax.swing.JPanel;

public class Repainter extends Thread {
	
	private ArrayList<JPanel> panelList;
	private boolean running;
	
	public Repainter()
	{
		panelList = new ArrayList<JPanel>();
		running = true;
	}
	
	public void registerPanel(JPanel frame)
	{
		panelList.add(frame);
	}
	
	public void run()
	{
		while(running)
		{
			for(JPanel panel : panelList)
				panel.repaint();
		}
	}
	
	public void end()
	{
		running = false;
	}
}
