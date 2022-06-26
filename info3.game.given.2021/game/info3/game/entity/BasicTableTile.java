package info3.game.entity;

import info3.game.content.Assembly;
import info3.game.content.Item;
import info3.game.graphics.Graphics;
import info3.game.position.AutDirection;
import info3.game.scene.Scene;

public class BasicTableTile extends KitchenTile {
	private final Assembly assembly = new Assembly();

	public BasicTableTile(Scene parent, int gridX, int gridY, AutDirection d) {
		super(parent, gridX, gridY, null, d);
	}

	@Override
	public EntityType getType() {
		return EntityType.TILE_TABLE;
	}

	@Override
	public boolean pop(AutDirection direction) {// poser

		parentScene.game.playSound("drop");
		Entity eInteracting = selectEntityToInteractWith();
		if (eInteracting instanceof CookEntity) {
			if (((CookEntity) eInteracting).assembly.getItems().size() == 0) {
				return false;
			} else {
				assembly.addAssembly(player.assembly);
				player.assembly.getItems().clear();

				return true;

			}
		}
		return false;
	}

	@Override
	public boolean wizz(AutDirection direction) {// rendre au player si fini / donner si pas fini

		parentScene.game.playSound("drop");
		Entity eInteracting = selectEntityToInteractWith();
		if (eInteracting instanceof CookEntity) {

			if (!assembly.getItems().isEmpty()) {
				// Si l'item à tenir est final : on le prend
				if (assembly.getItems().get(0).getType().isFinalItem()) {
					player.assembly.addAssembly(assembly);
					assembly.getItems().clear();
				} else {
					// Si il s'agit juste d'item empilés : on reprend le dernier posé
					player.assembly.addItem(assembly.getItems().remove(assembly.getItems().size() - 1));
				}
				return true;
			}

		}
		return false;

	}

	@Override
	public void render(Graphics g) {
		if (!assembly.getItems().isEmpty()) {
			for (Item item : assembly.getItems()) {
				g.drawSprite(item.getType().getSprite(), 0, 0);
			}
		}
	}

	@Override
	public boolean egg(AutDirection direction) {
		if (parentScene.entityList.size() <= Scene.MAXIMUM_ENTITIES) {
			Entity newEntity;
			newEntity = new BasicTableTile(this.parentScene, this.gridX, this.gridY, this.direction);
			return this.parentScene.addEntity(newEntity);
		}
		return false;
	}

	@Override
	public boolean gotStuff() {
		return !player.assembly.getItems().isEmpty();
	}

}
