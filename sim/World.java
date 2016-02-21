package sim;

import tools.Vector2D;

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
		
		float prof = 0;
		float vari;
		float moyprof;
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<length;j++)
			{
				if( i == 0 && j == 0)
				{
					grid[i][j] = new Cell();
				}
				else
				{
					vari = (float) (Math.random() * 20) - 10;
					if(i == 0)
					{
						moyprof = grid[i][j-1].getProfondeur();
						vari = (moyprof * vari)/100;
						prof = moyprof + vari;
					}
					if(j == 0)
					{
						moyprof = (grid[i-1][j].getProfondeur() + grid[i-1][j+1].getProfondeur())/2;
						vari = (moyprof * vari)/100;
						prof = moyprof + vari;
					}
					if(j != 0 && i != 0 && j < length-1)
					{
						moyprof = (grid[i][j-1].getProfondeur() + grid[i-1][j-1].getProfondeur() + grid[i-1][j].getProfondeur() + grid[i-1][j+1].getProfondeur())/4;
						vari = (moyprof * vari)/100;
						prof = moyprof + vari;
					}
					if(j == length-1 && i != 0)
					{
						moyprof = (grid[i][j-1].getProfondeur() + grid[i-1][j-1].getProfondeur() + grid[i-1][j].getProfondeur())/3;
						vari = (moyprof * vari)/100;
						prof = moyprof + vari;
					}
					if(prof < 0) prof = 0;
					if(prof >= 1000) prof = 999;
					
					grid[i][j] = new Cell(new Vector2D(), new Vector2D(), prof);
				}
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