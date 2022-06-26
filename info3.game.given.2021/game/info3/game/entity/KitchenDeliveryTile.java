package info3.game.entity;

import info3.game.content.Assembly;
import info3.game.content.Item;
import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutDirection;
import info3.game.scene.CityScene;
import info3.game.scene.KitchenScene;
import info3.game.scene.Scene;
import info3.game.screen.GameScreen;

public class KitchenDeliveryTile extends KitchenTile {

	Assembly assembly;

	public KitchenDeliveryTile(Scene parent, int gridX, int gridY, AutDirection d) {
		super(parent, gridX, gridY, null, d);
		assembly = new Assembly();
	}

	@Override
	public EntityType getType() {
		return EntityType.TILE_DELIVERY;
	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(Sprite.DELIVERYTILE, 0, 0);
		if (!assembly.getItems().isEmpty()) {
			for (Item item : assembly.getItems()) {
				g.drawSprite(item.getType().getSprite(), 0, 0);

			}
		}
	}

	boolean recetteReady(Item currentOrder1) {
		return (!assembly.getItems().isEmpty() && currentOrder1.equals(assembly.getItems().get(0)));
	}

	@Override
	public boolean pop(AutDirection direction) {
		Entity eInteracting = selectEntityToInteractWith();
		if (eInteracting instanceof CookEntity && ((CookEntity) eInteracting) != null) {
			if (player.m_assembly.getItems().isEmpty()) {
				return false;
			} else {
				parentScene.m_game.playSound("drop");
				assembly.addAssembly(player.m_assembly);
				player.m_assembly.getItems().clear();

				return true;

			}
		}
		return false;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		Entity eInteracting = selectEntityToInteractWith();
		if (eInteracting instanceof CookEntity && ((CookEntity) eInteracting) != null) {
			if (player.m_assembly.getItems().size() == 0 && assembly.getItems().size() != 0) {
				if (assembly.getItems().get(0).getType().isFinalItem()) {
					player.m_assembly.addAssembly(assembly);
					assembly.getItems().clear();
				} else {
					player.m_assembly.addItem(assembly.getItems().remove(assembly.getItems().size() - 1));
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean egg(AutDirection direction) {
		if (parentScene.entityList.size() <= Scene.MAXIMUM_ENTITIES) {
			Entity newEntity = null;
			newEntity = new KitchenDeliveryTile(this.parentScene, this.gridX, this.gridY, this.m_direction);
			return this.parentScene.addEntity(newEntity);
		}
		return false;
	}

	@Override
	public boolean hit(AutDirection direction) {
		CityScene cityScene = ((CityScene) ((GameScreen) this.parentScene.m_game.getScreen()).getCityScene());

		if (recetteReady(((KitchenScene) parentScene).currentOrder0) && cityScene.getCook().canDeliver()) {
			((KitchenScene) parentScene).currentOrder0 = Item.getRandomItem();
			assembly.getItems().clear();
			parentScene.m_game.addTime(50);
			// Indicates that the delivery has been done
			((CityScene) ((GameScreen) this.parentScene.m_game.getScreen()).getCityScene()).getDeliveryTile()
					.delivered();
			return true;
		}

		if (recetteReady(((KitchenScene) parentScene).currentOrder1) && cityScene.getCook().canDeliver()) {
			((KitchenScene) parentScene).currentOrder1 = Item.getRandomItem();
			assembly.getItems().clear();
			parentScene.m_game.addTime(50);
			// Indicates that the delivery has been done
			((CityScene) ((GameScreen) this.parentScene.m_game.getScreen()).getCityScene()).getDeliveryTile()
					.delivered();
			return true;
		}
		return false;
	}

	@Override
	public boolean gotPower() {
		if (recetteReady(((KitchenScene) parentScene).currentOrder0)
				|| recetteReady(((KitchenScene) parentScene).currentOrder1)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean gotStuff() {
		return !player.m_assembly.getItems().isEmpty();
	}

}
