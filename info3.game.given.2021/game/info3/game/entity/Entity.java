package info3.game.entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import info3.game.automata.AutomatonListener;
import info3.game.automata.GAutomaton;
import info3.game.automata.GState;
import info3.game.graphics.Graphics;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.AutKey;
import info3.game.position.PositionF;
import info3.game.position.PositionI;
import info3.game.scene.KitchenScene;
import info3.game.scene.Scene;

public abstract class Entity implements AutomatonListener {
	Scene parentScene;

	GAutomaton automaton;
	GState currentState;

	AutDirection m_direction;
	AutCategory category;

	PositionF position;

	int deathTime = 0;
	long start, finish, timeElapsed, timerToWait = 0;

	// If different from what `getType` returns, we should replace the automaton
	// This is checked at each tick
	EntityType lastEntityType = null;

	Entity(Scene parent, PositionF pos) {
		parentScene = parent;
		position = pos;
		m_direction = AutDirection.N;

		automaton = parentScene.m_game.getBoundAutomaton(getType());
		currentState = automaton.initial;
	}

	public abstract EntityType getType();

	public void setPosition(PositionF pos) {
		position = pos;
	}

	public PositionF getPosition() {
		return position;
	}

	public void tick(long elapsed) {
		EntityType entityType = getType();
		if (lastEntityType != entityType) {
			automaton = parentScene.m_game.getBoundAutomaton(entityType);
			currentState = automaton.initial;
			lastEntityType = entityType;
		}

		GState state = automaton.run(this, currentState);
		if (state != null) {
			currentState = state;
		}
		if (currentState.name.equals(""))
			parentScene.removeEntity(this);
	}

	void destroySpin() {
		// TODO
	}

	void destroyCrush() {
		// TODO
	}

	public void render(Graphics g) {
		// TODO
	}

	public static BufferedImage[] loadSprite(String filename, int nrows, int ncols) throws IOException {
		File imageFile = new File(filename);
		if (imageFile.exists()) {
			BufferedImage image = ImageIO.read(imageFile);
			int width = image.getWidth(null) / ncols;
			int height = image.getHeight(null) / nrows;

			BufferedImage[] images = new BufferedImage[nrows * ncols];
			for (int i = 0; i < nrows; i++) {
				for (int j = 0; j < ncols; j++) {
					int x = j * width;
					int y = i * height;
					images[(i * ncols) + j] = image.getSubimage(x, y, width, height);
				}
			}
			return images;
		}
		return null;
	}

	public AutDirection convertRelativToAbsolutedir(AutDirection direction) {
		switch (direction) {
		case F:
			return m_direction;
		case B:
			return m_direction.twoapart();
		case L:
			return m_direction.previous();
		case R:
			return m_direction.next();
		default:
			break;
		}
		return direction;
	}

	/**
	 * Allows to move tile by tile
	 */
	@Override
	public boolean move(AutDirection direction) {
		finish = System.currentTimeMillis();
		timeElapsed = finish - start;
		float shift = 0;
		if (parentScene instanceof KitchenScene)
			shift = parentScene.getTileWidth();
		else
			shift = 1;

		if (timeElapsed >= timerToWait) {
			AutDirection newDirection = convertRelativToAbsolutedir(direction);
			m_direction = newDirection;
			switch (newDirection) {
			case N: {
				PositionF newPos = new PositionF(0, -shift);
				this.position = position.add(newPos);
				start = System.currentTimeMillis();

				return true;
			}
			case W: {
				PositionF newPos = new PositionF(-shift, 0);
				this.position = position.add(newPos);
				start = System.currentTimeMillis();

				return true;
			}
			case E: {
				PositionF newPos = new PositionF(shift, 0);
				this.position = position.add(newPos);
				start = System.currentTimeMillis();

				return true;
			}
			case S: {
				PositionF newPos = new PositionF(0, shift);
				this.position = position.add(newPos);
				start = System.currentTimeMillis();

				return true;
			}
			default:
				start = System.currentTimeMillis();

				return false;
			}
		}
		return false;
	}

	@Override
	public boolean cell(AutDirection direction, AutCategory category) {
		int gridX = getGridPosFromPos().getX();
		int gridY = getGridPosFromPos().getY();
		AutDirection newDirection = convertRelativToAbsolutedir(direction);
		// Les entités qui bougent
		for (Entity entity : parentScene.entityList) {
			switch (newDirection) {
			case N: {
				if (entity.isItThatGrid(gridY - 1, gridX) && entity.category == category) {
					return true;
				}
				break;
			}
			case W: {
				if (entity.isItThatGrid(gridY, gridX - 1) && entity.category == category) {
					return true;
				}
				break;
			}
			case E: {
				if (entity.isItThatGrid(gridY, gridX + 1) && entity.category == category) {
					return true;
				}
				break;
			}
			case S: {
				if (entity.isItThatGrid(gridY + 1, gridX) && entity.category == category) {
					return true;
				}
				break;
			}
			case H: {
				if (entity.isItThatGrid(gridY, gridX) && entity.category == category) {
					return true;
				}
				break;
			}
			default:
				return false;
			}
		}
		// Les Tiles qui ne bougent pas
		switch (newDirection) {
		case N: {
			if (parentScene.getTileAt(gridX, gridY - 1) != null
					&& parentScene.getTileAt(gridX, gridY - 1).category == category) {
				return true;
			}
			break;
		}
		case W: {
			if (parentScene.getTileAt(gridX - 1, gridY) != null
					&& parentScene.getTileAt(gridX - 1, gridY).category == category) {
				return true;
			}
			break;
		}
		case E: {
			if (parentScene.getTileAt(gridX + 1, gridY) != null
					&& parentScene.getTileAt(gridX + 1, gridY).category == category) {
				return true;
			}
			break;
		}
		case S: {
			if (parentScene.getTileAt(gridX, gridY + 1) != null
					&& parentScene.getTileAt(gridX, gridY + 1).category == category) {
				return true;
			}
			break;
		}
		case H: {
			if (parentScene.getTileAt(gridX, gridY) != null
					&& parentScene.getTileAt(gridX, gridY).category == category) {
				return true;
			}
			break;
		}
		default:
			return false;
		}
		return false;
	}

