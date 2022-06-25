package info3.game.entity;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutDirection;
import info3.game.scene.KitchenScene;
import info3.game.scene.Scene;

public class CookTile extends KitchenTile {
	int compteur;
	static final Sprite EMPTY = Sprite.OFF_PAN_TILE, FULL = Sprite.ON_PAN_TILE;

	public CookTile(Scene parent, int gridX, int gridY, AutDirection d) {
		super(parent, gridX, gridY, null, d);
		defaultSprite = EMPTY;
	}

	@Override
	public EntityType getType() {
		return EntityType.TILE_PAN;
	}

	@Override
	public boolean pop(AutDirection direction) {// prend un ingrédiant à cuire et le cuit
		if (player.m_assembly.getItems().size() != 1 || this.item != null) {
			return false;
		} else {
			if (player.m_assembly.getItems().get(0).cook() == null) { // est-ce qu'on peut cuire ?
				return false;
			}
			this.item = player.m_assembly.getItems().get(0);
			player.m_assembly.getItems().clear();
			this.compteur = 200;
			defaultSprite = FULL;
			return true;
		}
	}

	@Override
	public boolean wizz(AutDirection direction) {// rend l'ingrédient au joueur
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
			defaultSprite = EMPTY;
			this.item = item.cook();
			this.compteur = 200;
			return true;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(defaultSprite, 0, m_direction == AutDirection.S ? 0 : 2);
		if (item != null) {
			g.drawSprite(item.getType().getSprite(), 0, 0);
		}
	}

	@Override
	public boolean egg(AutDirection direction) {
		if (parentScene.entityList.size() <= Scene.MAXIMUM_ENTITIES) {
			Entity newEntity = null;
			newEntity = new CookTile(this.parentScene, this.gridX, this.gridY, this.m_direction);
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
			((KitchenScene) parentScene).smoke = true;
			item = null;
			return true;

		}
	}

	@Override
	public boolean power() {
		return this.item != null;
	}

	@Override
	public boolean gotStuff() {
		return this.item != null;
	}

}
