package tools;

public class Vector2D {

	private static float randomRange = 2;
	private float x;
	private float y;
	
	public Vector2D(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public Vector2D()
	{
		x = (float) Math.random() * randomRange - randomRange;
		y = (float) Math.random() * randomRange - randomRange;
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
