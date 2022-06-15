package info3.game.entity;

import java.io.IOException;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.PositionF;
import info3.game.scene.Scene;

public class CockroachEntity extends Entity {

	public CockroachEntity(Scene parent, PositionF position, int gX, int gY) throws IOException {
		super(parent, position);
		automaton = parentScene.setupAutomaton("Cockroach");
		current_state = automaton.initial;
		gridX = gX;
		gridY = gY;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		AutDirection newDirection = convertRelativToAbsolutedir(direction);
		m_direction = newDirection;
		switch (newDirection) {
		case N: {
			PositionF newPos = new PositionF(0, -parentScene.getTileWidth());
			gridY--;
			// TODO : Tourner Sprite
			this.position = position.add(newPos);
			return true;
		}
		case W: {
			PositionF newPos = new PositionF(-parentScene.getTileWidth(), 0);
			gridX--;
			// TODO : Tourner Sprite
			this.position = position.add(newPos);
			return true;
		}
		case E: {
			PositionF newPos = new PositionF(parentScene.getTileWidth(), 0);
			gridX++;
			// TODO : Tourner Sprite
			this.position = position.add(newPos);
			return true;
		}
		case S: {
			PositionF newPos = new PositionF(0, parentScene.getTileWidth());
			gridY++;
			// TODO : Tourner Sprite
			this.position = position.add(newPos);
			return true;
		}
		default:
			return false;
		}
	}

	@Override
	public boolean pop(AutDirection direction) { // explode
		// TODO Auto-generated method stub
		return true; // temporary to prevent cockroach from being stuck in dupli statedd
	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(Sprite.COCKROACH_ENTITY, this.position.getX(), this.position.getY());
	}

	@Override
	public boolean gwait() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean egg(AutDirection direction) {
		Entity nouveau_carfard = null;
		try {
			nouveau_carfard = new CockroachEntity(this.parentScene, position, gridX, gridY);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.parentScene.addEntity(nouveau_carfard);
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
