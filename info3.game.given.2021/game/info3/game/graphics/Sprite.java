package info3.game.graphics;

import static info3.game.graphics.Sprite.Spritesheet.AUTOMATON_SELECTION;
import static info3.game.graphics.Sprite.Spritesheet.BLUE_CAR;
import static info3.game.graphics.Sprite.Spritesheet.CITY;
import static info3.game.graphics.Sprite.Spritesheet.CITY_ARROWS;
import static info3.game.graphics.Sprite.Spritesheet.CLOCK_;
import static info3.game.graphics.Sprite.Spritesheet.COCKROACH;
import static info3.game.graphics.Sprite.Spritesheet.COCKROACH_POP;
import static info3.game.graphics.Sprite.Spritesheet.COOK;
import static info3.game.graphics.Sprite.Spritesheet.INGREDIENTS;
import static info3.game.graphics.Sprite.Spritesheet.KETCHUP_INDIC;
import static info3.game.graphics.Sprite.Spritesheet.KETCHUP_MAYO_INDIC;
import static info3.game.graphics.Sprite.Spritesheet.KITCHEN;
import static info3.game.graphics.Sprite.Spritesheet.KITCHENTILES;
import static info3.game.graphics.Sprite.Spritesheet.MAYO_INDIC;
import static info3.game.graphics.Sprite.Spritesheet.MENU;
import static info3.game.graphics.Sprite.Spritesheet.RECIPES_;
import static info3.game.graphics.Sprite.Spritesheet.RED_CAR;
import static info3.game.graphics.Sprite.Spritesheet.SAUCES;
import static info3.game.graphics.Sprite.Spritesheet.SPEEDOMETER_;
import static info3.game.graphics.Sprite.Spritesheet.SPEEDOMETER_HIGH_;
import static info3.game.graphics.Sprite.Spritesheet.SPEEDOMETER_LOW_;
import static info3.game.graphics.Sprite.Spritesheet.YELLOW_CAR;

public enum Sprite {
	AS_LOGO(AUTOMATON_SELECTION, 10, 10, 6, 2), AS_BACKDROP(AUTOMATON_SELECTION, 0, 0, 16, 9),
	AS_LEFT(AUTOMATON_SELECTION, 0, 10, 1, 1), AS_RIGHT(AUTOMATON_SELECTION, 1, 10, 1, 1),
	AS_RESET(AUTOMATON_SELECTION, 8, 10, 1, 1), AS_LEFT_BOX(AUTOMATON_SELECTION, 0, 11, 1, 1),
	AS_RIGHT_BOX(AUTOMATON_SELECTION, 1, 11, 1, 1), AS_BOX(AUTOMATON_SELECTION, 2, 11, 6, 1),
	AS_RESET_BOX(AUTOMATON_SELECTION, 8, 11, 1, 1),

	// Kitchen tiles
	SAUCE_TABLE_TILE(KITCHENTILES, 0, 0, 1, 1), OFF_FRIE_TILE(KITCHENTILES, 2, 2, 1, 1),
	ON_FRIE_TILE(KITCHENTILES, 3, 1, 1, 1), CUT_TILE(KITCHENTILES, 1, 1, 1, 1), OFF_PAN_TILE(KITCHENTILES, 3, 0, 1, 1),
	ON_PAN_TILE(KITCHENTILES, 3, 2, 1, 1), TRASH_TILE_EMPTY(KITCHENTILES, 0, 2, 1, 1),
	TRASH_TILE_FULL(KITCHENTILES, 1, 2, 1, 1), STOCK_TABLE(KITCHENTILES, 0, 3, 1, 1),
	STOCK_TABLE_N(KITCHENTILES, 1, 3, 1, 1), BASICTABLE_N(KITCHENTILES, 1, 0, 1, 1),
	BASICTABLE_E(KITCHENTILES, 0, 1, 1, 1), BASICTABLE_W(KITCHENTILES, 0, 0, 1, 1),
	BASICTABLE_S(KITCHENTILES, 2, 0, 1, 1), DELIVERYTILE(KITCHENTILES, 2, 1, 1, 1), ORDER_CARD(MENU, 0, 0, 1, 1),
	RECIPES(RECIPES_, 0, 0, 32, 9), CLOCK(CLOCK_, 0, 0, 1, 1), INDIC_MAYO_(MAYO_INDIC, 0, 0, 1, 1),
	INDIC_KETCHUP_(KETCHUP_INDIC, 0, 0, 1, 1),

