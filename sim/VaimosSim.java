package sim;

import java.awt.KeyboardFocusManager;

import boat.Boat;
import boat.BoatController;
import boat.SquareController;
import ui.DepthDisplay;
import ui.TravelDisplay;
import ui.VaimosKeyEventDispatcher;

public class VaimosSim {

	public static void main(String[] args)
	{
		World world = new World(100, 100);
		Boat boat = new Boat(15, 15, world);
		int delai = 50;
		int value = 0;
		
		BoatController bc = new SquareController(boat);
		
		TravelDisplay travelDisplay = new TravelDisplay(world);
		DepthDisplay depthDisplay = new DepthDisplay(boat);
		
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new VaimosKeyEventDispatcher(depthDisplay));
        		
		while(value != 4)
		{			
			value = bc.control(value);
			travelDisplay.repaint();
			depthDisplay.repaint();				
	
			try {
				Thread.sleep(delai);
			} catch (InterruptedException e) {}
			
		}
	}	
}