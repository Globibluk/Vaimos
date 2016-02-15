package sim;

public class Boat {

	private int posX;
	private int posY;
	private World world;
	private Cell[][] grid;									// Cellule deja exploré
	
	public Boat(int posX, int posY, World world)
	{
		this.posX = posX;
		this.posY = posY;
		this.world = world;
		world.setBoat(this);
		grid = new Cell[world.getWorldHeight()][world.getWorldLength()];
	}
	
	public void move(int posX, int posY)
	{
		this.posX = posX;
		this.posY = posY;
		world.setBoat(this);
	}
	
	public int getPosX()
	{
		return posX;
	}
	
	public int getPosY()
	{
		return posY;
	}
	
	public void analyseCell()
	{
		grid[posX][posY] = world.getCellXY(posX, posY);
		System.out.println(toStringCell());
		world.getCellXY(posX, posY).setDecouvert();
	}
	
	public String toStringCell()
	{
		Cell c = world.getCellXY(posX, posY);
		return "Position : " + posX + "\t" + posY + "\n" +
				"Vent : " + c.getVent().getX() + "\t" + c.getVent().getY() + "\n" +
				"Courant : " + c.getCourant().getX() + "\t" + c.getCourant().getY() + "\n" +
				"Salinite : " + c.getSalinite() + "\n";
				
	}
}
