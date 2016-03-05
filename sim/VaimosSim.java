package sim;

import java.awt.KeyboardFocusManager;

import ui.DepthDisplay;
import ui.TravelDisplay;
import ui.VaimosKeyEventDispatcher;

public class VaimosSim {

	public static void main(String[] args)
	{
		World world = new World(120, 120);
		Boat boat = new Boat(1, 1, world);
		TravelDisplay display = new TravelDisplay(world);
		int delai = 100;
		
		DepthDisplay bol = new DepthDisplay(boat);
		
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new VaimosKeyEventDispatcher(bol));
		
		while(true)
		{
			display.repaint();
			bol.repaint();
			boat.analyseCell();
			
			boat.calculate();
			
			
	/*		if(Math.random() >= .90)
			{
				world.changementVent();
			}
		*/	
			try {
				Thread.sleep(delai);
			} catch (InterruptedException e) {
			}
			
		}
	}	
}