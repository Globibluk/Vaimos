package sim;

import java.awt.KeyboardFocusManager;
import java.io.File;

import boat.Boat;
import boat.BoatController;
import boat.SquareController;
import interpreter.Interpreter;
import interpreter.Loader;
import ui.DepthDisplay;
import ui.TravelDisplay;
import ui.VaimosKeyEventDispatcher;

public class Simulation {
	
	private World world;
	private Boat boat;
	
	private int delai;
	private int value;
	
	Interpreter interpreter;
	
	BoatController bc;
	File file;
	
	public Simulation(File file)
	{
		world = new World(100, 100);
		boat = new Boat(15, 15, world);
		delai = 50;
		value = 0;
		
		bc = new SquareController(boat);
		this.file = file;
	}
	
	public boolean setup()
	{
        
        interpreter = new Interpreter();
        Loader loader = new Loader(interpreter);
        try
        {
        	loader.load(file);
        }
        catch (Exception e)
        {
        	System.out.println("Can't load program");
        	return false;
        }
        
        return true;
	}
	
	public void launch()
	{
		TravelDisplay travelDisplay = new TravelDisplay(world);
		DepthDisplay depthDisplay = new DepthDisplay(boat);
		
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new VaimosKeyEventDispatcher(depthDisplay));
        
        /*try
        {
        	interpreter.run();
        }
        catch (Exception e)
        {
        	System.out.println("Runtime error");
        }*/
        		
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