	// Kitchen stock table indicators
	FULL_0(KITCHENTILES, 0, 4, 1, 1), FULL_1(KITCHENTILES, 1, 4, 1, 1), FULL_2(KITCHENTILES, 2, 4, 1, 1),
	FULL_3(KITCHENTILES, 3, 4, 1, 1), FULL_4(KITCHENTILES, 4, 4, 1, 1), FULL_5(KITCHENTILES, 5, 4, 1, 1),
	FULL_6(KITCHENTILES, 6, 4, 1, 1), FULL_7(KITCHENTILES, 7, 4, 1, 1),

	// Ingredients
	TOMATO(INGREDIENTS, 4, 4, 1, 1), POTATO(INGREDIENTS, 2, 3, 1, 1), SALADE(INGREDIENTS, 2, 4, 1, 1),
	BREAD(INGREDIENTS, 1, 3, 1, 1), CHEESE(INGREDIENTS, 1, 2, 1, 1), MEAT(INGREDIENTS, 5, 1, 1, 1),
	BREADSLICE(INGREDIENTS, 5, 0, 1, 1), COOKEDFRIES(INGREDIENTS, 0, 2, 1, 1), COOKEDHASHMEAT(INGREDIENTS, 5, 4, 1, 1),
	COOKEDMEAT(INGREDIENTS, 5, 2, 1, 1), FRIES(INGREDIENTS, 4, 1, 1, 1), GALETTE(INGREDIENTS, 2, 2, 1, 1),
	GALETTECOOKED(INGREDIENTS, 3, 2, 1, 1), HACHI(INGREDIENTS, 0, 3, 1, 1), HASHEDMEAT(INGREDIENTS, 5, 3, 1, 1),
	MASHEDPOTATO(INGREDIENTS, 0, 4, 1, 1), CLASSICBURGER(INGREDIENTS, 0, 0, 1, 1),
	POTATOCOOKED(INGREDIENTS, 3, 3, 1, 1), POTATOSALAD(INGREDIENTS, 3, 4, 1, 1), POUTINE(INGREDIENTS, 4, 3, 1, 1),
	SALADELEAF(INGREDIENTS, 3, 1, 1, 1), TOMATOSLICE(INGREDIENTS, 1, 4, 1, 1), VEGIBURGER(INGREDIENTS, 4, 0, 1, 1),
	KETCHUP(SAUCES, 0, 0, 1, 1), MAYONNAISE(SAUCES, 0, 1, 1, 1), FAILEDITEM(INGREDIENTS, 4, 2, 1, 1),
	KITCHEN_TRUCK(KITCHEN, 0, 0, 15, 6), KITCHEN_TRUCK_FLOOR(KITCHEN, 1, 6, 10, 4), KETCHUP_MAYO(SAUCES, 1, 0, 1, 1),
	KITCHEN_TRUCK_SMOKE(KITCHEN, 0, 11, 12, 6), KETCHUP_MAYO_INDIC_(KETCHUP_MAYO_INDIC, 0, 0, 1, 1),
	CLASSICBURGER_SALAD(INGREDIENTS, 1, 0, 1, 1), CLASSICBURGER_SALAD_TOMATO(INGREDIENTS, 2, 0, 1, 1),
	CLASSICBURGER_TOMATO(INGREDIENTS, 3, 0, 1, 1), VEGIBURGER_SALAD(INGREDIENTS, 0, 1, 1, 1),
	VEGIBURGER_SALAD_TOMATO(INGREDIENTS, 1, 1, 1, 1), VEGIBURGER_TOMATO(INGREDIENTS, 2, 1, 1, 1),

