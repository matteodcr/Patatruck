package info3.game.entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import info3.game.automata.AutomatonListener;
import info3.game.automata.GAutomaton;
import info3.game.automata.GState;
import info3.game.content.Item;
import info3.game.graphics.Graphics;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.AutKey;
import info3.game.position.PositionF;
import info3.game.scene.Scene;

public abstract class Entity implements AutomatonListener {
	Scene parentScene = null;
	PositionF position;
	AutDirection m_direction;
	GAutomaton automaton;
	int deathTime = 0;
	int move_timer = 0, move_timer_max = 0; // allows to move only when move_timer==0
	GState current_state;
	long start, finish, timeElapsed;

	AutCategory category;
	public Item item;
	boolean isTruck = false;
	boolean isPlayer = false;

	// If different from what `getType` returns, we should replace the automaton
	// This is checked at each tick
	EntityType lastEntityType = null;

	Entity(Scene parent, PositionF pos) {
		parentScene = parent;
		position = pos;
		m_direction = AutDirection.N;
		item = null;
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
			current_state = automaton.initial;
			lastEntityType = entityType;
		}

		GState state = automaton.run(this, current_state);
		if (state != null) {
			current_state = state;
		}
		if (current_state.name.equals(""))
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

	public boolean canMove() {
		return move_timer == 0;
	}

	public void hasMoved() {
		this.move_timer = move_timer_max;
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

	@Override
	public boolean move(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cell(AutDirection direction, AutCategory category) {
		int gridX = getGridPosFromPos().getX();
		int gridY = getGridPosFromPos().getY();
		AutDirection newDirection = convertRelativToAbsolutedir(direction);
		for (Entity entity : parentScene.entity_list) {
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

	public PositionI getGridPosFromPos() {
		PositionF pos_tmp = position.add(parentScene.getOriginOffset());
		return pos_tmp.divFloor(parentScene.getTileWidth());

	}

	// To handle corner equipments cases in the kitchen
	public Entity selectEntityToInteractWith() {
		int gridX = getGridPosFromPos().getX();
		int gridY = getGridPosFromPos().getY();
		for (Entity entity : parentScene.entity_list) {
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
		return null; // no entity fills the criteria
	}

	public void setDirection(AutDirection absDirection) {
		this.m_direction = absDirection;
	/*
	 * Fct qui renvoit la grille correspondante à la position de l'ENTITE en pixels.
	 * 
	 */
	public PositionI getGridPosFromPos() {
		PositionF pos_tmp = position.add(parentScene.getOriginOffset());
		if (pos_tmp.getX() < 0 || pos_tmp.getY() < 0) {
			pos_tmp = pos_tmp.divFloorF(parentScene.getTileWidth());
			float pos_tmp_x = pos_tmp.getX();
			float pos_tmp_y = pos_tmp.getY();
			int pos_x = (int) pos_tmp_x;
			int pos_y = (int) pos_tmp_y;
			if (pos_tmp_x < 0)
				pos_x = (int) Math.floor(pos_tmp_x);
			if (pos_tmp_y < 0)
				pos_y = (int) Math.floor(pos_tmp_y);
			return new PositionI(pos_x, pos_y);
		} else {
			return pos_tmp.divFloor(parentScene.getTileWidth());
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

	public boolean getIsTruck() {
		return this.isTruck;
	}

	public boolean getIsPlayer() {
		return this.isPlayer;
	}
}
