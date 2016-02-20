package sim;

public class VaimosSim {

	public static void main(String[] args)
	{
		World world = new World(75, 75);
		Boat boat = new Boat(10, 10, world);
		Display display = new Display(world);
		//AffichageVecteur affvec = new AffichageVecteur(world);
		int delai = 200;
		float newposX;
		float newposY;
		int heure = 0;
		int jour = 1;
		while(true) 										// 100 iteration pour le moment
		{
			System.out.println("\tJour : " + jour + "\tHeure : " + heure + "");
			display.repaint();
			//affvec.repaint();
			boat.analyseCell();
			newposX = boat.getFPosX() + world.getGrid()[boat.getIPosX()][boat.getIPosY()].getVent().getX(); // Calcul rapide d'une nouvelle position XY
			newposY = boat.getFPosY() + world.getGrid()[boat.getIPosX()][boat.getIPosY()].getVent().getY();
										
			boat.move(newposX, newposY);					// Jusqu'ici
			
			heure++;
			
			if(heure == 24)
			{
				world.changementVent();
				heure = 0;
				jour++;
			}
			
			try {
				Thread.sleep(delai);
			} catch (InterruptedException e) {
			}
			
		}
	}	
}