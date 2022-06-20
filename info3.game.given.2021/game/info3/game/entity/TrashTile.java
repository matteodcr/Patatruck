package info3.game.entity;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.scene.Scene;

public class TrashTile extends KitchenTile {

	public TrashTile(Scene parent, int gridX, int gridY, AutDirection d) {
		super(parent, gridX, gridY, null, d);
	}

	@Override
	public EntityType getType() {
		return EntityType.TILE_TRASH;
	}

	@Override
	public boolean pop(AutDirection direction) {
		return false;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		return false;
	}

	@Override
	public void render(Graphics g) {
		// BufferedImage img = m_images[m_imageIndex];
		g.drawSprite(Sprite.TRASH_TILE, 0, 0);
	}

	@Override
	public boolean gwait() {
		return false;
	}

	@Override
	public boolean egg(AutDirection direction) {
		return false;
	}

	@Override
	public boolean hit(AutDirection direction) {
		return false;
	}

	@Override
	public boolean jump(AutDirection direction) {
		return false;
	}

	@Override
	public boolean explode() {
		return false;
	}

	@Override
	public boolean pick(AutDirection direction) {
		return false;
	}

	@Override
	public boolean power() {
		return false;
	}

	@Override
	public boolean protect(AutDirection direction) {
		return false;
	}

	@Override
	public boolean store() {
		return false;
	}

	@Override
	public boolean turn(AutDirection direction) {
		return false;
	}

	@Override
	public boolean gthrow(AutDirection direction) {
		return false;
	}

	@Override
	public boolean myDir(AutDirection direction) {
		return false;
	}

	@Override
	public boolean closest(AutCategory category, AutDirection direction) {
		return false;
	}

	@Override
	public boolean gotPower() {
		return false;
	}

	@Override
	public boolean gotStuff() {
		return false;
	}

}
