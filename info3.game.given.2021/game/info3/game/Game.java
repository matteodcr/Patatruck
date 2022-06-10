/*
 * Copyright (C) 2020  Pr. Olivier Gruber
 * Educational software for a basic game development
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Created on: March, 2020
 *      Author: Pr. Olivier Gruber
 */
package info3.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.RandomAccessFile;

import javax.swing.JFrame;
import javax.swing.JLabel;

import info3.game.graphics.AwtGraphics;
import info3.game.graphics.GameCanvas;
import info3.game.graphics.Graphics;
import info3.game.scene.CityScene;
import info3.game.scene.KitchenScene;
import info3.game.sound.RandomFileInputStream;

public class Game {

	public static final int WIDTH = 256;
	public static final int HEIGHT = 144;

	public static final int SCALE_FACTOR = 6;

	/**
	 * Ticks par seconde
	 */
	private static final int TPS = 20;

	static Game game;

	public static void main(String args[]) throws Exception {
		try {
			System.out.println("Game starting...");
			game = new Game();
			System.out.println("Game started.");
		} catch (Throwable th) {
			th.printStackTrace(System.err);
		}
	}

	JFrame m_frame;
	JLabel m_text;
	GameCanvas m_canvas;
	CanvasListener m_listener;
	Cowboy m_cowboy1, m_cowboy2;
	Sound m_music;

	private final KitchenScene kitchenScene;
	private final CityScene cityScene;

	Game() throws Exception {
		// creating a cowboy, that would be a model
		// in an Model-View-Controller pattern (MVC)
		m_cowboy1 = new Cowboy();
		m_cowboy2 = new Cowboy();
		// creating a listener for all the events
		// from the game canvas, that would be
		// the controller in the MVC pattern
		m_listener = new CanvasListener(this);
		// creating the game canvas to render the game,
		// that would be a part of the view in the MVC pattern
		m_canvas = new GameCanvas(m_listener);

		System.out.println("  - creating frame...");
		Dimension d = new Dimension(WIDTH * SCALE_FACTOR, HEIGHT * SCALE_FACTOR);
		m_frame = m_canvas.createFrame(d);
		m_frame.setResizable(false);

		kitchenScene = new KitchenScene(WIDTH, HEIGHT / 2, m_cowboy1);
		cityScene = new CityScene(WIDTH, HEIGHT / 2, m_cowboy2);

		System.out.println("  - setting up the frame...");
		setupFrame();
	}

	/*
	 * Then it lays out the frame, with a border layout, adding a label to the north
	 * and the game canvas to the center.
	 */
	private void setupFrame() {

		m_frame.setTitle("Game");
		m_frame.setLayout(new BorderLayout());

		m_frame.add(m_canvas, BorderLayout.CENTER);

		m_text = new JLabel();
		m_text.setText("Tick: 0ms FPS=0");
		m_frame.add(m_text, BorderLayout.NORTH);

		// center the window on the screen
		m_frame.setLocationRelativeTo(null);

		// make the vindow visible
		m_frame.setVisible(true);

		m_cowboy2.m_x += 100;
	}

	/*
	 * ================================================================ All the
	 * methods below are invoked from the GameCanvas listener, once the window is
	 * visible on the screen.
	 * ==============================================================
	 */

	/*
	 * Called from the GameCanvas listener when the frame
	 */
	String m_musicName;

	void loadMusic() {
		m_musicName = m_musicNames[m_musicIndex];
		String filename = "resources/" + m_musicName + ".ogg";
		m_musicIndex = (m_musicIndex + 1) % m_musicNames.length;
		try {
			RandomAccessFile file = new RandomAccessFile(filename, "r");
			RandomFileInputStream fis = new RandomFileInputStream(file);
			m_canvas.playMusic(fis, 0, 1.0F);
		} catch (Throwable th) {
			th.printStackTrace(System.err);
			System.exit(-1);
		}
	}

	private int m_musicIndex = 0;
	private String[] m_musicNames = new String[] { "foire_saucisse" };

	private long m_textElapsed;

	/*
	 * This method is invoked almost periodically, given the number of milli-seconds
	 * that elapsed since the last time this method was invoked.
	 */
	void tick(long elapsed) {

		m_cowboy1.tick(elapsed);
		m_cowboy2.tick(elapsed);

		// Update every second
		// the text on top of the frame: tick and fps
		m_textElapsed += elapsed;
		if (m_textElapsed > (1000 / TPS)) {
			m_textElapsed = 0;
			float period = m_canvas.getTickPeriod();
			int fps = m_canvas.getFPS();

			String txt = "Tick=" + period + "ms";
			while (txt.length() < 15)
				txt += " ";
			txt = txt + fps + " fps   ";
			m_text.setText(txt);

			kitchenScene.tick();
			cityScene.tick();
		}

		moveCharacters();
	}

	void moveCharacters() {
		int VK_ENTER = '\n';
		int VK_ESCAPE = 0x1B;
		int VK_SPACE = 0x20;
		int VK_LEFT = 0x25;
		int VK_UP = 0x26;
		int VK_RIGHT = 0x27;
		int VK_DOWN = 0x28;
		int VK_Z = 0x5A;
		int VK_Q = 0x51;
		int VK_S = 0x53;
		int VK_D = 0x44;

		if (m_listener.keyboard.contains(VK_UP)) {
			m_cowboy1.move(Cowboy.UP);
		}
		if (m_listener.keyboard.contains(VK_DOWN)) {
			m_cowboy1.move(Cowboy.DOWN);
		}
		if (m_listener.keyboard.contains(VK_LEFT)) {
			m_cowboy1.move(Cowboy.LEFT);
		}
		if (m_listener.keyboard.contains(VK_RIGHT)) {
			m_cowboy1.move(Cowboy.RIGHT);
		}
		if (m_listener.keyboard.contains(VK_Z)) {
			m_cowboy2.move(Cowboy.UP);
		}
		if (m_listener.keyboard.contains(VK_S)) {
			m_cowboy2.move(Cowboy.DOWN);
		}
		if (m_listener.keyboard.contains(VK_Q)) {
			m_cowboy2.move(Cowboy.LEFT);
		}
		if (m_listener.keyboard.contains(VK_D)) {
			m_cowboy2.move(Cowboy.RIGHT);
		}
	}

	/*
	 * This request is to paint the Game Canvas, using the given graphics. This is
	 * called from the GameCanvasListener, called from the GameCanvas.
	 */
	void paint(java.awt.Graphics ag) {
		Graphics g = new AwtGraphics(ag, WIDTH, HEIGHT, SCALE_FACTOR);

		// paint
		int half = g.getHeight() / 2;
		kitchenScene.render(g.window(0, 0, g.getWidth(), half));
		cityScene.render(g.window(0, half, g.getWidth(), half));
	}

}
