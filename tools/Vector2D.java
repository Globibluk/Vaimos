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
		x = (float) (Math.random() * 2 - 1);
		y = (float) (Math.random() * 2 - 1);
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}
	
	public float getRotation()
	{
		float angle = 0;
		if(x > 0) angle += 180;
		angle += Math.acos(-y) * 180 / Math.PI;
		
		return angle;
	}
	
	public String toString()
	{
		return "("+x+","+y+")";
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
}