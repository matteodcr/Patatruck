package info3.game.graphics;

import static info3.game.graphics.Sprite.Spritesheet.AUTOMATON_SELECTION;
import static info3.game.graphics.Sprite.Spritesheet.BASICTABLEE;
import static info3.game.graphics.Sprite.Spritesheet.BASICTABLEN;
import static info3.game.graphics.Sprite.Spritesheet.BASICTABLES;
import static info3.game.graphics.Sprite.Spritesheet.BASICTABLEW;
import static info3.game.graphics.Sprite.Spritesheet.BREADSLICE_;
import static info3.game.graphics.Sprite.Spritesheet.BREAD_;
import static info3.game.graphics.Sprite.Spritesheet.CHEESE_;
import static info3.game.graphics.Sprite.Spritesheet.CITY;
import static info3.game.graphics.Sprite.Spritesheet.CLASSICBURGER_;
import static info3.game.graphics.Sprite.Spritesheet.CLOCK_;
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
import static info3.game.graphics.Sprite.Spritesheet.FAILEDITEM_;
import static info3.game.graphics.Sprite.Spritesheet.FRIES_;
import static info3.game.graphics.Sprite.Spritesheet.GALETTECOOKED_;
import static info3.game.graphics.Sprite.Spritesheet.GALETTE_;
import static info3.game.graphics.Sprite.Spritesheet.HACHI_;
import static info3.game.graphics.Sprite.Spritesheet.HASHEDMEAT_;
import static info3.game.graphics.Sprite.Spritesheet.KETCHUPTABLE;
import static info3.game.graphics.Sprite.Spritesheet.KETCHUP_INDIC;
import static info3.game.graphics.Sprite.Spritesheet.KETCHUP_MAYO_;
import static info3.game.graphics.Sprite.Spritesheet.KETCHUP_MAYO_INDIC;
import static info3.game.graphics.Sprite.Spritesheet.KITCHEN;
import static info3.game.graphics.Sprite.Spritesheet.MASHEDPOTATO_;
import static info3.game.graphics.Sprite.Spritesheet.MAYOTABLE;
import static info3.game.graphics.Sprite.Spritesheet.MAYO_INDIC;
import static info3.game.graphics.Sprite.Spritesheet.MEAT_;
import static info3.game.graphics.Sprite.Spritesheet.MENU;
import static info3.game.graphics.Sprite.Spritesheet.OFFFRIETILE;
import static info3.game.graphics.Sprite.Spritesheet.OFFPANTILE;
import static info3.game.graphics.Sprite.Spritesheet.ONFRIETILE;
import static info3.game.graphics.Sprite.Spritesheet.ONPANTILE;
import static info3.game.graphics.Sprite.Spritesheet.POTATOCOOKED_;
import static info3.game.graphics.Sprite.Spritesheet.POTATOSALAD_;
import static info3.game.graphics.Sprite.Spritesheet.POTATO_;
import static info3.game.graphics.Sprite.Spritesheet.POUTINE_;
import static info3.game.graphics.Sprite.Spritesheet.RECIPES_;
import static info3.game.graphics.Sprite.Spritesheet.SALADELEAF_;
import static info3.game.graphics.Sprite.Spritesheet.SALADE_;
import static info3.game.graphics.Sprite.Spritesheet.SAUCETABLETILE;
import static info3.game.graphics.Sprite.Spritesheet.STOCKTABLE;
import static info3.game.graphics.Sprite.Spritesheet.TOMATOSLICE_;
import static info3.game.graphics.Sprite.Spritesheet.TOMATO_;
import static info3.game.graphics.Sprite.Spritesheet.TRASHTILE_EMPTY;
import static info3.game.graphics.Sprite.Spritesheet.TRASHTILE_FULL;
import static info3.game.graphics.Sprite.Spritesheet.VEGIBURGER_;

public enum Sprite {
	AS_LOGO(AUTOMATON_SELECTION, 11, 10, 5, 2), AS_BACKDROP(AUTOMATON_SELECTION, 0, 0, 16, 9),
	AS_LEFT(AUTOMATON_SELECTION, 0, 10, 1, 1), AS_RIGHT(AUTOMATON_SELECTION, 1, 10, 1, 1),
	AS_RESET(AUTOMATON_SELECTION, 8, 10, 1, 1), AS_LEFT_BOX(AUTOMATON_SELECTION, 0, 11, 1, 1),
	AS_RIGHT_BOX(AUTOMATON_SELECTION, 1, 11, 1, 1), AS_BOX(AUTOMATON_SELECTION, 2, 11, 6, 1),
	AS_RESET_BOX(AUTOMATON_SELECTION, 8, 11, 1, 1),

