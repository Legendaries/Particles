package com.legendaries.gameEngine.gameComponents;

import java.awt.Graphics2D;

public class Player extends Entity{

	public Player(){
		
	}
	
	public void update(){
		
	}
	
	public void render(Graphics2D g){
		g.fillRect((int)posX, (int)posY, 10, 10);
	}
	
}
