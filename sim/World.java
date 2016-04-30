package sim;


import boat.Boat;
import tools.Vector2D;


public class World {

	private Cell[][] grid;
	private static int worldHeight;
	private static int worldLength;
	private static float maxWorldDepth;
	
	private Boat boat;
	
	public World(int height, int length)
	{
		grid = new Cell[height][length];
		worldHeight = height;
		worldLength = length;
		maxWorldDepth = height;
		populateGrid(height, length);
	}
	
	public void populateGrid(int height, int length)
	{
		
		float prof = 0;
		Vector2D vent = new Vector2D();
		Vector2D courant = new Vector2D();
		float xtmp = (float)( Math.random()*1 ) - (float)0.5;
		if(xtmp > 1) xtmp = 1;
		if(xtmp < -1) xtmp = -1;
		float ytmp = (float) Math.sqrt(1 - (xtmp * xtmp));
		if(Math.random() < 0.5) ytmp = -ytmp;
		xtmp=(float)-0.7;
		ytmp=(float)-0.7;
		vent.setX(xtmp);
		vent.setY(ytmp);
		courant.setX(xtmp);
		courant.setY(ytmp);
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<length;j++)
			{
				prof = worldHeight-i-(worldHeight/100*10);
				grid[i][j] = new Cell(vent, courant, prof);
			}
		}
	}
	
	public float getMaxWorldDepth() {
		return maxWorldDepth;
	}

	public void changementVent(){
		Vector2D newvent = new Vector2D();
		float addx =(float)( Math.random()*1 ) - (float)0.5;
		float xtmp = grid[0][0].getVent().getX()+addx;
		if(xtmp > 1) xtmp = 1;
		if(xtmp < -0.12) xtmp = (float)0.12;
		float ytmp = (float) Math.sqrt(1 - (xtmp * xtmp));
		if(Math.random() < 0.5) ytmp = -ytmp;
		newvent.setX(xtmp);
		newvent.setY(ytmp);
		ytmp *= ytmp;
		xtmp *= xtmp;
		Vector2D newcourant = new Vector2D(xtmp, ytmp);
		for(int i=0;i<worldHeight;i++)
		{
			for(int j=0;j<worldLength;j++)
			{
				grid[i][j].setVent(newvent);
				grid[i][j].setCourant(newcourant);
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