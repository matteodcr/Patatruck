package info3.game.content;

import info3.game.graphics.Sprite;

public enum ItemType {
	POTATO(Sprite.BASIC_TABLE, "patate", false, false, false),
	COOKED_POTATO(Sprite.BASIC_TABLE, "patate cuite", false, false, false),
	MASHED_POTATO(Sprite.BASIC_TABLE, "puree", false, false, false),
	FRIES(Sprite.BASIC_TABLE, "frites", false, false, false),
	COOKED_FRIES(Sprite.BASIC_TABLE, "frites cuites", false, false, false),
	GALETTE(Sprite.BASIC_TABLE, "galette", false, false, false),
	COOKED_GALETTE(Sprite.BASIC_TABLE, "galette cuite", false, false, false),
	SALAD(Sprite.BASIC_TABLE, "salade", false, false, false),
	SALAD_LEAF(Sprite.BASIC_TABLE, "feuille de salade", false, false, false),
	POTATO_SALAD(Sprite.BASIC_TABLE, "salade de patate", true, false, false),
	CHEESE(Sprite.BASIC_TABLE, "fromage", false, false, false),
	POUTINE(Sprite.BASIC_TABLE, "poutine", true, false, false),
	TOMATO(Sprite.BASIC_TABLE, "tomate", false, false, false),
	TOMATO_SLICE(Sprite.BASIC_TABLE, "rondelle de tomate", false, false, false),
	MEAT(Sprite.BASIC_TABLE, "viande", false, false, false),
	COOKED_MEAT(Sprite.BASIC_TABLE, "viande cuite", false, false, false),
	HASHED_MEAT(Sprite.BASIC_TABLE, "viande hachee", false, false, false),
	COOKED_HASHED_MEAT(Sprite.BASIC_TABLE, "viande hachee cuite", false, false, false),
	CLASSIC_BURGER(Sprite.BASIC_TABLE, "burger classique", true, true, true),
	BREAD_SLICE(Sprite.BASIC_TABLE, "tranche de pain", false, false, false),
	VEGI_BURGER(Sprite.BASIC_TABLE, "burger vegetarien", true, true, true),
	SHEPHERDS_PIE(Sprite.BASIC_TABLE, "hachis parmentier", true, false, false),
	BREAD(Sprite.BASIC_TABLE, "pain", false, false, false), KETCHUP(Sprite.BASIC_TABLE, "ketchup", false, false, false),
	MAYO(Sprite.BASIC_TABLE, "ketchup", false, false, false),
	KETCHUP_MAYO(Sprite.BASIC_TABLE, "ketchup", false, false, false),
	FAILED_Item(Sprite.BASIC_TABLE, "recette ratee", false, false, false);

	final boolean finalItem, acceptsOptionalSalad, acceptsOptionalTomato;

	public boolean isFinalItem() {
		return finalItem;
	}

	public boolean isAcceptsOptionalSalad() {
		return acceptsOptionalSalad;
	}

	public boolean isAcceptsOptionalTomato() {
		return acceptsOptionalTomato;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public String getDisplayName() {
		return displayName;
	}

	final Sprite sprite;
	final String displayName;

	ItemType(Sprite sprite, String displayName, boolean finalItem, boolean acceptsOptionalSalad,
			boolean acceptsOptionalTomato) {
		this.sprite = sprite;
		this.displayName = displayName;
		this.finalItem = finalItem;
		this.acceptsOptionalSalad = acceptsOptionalSalad;
		this.acceptsOptionalTomato = acceptsOptionalTomato;
	}
}
