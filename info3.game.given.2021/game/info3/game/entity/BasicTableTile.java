package info3.game.entity;

import info3.game.position.Direction;
import info3.game.scene.Scene;

public class BasicTableTile extends KitchenTile {

	public BasicTableTile(Scene parent, int gridX, int gridY, Direction d) {
		super(parent, gridX, gridY, null, d);
	}// TODO sprite à ajouter

}
