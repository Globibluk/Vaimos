package sim;

import java.io.IOException;

import javax.swing.UIManager;

import ui.MenuVaimos;

public class VaimosSim {

	public static void main(String[] args) throws IOException
	{
		
		try 
		{
		      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) { }

		MenuVaimos menu = new MenuVaimos();
	}
}