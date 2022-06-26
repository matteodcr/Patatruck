package info3.game.entity;

import java.io.IOException;

import info3.game.content.Item;
import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.PositionF;
import info3.game.scene.Scene;

public class CockroachEntity extends Entity {
	public Item item;

	private boolean usePopSprite = false;
	private int timerPopSprite = 0, maxTimerPopSprite = 6;

	public CockroachEntity(Scene parent, PositionF position) throws IOException {
		super(parent, position);
		category = AutCategory.A;
		this.timerToWait = 500;
	}

	@Override
	public EntityType getType() {
		return EntityType.COCKROACH;
	}

	@Override
	public void tick(long elapsed) {
		super.tick(elapsed);
		if (usePopSprite) {
			if (timerPopSprite > 0) {
				timerPopSprite--;
			} else {
				timerPopSprite = maxTimerPopSprite;
				usePopSprite = false;
			}
		}
	}

	@Override
	public boolean wizz(AutDirection direction) {
		return move(direction);
	}

	@Override
	public boolean pop(AutDirection direction) { // explode
		timerPopSprite = maxTimerPopSprite;
		usePopSprite = true;
		return true; // to prevent cockroach from being stuck in dupli statedd
	}

	@Override
	public void render(Graphics g) {
		if (m_direction == AutDirection.N) {
			if (usePopSprite)
				g.drawSprite(Sprite.COCKROACH_POP_N, 0, 0);
			else
				g.drawSprite(Sprite.COCKROACH_ENTITY_N, 0, 0);
		} else if (m_direction == AutDirection.E) {
			if (usePopSprite)
				g.drawSprite(Sprite.COCKROACH_POP_E, 0, 0);
			else
				g.drawSprite(Sprite.COCKROACH_ENTITY_E, 0, 0);
		} else if (m_direction == AutDirection.W) {
			if (usePopSprite)
				g.drawSprite(Sprite.COCKROACH_POP_W, 0, 0);
			else
				g.drawSprite(Sprite.COCKROACH_ENTITY_W, 0, 0);
		} else {
			if (usePopSprite)
				g.drawSprite(Sprite.COCKROACH_POP_S, 0, 0);
			else
				g.drawSprite(Sprite.COCKROACH_ENTITY_S, 0, 0);
		}
	}

	@Override
	public boolean egg(AutDirection direction) {
		if (parentScene.entityList.size() <= Scene.MAXIMUM_ENTITIES) {
			Entity newEntity = null;
			try {
				newEntity = new CockroachEntity(this.parentScene, position);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return this.parentScene.addEntity(newEntity);
		}
		return false;
	}

	@Override
	public boolean explode() {
		parentScene.m_game.playSound("crunch_cockroach");
		return true;
	}

	@Override
	public boolean cell(AutDirection direction, AutCategory category) {
		boolean c = super.cell(direction, category);
		return c;
	}
}
