package entity;

import position.Direction.dir;
import position.PositionF;

public class CookEntity extends Entity {
	// Item holding; Class Item TODO

	CookEntity(Scene parent, PositionF position) {
		super(parent, position);
	}

	// @Override
	boolean wizz(dir direction) {
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
