package sim;

import tools.Vector2D;

public class Cell {

	private Vector2D vent;
	private Vector2D courant;
	private float profondeur;
	private static float profondeurMax = 10;
	private boolean decouvert;
	
	public Cell(Vector2D vent, Vector2D courant, float profondeur)
	{
		this.vent = vent;
		this.courant = courant;
		this.profondeur = profondeur;
		decouvert = false;
	}
	
	public Cell()
	{
		vent = new Vector2D();
		courant = new Vector2D();
		profondeur = (float) Math.random() * profondeurMax;
		decouvert = false;
	}
	
	public Vector2D getVent()
	{
		return vent;
	}
	
	public Vector2D getCourant()
	{
		return courant;
	}
	
	public float getProfondeur()
	{
		return profondeur;
	}
	
	public boolean getDecouvert()
	{
		return decouvert;
	}
}
