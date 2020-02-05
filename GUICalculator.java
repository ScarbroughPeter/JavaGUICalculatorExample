package gui.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GUICalculator {
	
	JFrame window;
	JPanel panel;
	JTextArea textArea;
	JButton b1;
	JButton b2;
	JButton b3;
	JButton b4;
	JButton b5;
	JButton b6;
	JButton b7;
	JButton b8;
	JButton b9;
	JButton b0;
	JButton bEquals;
	JButton bPlus;
	JButton bDel;
	JButton bMinus;
	JButton bC;
	int memoryNumber;
	int equalsNumber;
	boolean operationClicked;
	boolean equalsClicked;
	String operationSelected;
	
	
	
	public static void main(String[] args)
	{
		GUICalculator gui = new GUICalculator();
		gui.loadGUI();
		gui.runGUI();
		gui.window.repaint();
	}
	
	public void loadGUI()
	{
		Font f = new Font("Tahoma", Font.PLAIN, 14);
		
		window = new JFrame();
		panel = new JPanel();
		b1 = new JButton("1");
		b1.setFont(f);
		b1.setBounds(54, 74, 43, 27);
		b2 = new JButton("2");
		b2.setFont(f);
		b2.setBounds(105, 74, 43, 27);
		b3 = new JButton("3");
		b3.setFont(f);
		b3.setBounds(156, 74, 43, 27);
		b4 = new JButton("4");
		b4.setFont(f);
		b4.setBounds(54, 112, 43, 27);
		b5 = new JButton("5");
		b5.setFont(f);
		b5.setBounds(105, 112, 43, 27);
		b6 = new JButton("6");
		b6.setFont(f);
		b6.setBounds(156, 112, 43, 27);
		b7 = new JButton("7");
		b7.setFont(f);
		b7.setBounds(54, 150, 43, 27);
		b8 = new JButton("8");
		b8.setFont(f);
		b8.setBounds(105, 150, 43, 27);
		b9 = new JButton("9");
		b9.setFont(f);
		b9.setBounds(156, 150, 43, 27);
		b0 = new JButton("0");
		b0.setFont(f);
		b0.setBounds(105, 188, 43, 27);
		bEquals = new JButton("=");
		bEquals.setFont(f);
		bEquals.setBounds(156, 188, 103, 27);
		bPlus = new JButton("+");
		bPlus.setFont(f);
		bPlus.setBounds(207, 112, 52, 27);
		bDel = new JButton("Del");
		bDel.setFont(f);
		bDel.setBounds(206, 74, 53, 27);
		bMinus = new JButton("-");	
		bMinus.setFont(f);
		bMinus.setBounds(207, 150, 52, 27);
		bC = new JButton("C");
		bC.setFont(f);
		bC.setBounds(54, 188, 43, 27);
	}
	
	public void runGUI()
	{
		window.setSize(340, 294);
		window.setLocation(500, 300);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setTitle("Calculator Test");
		window.setVisible(true);
		
		window.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Kristen ITC", Font.PLAIN, 28));
		textArea.setBounds(12, 13, 298, 48);
		
		textArea.setColumns(20);
		panel.add(textArea);
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		panel.add(b4);
		panel.add(b5);
		panel.add(b6);
		panel.add(b7);
		panel.add(b8);
		panel.add(b9);
		panel.add(b0);
		panel.add(bEquals);
		panel.add(bPlus);
		panel.add(bDel);
		panel.add(bMinus);
		panel.add(bC);
		
		window.addKeyListener(new WindowKeyListener(this));
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new WindowKeyDispatcher(this));
   
		
		b1.addActionListener(new NumberButtonActionListener());
		b2.addActionListener(new NumberButtonActionListener());
		b3.addActionListener(new NumberButtonActionListener());
		b4.addActionListener(new NumberButtonActionListener());
		b5.addActionListener(new NumberButtonActionListener());
		b6.addActionListener(new NumberButtonActionListener());
		b7.addActionListener(new NumberButtonActionListener());
		b8.addActionListener(new NumberButtonActionListener());
		b9.addActionListener(new NumberButtonActionListener());
		b0.addActionListener(new NumberButtonActionListener());
		
		bDel.addActionListener(new DelButtonActionListener());
		bPlus.addActionListener(new OperationButtonActionListener());
		bMinus.addActionListener(new OperationButtonActionListener());
		bEquals.addActionListener(new EqualsButtonActionListener());
		bC.addActionListener(new CButtonActionListener());
	}
	
	public class NumberButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(equalsClicked)
			{
				textArea.setText("");
				equalsClicked = false;
			}
			if(operationClicked)
			{
				textArea.setText("");
				operationClicked = false;
			}
			String s = textArea.getText();
			if(s.length() >= 10) return;
			JButton b = (JButton) event.getSource();
			String bStr = b.getText();
			if(bStr.equals("0") && s.equals("")) return;
			textArea.setText(s + bStr);
			
		}
	}
	
	public class CButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			memoryNumber = 0;
			equalsNumber = 0;
			operationClicked = false;
			operationSelected = "";
			equalsClicked = false;
			textArea.setText("");
		}
	}
	
	public class DelButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			equalsClicked = false;
			operationClicked = false;
			String s = textArea.getText();
			if(s.length() == 0) return;
			String newS = s.substring(0, (s.length()-1));
			if(newS.equals("-")) newS = "";
			textArea.setText(newS);
		}
	}
	
	public class OperationButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			equalsClicked = false;
			operationClicked = true;
			JButton b = (JButton) event.getSource();
			operationSelected = b.getText();
			String s = textArea.getText();
			if(s.equals("")) s = "0";
			memoryNumber = Integer.parseInt(s);
		}
	}
	
	public class EqualsButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			//System.out.println(operationSelected + " " + operationClicked);
			if(operationSelected == "") return;
			int operand = Integer.parseInt(textArea.getText());
			if(equalsClicked) operand = equalsNumber;
			equalsClicked = true;
			equalsNumber = operand;
			int result = 0;
			if(operationSelected == "+")
			{
				result = operand + memoryNumber;
			}
			if(operationSelected == "-")
			{
				result = memoryNumber - operand;
			}
			memoryNumber = result;
			String s = Integer.toString(result);
			textArea.setText(s);
			
		}
	}
}
