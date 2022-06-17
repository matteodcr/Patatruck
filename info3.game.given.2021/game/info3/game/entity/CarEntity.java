fpackage info3.game.entity;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.PositionF;
import info3.game.scene.Scene;

public class CarEntity extends Entity {
	boolean isTruck;

	public CarEntity(Scene parent, PositionF position, boolean isTruck) {
		super(parent, position);
		this.isTruck = isTruck;
		automaton = parentScene.setupAutomaton("Car");
		current_state = automaton.initial;
		category = AutCategory.A;
	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(Sprite.CAR_ENTITY, this.position.getX(), this.position.getY());
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
		AutDirection newDirection = convertRelativToAbsolutedir(direction);
		m_direction = newDirection;
		switch (newDirection) {
		case N: {
			PositionF newPos = new PositionF(0, -1);
			this.position = position.add(newPos);
			return true;
		}
		case W: {
			PositionF newPos = new PositionF(-1, 0);
			this.position = position.add(newPos);
			return true;
		}
		case E: {
			PositionF newPos = new PositionF(1, 0);
			this.position = position.add(newPos);
			return true;
		}
		case S: {
			PositionF newPos = new PositionF(0, 1);
			this.position = position.add(newPos);
			return true;
		}
		default:
			return false;
		}

	}

	public boolean move(AutDirection direction) {
		AutDirection newDirection = convertRelativToAbsolutedir(direction);
		m_direction = newDirection;
		switch (newDirection) {
		case N: {
			PositionF newPos = new PositionF(0, -1);
			this.position = position.add(newPos);
			return true;
		}
		case W: {
			PositionF newPos = new PositionF(-1, 0);
			this.position = position.add(newPos);
			return true;
		}
		case E: {
			PositionF newPos = new PositionF(1, 0);
			this.position = position.add(newPos);
			return true;
		}
		case S: {
			PositionF newPos = new PositionF(0, 1);
			this.position = position.add(newPos);
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
	
	@Override
	public boolean cell(AutDirection direction, AutCategory category) {
		AutDirection newDirection = convertRelativToAbsolutedir(direction);
		switch (newDirection) {
		case N: {
			if (gridY >= 1) {
				if (parentScene.getTileAt(gridX, gridY - 1) != null
						&& parentScene.getTileAt(gridX, gridY - 1).category == category) {
					return true;
				}
			}
			break;
		}
		case W: {
			if (gridX >= 1) {
				if (parentScene.getTileAt(gridX - 1, gridY) != null
						&& parentScene.getTileAt(gridX - 1, gridY).category == category) {
					return true;
				}
			}
			break;
		}
		case E: {
			if (gridX <= 8) {
				if (parentScene.getTileAt(gridX + 1, gridY) != null
						&& parentScene.getTileAt(gridX + 1, gridY).category == category) {
					return true;
				}
			}
			break;
		}
		case S: {
			if (gridY <= 2) {
				if (parentScene.getTileAt(gridX, gridY + 1) != null
						&& parentScene.getTileAt(gridX, gridY + 1).category == category) {
					return true;
				}
			}
			break;
		}
		case H: {
			if ((gridX >= 1) && (gridY >= 1) && (gridX <= 8) && (gridY <= 2)) {
				if (parentScene.getTileAt(gridX, gridY) != null
						&& parentScene.getTileAt(gridX, gridY).category == category) {
					return true;
				}
			}
			break;
		}
		default:
			return false;

		}
		return false;

	}
}
