package com.legendaries.gameEngine.core;

import javax.swing.JFrame;

public class Window extends JFrame {

	private int width, height;
	
	public Window(int width, int height, String title){
		setSize(this.width = width, this.height = height);
		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void pack(){
		pack();
		setSize(width, height);
		setLocationRelativeTo(null);
	}
	
}
