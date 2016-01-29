package sim;

import java.util.ArrayList;

public class World {

	private ArrayList<Cell> grid = new ArrayList<Cell>();
	private static int worldHeight = 10;
	private static int worldLength = 10;
	
	public World()
	{
		populateGrid(worldHeight, worldLength);
	}
	
	public World(int height, int length)
	{
		populateGrid(height, length);
	}
	
	public void populateGrid(int height, int length)
	{
		int size = height*length;
		for(int i=0;i<size;i++)
		{
			grid.add(new Cell());
		}
	}
}
