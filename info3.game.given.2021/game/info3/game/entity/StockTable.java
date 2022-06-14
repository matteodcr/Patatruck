package info3.game.entity;

import info3.game.position.AutDirection;
import info3.game.position.Direction;
import info3.game.scene.KitchenScene;
import info3.game.scene.Scene;

public class StockTable extends KitchenTile {
	Item item;
	int stock;

	public StockTable(Scene parent, int gridX, int gridY, Direction d,Item item) {
		super(parent, gridX, gridY, null, d);
		this.stock = 0;
		this.item;
		// TODO sprite de la StockTable
	}

	@Override
	public boolean pop(AutDirection direction) { // prendre un aliment
		Entity player = ((KitchenScene) this.parentScene).getCook();
		if (stock == 0 || player.item != null) {
			return false;
		} else {
			player.item = this.item;
			stock--;
			return true;
		}
	}

	@Override
	public boolean wizz(AutDirection direction) {
		return true;
	}

	public int getStock() {
		return this.stock;
	}

	public int addStock() {
		return ++this.stock;
	}

}
