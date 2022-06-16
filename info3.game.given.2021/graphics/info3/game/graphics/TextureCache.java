package info3.game.graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class TextureCache {

	private final Map<Sprite, Image> scaledSprites = new HashMap<>();
	private final AwtFont font7beige;

	public TextureCache(String resourceDirectory, int scaleFactor) {
		long start = System.currentTimeMillis();
		// Chargement des textures
		for (Sprite.Spritesheet spritesheet : Sprite.Spritesheet.values()) {
			try {
				BufferedImage img = ImageIO.read(new File(resourceDirectory, spritesheet.filename));
				for (Sprite s : Sprite.values()) {
					if (s.spritesheet == spritesheet) {
						int size = spritesheet.tileSize;
						scaledSprites.put(s, img.getSubimage(s.u * size, s.v * size, s.w * size, s.h * size)
								.getScaledInstance(s.w * size * scaleFactor, s.h * size * scaleFactor,
										Image.SCALE_FAST));
					}
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		font7beige = new AwtFont(scaleFactor, new File(resourceDirectory, "font7.png"), 10);

		System.out.printf("Cache de texture construit en %dms%n", System.currentTimeMillis() - start);
	}

	public Map<Sprite, Image> getScaledSprites() {
		return scaledSprites;
	}

	public AwtFont getFont7beige() {
		return font7beige;
	}
}