	private boolean isItThatGrid(int gY, int gX) {
		return gY == getGridPosFromPos().getY() && gX == getGridPosFromPos().getX();
	}

	@Override
	public boolean key(AutKey direction) {
		return parentScene.m_game.m_listener.isUp(direction.toString());
	}

	// To handle corner equipments cases in the kitchen
	public Entity selectEntityToInteractWith() {
		int gridX = getGridPosFromPos().getX();
		int gridY = getGridPosFromPos().getY();
		for (Entity entity : parentScene.entityList) {
			switch (m_direction) {
			case N:
				if (entity.isItThatGrid(gridY - 1, gridX) && entity.m_direction == AutDirection.S)
					return entity;
				break;
			case W:
				if (entity.isItThatGrid(gridY, gridX - 1) && entity.m_direction == AutDirection.E)
					return entity;
				break;
			case E:
				if (entity.isItThatGrid(gridY, gridX + 1) && entity.m_direction == AutDirection.W)
					return entity;
				break;
			case S:
				if (entity.isItThatGrid(gridY + 1, gridX) && entity.m_direction == AutDirection.N)
					return entity;
				break;
			default:
				break;
			}
		}
		return null;
	}

	public void setDirection(AutDirection absDirection) {
		this.m_direction = absDirection;
	}

	/*
	 * Fct qui renvoit la grille correspondante à la position de l'ENTITE en pixels.
	 *
	 */
	public PositionI getGridPosFromPos() {
		PositionF posTmp = position.add(parentScene.getOriginOffset());
		if (posTmp.getX() < 0 || posTmp.getY() < 0) {
			posTmp = posTmp.divFloorF(parentScene.getTileWidth());
			float posTmpX = posTmp.getX();
			float posTmpY = posTmp.getY();
			int posX = (int) posTmpX;
			int posY = (int) posTmpY;
			if (posTmpX < 0)
				posX = (int) Math.floor(posTmpX);
			if (posTmpY < 0)
				posY = (int) Math.floor(posTmpY);
			return new PositionI(posX, posY);
		} else {
			return posTmp.divFloor(parentScene.getTileWidth());
		}

	}

	/*
	 * Fct qui renvoit la category de l'entite si la position donnée correspond à la
	 * sienne
	 */
	public AutCategory catAtThisPos(PositionF pos) {
		float posX = position.getX();
		float posY = position.getY();
		if (posX <= pos.getX() && pos.getX() <= posX + 3 && posY <= pos.getY() && pos.getY() <= posY + 3)
			return category;
		return null;
	}

	@Override
	public boolean turn(AutDirection direction) {
		this.m_direction = convertRelativToAbsolutedir(direction);
		return true;
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
	public boolean gthrow(AutDirection direction) {
		return false;
	}

	@Override
	public boolean myDir(AutDirection direction) {
		return direction == m_direction;
	}

	/**
	 * returns if the closest entity is this category and in this direction
	 */
	@Override
	public boolean closest(AutCategory category, AutDirection direction) {
		PositionF closest = null;
		Entity closestEnt = null;
		// searching for the closest
		for (Entity entity : this.parentScene.entityList) {
			if (entity != null && entity.position != null && !(entity instanceof CityTile)) { // not to detect every
																								// road
				if (closest == null) {
					closest = entity.position;
					closestEnt = entity;
				} else if (Math.hypot(closest.getX() - this.position.getX(),
						closest.getY() - this.position.getY()) > Math.hypot(
								entity.position.getX() - this.position.getX(),
								entity.position.getY() - this.position.getY())) {
					closest = entity.position;
					closestEnt = entity;
				}
			}
		}
		if (closest == null || closestEnt == null || closestEnt.category != category)
			return false;
		// checking if in the right direction;
		AutDirection itsDir = null;
		if (closest.getY() >= Math.abs(closest.getX()))
			itsDir = AutDirection.N;
		else if (Math.abs(closest.getY()) >= Math.abs(closest.getX()))
			itsDir = AutDirection.S;
		else if (closest.getX() > 0)
			itsDir = AutDirection.E;
		else
			itsDir = AutDirection.W;
		AutDirection newDirection = convertRelativToAbsolutedir(direction);

		return (newDirection == itsDir);
	}

	@Override
	public boolean gotPower() {
		return false;
	}

	@Override
	public boolean gotStuff() {
		return false;
	}

	@Override
	public boolean gwait() {
		return false;
	}

	@Override
	public boolean explode() {
		return this.parentScene.entityList.remove(this);
	}

}
