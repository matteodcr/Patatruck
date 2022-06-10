package info3.game.entity;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;

// TODO extends Entity
public abstract class Tile {
	protected final Sprite defaultSprite;

	protected Tile() {
		this.defaultSprite = null;
	}

	protected Tile(Sprite defaultSprite) {
		this.defaultSprite = defaultSprite;
	}

	public void render(Graphics g) {
		if (defaultSprite != null)
			g.drawSprite(defaultSprite, 0, 0);
	}
}
