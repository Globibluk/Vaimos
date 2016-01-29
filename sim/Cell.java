package sim;

import tools.Vector2D;

public class Cell {

	private Vector2D vent;
	private Vector2D courant;
	private float profondeur;
	
	public Cell(Vector2D vent, Vector2D courant, float profondeur)
	{
		this.vent = vent;
		this.courant = courant;
		this.profondeur = profondeur;
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
