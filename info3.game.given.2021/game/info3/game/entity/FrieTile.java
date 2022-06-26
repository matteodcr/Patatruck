package info3.game.entity;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutDirection;
import info3.game.scene.KitchenScene;
import info3.game.scene.Scene;

public class FrieTile extends KitchenTile {
	int compteur;
	private boolean isOn = false;

	public FrieTile(Scene parent, int gridX, int gridY, AutDirection d) {
		super(parent, gridX, gridY, Sprite.OFF_FRIE_TILE, d);
	}

	@Override
	public EntityType getType() {
		return EntityType.TILE_FRYER;
	}

	@Override
	public boolean pop(AutDirection direction) {// prend un ingrédiant à frire
		if (player.assembly.getItems().size() != 1 || this.item != null) {
			return false;
		} else {
			if (player.assembly.getItems().get(0).fry() == null) { // est-ce qu'on peut frire ?
				return false;
			}
			this.item = player.assembly.getItems().get(0);
			((KitchenScene) this.parentScene).getCook().item = null;
			player.assembly.getItems().clear();
			this.compteur = 200;
			isOn = true;
			parentScene.game.playSound("frying");
			return true;
		}
	}

	@Override
	public boolean wizz(AutDirection direction) {// rend l'ingrédient cuit au joueur
		parentScene.game.playSound("drop");
		player.assembly.addItem(item);
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
			isOn = false;
			this.compteur = 200;
			return true;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(!isOn ? defaultSprite : Sprite.ON_FRIE_TILE, 0, 0);

		if (!isOn && item != null) {
			g.drawSprite(item.getType().getSprite(), 0, 0);

		}
	}

	@Override
	public boolean egg(AutDirection direction) {
		if (parentScene.entityList.size() <= Scene.MAXIMUM_ENTITIES) {
			Entity newEntity;
			newEntity = new FrieTile(this.parentScene, this.gridX, this.gridY, this.direction);
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
			parentScene.game.playSound("kitchen_fail");
			((KitchenScene) parentScene).smokeFryingOil = true;
			item = null;
			return true;
		}
	}

}
