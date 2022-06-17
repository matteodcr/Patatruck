package info3.game.entity;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.Direction;
import info3.game.scene.Scene;

public class BasicTableTile extends KitchenTile {
	// Item item;

	public BasicTableTile(Scene parent, int gridX, int gridY, Direction d) {
		super(parent, gridX, gridY, null, d);
	}

	@Override
	public EntityType getType() {
		return EntityType.TILE_TABLE;
	}

	@Override
	public boolean pop(AutDirection direction) {
		// poser
		/*
		 * Entity player = ((KitchenScene) this.parentScene).getCook(); if (player.item
		 * != null) { if (this.item != null) { if (true assemblage possible ) { //
		 * assembler } else { // plat raté } } else { this.item = player.item;
		 * player.item = null; } return true; } else { return false;
		 */return false;

	}

	@Override
	public boolean wizz(AutDirection direction) {// vider
		/*
		 * if (this.item == null) { return false; } else { Entity player =
		 * ((KitchenScene) this.parentScene).getCook(); player.item = this.item;
		 * this.item = null; return true; }
		 */
		return false;
	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(Sprite.BASIC_TABLE, 0, 0);
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
