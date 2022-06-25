package info3.game.entity;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutDirection;
import info3.game.scene.Scene;

public class TrashTile extends KitchenTile {
	Sprite empty = Sprite.TRASH_TILE_EMPTY, full = Sprite.TRASH_TILE_FULL;

	public TrashTile(Scene parent, int gridX, int gridY, AutDirection d) {
		super(parent, gridX, gridY, null, d);
		defaultSprite = empty;
	}

	@Override
	public EntityType getType() {
		return EntityType.TILE_TRASH;
	}

	@Override
	public boolean pop(AutDirection direction) {
		Entity eInteracting = selectEntityToInteractWith();
		if (eInteracting instanceof CookEntity && ((CookEntity) eInteracting) != null) {

			if (((CookEntity) eInteracting).m_assembly.getItems().size() != 0) {
				((CookEntity) eInteracting).m_assembly.getItems().clear();
				this.defaultSprite = full;
				return true;
			}
		}
		return false;

	}

	@Override
	public boolean wizz(AutDirection direction) {

		Entity eInteracting = selectEntityToInteractWith();
		if (eInteracting instanceof CookEntity && ((CookEntity) eInteracting) != null) {

			if (((CookEntity) eInteracting).m_assembly.getItems().size() != 0) {
				((CookEntity) eInteracting).m_assembly.getItems().clear();
				return true;
			}
		}
		return false;

	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(defaultSprite, 0, m_direction == AutDirection.S ? 0 : 2);
	}

	@Override
	public boolean egg(AutDirection direction) {
		if (parentScene.entityList.size() <= Scene.MAXIMUM_ENTITIES) {
			Entity newEntity = null;
			newEntity = new TrashTile(this.parentScene, this.gridX, this.gridY, this.m_direction);
			return this.parentScene.addEntity(newEntity);
		}
		return false;
	}

	@Override
	public boolean gotStuff() {
		return this.defaultSprite == full;
	}

}
