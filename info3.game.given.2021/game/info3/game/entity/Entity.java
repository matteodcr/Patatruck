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
import info3.game.scene.Scene;

public abstract class Entity implements AutomatonListener {
	Scene parentScene = null;
	PositionF position;
	AutDirection m_direction;
	int gridX, gridY;
	GAutomaton automaton;
	int deathTime = 0;
	int move_timer = 0, move_timer_max = 0; // allows to move only when move_timer==0
	GState current_state;

	AutCategory category;

	Entity(Scene parent, PositionF pos, int gX, int gY) {
		parentScene = parent;
		position = pos;
		gridX = gX;
		gridY = gY;
		parentScene.addEntity(this);
		m_direction = AutDirection.N;
	}

	void setPosition(PositionF pos) {
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
			switch (m_direction) {
			case N:
				return AutDirection.N;
			case W:
				return AutDirection.W;
			case E:
				return AutDirection.E;
			case S:
				return AutDirection.S;
			default:
				break;
			}
		case B:
			switch (m_direction) {
			case N:
				return AutDirection.S;
			case W:
				return AutDirection.E;
			case E:
				return AutDirection.W;
			case S:
				return AutDirection.N;
			default:
				break;
			}
		case L:
			switch (m_direction) {
			case N:
				return AutDirection.W;
			case W:
				return AutDirection.S;
			case E:
				return AutDirection.N;
			case S:
				return AutDirection.E;
			default:
				break;
			}
		case R:
			switch (m_direction) {
			case N:
				return AutDirection.E;
			case W:
				return AutDirection.N;
			case E:
				return AutDirection.S;
			case S:
				return AutDirection.W;
			default:
				break;
			}
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
		AutDirection newDirection = convertRelativToAbsolutedir(direction);
		for (Entity entity : parentScene.entity_list) {
			switch (newDirection) {
			case N: {
				if (gridY >= 1) {
					if (entity.isItThatGrid(gridY - 1, gridX) && entity.category == category) {
						return true;
					}
				}
				break;
			}
			case W: {
				if (gridX >= 1) {
					if (entity.isItThatGrid(gridY, gridX - 1) && entity.category == category) {
						return true;
					}
				}
				break;
			}
			case E: {
				if (gridX <= 8) {
					if (entity.isItThatGrid(gridY, gridX + 1) && entity.category == category) {
						return true;
					}
				}
				break;
			}
			case S: {
				if (gridY <= 2) {
					if (entity.isItThatGrid(gridY + 1, gridX) && entity.category == category) {
						return true;
					}
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
		return false;
	}

	private boolean isItThatGrid(int gY, int gX) {
		return gY == gridY && gX == gridX;
	}

	@Override
	public boolean key(AutKey direction) {
		return parentScene.m_game.m_listener.isUp(direction.toString());
	}
}
