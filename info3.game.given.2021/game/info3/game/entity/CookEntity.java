package info3.game.entity;

import java.io.IOException;

import info3.game.content.Assembly;
import info3.game.content.Item;
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

	public Item item;

	public CookEntity(Scene parent, PositionF position) throws IOException {
		super(parent, position);
		category = AutCategory.AROBASE;
		m_assembly = new Assembly();
		this.timerToWait = 200;
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
		move(direction);
		return false;
	}

	@Override
	public boolean egg(AutDirection direction) {
		if (parentScene.entityList.size() <= Scene.MAXIMUM_ENTITIES) {
			Entity newEntity = null;
			try {
				newEntity = new CookEntity(this.parentScene, position);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return this.parentScene.addEntity(newEntity);
		}
		return false;
	}

	@Override
	public boolean gotStuff() {
		return (this.item != null || this.m_assembly != null);
	}

	@Override
	public boolean cell(AutDirection direction, AutCategory category) {
		boolean c = super.cell(direction, category);
		m_direction = direction;
		return c;
	}
}
