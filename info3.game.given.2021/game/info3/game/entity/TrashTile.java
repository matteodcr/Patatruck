package info3.game.entity;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutDirection;
import info3.game.scene.Scene;

public class TrashTile extends KitchenTile {
	boolean isFull = false;

	public TrashTile(Scene parent, int gridX, int gridY, AutDirection d) {
		super(parent, gridX, gridY, Sprite.TRASH_TILE_EMPTY, d);
	}

	@Override
	public EntityType getType() {
		return EntityType.TILE_TRASH;
	}

	@Override
	public boolean pop(AutDirection direction) {
		Entity eInteracting = selectEntityToInteractWith();
		if (eInteracting instanceof CookEntity) {
			if (((CookEntity) eInteracting).assembly.getItems().size() != 0) {
				parentScene.game.playSound("trash");
				((CookEntity) eInteracting).assembly.getItems().clear();
				this.isFull = true;
				return true;
			}
		}
		return false;

	}

	@Override
	public boolean wizz(AutDirection direction) {

		Entity eInteracting = selectEntityToInteractWith();
		if (eInteracting instanceof CookEntity) {
			if (((CookEntity) eInteracting).assembly.getItems().size() != 0) {
				parentScene.game.playSound("trash");
				((CookEntity) eInteracting).assembly.getItems().clear();
				return true;
			}
		}
		return false;

	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(!isFull ? defaultSprite : Sprite.TRASH_TILE_FULL, 0, direction == AutDirection.S ? 0 : 2);
	}

	@Override
	public boolean egg(AutDirection direction) {
		if (parentScene.entityList.size() <= Scene.MAXIMUM_ENTITIES) {
			Entity newEntity;
			newEntity = new TrashTile(this.parentScene, this.gridX, this.gridY, this.direction);
			return this.parentScene.addEntity(newEntity);
		}
		return false;
	}

	@Override
	public boolean gotStuff() {
		return this.isFull;
	}

}
