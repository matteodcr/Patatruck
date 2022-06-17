package info3.game.graphics;

import static info3.game.graphics.Sprite.Spritesheet.BASICTABLEE;
import static info3.game.graphics.Sprite.Spritesheet.BASICTABLEN;
import static info3.game.graphics.Sprite.Spritesheet.BASICTABLES;
import static info3.game.graphics.Sprite.Spritesheet.BASICTABLEW;
import static info3.game.graphics.Sprite.Spritesheet.BREADSLICE_;
import static info3.game.graphics.Sprite.Spritesheet.BREAD_;
import static info3.game.graphics.Sprite.Spritesheet.CHEESE_;
import static info3.game.graphics.Sprite.Spritesheet.CITY;
import static info3.game.graphics.Sprite.Spritesheet.CLASSICBURGER_;
import static info3.game.graphics.Sprite.Spritesheet.COCKROACHE;
import static info3.game.graphics.Sprite.Spritesheet.COCKROACHN;
import static info3.game.graphics.Sprite.Spritesheet.COCKROACHS;
import static info3.game.graphics.Sprite.Spritesheet.COCKROACHW;
import static info3.game.graphics.Sprite.Spritesheet.COOKE;
import static info3.game.graphics.Sprite.Spritesheet.COOKEDFRIES_;
import static info3.game.graphics.Sprite.Spritesheet.COOKEDHASHMEAT_;
import static info3.game.graphics.Sprite.Spritesheet.COOKEDMEAT_;
import static info3.game.graphics.Sprite.Spritesheet.COOKN;
import static info3.game.graphics.Sprite.Spritesheet.COOKS;
import static info3.game.graphics.Sprite.Spritesheet.COOKW;
import static info3.game.graphics.Sprite.Spritesheet.CUTTILE;
import static info3.game.graphics.Sprite.Spritesheet.DELIVERY;
import static info3.game.graphics.Sprite.Spritesheet.FRIES_;
import static info3.game.graphics.Sprite.Spritesheet.GALETTECOOKED_;
import static info3.game.graphics.Sprite.Spritesheet.GALETTE_;
import static info3.game.graphics.Sprite.Spritesheet.HACHI_;
import static info3.game.graphics.Sprite.Spritesheet.HASHEDMEAT_;
import static info3.game.graphics.Sprite.Spritesheet.KETCHUPTABLE;
import static info3.game.graphics.Sprite.Spritesheet.KITCHEN_TRUCK;
import static info3.game.graphics.Sprite.Spritesheet.MASHEDPOTATO_;
import static info3.game.graphics.Sprite.Spritesheet.MAYOTABLE;
import static info3.game.graphics.Sprite.Spritesheet.MEAT_;
import static info3.game.graphics.Sprite.Spritesheet.OFFFRIETILE;
import static info3.game.graphics.Sprite.Spritesheet.OFFPANTILE;
import static info3.game.graphics.Sprite.Spritesheet.ONFRIETILE;
import static info3.game.graphics.Sprite.Spritesheet.ONPANTILE;
import static info3.game.graphics.Sprite.Spritesheet.POTATOCOOKED_;
import static info3.game.graphics.Sprite.Spritesheet.POTATOSALAD_;
import static info3.game.graphics.Sprite.Spritesheet.POTATO_;
import static info3.game.graphics.Sprite.Spritesheet.POUTINE_;
import static info3.game.graphics.Sprite.Spritesheet.SALADELEAF_;
import static info3.game.graphics.Sprite.Spritesheet.SALADE_;
import static info3.game.graphics.Sprite.Spritesheet.SAUCETABLETILE;
import static info3.game.graphics.Sprite.Spritesheet.STOCKTABLE;
import static info3.game.graphics.Sprite.Spritesheet.TOMATOSLICE_;
import static info3.game.graphics.Sprite.Spritesheet.TOMATO_;
import static info3.game.graphics.Sprite.Spritesheet.TRASHTILE;
import static info3.game.graphics.Sprite.Spritesheet.VEGIBURGER_;

public enum Sprite {

