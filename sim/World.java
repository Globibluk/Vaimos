package sim;

public class World {

	private Cell[][] grid;
	private static int worldHeight;
	private static int worldLength;
	
	private Boat boat;
	
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
	
	public void changementVent(){
		for(int i=0;i<worldHeight;i++)
		{
			for(int j=0;j<worldLength;j++)
			{
				grid[i][j].setVent();
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
		this.boat = boat;
	}
	
	public Boat getBoat()
	{
		return boat;
	}
	
}
