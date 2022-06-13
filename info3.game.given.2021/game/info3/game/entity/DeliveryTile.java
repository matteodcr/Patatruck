package info3.game.entity;

import info3.game.position.Direction;
import info3.game.scene.Scene;

public class DeliveryTile extends KitchenTile {

	// IList<Item> assiette;
	// Recipe recette;

	protected DeliveryTile(Scene parent, int gridX, int gridY, Direction d) {
		super(parent, gridX, gridY, null, d);
		// TODO Auto-generated constructor stub
	}

	boolean wizz(Direction direction) {
		// TODO selon l'automate c'est graphique
		return true;
	}

	boolean pop(Direction direction) {
		// TODO selon l'automate c'est graphique
		return true;
	}

	boolean recetteReady() {
		// TODO Check si il y a les items nécessaires pour la recette
		// Cette méthode sera appelée à chaque fois qu'un item est déposé
		return true;
	}

	/*
	 * TODO void addItem(Item item) { // TODO }
	 * 
	 * TODO Item removeItem() { // TODO }
	 */

}
