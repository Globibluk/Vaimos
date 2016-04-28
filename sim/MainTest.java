package sim;

import java.io.File;

public class MainTest {
	
	public static void main(String args[])
	{
		System.out.println("eee");
		Simulation sim = new Simulation(new File("Prog"));
		System.out.println("eee");
		sim.setup();
		System.out.println("eee");
		sim.run();
	}
}