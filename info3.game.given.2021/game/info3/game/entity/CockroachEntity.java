package info3.game.entity;

import java.io.IOException;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.PositionF;
import info3.game.scene.KitchenScene;
import info3.game.scene.Scene;

public class CockroachEntity extends Entity {

	

	public CockroachEntity(Scene parent, PositionF position, int gX, int gY) throws IOException {
		super(parent, position, gX, gY);
		automaton = parentScene.setupAutomaton("Cockroach");
		current_state = automaton.initial;
		category = AutCategory.A;
		Cockroach_counterAdd(1);
	}

	@Override
	public boolean wizz(AutDirection direction) {

		finish = System.currentTimeMillis();
		timeElapsed = finish - start;

		if (timeElapsed >= 500) {
			AutDirection newDirection = convertRelativToAbsolutedir(direction);
			m_direction = newDirection;
			switch (newDirection) {
			case N: {
				PositionF newPos = new PositionF(0, -parentScene.getTileWidth());
				gridY--;
				this.position = position.add(newPos);
				start = System.currentTimeMillis();

				return true;
			}
			case W: {
				PositionF newPos = new PositionF(-parentScene.getTileWidth(), 0);
				gridX--;
				this.position = position.add(newPos);
				start = System.currentTimeMillis();

				return true;
			}
			case E: {
				PositionF newPos = new PositionF(parentScene.getTileWidth(), 0);
				gridX++;
				this.position = position.add(newPos);
				start = System.currentTimeMillis();

				return true;
			}
			case S: {
				PositionF newPos = new PositionF(0, parentScene.getTileWidth());
				gridY++;
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
	public boolean pop(AutDirection direction) { // explode

		return true; // temporary to prevent cockroach from being stuck in dupli statedd
	}

	@Override
	public void render(Graphics g) {
		if (m_direction == AutDirection.N) {
			g.drawSprite(Sprite.COCKROACH_ENTITY_N, this.position.getX(), this.position.getY());
		} else if (m_direction == AutDirection.E) {
			g.drawSprite(Sprite.COCKROACH_ENTITY_E, this.position.getX(), this.position.getY());
		} else if (m_direction == AutDirection.W) {
			g.drawSprite(Sprite.COCKROACH_ENTITY_W, this.position.getX(), this.position.getY());
		} else {
			g.drawSprite(Sprite.COCKROACH_ENTITY_S, this.position.getX(), this.position.getY());
		}
	}

	@Override
	public boolean gwait() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean egg(AutDirection direction) {
		if (((KitchenScene) parentScene).getCockroach_counter() <= KitchenScene.MAXIMUM_COCKROACH_NUMBER) {
			Entity nouveau_carfard = null;
			try {
				nouveau_carfard = new CockroachEntity(this.parentScene, position, gridX, gridY);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return this.parentScene.addEntity(nouveau_carfard);
		}
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
		Cockroach_counterAdd(-1);
		return true;
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
		return c;
	}

	public void Cockroach_counterAdd(int value) {
		((KitchenScene) parentScene).setCockroach_counter(((KitchenScene) parentScene).getCockroach_counter() + value);
		;
	}

}