	// Kitchen tiles
	PLAYER_KITCHEN_N(COOKN, 0, 0, 1, 1), PLAYER_KITCHEN_E(COOKE, 0, 0, 1, 1), PLAYER_KITCHEN_W(COOKW, 0, 0, 1, 1),
	PLAYER_KITCHEN_S(COOKS, 0, 0, 1, 1), SAUCE_TABLE_TILE(SAUCETABLETILE, 0, 0, 1, 1),
	OFF_FRIE_TILE(OFFFRIETILE, 0, 0, 1, 1), ON_FRIE_TILE(ONFRIETILE, 0, 0, 1, 1), CUT_TILE(CUTTILE, 0, 0, 1, 1),
	OFF_PAN_TILE(OFFPANTILE, 0, 0, 1, 1), ON_PAN_TILE(ONPANTILE, 0, 0, 1, 1),
	TRASH_TILE_EMPTY(TRASHTILE_EMPTY, 0, 0, 1, 1), TRASH_TILE_FULL(TRASHTILE_FULL, 0, 0, 1, 1),
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
	KETCHUP(KETCHUPTABLE, 0, 0, 1, 1), MAYONNAISE(MAYOTABLE, 0, 0, 1, 1), FAILEDITEM(FAILEDITEM_, 0, 0, 1, 1),
	KITCHEN_TRUCK(KITCHEN, 0, 0, 15, 6), KITCHEN_TRUCK_FLOOR(KITCHEN, 1, 6, 10, 4), ORDER_CARD(MENU, 0, 0, 1, 1),
	CLOCK(CLOCK_, 0, 0, 1, 1), INDIC_MAYO_(MAYO_INDIC, 0, 0, 1, 1), INDIC_KETCHUP_(KETCHUP_INDIC, 0, 0, 1, 1),
	KETCHUP_MAYO(KETCHUP_MAYO_, 0, 0, 1, 1), KITCHEN_TRUCK_SMOKE(KITCHEN, 0, 11, 12, 6),
	KETCHUP_MAYO_INDIC_(KETCHUP_MAYO_INDIC, 0, 0, 1, 1), RECIPES(RECIPES_, 0, 0, 32, 9),

	// City building sprites
	CITY_SQUARE_1_SQUARE(CITY, 0, 0, 1, 1), CITY_SQUARE_1_ROUND(CITY, 0, 1, 1, 1),
	CITY_SQUARE_1_SQUIRCLE(CITY, 0, 2, 1, 1), CITY_BAR_2_VERT_NORMAL(CITY, 1, 0, 1, 2),
	CITY_BAR_2_VERT_DENTED(CITY, 1, 2, 1, 2), CITY_BAR_2_HORIZ_NORMAL(CITY, 0, 4, 2, 1),
	CITY_BAR_2_HORIZ_DENTED(CITY, 0, 5, 2, 1), CITY_BAR_3_VERT_NORMAL(CITY, 2, 0, 1, 3),
	CITY_BAR_3_VERT_ROUNDED(CITY, 2, 3, 1, 3), CITY_BAR_3_HORIZ_NORMAL(CITY, 0, 6, 3, 1),
	CITY_BAR_3_HORIZ_ROUNDED(CITY, 0, 7, 3, 1), CITY_SQUARE_2_SQUARE(CITY, 6, 0, 2, 2),
	CITY_SQUARE_2_DENTED(CITY, 6, 2, 2, 2), CITY_SQUARE_2_BISCUIT(CITY, 6, 4, 2, 2), CITY_CORNER_BR(CITY, 12, 0, 2, 2),
	CITY_CORNER_TL(CITY, 12, 4, 2, 2), CITY_CORNER_BL(CITY, 12, 2, 2, 2), CITY_CORNER_TR(CITY, 12, 6, 2, 2);

	public static final Sprite[] CITY_SQUARES_1 = new Sprite[] { CITY_SQUARE_1_SQUARE, CITY_SQUARE_1_ROUND,
			CITY_SQUARE_1_SQUIRCLE };
	public static final Sprite[] CITY_SQUARES_2 = new Sprite[] { CITY_SQUARE_2_SQUARE, CITY_SQUARE_2_DENTED,
			CITY_SQUARE_2_BISCUIT };

	public static final Sprite[] CITY_BAR_2_VERT = new Sprite[] { CITY_BAR_2_VERT_NORMAL, CITY_BAR_2_VERT_DENTED };
	public static final Sprite[] CITY_BAR_2_HORIZ = new Sprite[] { CITY_BAR_2_HORIZ_NORMAL, CITY_BAR_2_HORIZ_DENTED };
	public static final Sprite[] CITY_BAR_3_VERT = new Sprite[] { CITY_BAR_3_VERT_NORMAL, CITY_BAR_3_VERT_ROUNDED };
	public static final Sprite[] CITY_BAR_3_HORIZ = new Sprite[] { CITY_BAR_3_HORIZ_NORMAL, CITY_BAR_3_HORIZ_ROUNDED };

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

