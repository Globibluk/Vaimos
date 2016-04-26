package sim;

import java.io.File;

public class MainTest {
	
	public static void main(String args[])
	{
		Simulation sim = new Simulation(new File("Prog"));
		sim.setup();
		sim.run();
	}
}