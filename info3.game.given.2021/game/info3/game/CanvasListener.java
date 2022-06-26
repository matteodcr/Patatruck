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
import java.util.HashSet;

import info3.game.graphics.GameCanvasListener;

public class CanvasListener implements GameCanvasListener {
	final Game game;

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
	private static final int VK_G = 0x47;

	private boolean spaceUsed = false, escapeUsed = false, enterUsed = false, gUsed = false;

	public final HashSet<Integer> keyboard = new HashSet<>();

	CanvasListener(Game game) {
		this.game = game;
	}

	public boolean isUp(String name) {
		switch (name) {
		case "ENTER":
			if (keyboard.contains(VK_ENTER) && !enterUsed) {
				enterUsed = true;
				return true;
			} else {
				return false;
			}
		case "ESCAPE":
			if (keyboard.contains(VK_ESCAPE) && !enterUsed) {
				escapeUsed = true;
				return true;
			} else {
				return false;
			}
		case "SPACE":
			if (keyboard.contains(VK_SPACE) && !enterUsed) {
				spaceUsed = true;
				return true;
			} else {
				return false;
			}
		case "FU":
		case "UP":
			return keyboard.contains(VK_UP);
		case "FR":
		case "RIGHT":
			return keyboard.contains(VK_RIGHT);
		case "FD":
		case "DOWN":
			return keyboard.contains(VK_DOWN);
		case "FL":
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
		case "G":
			if (keyboard.contains(VK_G) && !gUsed) {
				gUsed = true;
				return true;
			} else {
				return false;
			}
		default:
			return false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyboard.add(e.getKeyCode());
		if (e.getKeyCode() == VK_ENTER) {
			enterUsed = false;
		}
		if (e.getKeyCode() == VK_SPACE) {
			spaceUsed = false;
		}
		if (e.getKeyCode() == VK_ESCAPE) {
			escapeUsed = false;
		}
		if (e.getKeyCode() == VK_G) {
			gUsed = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyboard.remove(e.getKeyCode());
		if (e.getKeyCode() == VK_ENTER) {
			enterUsed = false;
		}
		if (e.getKeyCode() == VK_SPACE) {
			spaceUsed = false;
		}
		if (e.getKeyCode() == VK_ESCAPE) {
			escapeUsed = false;
		}
		if (e.getKeyCode() == VK_G) {
			gUsed = false;
		}
	}

	@Override
	public void tick(long elapsed) {
		game.tick(elapsed);
	}

	@Override
	public void paint(Graphics g) {
		game.paint(g);
	}

	@Override
	public void windowOpened() {
		game.loadMusic();
	}

	@Override
	public void exit() {
	}

	boolean expired;

	@Override
	public void endOfPlay(String name) {
		game.currentSounds.remove(name);
		if (expired) // only reload if it was a forced reload by timer
			game.loadMusic();
		expired = false;
	}

	@Override
	public void expired() {
		// will force a change of music, after 6s of play
		System.out.println("Forcing an ealy change of music");
		expired = true;
		game.loadMusic();
	}

}
