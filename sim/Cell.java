package sim;

import tools.Vector2D;

public class Cell {

	private Vector2D vent;
	private Vector2D courant;
	private float profondeur;
	private static float profondeurMax = 10;
	
	public Cell(Vector2D vent, Vector2D courant, float profondeur)
	{
		this.vent = vent;
		this.courant = courant;
		this.profondeur = profondeur;
	}
	
	public Cell()
	{
		vent = new Vector2D();
		profondeur = (float) Math.random() * profondeurMax;
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
}
