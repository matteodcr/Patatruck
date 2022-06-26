package info3.game.entity;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutDirection;
import info3.game.scene.Scene;

public class CutTile extends KitchenTile {
	int compteur = 0;

	public CutTile(Scene parent, int gridX, int gridY, AutDirection d) {
		super(parent, gridX, gridY, null, d);
	}

	@Override
	public boolean pop(AutDirection direction) {// prend un ingrédient à couper
		if (player.m_assembly.getItems().size() != 1 || this.item != null) {
			return false;
		} else {
			if (player.m_assembly.getItems().get(0).cut() == null) { // est-ce qu'on peut couper?
				return false;
			}
			parentScene.m_game.playSound("cut");
			this.item = player.m_assembly.getItems().get(0);
			player.m_assembly.getItems().clear();
			this.compteur = 200;
			return true;
		}
	}

	@Override
	public EntityType getType() {
		return EntityType.TILE_CUT;
	}

	@Override
	public boolean wizz(AutDirection direction) {// rend l'ingrédient coupé au joueur
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
			this.item = item.cut();
			return true;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(Sprite.CUT_TILE, 0, 0);
		if (item != null) {
			g.drawSprite(item.getType().getSprite(), 0, 0);
		}
	}

	@Override
	public boolean egg(AutDirection direction) {
		if (parentScene.entityList.size() <= Scene.MAXIMUM_ENTITIES) {
			Entity newEntity = null;
			newEntity = new CutTile(this.parentScene, this.gridX, this.gridY, this.m_direction);
			return this.parentScene.addEntity(newEntity);
		}
		return false;
	}

	@Override
	public boolean store() {
		return this.item != null;
	}

	@Override
	public boolean gotStuff() {
		return this.item != null;
	}

}
