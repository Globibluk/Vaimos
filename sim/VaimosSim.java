package sim;

public class VaimosSim {

	public static void main(String[] args)
	{
		World world = new World(50, 50);
		Display display = new Display(world);
		while(true)
		{
			display.repaint();
		}
	}
	
}
