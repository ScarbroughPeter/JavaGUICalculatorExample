/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.calculator;

import java.awt.event.*;

public class WindowKeyListener implements KeyListener
{

	GUICalculator gui;
	boolean shiftHeld;
	
	WindowKeyListener(GUICalculator g)
	{
		gui = g;
	}
	
	public void keyTyped(KeyEvent event)
	{
		//System.out.println(event);
	}
	
	public void keyPressed(KeyEvent event)
	{
		int keyCode = event.getKeyCode();
		
		// Shift Key Processor
		if(keyCode == 16)
		{
			shiftHeld = true;
		}
		
		// Number Key Processor
		if(keyCode >= 48 && keyCode <= 57)
		{
			int keyNumber = keyCode - 48;
			String sNumber = Integer.toString(keyNumber);
			
			if(gui.equalsClicked)
			{
				gui.textArea.setText("");
				gui.equalsClicked = false;
			}
			if(gui.operationClicked)
			{
				gui.textArea.setText("");
				gui.operationClicked = false;
			}
			String s = gui.textArea.getText();
			if(s.length() >= 10) return;
			if(sNumber.equals("0") && s.equals("")) return;
			gui.textArea.setText(s + sNumber);
		}
		
		// Del Key Processor
		if(keyCode == 8)
		{
			ActionEvent emptyEvent = new ActionEvent(keyCode, 0, "");
			ActionListener[] aList = gui.bDel.getActionListeners();
			for(ActionListener a : aList)
			{
				a.actionPerformed(emptyEvent);
			}
			
		}
		
		// Equal Key Processor
		if(keyCode == 10)
		{
			ActionEvent emptyEvent = new ActionEvent(keyCode, 0, "");
			ActionListener[] aList = gui.bEquals.getActionListeners();
			for(ActionListener a : aList)
			{
				a.actionPerformed(emptyEvent);
			}			
		}
		
		// Plus Key Processor
		if(keyCode == 61 && shiftHeld)
		{
			gui.equalsClicked = false;
			gui.operationClicked = true;
			gui.operationSelected = "+";
			String s = gui.textArea.getText();
			if(s.equals("")) s = "0";
			gui.memoryNumber = Integer.parseInt(s);			
		}
		
		// Minus Key Processor
		if(keyCode == 45)
		{
			gui.equalsClicked = false;
			gui.operationClicked = true;
			gui.operationSelected = "-";
			String s = gui.textArea.getText();
			if(s.equals("")) s = "0";
			gui.memoryNumber = Integer.parseInt(s);	
		}
		
	}
	
	public void keyReleased(KeyEvent event)
	{
		int keyCode = event.getKeyCode();
		if(keyCode == 16)
		{
			shiftHeld = false;
		}
	}
}