package info3.game.entity;

import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.Direction;
import info3.game.position.PositionF;
import info3.game.scene.Scene;

public class CarEntity extends Entity {
	boolean isTruck;
	Direction dir;

	CarEntity(Scene parent, PositionF position, boolean isTruck, Direction direction) {
		super(parent, position);
		this.isTruck = isTruck;
		this.dir = direction;
	}

	@Override
	public EntityType getType() {
		return EntityType.CAR;
	}

	@Override
	public boolean pop(AutDirection direction) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		return changePos(this.dir);
	}

	public boolean move(AutDirection direction) {
		switch (direction) {
		case L: {
			this.changeDir(direction);
			return changePos(this.dir);
		}
		case F: {
			return changePos(this.dir);
		}
		case R: {
			this.changeDir(direction);
			return changePos(this.dir);
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

	boolean changePos(Direction direction) {
		switch (direction) {
		case NORD: {
			PositionF newPos = new PositionF(0, -parentScene.getTileWidth());
			position.add(newPos);
			return true;
		}
		case SUD: {
			PositionF newPos = new PositionF(-parentScene.getTileWidth(), 0);
			position.add(newPos);
			return true;
		}
		case OUEST: {
			PositionF newPos = new PositionF(parentScene.getTileWidth(), 0);
			position.add(newPos);
			return true;
		}
		case EST: {
			PositionF newPos = new PositionF(0, parentScene.getTileWidth());
			position.add(newPos);
			return true;
		}
		default:
			return false;
		}
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
