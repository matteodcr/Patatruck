package info3.game.entity;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.Direction;
import info3.game.scene.Scene;

public class StockTable extends KitchenTile {
	// Item item;
	int stock;

	public StockTable(Scene parent, int gridX, int gridY, Direction d) {
		super(parent, gridX, gridY, null, d);
	}

	@Override
	public EntityType getType() {
		return EntityType.TILE_STOCK;
	}

	@Override
	public boolean pop(AutDirection direction) { // prendre un aliment
		/*
		 * Entity player = ((KitchenScene) this.parentScene).getCook(); if (stock == 0
		 * || player.item != null) { return false; } else { player.item = this.item;
		 * stock--; return true; }
		 */
		return false;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		return true;
	}

	public int getStock() {
		return this.stock;
	}

	public int addStock() {
		return ++this.stock;
	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(Sprite.STOCK_TABLE, 0, 0);
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
