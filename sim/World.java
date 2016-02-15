package sim;

public class World {

	private Cell[][] grid;
	private static int worldHeight;
	private static int worldLength;
	
	private static Boat boat;
	
	public World(int height, int length)
	{
		grid = new Cell[height][length];
		worldHeight = height;
		worldLength = length;
		populateGrid(height, length);
	}
	
	public void populateGrid(int height, int length)
	{
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<length;j++)
			{
				grid[i][j] = new Cell();
			}
		}
	}
	
	public Cell[][] getGrid()
	{
		return grid;
	}
	
	public Cell getCellXY(int x, int y)
	{
		return grid[x][y];
	}
	
	public int getWorldHeight()
	{
		return worldHeight;
	}
	
	public int getWorldLength()
	{
		return worldLength;
	}
	
	public void setBoat(Boat boat)
	{
		this.boat = boat;     										// Warnning ? 
	}
	
	public Boat getBoat()
	{
		return boat;
	}
	
}
