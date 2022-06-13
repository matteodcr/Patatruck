package info3.game.entity;

import info3.game.position.Direction;
import info3.game.scene.Scene;

public class StockTable extends KitchenTile {

	public StockTable(Scene parent, int gridX, int gridY, Direction d) {
		super(parent, gridX, gridY, null, d);
		// TODO Remplacer null par le sprite de la StockTable
	}

}
