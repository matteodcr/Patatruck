package info3.game.graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.charset.StandardCharsets;

import javax.imageio.ImageIO;

public class AwtFont {

	private final int scaleFactor;

	private static class Grapheme {
		final Image scaledSprite;
		final int width;

		private Grapheme(Image scaledSprite, int width) {
			this.scaledSprite = scaledSprite;
			this.width = width;
		}
	}

	private final Grapheme[] chars = new Grapheme[256];

	public AwtFont(int scaleFactor, File file, int spriteSize) {
		this.scaleFactor = scaleFactor;
		try {
			BufferedImage img = ImageIO.read(file);
			for (int i = 0; i < 256; i++) {
				int y = (i / 16) * spriteSize;
				int x = (i % 16) * spriteSize;
				tryInsertChar(scaleFactor, i, img.getSubimage(x, y, spriteSize, spriteSize));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static int getWidth(BufferedImage img) {
		for (int x = img.getWidth() - 1; x >= 0; x--) {
			for (int y = 0; y < img.getHeight(); y++) {
				if ((img.getRGB(x, y) & 0xff000000) != 0) {
					return x + 1;
				}
			}
		}

		return 0;
	}

	private void tryInsertChar(int scaleFactor, int c, BufferedImage sprite) {
		Grapheme g = null;

		if (c == ' ') {
			g = new Grapheme(new BufferedImage(2, sprite.getHeight(), BufferedImage.TYPE_INT_ARGB), 2);
		}

		int width = getWidth(sprite);
		if (width != 0 && c != ' ') {
			g = new Grapheme(sprite.getSubimage(0, 0, width, sprite.getHeight())
					.getScaledInstance(width * scaleFactor, -1, Image.SCALE_REPLICATE), width);
		}

		if (g != null)
			chars[c] = g;
	}

	private Grapheme getSpriteFor(byte c) {
		int cInt = c;
		if (c < 0)
			cInt += 256;

		Grapheme actual = chars[cInt];
		if (actual == null)
			return getSpriteFor((byte) '?');

		return actual;
	}

	public int measureText(String text) {
		byte[] bytes = text.getBytes(StandardCharsets.ISO_8859_1);

		int x = 0;
		for (byte b : bytes) {
			x += getSpriteFor(b).width + 1;
		}

		return x - 1;
	}

	public void drawText(AwtGraphics g, String text, int x, int y) {
		byte[] bytes = text.getBytes(StandardCharsets.ISO_8859_1);

		for (byte b : bytes) {
			Grapheme sprite = getSpriteFor(b);
			g.drawSpriteRealCoords(sprite.scaledSprite, x * scaleFactor, y * scaleFactor);
			x += sprite.width + 1;
		}
	}
}
