package sim;

import java.awt.KeyboardFocusManager;
import java.io.File;

import boat.Boat;
import interpreter.Interpreter;
import interpreter.Loader;
import ui.DepthDisplay;
import ui.Repainter;
import ui.TravelDisplay;
import ui.VaimosKeyEventDispatcher;

public class Simulation extends Thread {
	
	private World world;
	private Boat boat;
	
	private int delai;
	
	private Interpreter interpreter;
	
	private File file;
	
	private TravelDisplay travelDisplay;
	private DepthDisplay depthDisplay;
	private KeyboardFocusManager manager;
	
	private Repainter repainter;
	
	public Simulation(File file)
	{
		world = new World(100, 100);
		boat = new Boat(85, 50, world);
		//boat = new Boat(50, 50, world);
		delai = 0;
		
		travelDisplay = new TravelDisplay(world);
		depthDisplay = new DepthDisplay(boat);
		manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new VaimosKeyEventDispatcher(depthDisplay));
        
        repainter = new Repainter();       
        repainter.registerPanel(travelDisplay);
        repainter.registerPanel(depthDisplay);
		
		this.file = file;
		
	}
	
	public boolean setup()
	{
        
        interpreter = new Interpreter();
        Loader loader = new Loader(interpreter);
        interpreter.setBoat(boat);
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
	
	public void run()
	{
		
		travelDisplay.switchState();
		depthDisplay.switchState();
		boat.setWind(world.getCellXY(0, 0).getVent());
		try
        {
        	repainter.start();
        }
        catch (Exception e)
        {
        	System.out.println("UI error");
        }
		
        try
        {
        	interpreter.start();
        }
        catch (Exception e)
        {
        	System.out.println("Runtime error");
        }        
        
        while(true)
		{}
	}

}