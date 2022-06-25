package info3.game.entity;

import info3.game.content.Assembly;
import info3.game.content.Item;
import info3.game.graphics.Graphics;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.scene.Scene;

public class BasicTableTile extends KitchenTile {
	Assembly assembly;
	public Item item;

	public BasicTableTile(Scene parent, int gridX, int gridY, AutDirection d) {
		super(parent, gridX, gridY, null, d);
		this.assembly = new Assembly();
	}

	@Override
	public EntityType getType() {
		return EntityType.TILE_TABLE;
	}

	@Override
	public boolean pop(AutDirection direction) {// poser
		Entity eInteracting = selectEntityToInteractWith();
		if (eInteracting instanceof CookEntity && ((CookEntity) eInteracting) != null) {
			if (((CookEntity) eInteracting).m_assembly.getItems().size() == 0) {
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

			if (!assembly.getItems().isEmpty()) {
				// Si l'item à tenir est final : on le prend
				if (assembly.getItems().get(0).getType().isFinalItem()) {
					player.m_assembly.addAssembly(assembly);
					assembly.getItems().clear();
					return true;
				} else {
					// Si il s'agit juste d'item empilés : on reprend le dernier posé
					player.m_assembly.addItem(assembly.getItems().remove(assembly.getItems().size() - 1));
					return true;
				}
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
		return !player.m_assembly.getItems().isEmpty();
	}

}
