package ui;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

public class VaimosKeyEventDispatcher implements KeyEventDispatcher {
	
	private DepthDisplay dd;
	
	public VaimosKeyEventDispatcher(DepthDisplay dd)
	{
		super();
		this.dd = dd;
	}
 
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
        	keyPressTreat(e);
        }            
            
        else if (e.getID() == KeyEvent.KEY_RELEASED) {
        } 
        
        else if (e.getID() == KeyEvent.KEY_TYPED) {
        }
        return false;
    }

	public void keyPressTreat(KeyEvent e) {
		
		int c = e.getKeyCode();
	    if ( c == KeyEvent.VK_D ) dd.switchState();
	}


}