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
		float xtmp = vent.getX();
		xtmp *= xtmp;
		float ytmp = vent.getY();
		ytmp *= ytmp;
		courant.setX(xtmp);
		courant.setY(ytmp);
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<length;j++)
			{
				prof = worldHeight-i-(worldHeight/100*10);
				grid[i][j] = new Cell(vent, courant, prof);
				/*if( i == 0 && j == 0)
				{
					grid[i][j] = new Cell();
					grid[i][j].setVent(vent);
					grid[i][j].setCourant(courant);
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
					if(i > worldHeight-20)
					{
						prof = worldHeight-i;
					}
					if(i > worldHeight-10)
					{
						prof = -111;
					}
					if(prof < 0) prof = 0;
					if(prof >= 1000) prof = 999;
					
					grid[i][j] = new Cell(vent, courant, prof);
				}*/
			}
		}
	}
	
	public float getMaxWorldDepth() {
		return maxWorldDepth;
	}

	public void changementVent(){
		//System.out.println(grid[0][0].getVent().toString());
		Vector2D newvent = new Vector2D();
		float addx =(float)( Math.random()*2 ) - 1;
		float addy =(float)( Math.random()*2 ) - 1;
		float xtmp = grid[0][0].getVent().getX()+addx;
		float ytmp = grid[0][0].getVent().getY()+addy;
		if(xtmp > 25) xtmp = 25;
		if(xtmp < -25) xtmp = -25;
		if(ytmp > 25) ytmp = 25;
		if(ytmp < -25) ytmp = -25;
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