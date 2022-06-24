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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JLabel;

import info3.automata.ast.AST;
import info3.automata.parser.AutomataParser;
import info3.game.automata.AutomataGenerator;
import info3.game.automata.GAutomaton;
import info3.game.entity.EntityType;
import info3.game.graphics.AwtGraphics;
import info3.game.graphics.GameCanvas;
import info3.game.screen.EndScreen;
import info3.game.screen.Screen;
import info3.game.screen.StartScreen;
import info3.game.sound.RandomFileInputStream;

public class Game {

	public static final int WIDTH = 256;
	public static final int HEIGHT = 144;

	public static final int SCALE_FACTOR = 6;
	public static final int START_TIME = 120000; // ms

	/**
	 * Ticks par seconde
	 */
	private static final int TPS = 20;

	static Game game;

	public static void main(String args[]) {
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
	public final CanvasListener m_listener = new CanvasListener(this);
	Sound m_music;

	private final Map<String, GAutomaton> automataList; // can be moved
	public Map<EntityType, GAutomaton> boundAutomata = new HashMap<>();

	public GAutomaton getBoundAutomaton(EntityType type) {
		if (boundAutomata == null)
			throw new RuntimeException("AutomatonSelectionScreen wasn't shown");

		return boundAutomata.get(type);
	}

	Screen screen;
	public long timeGame;
	private boolean timerHasBeenSet;
	private long startTimeGame;

	public long highScore;

	Game() {
		timerHasBeenSet = false;
		highScore = loadHighScore();
		automataList = loadAutomata("data");
		m_canvas = new GameCanvas(m_listener);

		System.out.println("  - creating frame...");
		Dimension d = new Dimension(WIDTH * SCALE_FACTOR, HEIGHT * SCALE_FACTOR);
		m_frame = m_canvas.createFrame(d);
		m_frame.setResizable(false);

		System.out.println("  - setting up the frame...");
		setupFrame();
		screen = new StartScreen(this);

		final List<GAutomaton> allAutomata = new ArrayList<>(automataList.values());
		final Map<EntityType, Integer> selection = new TreeMap<>();
		for (EntityType type : EntityType.values()) {
			GAutomaton aut = automataList.get(type.defaultAutomaton);
			selection.put(type, allAutomata.indexOf(aut));
		}

		for (Map.Entry<EntityType, Integer> entry : selection.entrySet()) {
			boundAutomata.put(entry.getKey(), allAutomata.get(entry.getValue()));
		}

	}

	public void changeScreen(Screen newScreen) {
		screen = newScreen;
	}

	public GAutomaton getAutomaton(String name) {
		GAutomaton a = automataList.get(name);

		if (a != null)
			return a;
		else
			throw new RuntimeException("automaton " + name + " not found");
	}

	public Collection<GAutomaton> getAllAutomata() {
		return automataList.values();
	}

	/*
	 * Then it lays out the frame, with a border layout, adding a label to the north
	 * and the game canvas to the center.
	 */
	private void setupFrame() {

		m_frame.setTitle("Patatruck");
		m_frame.setLayout(new BorderLayout());

		m_frame.add(m_canvas, BorderLayout.CENTER);

		m_text = new JLabel();
		m_text.setText("Tick: 0ms FPS=0  Nb_entities=0");
		m_frame.add(m_text, BorderLayout.NORTH);

		// center the window on the screen
		m_frame.setLocationRelativeTo(null);

		// make the vindow visible
		m_frame.setVisible(true);
	}

	/*
	 * ================================================================ All the
	 * methods below are invoked from the GameCanvas listener, once the window is
	 * visible on the screen.
	 * ==============================================================
	 */

	/*
	 * Called from the GameCanvas listener when the frame
	 */ String m_musicName;

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
		try {
			if (screen == null)
				return;

			// Update every second
			// the text on top of the frame: tick and fps
			m_textElapsed += elapsed;
			if (m_textElapsed > (1000 / TPS)) {
				m_textElapsed = 0;
				float period = m_canvas.getTickPeriod();
				int fps = m_canvas.getFPS();
				int nb_entities_k = screen.getEntityCount();
				String txt = "Tick=" + period + "ms";
				while (txt.length() < 15)
					txt += " ";
				txt = txt + fps + " fps   ";
				txt += "Nb_entities=" + nb_entities_k;
				m_text.setText(txt);

				screen.tick(elapsed);
			}
			timer(elapsed);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	AwtGraphics g = null;

	/*
	 * This request is to paint the Game Canvas, using the given graphics. This is
	 * called from the GameCanvasListener, called from the GameCanvas.
	 */
	void paint(java.awt.Graphics ag) {
		try {
			g = new AwtGraphics(g, ag, WIDTH, HEIGHT, SCALE_FACTOR);

			// paint
			screen.render(g);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	/* Generates automata list from .gal file */
	Map<String, GAutomaton> loadAutomata(String filename) {
		try {
			Map<String, GAutomaton> automata = new TreeMap<>();
			List<GAutomaton> automata_tmp;
			File folder = new File(filename);
			for (File file : folder.listFiles()) {
				if (!file.isDirectory()) {
					try {
						AST ast = AutomataParser.from_file(file.getAbsolutePath());
						AutomataGenerator ast_visitor = new AutomataGenerator();
						automata_tmp = (List<GAutomaton>) ast.accept(ast_visitor);

						automata.putAll(
								automata_tmp.stream().collect(Collectors.toMap(a -> a.name, Function.identity())));

						System.out.printf("successfully loaded automata from %s \n", file.getName());
					} catch (Exception e) {
						System.err.printf("error while loading file \"%s\"%n", file.getCanonicalFile());
						e.printStackTrace();
						System.exit(1);
					}
				}
			}
			return automata;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	void timer(long elapsed) {
		if (timerHasBeenSet) {
			timeGame -= elapsed;

			if (timeGame <= 0) {
				timerHasBeenSet = false;
				long score = (long) Math
						.ceil(((double) (System.currentTimeMillis()) - ((double) startTimeGame)) / 1000);
				if (score > highScore)
					storeHighScore(score);
				changeScreen(new EndScreen(this, score));
			}
		}
	}

	public void addTime(long time) {
		if ((timeGame + time * 1000) <= 999999)
			timeGame += time * 1000;
		else
			timeGame = 999999;
	}

	public void setTimer() {
		startTimeGame = System.currentTimeMillis();
		timerHasBeenSet = true;
		timeGame = START_TIME;
	}

	private long loadHighScore() {
		long score = 0;
		try {
			File file = new File("resources/scores.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			try {
				score = Integer.parseInt(line.trim()); // parse each line as an int
			} catch (NumberFormatException e) {
				System.out.println("Score invalide ds resources/scores.txts");
			}
			br.close();
			return score;

		} catch (IOException ex) {
			System.out.println("Couldn't find save file : resources/scores.txt");
			return score;
		}
	}

	private boolean storeHighScore(long s) {
		try {
			File file = new File("resources/scores.txt");
			BufferedWriter br = new BufferedWriter(new FileWriter(file));
			String line = String.valueOf(s);
			br.write(line);
			br.close();
			highScore = s;
			return true;
		} catch (IOException ex) {
			System.out.println("Couldn't find save file : resources/scores.txt");
			return false;
		}
	}
}
