package info3.game.entity;

import info3.game.position.AutDirection;
import info3.game.position.Direction;
import info3.game.scene.KitchenScene;
import info3.game.scene.Scene;

public class SauceTableTile extends KitchenTile {
	Sauce sauce = null; // ketchup ou mayo

	public SauceTableTile(Scene parent, int gridX, int gridY, Direction d, Sauce sauce) {
		super(parent, gridX, gridY, null, d);
		this.sauce = sauce;
	}// TODO sprite à ajouter

	@Override
	public boolean pop(AutDirection direction) {
		return true;
	}

	@Override
	public boolean wizz(AutDirection direction) {// mettre la sauce
		Item item = ((KitchenScene) this.parentScene).getCook().item;
		if (item == null || !(item.allowSauce(this.sauce))) {
			return false;
		} else {
			// ajouter la sauce sur item
			return true;
		}
	}

}