	// Entities
	PLAYER_KITCHEN_N(COOK, 1, 0, 1, 1), PLAYER_KITCHEN_E(COOK, 0, 0, 1, 1), PLAYER_KITCHEN_W(COOK, 1, 1, 1, 1),
	PLAYER_KITCHEN_S(COOK, 0, 1, 1, 1), COCKROACH_POP_N(COCKROACH_POP, 1, 0, 1, 1),
	COCKROACH_POP_S(COCKROACH_POP, 0, 1, 1, 1), COCKROACH_POP_E(COCKROACH_POP, 0, 0, 1, 1),
	COCKROACH_POP_W(COCKROACH_POP, 1, 0, 1, 1), COCKROACH_ENTITY_N(COCKROACH, 1, 0, 1, 1),
	COCKROACH_ENTITY_E(COCKROACH, 0, 0, 1, 1), COCKROACH_ENTITY_W(COCKROACH, 1, 1, 1, 1),
	COCKROACH_ENTITY_S(COCKROACH, 0, 1, 1, 1), BLUE_CAR_N(BLUE_CAR, 0, 0, 1, 1), BLUE_CAR_S(BLUE_CAR, 1, 0, 1, 1),
	BLUE_CAR_E(BLUE_CAR, 0, 1, 1, 1), BLUE_CAR_W(BLUE_CAR, 1, 1, 1, 1), RED_CAR_N(RED_CAR, 0, 0, 1, 1),
	RED_CAR_S(RED_CAR, 1, 1, 1, 1), RED_CAR_E(RED_CAR, 1, 0, 1, 1), RED_CAR_W(RED_CAR, 0, 1, 1, 1),
	YELLOW_CAR_N(YELLOW_CAR, 0, 0, 1, 1), YELLOW_CAR_S(YELLOW_CAR, 1, 0, 1, 1), YELLOW_CAR_E(YELLOW_CAR, 0, 1, 1, 1),
	YELLOW_CAR_W(YELLOW_CAR, 1, 1, 1, 1),

	// City building sprites
	CITY_SQUARE_1_SQUARE(CITY, 0, 0, 1, 1), CITY_SQUARE_1_ROUND(CITY, 0, 1, 1, 1),
	CITY_SQUARE_1_SQUIRCLE(CITY, 0, 2, 1, 1), CITY_BAR_2_VERT_NORMAL(CITY, 1, 0, 1, 2),
	CITY_BAR_2_VERT_DENTED(CITY, 1, 2, 1, 2), CITY_BAR_2_HORIZ_NORMAL(CITY, 0, 4, 2, 1),
	CITY_BAR_2_HORIZ_DENTED(CITY, 0, 5, 2, 1), CITY_BAR_3_VERT_NORMAL(CITY, 2, 0, 1, 3),
	CITY_BAR_3_VERT_ROUNDED(CITY, 2, 3, 1, 3), CITY_BAR_3_HORIZ_NORMAL(CITY, 0, 6, 3, 1),
	CITY_BAR_3_HORIZ_ROUNDED(CITY, 0, 7, 3, 1), CITY_SQUARE_2_SQUARE(CITY, 6, 0, 2, 2),
	CITY_SQUARE_2_DENTED(CITY, 6, 2, 2, 2), CITY_SQUARE_2_BISCUIT(CITY, 6, 4, 2, 2), CITY_CORNER_BR(CITY, 12, 0, 2, 2),
	CITY_CORNER_TL(CITY, 12, 4, 2, 2), CITY_CORNER_BL(CITY, 12, 2, 2, 2), CITY_CORNER_TR(CITY, 12, 6, 2, 2),
	SPEEDOMETER(SPEEDOMETER_, 0, 0, 1, 1), SPEEDOMETER_LOW(SPEEDOMETER_LOW_, 0, 0, 1, 1),
	SPEEDOMETER_HIGH(SPEEDOMETER_HIGH_, 0, 0, 1, 1), CITY_MARKET_CROSSING(CITY, 0, 8, 1, 1),
	CITY_MARKET_T(CITY, 1, 8, 1, 1), CITY_MARKET_L(CITY, 2, 8, 1, 1), CITY_MARKET_TR(CITY, 3, 8, 1, 1),
	CITY_MARKET_LB(CITY, 4, 8, 1, 1), CITY_MARKET_TL(CITY, 5, 8, 1, 1), CITY_MARKET_LT(CITY, 6, 8, 1, 1),

