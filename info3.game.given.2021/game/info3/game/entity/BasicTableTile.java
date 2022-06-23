package info3.game.entity;

import info3.game.content.Assembly;
import info3.game.content.Item;
import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.scene.Scene;

public class BasicTableTile extends KitchenTile {
	Assembly assembly;

	public BasicTableTile(Scene parent, int gridX, int gridY, AutDirection d) {
		super(parent, gridX, gridY, null, d);
		this.assembly = new Assembly();
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

			// Si le joueur ne tient rien
			if (player.m_assembly.getItems().size() == 0 && assembly.getItems().size() != 0) {

				// Si l'item à tenir est final : on le prend
				if (assembly.getItems().get(0).getType().isFinalItem()) {
					player.m_assembly.addAssembly(assembly);
					assembly.getItems().clear();
					return true;
				} else {
					// Si il s'agit juste d'item empilés : on reprend le dernier posé
					player.m_assembly.addItem(assembly.getItems().remove(assembly.getItems().size() - 1));
					return false;
				}

				// Si le joueur tient un objet, il le dépose sur la table
			} else if (player.m_assembly.getItems().size() == 1) {
				assembly.addAssembly(player.m_assembly);
				player.m_assembly.getItems().clear();
				return true;
			}
		}
		return false;
	}

	@Override
	public void render(Graphics g) {
		switch (m_direction) {
		case E:
			g.drawSprite(Sprite.BASICTABLE_E, 0, 0);
			break;
		case N:
			g.drawSprite(Sprite.BASICTABLE_N, 0, 0);
			break;
		case S:
			g.drawSprite(Sprite.BASICTABLE_S, 0, 0);
			break;
		case W:
			g.drawSprite(Sprite.BASICTABLE_W, 0, 0);
			break;
		default:
			break;
		}
		if (!assembly.getItems().isEmpty()) {
			for (Item item : assembly.getItems()) {
				g.drawSprite(item.getType().getSprite(), 0, 0);

			}
		}
	}

	@Override
	public EntityType getType() {
		return EntityType.TILE_TABLE;
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

}
