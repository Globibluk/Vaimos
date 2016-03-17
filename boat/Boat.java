package boat;

import sim.Cell;
import sim.World;

public class Boat {

	private int iPosX;
	private int iPosY;
	private double fPosX;
	private double fPosY;
	private World world;
	private Cell[][] grid;									// Cellule deja exploré
	private int tempsM = 0;
	private int tempsH = 0;
	private int tempsJ = 0;
	
	private Sailboat sailboat;
	
	public Boat(int posX, int posY, World world)
	{
		this.iPosX = posX;
		this.fPosX = iPosX;
		this.iPosY = posY;
		this.fPosY = iPosY;
		this.world = world;
		world.setBoat(this);
		grid = new Cell[world.getWorldHeight()][world.getWorldLength()];
		
		sailboat = new Sailboat(iPosX, iPosX, iPosY, iPosY);
	}
	
	public void calculate()
	{
		sailboat.update();
		move(sailboat.getX(), sailboat.getY());	
		analyseCell();
	}
	
	private void move(double posX, double posY)
	{
		time();
		fPosX = posX;
		if(fPosX >= world.getWorldHeight() -1) fPosX = world.getWorldHeight() -1;
		if(fPosX < 0) fPosX = 0;
		iPosX = Math.round((float)fPosX);
		fPosY = posY;
		if(fPosY >= world.getWorldLength() -1) fPosY = world.getWorldLength() -1;
		if(fPosY < 0) fPosY = 0;
		iPosY = Math.round((float)fPosY);
	}
	
	public void changeTarget(int ax, int ay, int bx, int by)
	{
		sailboat.setCoordinates(ax, ay, bx, by);
	}
	
	private void time()
	{
		tempsM += 10;
		if(tempsM == 60)
		{
			tempsM = 0;
			tempsH++;
		}
		if(tempsH == 24)
		{
			tempsH = 0;
			tempsJ++;
		}
	}
	
	public int getIPosX()
	{
		return iPosX;
	}
	
	public int getIPosY()
	{
		return iPosY;
	}
	
	public double getFPosX()
	{
		return fPosX;
	}
	
	public double getFPosY()
	{
		return fPosY;
	}
	
	public Cell getGridXY(int i, int j)
	{
		if(grid[i][j] != null) return grid[i][j];
		return null;
		
	}
	
	private void analyseCell()
	{
		grid[iPosX][iPosY] = world.getCellXY(iPosX, iPosY);
		//System.out.println(toStringCell());
		world.getCellXY(iPosX, iPosY).setDecouvert();
	}
	
	private String toStringCell()
	{
		Cell c = grid[iPosX][iPosY];
		return "\tJour : " + tempsJ + "\tHeure : " + tempsH + "\tMinutes : " + tempsM + "\n\n" +
				"Position : " + fPosX + "\t" + fPosY + "\n" +
				"Vent : " + c.getVent().getX() + "\t" + c.getVent().getY() + "\n" +
				"Courant : " + c.getCourant().getX() + "\t" + c.getCourant().getY() + "\n" +
				"Salinite : " + c.getSalinite() + "\n" +
				"Profondeur : " + c.getProfondeur()+ "\n" +
				c.getVent().toString();
				
	}

	public World getWorld() {
		return world;
	}
}