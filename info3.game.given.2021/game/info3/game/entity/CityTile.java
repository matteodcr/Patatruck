package info3.game.entity;

import info3.game.graphics.Sprite;

public class CityTile extends Tile {
	private static final Sprite[] BUILDINGS_1X1 = new Sprite[] { Sprite.BUILDING_SQUARE_1_NORMAL,
			Sprite.BUILDING_SQUARE_1_ROUNDED, Sprite.BUILDING_SQUARE_1_SQUIRCLE, };

	final int gridX, gridY;

	public CityTile(int gridX, int gridY) {
		super(BUILDINGS_1X1[Math.floorMod(gridX + gridY, 3)]);
		this.gridX = gridX;
		this.gridY = gridY;
	}
}
