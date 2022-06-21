package info3.game.worldgen;

import info3.game.graphics.Sprite;

public class GenTile {
	public final Sprite buildingSprite;
	public final int buildingSpriteOffsetX, buildingSpriteOffsetY;
	final boolean collisionTL, collisionT, collisionL;
	final boolean hasMarketPaving;

	GenTile(Sprite buildingSprite, int buildingSpriteOffsetX, int buildingSpriteOffsetY, boolean collisionTL,
			boolean collisionT, boolean collisionL, boolean hasMarketPaving) {
		this.buildingSprite = buildingSprite;
		this.buildingSpriteOffsetX = buildingSpriteOffsetX;
		this.buildingSpriteOffsetY = buildingSpriteOffsetY;
		this.collisionTL = collisionTL;
		this.collisionT = collisionT;
		this.collisionL = collisionL;
		this.hasMarketPaving = hasMarketPaving;
	}
}
