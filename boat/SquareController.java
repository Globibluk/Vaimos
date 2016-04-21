package boat;

public class SquareController implements BoatController {
	
	public Boat boat;
	
	public SquareController(Boat boat)
	{
		this.boat = boat;
	}

	@Override
	public int control(int value)
	{
		int posX = boat.getIPosX();
		int posY = boat.getIPosY();
		
		switch(value)
		{
			case 0:
				
				if(posX > 85) return 1;
				boat.setTarget(posX, posY, 85, 15);
				boat.calculate();
				return 0;

			case 1:
				if(posY > 85) return 2;
				boat.setTarget(posX, posY, 85, 85);
				boat.calculate();
				return 1;
				
			case 2:
				if(posX < 15) return 3;
				boat.setTarget(posX, posY, 15, 85);
				boat.calculate();
				return 2;
				
			case 3:
				if(posY < 15) return 4;
				boat.setTarget(posX, posY, 15, 15);
				boat.calculate();
				return 3;
				
			default:
				return 4;
		}
	}

}
