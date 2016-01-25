package com.legendaries.gameEngine.core;

import javax.swing.JFrame;

public class Window extends JFrame {

	public Window(int width, int height, String title){
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
