package com.legendaries.gameEngine.core;

import com.legendaries.gameEngine.game.Game;

public class Main {

	public static final int WIDTH = 1024, HEIGHT = 720;
	
	public Main() {
//		System.setProperty("sun.java2d.opengl", "true");
		Window window = new Window(WIDTH, HEIGHT, "Engine");
		GameRenderPanel mainPanel = new GameRenderPanel();
		window.add(mainPanel);
		mainPanel.repaint();
		window.repaint();
		new Game(mainPanel);
//		window.pack();
	}

	public static void main(String[] args) {
		new Main();
	}

}
