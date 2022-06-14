package info3.game.graphics;

import static info3.game.graphics.Sprite.Spritesheet.CITY;
import static info3.game.graphics.Sprite.Spritesheet.COWBOY;
import static info3.game.graphics.Sprite.Spritesheet.KITCHEN;

public enum Sprite {

	COWBOY0(COWBOY, 0, 0, 1, 1), COWBOY1(COWBOY, 0, 1, 1, 1), BUILDING_SQUARE_1_NORMAL(CITY, 0, 0, 1, 1),
	BUILDING_SQUARE_1_ROUNDED(CITY, 0, 1, 1, 1), BUILDING_SQUARE_1_SQUIRCLE(CITY, 0, 2, 1, 1),
	KITCHEN_TILE1(KITCHEN, 0, 0, 1, 1);

	public final Spritesheet spritesheet;
	public final int u, v, w, h;

	Sprite(Spritesheet spritesheet, int u, int v, int w, int h) {
		this.spritesheet = spritesheet;
		this.u = u;
		this.v = v;
		this.w = w;
		this.h = h;
	}

	public enum Spritesheet {
		// TODO

		COWBOY("winchester-4x6.png", 13), CITY("city.png", 20), KITCHEN("kitchenTable.png", 13);

		public final String filename;
		public final int tileSize;

		Spritesheet(String filename, int tileSize) {
			this.filename = filename;
			this.tileSize = tileSize;
		}
	}

}
