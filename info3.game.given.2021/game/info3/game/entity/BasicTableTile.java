package info3.game.entity;

import info3.game.position.AutDirection;
import info3.game.position.Direction;
import info3.game.scene.KitchenScene;
import info3.game.scene.Scene;

public class BasicTableTile extends KitchenTile {
	Item item;

	public BasicTableTile(Scene parent, int gridX, int gridY, Direction d) {
		super(parent, gridX, gridY, null, d);
		this.item = null;
	}// TODO sprite à ajouter

	@Override
	public boolean pop(AutDirection direction) {// poser
		Entity player = ((KitchenScene) this.parentScene).getCook();
		if (player.item != null) {
			if (this.item != null) {
				if (true/* assemblage possible */) {
					// assembler
				} else {
					// plat raté
				}
			} else {
				this.item = player.item;
				player.item = null;
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean wizz(AutDirection direction) {//vider
		if (this.item == null) {
			return false;
		} else {
			Entity player = ((KitchenScene) this.parentScene).getCook();
			player.item=this.item;
			this.item = null;
			return true;
		}
	}
