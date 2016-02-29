package sim;

import java.awt.KeyboardFocusManager;

import ui.DepthDisplay;
import ui.TravelDisplay;
import ui.VaimosKeyEventDispatcher;

public class VaimosSim {

	public static void main(String[] args)
	{
		World world = new World(60, 66);
		Boat boat = new Boat(10, 10, world);
		TravelDisplay display = new TravelDisplay(world);
		//AffichageVecteur affvec = new AffichageVecteur(world);
		int delai = 100;
		double newposX;
		double newposY;
		
		DepthDisplay bol = new DepthDisplay(boat);
		
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new VaimosKeyEventDispatcher(bol));
		
		while(true) 										// 100 iteration pour le moment
		{
			display.repaint();
			bol.repaint();
			//affvec.repaint();
			boat.analyseCell();
			//newposX = boat.getFPosX() + world.getGrid()[boat.getIPosX()][boat.getIPosY()].getVent().getX(); // Calcul rapide d'une nouvelle position XY
			//newposY = boat.getFPosY() + world.getGrid()[boat.getIPosX()][boat.getIPosY()].getVent().getY();
										
			//boat.move(newposX, newposY);					// Jusqu'ici
			
			boat.calculate();
			
			
			if(Math.random() >= .90)
			{
				world.changementVent();
			}
			
			try {
				Thread.sleep(delai);
			} catch (InterruptedException e) {
			}
			
		}
	}	
}