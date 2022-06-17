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
		category = AutCategory.AROBASE;
	}

	@Override
	public EntityType getType() {
		return EntityType.COOK;
	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(Sprite.PLAYER_KITCHEN, this.position.getX(), this.position.getY());
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
			m_direction = direction;
			// TODO : Tourner Sprite
			this.position = position.add(newPos);
			return true;
		}
		case W: {
			PositionF newPos = new PositionF(-parentScene.getTileWidth(), 0);
			m_direction = direction;
			// TODO : Tourner Sprite
			this.position = position.add(newPos);
			return true;
		}
		case E: {
			PositionF newPos = new PositionF(parentScene.getTileWidth(), 0);
			m_direction = direction;
			// TODO : Tourner Sprite
			this.position = position.add(newPos);
			return true;
		}
		case S: {
			PositionF newPos = new PositionF(0, parentScene.getTileWidth());
			m_direction = direction;
			// TODO : Tourner Sprite
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

}