	PLAYER_KITCHEN_N(COOKN, 0, 0, 1, 1), PLAYER_KITCHEN_E(COOKE, 0, 0, 1, 1), PLAYER_KITCHEN_W(COOKW, 0, 0, 1, 1),
	PLAYER_KITCHEN_S(COOKS, 0, 0, 1, 1), BUILDING_SQUARE_1_NORMAL(CITY, 0, 0, 1, 1),
	BUILDING_SQUARE_1_ROUNDED(CITY, 0, 1, 1, 1), BUILDING_SQUARE_1_SQUIRCLE(CITY, 0, 2, 1, 1),
	KITCHENTRUCK(KITCHEN_TRUCK, 0, 0, 1, 1), SAUCE_TABLE_TILE(SAUCETABLETILE, 0, 0, 1, 1),
	OFF_FRIE_TILE(OFFFRIETILE, 0, 0, 1, 1), ON_FRIE_TILE(ONFRIETILE, 0, 0, 1, 1), CUT_TILE(CUTTILE, 0, 0, 1, 1),
	OFF_PAN_TILE(OFFPANTILE, 0, 0, 1, 1), ON_PAN_TILE(ONPANTILE, 0, 0, 1, 1), TRASH_TILE(TRASHTILE, 0, 0, 1, 1),
	STOCK_TABLE(STOCKTABLE, 0, 0, 1, 1), BASICTABLE_N(BASICTABLEN, 0, 0, 1, 1), BASICTABLE_E(BASICTABLEE, 0, 0, 1, 1),
	BASICTABLE_W(BASICTABLEW, 0, 0, 1, 1), BASICTABLE_S(BASICTABLES, 0, 0, 1, 1), DELIVERYTILE(DELIVERY, 0, 0, 1, 1),
	TOMATO(TOMATO_, 0, 0, 1, 1), POTATO(POTATO_, 0, 0, 1, 1), SALADE(SALADE_, 0, 0, 1, 1), BREAD(BREAD_, 0, 0, 1, 1),
	CHEESE(CHEESE_, 0, 0, 1, 1), MEAT(MEAT_, 0, 0, 1, 1), COCKROACH_ENTITY_N(COCKROACHN, 0, 0, 1, 1),
	COCKROACH_ENTITY_E(COCKROACHE, 0, 0, 1, 1), COCKROACH_ENTITY_W(COCKROACHW, 0, 0, 1, 1),
	COCKROACH_ENTITY_S(COCKROACHS, 0, 0, 1, 1), BREADSLICE(BREADSLICE_, 0, 0, 1, 1),
	COOKEDFRIES(COOKEDFRIES_, 0, 0, 1, 1), COOKEDHASHMEAT(COOKEDHASHMEAT_, 0, 0, 1, 1),
	COOKEDMEAT(COOKEDMEAT_, 0, 0, 1, 1), FRIES(FRIES_, 0, 0, 1, 1), GALETTE(GALETTE_, 0, 0, 1, 1),
	GALETTECOOKED(GALETTECOOKED_, 0, 0, 1, 1), HACHI(HACHI_, 0, 0, 1, 1), HASHEDMEAT(HASHEDMEAT_, 0, 0, 1, 1),
	MASHEDPOTATO(MASHEDPOTATO_, 0, 0, 1, 1), CLASSICBURGER(CLASSICBURGER_, 0, 0, 1, 1),
	POTATOCOOKED(POTATOCOOKED_, 0, 0, 1, 1), POTATOSALAD(POTATOSALAD_, 0, 0, 1, 1), POUTINE(POUTINE_, 0, 0, 1, 1),
	SALADELEAF(SALADELEAF_, 0, 0, 1, 1), TOMATOSLICE(TOMATOSLICE_, 0, 0, 1, 1), VEGIBURGER(VEGIBURGER_, 0, 0, 1, 1),
	KETCHUP(KETCHUPTABLE, 0, 0, 1, 1), MAYONNAISE(MAYOTABLE, 0, 0, 1, 1);

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

		COOKN("cookN.png", 13), CITY("city.png", 20), SAUCETABLETILE("SauceTableTile.png", 13),
		OFFPANTILE("OffPanTable.png", 13), ONPANTILE("OnPanTable.png", 13), OFFFRIETILE("OffFrieTile.png", 13),
		ONFRIETILE("OnFrieTile.png", 13), CUTTILE("CutTile.png", 13), KITCHEN_TRUCK("kitchen.png", 256),
		TRASHTILE("TrashTile.png", 13), STOCKTABLE("StockTable.png", 13), BASICTABLEN("BasicTableTileN.png", 13),
		BASICTABLEE("BasicTableTileE.png", 13), BASICTABLEW("BasicTableTileW.png", 13),
		BASICTABLES("BasicTableTileS.png", 13), DELIVERY("DeliveryTile.png", 13),
		TOMATO_("Sprite_cuisine_ingredient/tomato.png", 13), POTATO_("Sprite_cuisine_ingredient/Potato.png", 13),
		SALADE_("Sprite_cuisine_ingredient/salade.png", 13), BREAD_("Sprite_cuisine_ingredient/bread.png", 13),
		CHEESE_("Sprite_cuisine_ingredient/cheese.png", 13), MEAT_("Sprite_cuisine_ingredient/meat.png", 13),
		COCKROACHN("cockroachN.png", 13), COCKROACHE("cockroachE.png", 13), COCKROACHW("cockroachW.png", 13),
		COCKROACHS("cockroachS.png", 13), COOKE("cookE.png", 13), COOKW("cookW.png", 13), COOKS("cookS.png", 13),
		BREADSLICE_("Sprite_cuisine_ingredient/breadslice.png", 13),
		COOKEDFRIES_("Sprite_cuisine_ingredient/coockedfries.png", 13),
		COOKEDHASHMEAT_("Sprite_cuisine_ingredient/cookedhashsedmeat.png", 13),
		COOKEDMEAT_("Sprite_cuisine_ingredient/cookedmeat.png", 13), FRIES_("Sprite_cuisine_ingredient/fries.png", 13),
		GALETTE_("Sprite_cuisine_ingredient/galette.png", 13),
		GALETTECOOKED_("Sprite_cuisine_ingredient/galettecoocked.png", 13),
		HACHI_("Sprite_cuisine_ingredient/hachi.png", 13), HASHEDMEAT_("Sprite_cuisine_ingredient/hashedmeat.png", 13),
		MASHEDPOTATO_("Sprite_cuisine_ingredient/mashed_potato.png", 13),
		CLASSICBURGER_("Sprite_cuisine_ingredient/meatburger.png", 13),
		POTATOCOOKED_("Sprite_cuisine_ingredient/potatocoocked.png", 13),
		POTATOSALAD_("Sprite_cuisine_ingredient/potatosalade.png", 13),
		POUTINE_("Sprite_cuisine_ingredient/poutine.png", 13),
		SALADELEAF_("Sprite_cuisine_ingredient/saladeleaf.png", 13),
		TOMATOSLICE_("Sprite_cuisine_ingredient/tomatoslice.png", 13), KETCHUPTABLE("ketchup.png", 13),
		MAYOTABLE("mayonnaise.png", 13), VEGIBURGER_("Sprite_cuisine_ingredient/vegiburger.png", 13);

		public final String filename;
		public final int tileSize;

		Spritesheet(String filename, int tileSize) {
			this.filename = filename;
			this.tileSize = tileSize;
		}
	}

}
