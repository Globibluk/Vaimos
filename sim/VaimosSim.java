package sim;

public class VaimosSim {

	public static void main(String[] args)
	{
		World world = new World(50, 50);
		Boat boat = new Boat(10, 10, world);
		Display display = new Display(world);
		int i = 0;
		int newposX;
		int newposY;
		while(i<100) 										// 100 iteration pour le moment
		{
			display.repaint();
			boat.analyseCell();
			newposX = boat.getPosX() + (int)world.getGrid()[boat.getPosX()][boat.getPosY()].getVent().getX(); // Calcul rapide d'une nouvelle position XY
			newposY = boat.getPosY() + (int)world.getGrid()[boat.getPosX()][boat.getPosY()].getVent().getY();
			if(newposX < 0)
			{
				newposX = 0;
			}
			if(newposX > world.getWorldHeight())   			// Changement de position du bateau et evitement d'erreur de seg
			{
				newposX = world.getWorldHeight();
			}
			if(newposY < 0)
			{
				newposY = 0;
			}
			if(newposY > world.getWorldLength())
			{
				newposY = world.getWorldLength();
			}												
			boat.move(newposX, newposY);					// Jusqu'ici
			i++;
		}
	}	
}
