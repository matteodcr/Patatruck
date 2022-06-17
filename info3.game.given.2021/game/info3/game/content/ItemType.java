package info3.game.content;

import info3.game.graphics.Sprite;

public enum ItemType {
	POTATO(Sprite.BASIC_TABLE, "patate", false), COOKED_POTATO(Sprite.BASIC_TABLE, "patate cuite", false),
	MASHED_POTATO(Sprite.BASIC_TABLE, "puree", false), FRIES(Sprite.BASIC_TABLE, "frites", false),
	COOKED_FRIES(Sprite.BASIC_TABLE, "frites cuites", false), GALETTE(Sprite.BASIC_TABLE, "galette", false),
	COOKED_GALETTE(Sprite.BASIC_TABLE, "galette cuite", false), SALAD(Sprite.BASIC_TABLE, "salade", false),
	SALAD_LEAF(Sprite.BASIC_TABLE, "feuille de salade", false),
	POTATO_SALAD(Sprite.BASIC_TABLE, "salade de patate", true), CHEESE(Sprite.BASIC_TABLE, "fromage", false),
	POUTINE(Sprite.BASIC_TABLE, "poutine", true), TOMATO(Sprite.BASIC_TABLE, "tomate", false),
	TOMATO_SLICE(Sprite.BASIC_TABLE, "rondelle de tomate", false), MEAT(Sprite.BASIC_TABLE, "viande", false),
	COOKED_MEAT(Sprite.BASIC_TABLE, "viande cuite", false), HASHED_MEAT(Sprite.BASIC_TABLE, "viande hachee", false),
	COOKED_HASHED_MEAT(Sprite.BASIC_TABLE, "viande hachee cuite", false),
	CLASSIC_BURGER(Sprite.BASIC_TABLE, "burger classique", true),
	CLASSIC_BURGER_SALAD(Sprite.BASIC_TABLE, "burger classique", true),
	CLASSIC_BURGER_TOMATO(Sprite.BASIC_TABLE, "burger classique", true),
	CLASSIC_BURGER_SALAD_TOMATO(Sprite.BASIC_TABLE, "burger classique", true),
	BREAD_SLICE(Sprite.BASIC_TABLE, "tranche de pain", false),
	VEGI_BURGER(Sprite.BASIC_TABLE, "burger vegetarien", true),
	VEGI_BURGER_SALAD(Sprite.BASIC_TABLE, "burger classique", true),
	VEGI_BURGER_TOMATO(Sprite.BASIC_TABLE, "burger classique", true),
	VEGI_BURGER_SALAD_TOMATO(Sprite.BASIC_TABLE, "burger classique", true),
	SHEPHERDS_PIE(Sprite.BASIC_TABLE, "hachis parmentier", true), BREAD(Sprite.BASIC_TABLE, "pain", false),
	KETCHUP(Sprite.BASIC_TABLE, "ketchup", false), MAYO(Sprite.BASIC_TABLE, "ketchup", false),
	KETCHUP_MAYO(Sprite.BASIC_TABLE, "ketchup", false), FAILED_Item(Sprite.BASIC_TABLE, "recette ratee", false);

	final boolean finalItem;

	public boolean isFinalItem() {
		return finalItem;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public String getDisplayName() {
		return displayName;
	}

	final Sprite sprite;
	final String displayName;

	ItemType(Sprite sprite, String displayName, boolean finalItem) {
		this.sprite = sprite;
		this.displayName = displayName;
		this.finalItem = finalItem;
	}
}
