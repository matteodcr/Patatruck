package info3.game.graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AwtGraphics implements Graphics {

	private final java.awt.Graphics g;
	private final int width;
	private final int height;
	private final int scaleFactor;

	private final static Map<Sprite, BufferedImage> SPRITES = new HashMap<>();

	static {
		// Chargement des textures
		for (Sprite.Spritesheet spritesheet : Sprite.Spritesheet.values()) {
			try {
				BufferedImage img = ImageIO.read(new File("resources", spritesheet.filename));
				for (Sprite s : Sprite.values()) {
					if (s.spritesheet == spritesheet) {
						int size = spritesheet.tileSize;
						SPRITES.put(s, img.getSubimage(s.u * size, s.v * size, s.w * size, s.h * size));
					}
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public AwtGraphics(java.awt.Graphics g, int width, int height, int scaleFactor) {
		this.g = g;
		this.width = width;
		this.height = height;
		this.scaleFactor = scaleFactor;
	}

	@Override public Graphics window(int x, int y, int w, int h) {
		return new AwtGraphics(g.create(x * scaleFactor, y * scaleFactor, w * scaleFactor, h * scaleFactor), w, h,
				scaleFactor);
	}

	@Override public Graphics window(float x, float y, int w, int h) {
		return new AwtGraphics(
				g.create((int) (x * scaleFactor), (int) (y * scaleFactor), w * scaleFactor, h * scaleFactor), w, h,
				scaleFactor);
	}

	@Override public int getWidth() {
		return width;
	}

	@Override public int getHeight() {
		return height;
	}

	@Override public void fill(int color, int x, int y, int width, int height) {
		g.setColor(new Color(color));
		g.fillRect(x * scaleFactor, y * scaleFactor, width * scaleFactor, height * scaleFactor);
	}

	private void drawSpriteRealCoords(Sprite sprite, int dx, int dy) {
		BufferedImage img = SPRITES.get(sprite);
		if (img == null)
			throw new IllegalStateException("Texture non trouvée");

		int tileSize = sprite.spritesheet.tileSize;

		int dx2 = dx + sprite.w * tileSize * this.scaleFactor;
		int dy2 = dy + sprite.h * tileSize * this.scaleFactor;

		g.drawImage(img, dx, dy, dx2, dy2, 0, 0, img.getWidth(), img.getHeight(), null);
	}

	@Override public void drawSprite(Sprite sprite, int x, int y) {
		drawSpriteRealCoords(sprite, x * this.scaleFactor, y * this.scaleFactor);
	}

	@Override public void drawSprite(Sprite sprite, float x, float y) {
		drawSpriteRealCoords(sprite, (int) (x * this.scaleFactor), (int) (y * this.scaleFactor));
	}

	@Override protected void finalize() {
		g.dispose();
	}

}
