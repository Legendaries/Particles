package com.legendaries.gameEngine.game;

import com.legendaries.gameEngine.core.GameRenderPanel;

public class Game {

	private double m_frameTime = 1 / 60f;

	private GameRenderPanel panel;

	public Game(GameRenderPanel panel) {
		this.panel = panel;
		run();
	}

	public void run() {

		int frames = 0;
		double frameCounter = 0;

		double lastTime = getTime();
		double unprocessedTime = 0;

		while (true) {
			boolean render = false;

			double startTime = getTime();
			double passedTime = startTime - lastTime;
			lastTime = startTime;

			unprocessedTime += passedTime;
			frameCounter += passedTime;

			while (unprocessedTime > m_frameTime) {
				render = true;

				unprocessedTime -= m_frameTime;

				// m_game.Input((float) m_frameTime);
				// Input.Update();

				update((float) m_frameTime);

				if (frameCounter >= 1.0) {
					System.out.println(frames);
					frames = 0;
					frameCounter = 0;
				}
			}
			if (render) {
				render();
				frames++;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void update(float delta) {
		panel.update();
	}

	public void render() {
		panel.repaint();
	}

	private static final long SECOND = 1000000000L;

	public static double getTime() {
		return (double) System.nanoTime() / (double) SECOND;
	}

}
