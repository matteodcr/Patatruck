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

	Entity(Scene parent, PositionF pos) {
		parentScene = parent;
		position = pos;
		m_direction = AutDirection.N;

		automaton = parentScene.m_game.getBoundAutomaton(getType());
		current_state = automaton.initial;
	}

	public abstract EntityType getType();

	public void setPosition(PositionF pos) {
		position = pos;
	}

	PositionF getPosition() {
		return position;
	}

	public void tick(long elapsed) {
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
		int gridX = getGridFromPos(position).getX();
		int gridY = getGridFromPos(position).getY();
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

				if ((gridX >= 1) && (gridY >= 1) && (gridX <= 8) && (gridY <= 2)) {
					if (entity.isItThatGrid(gridY, gridX) && entity.category == category) {
						return true;
					}
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
			if (direction == AutDirection.F && category == AutCategory.AROBASE)
				// System.out.println("H");
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

	private boolean isItThatGrid(int gY, int gX) {
		return gY == getGridFromPos(position).getY() && gX == getGridFromPos(position).getX();
	}

	@Override
	public boolean key(AutKey direction) {
		return parentScene.m_game.m_listener.isUp(direction.toString());
	}

	private PositionI getGridFromPos(PositionF pos) {
		PositionF pos_tmp = pos.add(parentScene.getOriginOffset());
		return pos_tmp.divFloor(parentScene.getTileWidth());

	}

}
