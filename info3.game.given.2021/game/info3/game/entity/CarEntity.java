package info3.game.entity;

import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.Direction;
import info3.game.position.PositionF;
import info3.game.scene.Scene;

public class CarEntity extends Entity {
	boolean isTruck;
	Direction dir;
	Sprite sprite;

	CarEntity(Scene parent, PositionF position, boolean isTruck, Direction direction) {
		super(parent, position);
		category = AutCategory.A;
		this.isTruck = isTruck;
		if (isTruck) {
			//sprite = Sprite.CITYTRUCK;
		} else {
			//sprite = Sprite.CAR;
		}
		this.dir = direction;
	}

	@Override
	public EntityType getType() {
		return EntityType.CAR;
	}

	@Override
	public boolean pop(AutDirection direction) {
		return true;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		return changePos(this.dir, parentScene.getTileWidth()/2);
	}

	public boolean move(AutDirection direction) {
		switch (direction) {
		case L: {
			this.changeDir(direction);
			return changePos(this.dir, parentScene.getTileWidth());
		}
		case F: {
			return changePos(this.dir, parentScene.getTileWidth());
		}
		case R: {
			this.changeDir(direction);
			return changePos(this.dir, parentScene.getTileWidth());
		}
		default:
			return false;
		}

	}

	void changeDir(AutDirection direction) {
		switch (direction) {
		case L:
			this.dir = this.dir.previous();

		case R:
			this.dir = this.dir.next();

		default:
			break;
		}

	}

	boolean changePos(Direction direction, int distance) {
		switch (direction) {
		case NORD: {
			PositionF newPos = new PositionF(0, -distance);
			position.add(newPos);
			return true;
		}
		case SUD: {
			PositionF newPos = new PositionF(-distance, 0);
			position.add(newPos);
			return true;
		}
		case OUEST: {
			PositionF newPos = new PositionF(distance, 0);
			position.add(newPos);
			return true;
		}
		case EST: {
			PositionF newPos = new PositionF(0, distance);
			position.add(newPos);
			return true;
		}
		default:
			return false;
		}
	}

	@Override
	public boolean gwait() {
		return false;
	}

	@Override
	public boolean egg(AutDirection direction) {
		return false;
	}

	@Override
	public boolean hit(AutDirection direction) {
		return false;
	}

	@Override
	public boolean jump(AutDirection direction) {
		return false;
	}

	@Override
	public boolean explode() {
		return false;
	}

	@Override
	public boolean pick(AutDirection direction) {
		return false;
	}

	@Override
	public boolean power() {
		return false;
	}

	@Override
	public boolean protect(AutDirection direction) {
		return false;
	}

	@Override
	public boolean store() {
		return false;
	}

	@Override
	public boolean turn(AutDirection direction) {
		return false;
	}

	@Override
	public boolean gthrow(AutDirection direction) {
		return false;
	}

	@Override
	public boolean myDir(AutDirection direction) {
		return false;
	}

	@Override
	public boolean closest(AutCategory category, AutDirection direction) {
		return false;
	}

	@Override
	public boolean gotPower() {
		return false;
	}

	@Override
	public boolean gotStuff() {
		return false;
	}

}
