package info3.game.entity;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutDirection;
import info3.game.scene.KitchenScene;
import info3.game.scene.Scene;

public class FrieTile extends KitchenTile {
	int compteur;
	static final Sprite EMPTY = Sprite.OFF_FRIE_TILE, FULL = Sprite.ON_FRIE_TILE;

	public FrieTile(Scene parent, int gridX, int gridY, AutDirection d) {
		super(parent, gridX, gridY, null, d);
		defaultSprite = EMPTY;
	}

	@Override
	public EntityType getType() {
		return EntityType.TILE_FRYER;
	}

	@Override
	public boolean pop(AutDirection direction) {// prend un ingrédiant à frire
		if (player.m_assembly.getItems().size() != 1 || this.item != null) {
			return false;
		} else {
			if (player.m_assembly.getItems().get(0).fry() == null) { // est-ce qu'on peut frire ?
				return false;
			}
			this.item = player.m_assembly.getItems().get(0);
			;
			((KitchenScene) this.parentScene).getCook().item = null;
			player.m_assembly.getItems().clear();
			this.compteur = 200;
			defaultSprite = FULL;
			parentScene.m_game.playSound("frying");
			return true;
		}
	}

	@Override
	public boolean wizz(AutDirection direction) {// rend l'ingrédient cuit au joueur
		parentScene.m_game.playSound("drop");
		player.m_assembly.addItem(item);
		this.item = null;
		return true;

	}

	@Override
	public boolean gwait() {
		if (this.compteur > 0) {
			this.compteur--;
			return false;
		} else {
			this.item = item.fry();
			defaultSprite = EMPTY;
			this.compteur = 200;
			return true;

		}
	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(defaultSprite, 0, 0);

		if (defaultSprite == EMPTY && item != null) {
			g.drawSprite(item.getType().getSprite(), 0, 0);

		}
	}

	@Override
	public boolean egg(AutDirection direction) {
		if (parentScene.entityList.size() <= Scene.MAXIMUM_ENTITIES) {
			Entity newEntity = null;
			newEntity = new FrieTile(this.parentScene, this.gridX, this.gridY, this.m_direction);
			return this.parentScene.addEntity(newEntity);
		}
		return false;
	}

	@Override
	public boolean explode() {
		if (this.compteur > 0) {
			this.compteur--;
			return false;
		} else {
			parentScene.m_game.playSound("kitchen_fail");
			((KitchenScene) parentScene).smokeFryingOil = true;
			item = null;
			return true;

		}
	}

}
