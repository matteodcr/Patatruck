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

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.HashSet;

import info3.game.graphics.GameCanvasListener;

public class CanvasListener implements GameCanvasListener {
	Game m_game;

	private static final int VK_ENTER = '\n';
	private static final int VK_ESCAPE = 0x1B;
	private static final int VK_SPACE = 0x20;
	private static final int VK_LEFT = 0x25;
	private static final int VK_UP = 0x26;
	private static final int VK_RIGHT = 0x27;
	private static final int VK_DOWN = 0x28;
	private static final int VK_Z = 0x5A;
	private static final int VK_Q = 0x51;
	private static final int VK_S = 0x53;
	private static final int VK_D = 0x44;

	public HashSet<Integer> keyboard = new HashSet<>();
	private HashMap<Integer, String> dico = new HashMap<>(); // Only necessary for the debug in the console

	CanvasListener(Game game) {
		m_game = game;
		dico.put((int) '\n', "ENTER");
		dico.put(0x1B, "ESCAPE");
		dico.put(0x20, "SPACE");
		dico.put(0x25, "LEFT");
		dico.put(0x26, "UP");
		dico.put(0x27, "RIGHT");
		dico.put(0x28, "DOWN");
		dico.put(0x5A, "Z");
		dico.put(0x51, "Q");
		dico.put(0x53, "S");
		dico.put(0x44, "D");
	}

	public boolean isUp(String name) {
		switch (name) {
		case "ENTER":
			return keyboard.contains(VK_ENTER);
		case "ESCAPE":
			return keyboard.contains(VK_ESCAPE);
		case "SPACE":
			return keyboard.contains(VK_SPACE);
		case "UP":
			return keyboard.contains(VK_UP);
		case "RIGHT":
			return keyboard.contains(VK_RIGHT);
		case "DOWN":
			return keyboard.contains(VK_DOWN);
		case "LEFT":
			return keyboard.contains(VK_LEFT);
		case "Z":
			return keyboard.contains(VK_Z);
		case "Q":
			return keyboard.contains(VK_Q);
		case "S":
			return keyboard.contains(VK_S);
		case "D":
			return keyboard.contains(VK_D);
		default:
			return false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		/*
		 * System.out.println("Mouse clicked: (" + e.getX() + "," + e.getY() + ")");
		 * System.out.println("   modifiers=" + e.getModifiersEx());
		 * System.out.println("   buttons=" + e.getButton());
		 */
	}

	@Override
	public void mousePressed(MouseEvent e) {
		/*
		 * System.out.println("Mouse pressed: (" + e.getX() + "," + e.getY() + ")");
		 * System.out.println("   modifiers=" + e.getModifiersEx());
		 * System.out.println("   buttons=" + e.getButton());
		 */
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		/*
		 * System.out.println("Mouse released: (" + e.getX() + "," + e.getY() + ")");
		 * System.out.println("   modifiers=" + e.getModifiersEx());
		 * System.out.println("   buttons=" + e.getButton());
		 */
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		/*
		 * System.out.println("Mouse entered: (" + e.getX() + "," + e.getY() + ")");
		 * System.out.println("   modifiers=" + e.getModifiersEx());
		 * System.out.println("   buttons=" + e.getButton());
		 */
	}

	@Override
	public void mouseExited(MouseEvent e) {
		/*
		 * System.out.println("Mouse exited: (" + e.getX() + "," + e.getY() + ")");
		 * System.out.println("   modifiers=" + e.getModifiersEx());
		 * System.out.println("   buttons=" + e.getButton());
		 */
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		/*
		 * System.out.println("Mouse dragged: (" + e.getX() + "," + e.getY() + ")");
		 * System.out.println("   modifiers=" + e.getModifiersEx());
		 * System.out.println("   buttons=" + e.getButton());
		 */
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		/*
		 * System.out.println("Mouse moved: (" + e.getX() + "," + e.getY() + ")");
		 * System.out.println("   modifiers=" + e.getModifiersEx());
		 * System.out.println("   buttons=" + e.getButton());
		 */
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// System.out.println("Key typed: " + e.getKeyChar() + " code=" +
		// e.getKeyCode());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println("Key pressed: " + e.getKeyChar() + " code=" +
		// e.getKeyCode());
		keyboard.add(e.getKeyCode());
		// printKeyboard();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// System.out.println("Key released: " + e.getKeyChar() + " code=" +
		// e.getKeyCode());
		keyboard.remove(e.getKeyCode());
		// printKeyboard();
	}

	public void printKeyboard() {
		boolean hasToPrint = true;
		for (Object o : keyboard) {
			if (dico.get(o) != null) {
				if (hasToPrint) {
					System.out.print("Keyboard :");
					hasToPrint = false;
				}
				System.out.print(" " + dico.get(o));
			}
		}
		if (!hasToPrint) {
			System.out.println("");
		}
	}

	@Override
	public void tick(long elapsed) {
		m_game.tick(elapsed);
	}

	@Override
	public void paint(Graphics g) {
		m_game.paint(g);
	}

	@Override
	public void windowOpened() {
		m_game.loadMusic();
//    m_game.m_canvas.setTimer(6000);
	}

	@Override
	public void exit() {
	}

//  boolean m_expired;
	@Override
	public void endOfPlay(String name) {
//    if (!m_expired) // only reload if it was a forced reload by timer
		m_game.loadMusic();
//    m_expired = false;
	}

	@Override
	public void expired() {
		// will force a change of music, after 6s of play
//    System.out.println("Forcing an ealy change of music");
//    m_expired = true;
//    m_game.loadMusic();    
	}

}
