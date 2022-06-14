package info3.game.content;

import info3.game.graphics.Sprite;

public enum ItemType {

	// TODO Change Sprite of every Item
	POTATO(Sprite.COWBOY0, "patate", false), COOKED_POTATO(Sprite.COWBOY0, "patate cuite", false),
	MASHED_POTATO(Sprite.COWBOY0, "purée", false), FRIES(Sprite.COWBOY0, "frites", false),
	COOKED_FRIES(Sprite.COWBOY0, "frites cuites", false), GALETTE(Sprite.COWBOY0, "galette", false),
	COOKED_GALETTE(Sprite.COWBOY0, "galette cuite", false), SALAD(Sprite.COWBOY0, "salade", false),
	SALAD_LEAF(Sprite.COWBOY0, "feuille de salade", false), POTATO_SALAD(Sprite.COWBOY0, "salade de patate", true),
	CHEESE(Sprite.COWBOY0, "fromage", false), POUTINE(Sprite.COWBOY0, "poutine", true),
	TOMATO(Sprite.COWBOY0, "tomate", false), TOMATO_SLICE(Sprite.COWBOY0, "rondelle de tomate", false),
	MEAT(Sprite.COWBOY0, "viande", false), COOKED_MEAT(Sprite.COWBOY0, "viande cuite", false),
	HASHED_MEAT(Sprite.COWBOY0, "viande hachée", false),
	COOKED_HASHED_MEAT(Sprite.COWBOY0, "viande hachée cuite", false),
	CLASSIC_BURGER(Sprite.COWBOY0, "burger classique", true), BREAD_SLICE(Sprite.COWBOY0, "tranche de pain", false),
	VEGI_BURGER(Sprite.COWBOY0, "burger végétarien", true), SHEPHERDS_PIE(Sprite.COWBOY0, "hachis parmentier", true),
	BREAD(Sprite.COWBOY0, "pain", false);

	Sprite sprite;
	String DisplayName;
	boolean sauceAllowed = false;

	ItemType(Sprite sprite, String DisplayName, boolean sauceAllowed) {
		this.sprite = sprite;
		this.DisplayName = DisplayName;
		this.sauceAllowed = sauceAllowed;
	}

}
