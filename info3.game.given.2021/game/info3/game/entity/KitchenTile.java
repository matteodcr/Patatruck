package info3.game.entity;

import info3.game.position.Direction;

public abstract class KitchenTile extends Tile {
	Direction dir;

	abstract void onInteract();
}
