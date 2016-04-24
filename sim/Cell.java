package sim;

import tools.Vector2D;

public class Cell {

	private Vector2D vent;
	private Vector2D courant;
	private float profondeur;
	private static float profondeurMax = 1000;
	private boolean decouvert;
	private float salinite;											// Donnee en plus
	
	public Cell(Vector2D vent, Vector2D courant, float profondeur)
	{
		this.vent = vent;
		this.courant = courant;
		this.profondeur = profondeur;
		decouvert = false;
		salinite =(float) Math.random();
	}
	
	public Cell()
	{
		vent = new Vector2D();
		courant = new Vector2D();
		profondeur = (float) Math.random() * profondeurMax;
		decouvert = false;
		salinite =(float) Math.random();
	}
	
	public Vector2D getVent()
	{
		return vent;
	}
	
	public void setVent()
	{
		vent = new Vector2D();
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

	public float getSalinite() {
		return salinite;
	}

	public void setSalinite(float salinite) {
		this.salinite = salinite;
	}

	public void setDecouvert() {
		this.decouvert = true;
	}
}