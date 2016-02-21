package sim;

public class Boat {

	private int iPosX;
	private int iPosY;
	private float fPosX;
	private float fPosY;
	private World world;
	private Cell[][] grid;									// Cellule deja exploré
	
	public Boat(int posX, int posY, World world)
	{
		this.iPosX = posX;
		this.fPosX = iPosX;
		this.iPosY = posY;
		this.fPosY = iPosY;
		this.world = world;
		world.setBoat(this);
		grid = new Cell[world.getWorldHeight()][world.getWorldLength()];
	}
	
	public void move(float posX, float posY)
	{
		fPosX = posX;
		if(fPosX >= world.getWorldHeight() -1) fPosX = world.getWorldHeight() -1;
		if(fPosX < 0) fPosX = 0;
		iPosX = Math.round(fPosX);
		fPosY = posY;
		if(fPosY >= world.getWorldLength() -1) fPosY = world.getWorldLength() -1;
		if(fPosY < 0) fPosY = 0;
		iPosY = Math.round(fPosY);
	}
	
	public int getIPosX()
	{
		return iPosX;
	}
	
	public int getIPosY()
	{
		return iPosY;
	}
	
	public float getFPosX()
	{
		return fPosX;
	}
	
	public float getFPosY()
	{
		return fPosY;
	}
	
	public Cell getGridXY(int i, int j)
	{
		if(grid[i][j] != null) return grid[i][j];
		return null;
		
	}
	
	public void analyseCell()
	{
		grid[iPosX][iPosY] = world.getCellXY(iPosX, iPosY);
		System.out.println(toStringCell());
		world.getCellXY(iPosX, iPosY).setDecouvert();
	}
	
	public String toStringCell()
	{
		Cell c = grid[iPosX][iPosY];
		return "Position : " + fPosX + "\t" + fPosY + "\n" +
				"Vent : " + c.getVent().getX() + "\t" + c.getVent().getY() + "\n" +
				"Courant : " + c.getCourant().getX() + "\t" + c.getCourant().getY() + "\n" +
				"Salinite : " + c.getSalinite() + "\n" +
				"Profondeur : " + c.getProfondeur()+ "\n";
				
	}

	public World getWorld() {
		return world;
	}
}