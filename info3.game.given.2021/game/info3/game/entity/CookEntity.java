package info3.game.entity;

import java.io.IOException;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.PositionF;
import info3.game.scene.Scene;

public class CookEntity extends Entity {
	// Item holding; Class Item TODO

	long m_imageElapsed;
	long m_moveElapsed;
	int m_imageIndex;

	public CookEntity(Scene parent, PositionF position) throws IOException {
		super(parent, position);
		move_timer_max = 100;
		automaton = parentScene.setupAutomaton("Cook");
		current_state = automaton.initial;
		category = AutCategory.AROBASE;
		gridX = 1;
		gridY = 1;
	}

	@Override
	public void render(Graphics g) {
		if (m_direction == AutDirection.N) {
			g.drawSprite(Sprite.PLAYER_KITCHEN_N, this.position.getX(), this.position.getY());
		} else if (m_direction == AutDirection.E) {
			g.drawSprite(Sprite.PLAYER_KITCHEN_E, this.position.getX(), this.position.getY());
		} else if (m_direction == AutDirection.W) {
			g.drawSprite(Sprite.PLAYER_KITCHEN_W, this.position.getX(), this.position.getY());
		} else {
			g.drawSprite(Sprite.PLAYER_KITCHEN_S, this.position.getX(), this.position.getY());
		}
	}

	@Override
	public boolean pop(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		switch (direction) {
		case N: {
			PositionF newPos = new PositionF(0, -parentScene.getTileWidth());
			gridY--;
			this.position = position.add(newPos);
			return true;
		}
		case W: {
			PositionF newPos = new PositionF(-parentScene.getTileWidth(), 0);
			gridX--;
			this.position = position.add(newPos);
			return true;
		}
		case E: {
			PositionF newPos = new PositionF(parentScene.getTileWidth(), 0);
			gridX++;
			this.position = position.add(newPos);
			return true;
		}
		case S: {
			PositionF newPos = new PositionF(0, parentScene.getTileWidth());
			gridY++;
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
		boolean c = super.cell(direction, category);
		m_direction = direction;
		return c;
	}
}
