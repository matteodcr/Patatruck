package info3.game.graphics;

import static info3.game.graphics.Sprite.Spritesheet.BASICTABLE;
import static info3.game.graphics.Sprite.Spritesheet.CITY;
import static info3.game.graphics.Sprite.Spritesheet.COOK;
import static info3.game.graphics.Sprite.Spritesheet.CUTTILE;
import static info3.game.graphics.Sprite.Spritesheet.DELIVERYTILE;
import static info3.game.graphics.Sprite.Spritesheet.FRIETILE;
import static info3.game.graphics.Sprite.Spritesheet.KITCHEN_TRUCK;
import static info3.game.graphics.Sprite.Spritesheet.PANTILE;
import static info3.game.graphics.Sprite.Spritesheet.SAUCETABLETILE;
import static info3.game.graphics.Sprite.Spritesheet.STOCKTABLE;
import static info3.game.graphics.Sprite.Spritesheet.TRASHTILE;

public enum Sprite {

	PLAYER_KITCHEN(COOK, 0, 0, 1, 1), BUILDING_SQUARE_1_NORMAL(CITY, 0, 0, 1, 1),
	BUILDING_SQUARE_1_ROUNDED(CITY, 0, 1, 1, 1), BUILDING_SQUARE_1_SQUIRCLE(CITY, 0, 2, 1, 1),
	KITCHENTRUCK(KITCHEN_TRUCK, 0, 0, 1, 1), SAUCE_TABLE_TILE(SAUCETABLETILE, 0, 0, 1, 1),
	FRIE_TILE(FRIETILE, 0, 0, 1, 1), CUT_TILE(CUTTILE, 0, 0, 1, 1), PAN_TILE(PANTILE, 0, 0, 1, 1),
	TRASH_TILE(TRASHTILE, 0, 0, 1, 1), STOCK_TABLE(STOCKTABLE, 0, 0, 1, 1), BASIC_TABLE(BASICTABLE, 0, 0, 1, 1),
	DELIVERY_TILE(DELIVERYTILE, 0, 0, 1, 1);

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

		COOK("cook.png", 13), CITY("city.png", 20), SAUCETABLETILE("SauceTableTile.png", 13),
		PANTILE("PanTile.png", 13), FRIETILE("FrieTile.png", 13), CUTTILE("CutTile.png", 13),
		KITCHEN_TRUCK("kitchen.png", 256), TRASHTILE("TrashTile.png", 13), STOCKTABLE("StockTable.png", 13),
		BASICTABLE("BasicTableTile.png", 13), DELIVERYTILE("DeliveryTile.png", 13);

		public final String filename;
		public final int tileSize;

		Spritesheet(String filename, int tileSize) {
			this.filename = filename;
			this.tileSize = tileSize;
		}
	}

}
