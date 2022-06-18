package info3.game.entity;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.PositionF;
import info3.game.scene.Scene;

// TODO extends Entity
public abstract class Tile extends Entity {
	protected Sprite defaultSprite;

	int gridX;
	int gridY;

	protected Tile(Scene parent, int gX, int gY) {
		this(parent, gX, gY, null);
	}

	protected Tile(Scene parent, int gX, int gY, Sprite defaultSprite) {
		super(parent, new PositionF(gX, gY).mul(parent.getTileWidth()).minus(parent.getOriginOffset()));
		this.defaultSprite = defaultSprite;
		gridX = gX;
		gridY = gY;
	}

	public void render(Graphics g) {
		if (defaultSprite != null)
			g.drawSprite(defaultSprite, 0, 0);
	}
}
