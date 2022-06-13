package info3.game.entity;

import info3.game.graphics.Sprite;
import info3.game.position.Direction;
import info3.game.scene.Scene;

public abstract class KitchenTile extends Tile {
	private Direction direction;

	protected KitchenTile(Scene parent, int gridX, int gridY, Sprite sprite, Direction d) {
		super(parent, gridX, gridY, sprite);
		setDirection(d);
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
