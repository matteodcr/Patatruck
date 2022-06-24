package info3.game.entity;

import java.io.IOException;

import info3.game.content.Assembly;
import info3.game.graphics.Graphics;
import info3.game.graphics.RenderUtils;
import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.PositionF;
import info3.game.scene.Scene;

public class CookEntity extends Entity {
	long m_imageElapsed;
	long m_moveElapsed;
	int m_imageIndex;
	Assembly m_assembly;

	public CookEntity(Scene parent, PositionF position) throws IOException {
		super(parent, position);
		category = AutCategory.AROBASE;
		m_assembly = new Assembly();
	}

	@Override
	public EntityType getType() {
		return EntityType.COOK;
	}

	@Override
	public void render(Graphics g) {

		if (m_direction == AutDirection.N) {
			g.drawSprite(Sprite.PLAYER_KITCHEN_N, 0, 0);
		} else if (m_direction == AutDirection.E) {
			g.drawSprite(Sprite.PLAYER_KITCHEN_E, 0, 0);
		} else if (m_direction == AutDirection.W) {
			g.drawSprite(Sprite.PLAYER_KITCHEN_W, 0, 0);
		} else {
			g.drawSprite(Sprite.PLAYER_KITCHEN_S, 0, 0);
		}
		if (m_assembly.getItems().size() >= 1) {
			RenderUtils.drawItem(g, m_assembly.getItems().get(0), 0, 0);
		}
	}

	@Override
	public boolean pop(AutDirection direction) {
		// changer le sprite
		return false;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		// le timeElapsed permet de limiter la vitesse du joueur
		finish = System.currentTimeMillis();
		timeElapsed = finish - start;

		if (timeElapsed >= 200) {
			switch (direction) {
			case N: {
				PositionF newPos = new PositionF(0, -parentScene.getTileWidth());
				this.position = position.add(newPos);
				start = System.currentTimeMillis();

				return true;
			}
			case W: {
				PositionF newPos = new PositionF(-parentScene.getTileWidth(), 0);
				this.position = position.add(newPos);
				start = System.currentTimeMillis();

				return true;
			}
			case E: {
				PositionF newPos = new PositionF(parentScene.getTileWidth(), 0);
				this.position = position.add(newPos);
				start = System.currentTimeMillis();

				return true;
			}
			case S: {
				PositionF newPos = new PositionF(0, parentScene.getTileWidth());
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
