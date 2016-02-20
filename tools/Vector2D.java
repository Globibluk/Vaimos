package tools;

public class Vector2D {

	private float x;
	private float y;
	
	public Vector2D(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public Vector2D()
	{
		x = (float) Math.random() * 2 - 1;
		y = (float) Math.random() * 2 - 1;
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}
}