package com.legendaries.gameEngine.core;

import java.awt.Graphics2D;

import com.legendaries.gameEngine.util.Vector2f;

public class TempEntity {

	public Vector2f pos;
	public Vector2f velocity;
	public Vector2f acceleration = new Vector2f(0, 0.0098f);
	public Vector2f target = GameRenderPanel.mousePos;
	
	public TempEntity(){
		this(new Vector2f(Main.WIDTH/2f, Main.HEIGHT/2f));
	}
	
	public TempEntity(Vector2f pos){
		this(pos, new Vector2f(0, 0));
	}
	
	public TempEntity(Vector2f pos, Vector2f velocity){
		this.pos = pos;
		this.velocity = velocity;
	}
	
	private static boolean shouldStopAtDestination = true;
	
	public void update(){
		Vector2f dif = target.sub(pos);

		shouldStopAtDestination =  GameRenderPanel.gravity == 0.9f;
		if(dif.length() != 0)
			if(shouldStopAtDestination){
				if(dif.length() < 1.0f){
					pos = target;
					acceleration = new Vector2f(0,0);
					velocity = new Vector2f(0,0);
				}else if(dif.length() >= 1.0f){
					acceleration = dif.normalize().scale(1f/GameRenderPanel.gravity);
					velocity = velocity.add(acceleration).scale(GameRenderPanel.gravity);
					pos = pos.add(velocity);
				}
			}else{
				acceleration = dif.normalize().scale(1f/GameRenderPanel.gravity);
				velocity = velocity.add(acceleration).scale(GameRenderPanel.gravity);
				pos = pos.add(velocity);
			}
	}
	
	public void draw(Graphics2D g){
		g.fillRect((int)pos.x, (int)pos.y, 1, 1);
	}
	
}