		COOKN("cookN.png", 13), CITY("city.png", 20), SAUCETABLETILE("BasicTableTile.png", 13),
		OFFPANTILE("OffPanTable.png", 13), ONPANTILE("OnPanTable.png", 13), OFFFRIETILE("OffFrieTile.png", 13),
		ONFRIETILE("OnFrieTile.png", 13), CUTTILE("CutTile.png", 13), KITCHEN_TRUCK("kitchen.png", 256),
		TRASHTILE_EMPTY("EmptyTrash.png", 13), TRASHTILE_FULL("FullTrash.png", 13), STOCKTABLE("StockTable.png", 13),
		BASICTABLEN("BasicTableTileN.png", 13), BASICTABLEE("BasicTableTileE.png", 13),
		BASICTABLEW("BasicTableTileW.png", 13), BASICTABLES("BasicTableTileS.png", 13),
		DELIVERY("DeliveryTile.png", 13), TOMATO_("Sprite_cuisine_ingredient/tomate.png", 13),
		POTATO_("Sprite_cuisine_ingredient/patate.png", 13), SALADE_("Sprite_cuisine_ingredient/salade.png", 13),
		BREAD_("Sprite_cuisine_ingredient/pain.png", 13), CHEESE_("Sprite_cuisine_ingredient/fromage.png", 13),
		MEAT_("Sprite_cuisine_ingredient/viande.png", 13), COCKROACHN("cockroachN.png", 13),
		COCKROACHE("cockroachE.png", 13), COCKROACHW("cockroachW.png", 13), COCKROACHS("cockroachS.png", 13),
		COOKE("cookE.png", 13), COOKW("cookW.png", 13), COOKS("cookS.png", 13),
		BREADSLICE_("Sprite_cuisine_ingredient/tranche de pain.png", 13),
		COOKEDFRIES_("Sprite_cuisine_ingredient/frites cuite.png", 13),
		COOKEDHASHMEAT_("Sprite_cuisine_ingredient/viande hachee cuite.png", 13),
		COOKEDMEAT_("Sprite_cuisine_ingredient/viande cuite.png", 13),
		FRIES_("Sprite_cuisine_ingredient/frites.png", 13), GALETTE_("Sprite_cuisine_ingredient/galette.png", 13),
		GALETTECOOKED_("Sprite_cuisine_ingredient/galette cuite.png", 13),
		HACHI_("Sprite_cuisine_ingredient/hachis parmentier.png", 13),
		HASHEDMEAT_("Sprite_cuisine_ingredient/viande hachee.png", 13),
		MASHEDPOTATO_("Sprite_cuisine_ingredient/puree.png", 13),
		CLASSICBURGER_("Sprite_cuisine_ingredient/burger classique.png", 13),
		POTATOCOOKED_("Sprite_cuisine_ingredient/patate cuite.png", 13),
		POTATOSALAD_("Sprite_cuisine_ingredient/salade de patate.png", 13),
		POUTINE_("Sprite_cuisine_ingredient/poutine.png", 13),
		SALADELEAF_("Sprite_cuisine_ingredient/feuille de salade.png", 13),
		TOMATOSLICE_("Sprite_cuisine_ingredient/rondelle de tomate.png", 13), KETCHUPTABLE("ketchup.png", 13),
		MAYOTABLE("mayonnaise.png", 13), VEGIBURGER_("Sprite_cuisine_ingredient/burger vegetarien.png", 13),
		FAILEDITEM_("Sprite_cuisine_ingredient/garbageplate.png", 13),
		AUTOMATON_SELECTION("automaton_selection.png", 16), CLOCK_("clock.png", 19),
		KETCHUP_MAYO_("ketchup_mayo.png", 13),

		BASICTABLE("BasicTableTile.png", 13), COOK("cook.png", 13), KITCHEN("kitchen.png", 13),
		DELIVERYTILE("DeliveryTile.png", 13), COCKROACH("cockroach.png", 13), MENU("menu.png", 69),

		MAYO_INDIC("indic_mayo.png", 13), KETCHUP_INDIC("indic_ketchup.png", 13),
		KETCHUP_MAYO_INDIC("indic_mayo_ketchup.png", 13),

		RECIPES_("Recipes.png", 8);

		public final String filename;
		public final int tileSize;

		Spritesheet(String filename, int tileSize) {
			this.filename = filename;
			this.tileSize = tileSize;
		}
	}

}
