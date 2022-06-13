package info3.game.entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import info3.game.graphics.Graphics;
import info3.game.position.PositionF;
import info3.game.scene.Scene;
import javax.imageio.ImageIO;

public abstract class Entity {
	Scene parentScene = null;
	PositionF position;
	// FIXME Automaton automate;
	int deathTime = 0;
	int move_timer = 0, move_timer_max = 0; // allows to move only when move_timer==0

	Entity(Scene parent, PositionF pos) {
		parentScene = parent;
		position = pos;
	}

	void setPosition(PositionF pos) {
		position = pos;
	}

	PositionF getPosition() {
		return position;
	}

	void tick(long elapsed) {
		// TODO
	}

	void destroySpin() {
		// TODO
	}

	void destroyCrush() {
		// TODO
	}

	public void render(Graphics g) {
		// TODO
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

	public boolean canMove() {
		return move_timer == 0;
	}

	public void hasMoved() {
		this.move_timer = move_timer_max;
	}
}
