package com.legendaries.gameEngine.core;

import java.awt.Graphics2D;

import com.legendaries.gameEngine.util.Vector2f;

public class TempEntity {

	public Vector2f pos;
	public Vector2f velocity;
	public Vector2f acceleration = new Vector2f(0, 0.0098f);
	
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
	
	public void update(){
		Vector2f dif = GameRenderPanel.mousePos.sub(pos);
		if(dif.length() < 600)
			acceleration = dif.normalize().scale(1f/GameRenderPanel.gravity);
//		else
//			acceleration = Vector2f.zeroVec;
		for(Vector2f v2 : GameRenderPanel.attractors)
			acceleration = acceleration.add(new Vector2f(v2.x - pos.x,  v2.y - pos.y).normalize().scale(1/GameRenderPanel.gravity));
		for(Vector2f v2 : GameRenderPanel.repellors)
			acceleration = acceleration.sub(new Vector2f(v2.x - pos.x,  v2.y - pos.y).normalize().scale(1/GameRenderPanel.gravity));
		velocity = velocity.add(acceleration).scale(GameRenderPanel.gravity);
		pos = pos.add(velocity);
	}
	
	public void draw(Graphics2D g){
		g.fillRect((int)pos.x, (int)pos.y, 1, 1);
	}
	
}
