package info3.game.entity;

import info3.game.graphics.Graphics;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.Direction;
import info3.game.position.PositionF;
import info3.game.scene.CityScene;
import info3.game.scene.Scene;
import info3.game.worldgen.GenTile;

public class CityTile extends Tile {
	private final GenTile genTile;

	public CityTile(Scene parent, int gridX, int gridY) {
		super(parent, gridX, gridY);
		genTile = ((CityScene) parent).worldGenerator.generate(gridX, gridY);
	}

	@Override
	public EntityType getType() {
		return EntityType.TILE_CITY;
	}

	@Override
	public void render(Graphics g) {
		g.drawSpritePart(genTile.buildingSprite, 0, 0, genTile.buildingSpriteOffsetX, genTile.buildingSpriteOffsetY);
	}

	@Override
	public boolean pop(AutDirection direction) {
		// Don't know what to do with this method
		return false;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		this.parentScene.addEntity(new CarEntity(this.parentScene, new PositionF(gridX, gridY), false, Direction.NORD));
		return true;
	}

	@Override
	public boolean gwait() {
		return false;
	}

	@Override
	public boolean egg(AutDirection direction) {
		this.parentScene.addEntity(new CarEntity(this.parentScene, new PositionF(gridX, gridY), true, Direction.NORD));
		return true;
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
