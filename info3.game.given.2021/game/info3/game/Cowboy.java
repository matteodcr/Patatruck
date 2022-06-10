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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;

/**
 * A simple class that holds the images of a sprite for an animated cowbow.
 *
 */
public class Cowboy {
	BufferedImage[] m_images;
	int m_imageIndex;
	long m_imageElapsed;
	long m_moveElapsed;
	int m_x = 0, m_y = -48;
	int m_width, m_height;
	int move_timer = 0, move_timer_max = 10;

	public static final int UP = 1, RIGHT = 2, DOWN = 3, LEFT = 4;

	Cowboy() throws IOException {
		m_images = loadSprite("resources/winchester-4x6.png", 4, 6);
	}

	/*
	 * Simple animation here, the cowbow
	 */
	public void tick(long elapsed) {
		m_imageElapsed += elapsed;
		if (m_imageElapsed > 200) {
			m_imageElapsed = 0;
			m_imageIndex = (m_imageIndex + 1) % m_images.length;
		}
		m_moveElapsed += elapsed;
		if (m_moveElapsed > 24 & m_width != 0) {
			m_moveElapsed = 0;
			m_x = m_x % m_width;
			m_y = m_y % m_height;
			if (m_x < 0)
				m_x = m_width;
		}
		move_timer -= elapsed;
		if (move_timer < 0) {
			move_timer = 0;
		}
	}

	public void paint(Graphics g, int width, int height) {
		m_width = width;
		m_height = height + 48;
		// BufferedImage img = m_images[m_imageIndex];
		g.drawSprite(Sprite.COWBOY1, m_x, m_y + 48);
	}

	public static BufferedImage[] loadSprite(String filename, int nrows, int ncols) throws IOException {
		File imageFile = new File(filename);
		if (imageFile.exists()) {
			BufferedImage image = ImageIO.read(imageFile);
			int width = image.getWidth(null) / ncols;
			int height = image.getHeight(null) / nrows;

			BufferedImage[] images = new BufferedImage[nrows * ncols];
			for (int i = 0; i < nrows; i++) {
				for (int j = 0; j < ncols; j++) {
					int x = j * width;
					int y = i * height;
					images[(i * ncols) + j] = image.getSubimage(x, y, width, height);
				}
			}
			return images;
		}
		return null;
	}

	void move(int direction) {
		if (move_timer == 0) {
			switch (direction) {
			case UP:
				m_y -= 1;
				move_timer = move_timer_max;
				break;
			case RIGHT:
				m_x += 1;
				move_timer = move_timer_max;
				break;
			case DOWN:
				m_y += 1;
				move_timer = move_timer_max;
				break;
			case LEFT:
				m_x -= 1;
				move_timer = move_timer_max;
				break;
			default:
			}
		}

	}

}
