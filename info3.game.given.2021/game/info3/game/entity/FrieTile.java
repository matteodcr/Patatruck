package info3.game.entity;

import info3.game.position.AutDirection;
import info3.game.position.Direction;
import info3.game.scene.KitchenScene;
import info3.game.scene.Scene;

public class FrieTile extends KitchenTile {
	Item item;
	int compteur;
	// TODO sprite à ajouter

	public FrieTile(Scene parent, int gridX, int gridY, Direction d) {
		super(parent, gridX, gridY, null, d);
		this.item = null;
		this.compteur = 0;
	}

	@Override
	public boolean pop(AutDirection direction) {// prend un ingrédiant à frire
		if (this.item != null) {
			return false;
		} else {
			Entity player = ((KitchenScene) this.parentScene).getCook();
			this.item = player.item;
			player.item = null;
			this.compteur = 10;
			return true;
		}
	}

	@Override
	public boolean wizz(AutDirection direction) {// rend l'ingrédient au joueur
		Entity player = ((KitchenScene) this.parentScene).getCook();
		player.item = this.item;
		this.item = null;
		return true;

	}

	public boolean wait() {
		if (this.compteur > 0)
			this.compteur ++;
			return false;
		else
			return true;
	}

}
