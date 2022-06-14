package info3.game.entity;

import info3.game.graphics.Sprite;
import info3.game.position.AutDirection;
import info3.game.scene.Scene;

public class CityTile extends Tile {
	private static final Sprite[] BUILDINGS_1X1 = new Sprite[] { Sprite.BUILDING_SQUARE_1_NORMAL,
			Sprite.BUILDING_SQUARE_1_ROUNDED, Sprite.BUILDING_SQUARE_1_SQUIRCLE, };

	final int gridX, gridY;

	public CityTile(Scene parent, int gridX, int gridY) {
		super(parent, gridX, gridY, BUILDINGS_1X1[Math.floorMod(gridX + gridY, 3)]);
		this.gridX = gridX;
		this.gridY = gridY;
	}

	@Override
	public boolean pop(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}
}
