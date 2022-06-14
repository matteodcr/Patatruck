package info3.game.content;

import info3.game.graphics.Sprite;

public enum Ingredients {

	POTATO(Sprite.COWBOY0, "patate", false, false, false),
	COOKED_POTATO(Sprite.COWBOY0, "patate cuite", false, false, false),
	MASHED_POTATO(Sprite.COWBOY0, "puree", false, false, false), FRIES(Sprite.COWBOY0, "frites", false, false, false),
	COOKED_FRIES(Sprite.COWBOY0, "frites cuites", false, false, false),
	GALETTE(Sprite.COWBOY0, "galette", false, false, false),
	COOKED_GALETTE(Sprite.COWBOY0, "galette cuite", false, false, false),
	SALAD(Sprite.COWBOY0, "salade", false, false, false),
	SALAD_LEAF(Sprite.COWBOY0, "feuille de salade", false, false, false),
	POTATO_SALAD(Sprite.COWBOY0, "salade de patate", true, false, false),
	CHEESE(Sprite.COWBOY0, "fromage", false, false, false), POUTINE(Sprite.COWBOY0, "poutine", true, false, false),
	TOMATO(Sprite.COWBOY0, "tomate", false, false, false),
	TOMATO_SLICE(Sprite.COWBOY0, "rondelle de tomate", false, false, false),
	MEAT(Sprite.COWBOY0, "viande", false, false, false),
	COOKED_MEAT(Sprite.COWBOY0, "viande cuite", false, false, false),
	HASHED_MEAT(Sprite.COWBOY0, "viande hachee", false, false, false),
	COOKED_HASHED_MEAT(Sprite.COWBOY0, "viande hachee cuite", false, false, false),
	CLASSIC_BURGER(Sprite.COWBOY0, "burger classique", true, true, true),
	BREAD_SLICE(Sprite.COWBOY0, "tranche de pain", false, false, false),
	VEGI_BURGER(Sprite.COWBOY0, "burger vegetarien", true, true, true),
	SHEPHERDS_PIE(Sprite.COWBOY0, "hachis parmentier", true, false, false),
	BREAD(Sprite.COWBOY0, "pain", false, false, false), KETCHUP(Sprite.COWBOY0, "ketchup", false, false, false),
	MAYO(Sprite.COWBOY0, "ketchup", false, false, false), KETCHUP_MAYO(Sprite.COWBOY0, "ketchup", false, false, false),
	FAILED_RECIPE(Sprite.COWBOY0, "recette ratee", false, false, false);

	boolean finalRecipe = false, acceptsOptionalSalad = false, acceptsOptionalTomato = false;
	Sprite sprite;
	String DisplayName;

	Ingredients(Sprite sprite, String DisplayName, boolean finalRecipe, boolean acceptsOptionalSalad,
			boolean acceptsOptionalTomato) {
		this.sprite = sprite;
		this.DisplayName = DisplayName;
		this.finalRecipe = finalRecipe;
		this.acceptsOptionalSalad = acceptsOptionalSalad;
		this.acceptsOptionalTomato = acceptsOptionalTomato;
	}

}
