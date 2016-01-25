package com.legendaries.gameEngine.util;

public class Vector2f {

	public float x, y;
	
	public static final Vector2f zeroVec = new Vector2f(0, 0);
	
	public Vector2f(float x, float y){
		this.x = x;
		this.y = y;
	}

	public Vector2f add(Vector2f vec2){
		return new Vector2f(x + vec2.x, y + vec2.y);
	}
	
	public Vector2f sub(Vector2f vec2){
		return new Vector2f(x - vec2.x, y - vec2.y);
	}
	
	public float length(){
		return (float)Math.sqrt(x * x + y * y);
	}
	
	public Vector2f normalize(){
		return new Vector2f(x/length(), y/length());
	}
	
	public void normalized(){
		x /= length();
		y /= length();
	}
	
	public Vector2f scale(float amount){
		return new Vector2f(x*amount, y*amount);
	}
	
	public void scaled(float amount){
		x *= amount;
		y *= amount;
	}
	
	public void add(float x, float y){
		this.x += x;
		this.y += y;
	}
	
}
