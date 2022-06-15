package info3.game.entity;

import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.Direction;
import info3.game.position.PositionF;
import info3.game.scene.Scene;

public class CafardEntity extends Entity {

	CafardEntity(Scene parent, PositionF position) {
		super(parent, position);
	}

	boolean egg(PositionF position) {
		Entity nouveau_carfard = new CafardEntity(this.parentScene, position);
		return this.parentScene.addEntity(nouveau_carfard);
	}

	boolean move(Direction direction) {
		switch (direction) {
		case NORD: {
			float x = position.getX();
			float y = position.getY();
			PositionF newPos = new PositionF(0, -parentScene.getTileWidth());
			// Tile nextTile = parent.getTile(x, y - parent.getTileWidth()); // On récupère
			// la tile où le cuisinier veut se déplacer
			// On gère plus tard les colisions, la vérification que la tile soit libre
			position.add(newPos);
			return true;
		}
		case OUEST: {
			float x = position.getX();
			float y = position.getY();
			PositionF newPos = new PositionF(-parentScene.getTileWidth(), 0);
			// Tile nextTile = parent.getTile(x - parent.getTileWidth(), y); // On récupère
			// la tile où le cuisinier veut se déplacer
			// On gère plus tard les colisions, la vérification que la tile soit libre
			position.add(newPos);
			return true;
		}
		case EST: {
			float x = position.getX();
			float y = position.getY();
			PositionF newPos = new PositionF(parentScene.getTileWidth(), 0);
			// Tile nextTile = parent.getTile(x + parent.getTileWidth(), y); // On récupère
			// la tile où le cuisinier veut se déplacer
			// On gère plus tard les colisions, la vérification que la tile soit libre
			position.add(newPos);
			return true;
		}
		case SUD: {
			float x = position.getX();
			float y = position.getY();
			PositionF newPos = new PositionF(0, parentScene.getTileWidth());
			// Tile nextTile = parent.getTile(x, y + parent.getTileWidth()); // On récupère
			// la tile où le cuisinier veut se déplacer
			// On gère plus tard les colisions, la vérification que la tile soit libre
			position.add(newPos);
			return true;
		}
		default:
			return false;
		}

	}

	@Override
	public boolean pop(AutDirection direction) {
		return egg(position);
	}

	@Override
	public boolean wizz(AutDirection direction) {
		return move(direction);
	}

	@Override
	public boolean gwait() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean egg(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hit(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean jump(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean explode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pick(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean power() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean protect(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean store() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean turn(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gthrow(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean myDir(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closest(AutCategory category, AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gotPower() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gotStuff() {
		// TODO Auto-generated method stub
		return false;
	}
}
