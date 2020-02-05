/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.calculator;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

public class WindowKeyDispatcher implements KeyEventDispatcher
{
	
	GUICalculator gui;
	WindowKeyListener wkl;
	long lastPressProcessed; //could reprogram to no repeats, just one KEY_PRESSED, KEY_TYPED at a time....
	
	WindowKeyDispatcher(GUICalculator g)
	{
		gui = g;
		wkl = new WindowKeyListener(gui);
	}
	
	public boolean dispatchKeyEvent(KeyEvent event)
	{
		if(event.getID() == KeyEvent.KEY_PRESSED && (System.currentTimeMillis() - lastPressProcessed > 50)) // delay auto-repeat
		{
			wkl.keyPressed(event);
			lastPressProcessed = System.currentTimeMillis();
		}
		else if(event.getID() == KeyEvent.KEY_RELEASED) wkl.keyReleased(event);
		else if(event.getID() == KeyEvent.KEY_TYPED) wkl.keyTyped(event);
		return(true);
	}
}
