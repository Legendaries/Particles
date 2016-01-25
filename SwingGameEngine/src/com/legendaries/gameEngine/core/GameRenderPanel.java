package com.legendaries.gameEngine.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.legendaries.gameEngine.util.Vector2f;

public class GameRenderPanel extends JPanel {

	public static final List<TempEntity> entitiesToAdd = new ArrayList<TempEntity>();
	public static final List<Vector2f> attractorsToAdd = new ArrayList<Vector2f>();
	public static final List<Vector2f> repellorsToAdd = new ArrayList<Vector2f>();
	
	public static final List<TempEntity> entities = new ArrayList<TempEntity>();
	public static final List<Vector2f> attractors = new ArrayList<Vector2f>();
	public static final List<Vector2f> repellors = new ArrayList<Vector2f>();
	
	public static Vector2f mousePos = new Vector2f(0,0);
	
	public static final BufferedImage bg = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_RGB);
	public int[] pixels = ((DataBufferInt)bg.getRaster().getDataBuffer()).getData();
	
	public static float gravity = 1.01f;
	
	public GameRenderPanel(){
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me){
				requestFocus();
				if(me.getButton() == MouseEvent.BUTTON1)
					gravity = 0.99f;
				else if(me.getButton() == MouseEvent.BUTTON2)
					attractorsToAdd.add(new Vector2f(me.getX(), me.getY()));
				else
					repellorsToAdd.add(new Vector2f(me.getX(), me.getY()));
			}
			@Override
			public void mouseReleased(MouseEvent me){
				if(me.getButton() == MouseEvent.BUTTON1)
					gravity = 1.01f;
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent me){
				mousePos = new Vector2f(me.getX(), me.getY());
			}
			@Override
			public void mouseDragged(MouseEvent me){
				mousePos = new Vector2f(me.getX(), me.getY());
//				for(int i = 0; i < 1000; i++)
//					entitiesToAdd.add(new TempEntity(new Vector2f(400, 300), new Vector2f(i/100f, i/100f)));
			}
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode() == KeyEvent.VK_SPACE){
					spaceDown = true;
				}
			}
			@Override
			public void keyReleased(KeyEvent ke){
				if(ke.getKeyCode() == KeyEvent.VK_SPACE){
					spaceDown = false;
				}
			}
		});
	}
	
	private boolean spaceDown = false;
	
	public void update(){
		if(spaceDown)
			for(int i = 0; i < 1000; i++)
				entitiesToAdd.add(new TempEntity(new Vector2f(Main.WIDTH/2f, Main.HEIGHT/2f), new Vector2f(i/100f, i/100f)));
		entities.addAll(entitiesToAdd);
		attractors.addAll(attractorsToAdd);
		repellors.addAll(repellorsToAdd);
		entitiesToAdd.clear();
		attractorsToAdd.clear();
		repellorsToAdd.clear();
		for(TempEntity e : entities)
			e.update();
	}
	
	int t = 0;
	
	@Override
	public void paintComponent(Graphics g){
//		g.setColor(Color.black);
//		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
//		g.setColor(Color.green);
//		System.out.println(new Color(255, 255,255).getRGB());
		
		//set to black
		for(int i = 0; i < pixels.length; i++)
			pixels[i] = 0;
		
//		pixels[Main.WIDTH * 5 + t++] = -1;
//		System.out.println(new Color(255, 165, 0).getRGB());
		final int size = 1;
		for(TempEntity e : entities)
			if(e.pos.x > 0 && e.pos.x < Main.WIDTH-size && e.pos.y > 0 && e.pos.y < Main.HEIGHT-size){
				for(int i = 0; i < size; i++)
					for(int j = 0; j < size; j++){
						if((Main.WIDTH * (int)(e.pos.y+i) + (int)(e.pos.x+j)) < pixels.length && (Main.WIDTH * (int)(e.pos.y) + (int)(e.pos.x)) >= 0){
							Color pixelColor = new Color(pixels[Main.WIDTH * (int)(e.pos.y+i) + (int)(e.pos.x+j)]);
							pixels[Main.WIDTH * (int)(e.pos.y+i) + (int)(e.pos.x+j)] = new Color(min(pixelColor.getRed()+100, 255), min(pixelColor.getGreen()+16, 255), min(pixelColor.getBlue()+(int)(Math.abs(e.velocity.x*10f)), 255)).getRGB();
//							float velocity = e.velocity.length();
//							pixels[Main.WIDTH * (int)(e.pos.y+i) + (int)(e.pos.x+j)] = new Color(min(pixelColor.getRed()+(int)(100f*velocity), 255), min(pixelColor.getGreen()+(int)(255), 255), min(pixelColor.getBlue()+(int)(100f*velocity), 255)).getRGB();
//							System.out.println(pixels[Main.WIDTH * (int)(e.pos.y+i) + (int)(e.pos.x+j)]);
//							if(pixels[Main.WIDTH * (int)(e.pos.y+i) + (int)(e.pos.x+j)] >= Color.white.getRGB())
//								pixels[Main.WIDTH * (int)(e.pos.y+i) + (int)(e.pos.x+j)] = Color.white.getRGB();
						}
					}
			}else{
				if(e.pos.x < 0 || e.pos.x > Main.WIDTH-size)
					e.velocity.x = -e.velocity.x;
				else if(e.pos.y < 0 || e.pos.y > Main.HEIGHT-size)
					e.velocity.y = -e.velocity.y;
			}
		
		for(Vector2f v2 : attractors)
			for(int i = 0; i < 3; i++)
				for(int j = 0; j < 3; j++)
					pixels[Main.WIDTH * (int)(v2.y+i) + (int)(v2.x+j)] = Color.green.getRGB();
				
		for(Vector2f v2 : repellors)
			for(int i = 0; i < 3; i++)
				for(int j = 0; j < 3; j++)
					pixels[Main.WIDTH * (int)(v2.y+i) + (int)(v2.x+j)] = Color.blue.getRGB();
		
		g.drawImage(bg, 0, 0, this);
		g.setColor(Color.white);
		g.drawString("" + entities.size(), 50, 50);
//		for(TempEntity e : entities)
			
//			e.draw((Graphics2D)g);
	}
	
	public int min(int value, int val2){
		return (value < val2) ? value : val2;
	}
	
}