	// City direction arrows sprites
	MARKET_LEFT_ARROW(CITY_ARROWS, 0, 0, 1, 1), MARKET_UP_ARROW(CITY_ARROWS, 1, 0, 1, 1),
	MARKET_RIGHT_ARROW(CITY_ARROWS, 2, 0, 1, 1), MARKET_DOWN_ARROW(CITY_ARROWS, 3, 0, 1, 1),
	MARKET_UPLEFT_ARROW(CITY_ARROWS, 0, 1, 1, 1), MARKET_UPRIGHT_ARROW(CITY_ARROWS, 1, 1, 1, 1),
	MARKET_DOWNRIGHT_ARROW(CITY_ARROWS, 2, 1, 1, 1), MARKET_DOWNLEFT_ARROW(CITY_ARROWS, 3, 1, 1, 1),
	DELIVERY_LEFT_ARROW(CITY_ARROWS, 0, 2, 1, 1), DELIVERY_UP_ARROW(CITY_ARROWS, 1, 2, 1, 1),
	DELIVERY_RIGHT_ARROW(CITY_ARROWS, 2, 2, 1, 1), DELIVERY_DOWN_ARROW(CITY_ARROWS, 3, 2, 1, 1),
	DELIVERY_UPLEFT_ARROW(CITY_ARROWS, 0, 3, 1, 1), DELIVERY_UPRIGHT_ARROW(CITY_ARROWS, 1, 3, 1, 1),
	DELIVERY_DOWNRIGHT_ARROW(CITY_ARROWS, 2, 3, 1, 1), DELIVERY_DOWNLEFT_ARROW(CITY_ARROWS, 3, 3, 1, 1);

	public static final Sprite[] FULL_INDICATORS = new Sprite[] { FULL_0, FULL_1, FULL_2, FULL_3, FULL_4, FULL_5,
			FULL_6, FULL_7 };

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

		// Kitchen
		KITCHENTILES("kitchen_tiles.png", 13), KITCHEN_TRUCK("kitchen.png", 256), COOK("cook.png", 13),
		KITCHEN("kitchen.png", 13), COCKROACH("cockroach.png", 13), MENU("menu.png", 69),
		COCKROACH_POP("cockroach_pop.png", 13), RECIPES_("Recipes.png", 8), INGREDIENTS("ingredients.png", 13),
		CLOCK_("clock.png", 19), MAYO_INDIC("indic_mayo.png", 13), KETCHUP_INDIC("indic_ketchup.png", 13),
		KETCHUP_MAYO_INDIC("indic_mayo_ketchup.png", 13), SAUCES("sauces.png", 13),

		// City
		CITY("city.png", 20), CITYTRUCK("cityTruck.png", 13), SPEEDOMETER_("speedometer.png", 19),
		SPEEDOMETER_LOW_("speedometerLow.png", 19), SPEEDOMETER_HIGH_("speedometerHigh.png", 19),
		BLUE_CAR("blue_car.png", 4), RED_CAR("red_car.png", 4), YELLOW_CAR("yellow_car.png", 4),

		// Other
		AUTOMATON_SELECTION("automaton_selection.png", 16), CITY_ARROWS("city-arrows.png", 7);

		public final String filename;
		public final int tileSize;

		Spritesheet(String filename, int tileSize) {
			this.filename = filename;
			this.tileSize = tileSize;
		}
	}

}
