package info3.game.entity;

import info3.game.content.Assembly;
import info3.game.content.Item;
import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.Direction;
import info3.game.scene.KitchenScene;
import info3.game.scene.Scene;

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

	boolean wizz(Direction direction) {
		// TODO selon l'automate c'est graphique
		return true;
	}

	boolean pop(Direction direction) {
		// TODO selon l'automate c'est graphique
		return true;
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
		Entity eInteracting = selectEntityToInteractWith();
		if (eInteracting instanceof CookEntity && ((CookEntity) eInteracting) != null) {

			if (recetteReady(((KitchenScene) parentScene).currentOrder0)) {
				((KitchenScene) parentScene).currentOrder0 = Item.getRandomItem();
				assembly.getItems().clear();
				parentScene.m_game.timeGame += 30000;
				return true;
			}

			if (recetteReady(((KitchenScene) parentScene).currentOrder1)) {
				((KitchenScene) parentScene).currentOrder1 = Item.getRandomItem();
				assembly.getItems().clear();
				parentScene.m_game.timeGame += 30000;
				return true;
			}
		}
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
		Entity eInteracting = selectEntityToInteractWith();
		if (eInteracting instanceof CookEntity && ((CookEntity) eInteracting) != null) {
			if (recetteReady(((KitchenScene) parentScene).currentOrder0)
					|| recetteReady(((KitchenScene) parentScene).currentOrder1)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean gotStuff() {
		return !player.m_assembly.getItems().isEmpty();
	}

}
