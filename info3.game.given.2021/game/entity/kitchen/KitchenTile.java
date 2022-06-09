package entity.kitchen;

import entity.Tile;
import position.Direction;

public abstract class KitchenTile extends Tile {
	Direction dir;

	abstract void onInteract();
}
