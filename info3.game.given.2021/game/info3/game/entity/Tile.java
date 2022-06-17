package info3.game.entity;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.PositionF;
import info3.game.scene.Scene;

// TODO extends Entity
public abstract class Tile extends Entity {
	protected Sprite defaultSprite;

	protected Tile(Scene parent, int gridX, int gridY) {
		this(parent, gridX, gridY, null);
	}

	protected Tile(Scene parent, int gridX, int gridY, Sprite defaultSprite) {
		super(parent, new PositionF(gridX, gridY).mul(parent.getTileWidth()));
		this.defaultSprite = defaultSprite;
	}

	public void render(Graphics g) {
		if (defaultSprite != null)
			g.drawSprite(defaultSprite, 0, 0);
	}
}
