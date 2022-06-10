package info3.game.entity;

import info3.game.position.Direction;
import info3.game.position.PositionF;
import info3.game.scene.Scene;

public class CafardEntity extends Entity {
	// Item holding; Class Item TODO

	CafardEntity(Scene parent, PositionF position) {
		super(parent, position);
	}

	boolean wizz() {
		// effet graphique

	}

	boolean pop() {
		// effet graphique

	}

	boolean egg() {

	}

	boolean move(Direction direction) {
		switch (direction) {
		case NORD: {
			// float x = position.getX();
			float y = position.getY();
			// Tile nextTile = parent.getTile(x, y - parent.getTileWidth()); // On récupère
			// la tile où le cuisinier veut se déplacer
			// On gère plus tard les colisions, la vérification que la tile soit libre
			position.setY(y - parent.getTileWidth());
			return true;
			break;
		}
		case OUEST: {
			float x = position.getX();
			// float y = position.getY();
			// Tile nextTile = parent.getTile(x - parent.getTileWidth(), y); // On récupère
			// la tile où le cuisinier veut se déplacer
			// On gère plus tard les colisions, la vérification que la tile soit libre
			position.setX(x - parent.getTileWidth());
			return true;
			break;
		}
		case EST: {
			float x = position.getX();
			// float y = position.getY();
			// Tile nextTile = parent.getTile(x + parent.getTileWidth(), y); // On récupère
			// la tile où le cuisinier veut se déplacer
			// On gère plus tard les colisions, la vérification que la tile soit libre
			position.setX(x + parent.getTileWidth());
			return true;
			break;
		}
		case SUD: {
			// float x = position.getX();
			float y = position.getY();
			// Tile nextTile = parent.getTile(x, y + parent.getTileWidth()); // On récupère
			// la tile où le cuisinier veut se déplacer
			// On gère plus tard les colisions, la vérification que la tile soit libre
			position.setY(y + parent.getTileWidth());
			return true;
			break;
		}
		default:
			return false;
		}

	}
}
