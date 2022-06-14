package info3.game.entity;

import java.io.IOException;
import java.util.List;

import info3.game.automata.GAutomaton;
import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.PositionF;
import info3.game.scene.KitchenScene;
import info3.game.scene.Scene;

public class CookEntity extends Entity {
	// Item holding; Class Item TODO

	// BufferedImage[] m_images; Utile ?
	long m_imageElapsed;
	long m_moveElapsed;
	int m_imageIndex;
	AutDirection m_direction;

	public CookEntity(Scene parent, PositionF position) throws IOException {
		super(parent, position);
		// m_images = loadSprite("resources/winchester-4x6.png", 4, 6); Utile ?
		move_timer_max = 100;
		List<GAutomaton> automata_list = parent.m_game.automata_list;
		for (GAutomaton current_automaton : automata_list) {
			if (current_automaton.name.equals("Cook")) {
				automaton = current_automaton;
				break;
			}
		}
		current_state = automaton.initial;
		parentScene.addEntity(this);
		category = AutCategory.J;
		gridX = 1;
		gridY = 1;
	}

	@Override
	public void render(Graphics g) {
		// BufferedImage img = m_images[m_imageIndex]; Utile ?
		g.drawSprite(Sprite.PLAYER_KITCHEN, this.position.getX(), this.position.getY());
	}

	@Override
	public boolean pop(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		// TODO : Les colisions
		switch (direction) {
		case N: {
			if (gridY >= 1) {
				if (((KitchenScene) parentScene).KitchenGrid[gridY - 1][gridX] == null) {
					PositionF newPos = new PositionF(0, -parentScene.getTileWidth());
					m_direction = direction;
					gridY--;
					// TODO : Tourner Sprite
					this.position = position.add(newPos);
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		case W: {
			if (gridX >= 1) {
				if (((KitchenScene) parentScene).KitchenGrid[gridY][gridX - 1] == null) {
					PositionF newPos = new PositionF(-parentScene.getTileWidth(), 0);
					m_direction = direction;
					gridX--;
					// TODO : Tourner Sprite
					this.position = position.add(newPos);
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		case E: {
			if (gridX <= 8) {
				if (((KitchenScene) parentScene).KitchenGrid[gridY][gridX + 1] == null) {
					PositionF newPos = new PositionF(parentScene.getTileWidth(), 0);
					m_direction = direction;
					gridX++;
					// TODO : Tourner Sprite
					this.position = position.add(newPos);
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		case S: {
			if (gridY <= 1) {
				if (((KitchenScene) parentScene).KitchenGrid[gridY + 1][gridX] == null) {
					PositionF newPos = new PositionF(0, parentScene.getTileWidth());
					m_direction = direction;
					gridY++;
					// TODO : Tourner Sprite
					this.position = position.add(newPos);
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
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
