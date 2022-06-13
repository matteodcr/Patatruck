package info3.game.content;

import info3.game.graphics.Sprite;

public enum ItemType {

	// TODO Change Sprite of every Item
	POTATO(Sprite.COWBOY0, "Potato", false), COOKED_POTATO(Sprite.COWBOY0, "Cooked_Potato", false),
	MASHED_POTATO(Sprite.COWBOY0, "Mashed_Potato", false), FRIES(Sprite.COWBOY0, "Fries", false),
	COOKED_FRIES(Sprite.COWBOY0, "Cooked_Fries", false), GALETTE(Sprite.COWBOY0, "Galette", false),
	COOKED_GALETTE(Sprite.COWBOY0, "Cooked_Galette", false), SALAD(Sprite.COWBOY0, "Salad", false),
	SALAD_LEAF(Sprite.COWBOY0, "Salad_Leaf", false), POTATO_SALAD(Sprite.COWBOY0, "Potato_Salad", true),
	CHEESE(Sprite.COWBOY0, "Cheese", false), POUTINE(Sprite.COWBOY0, "Poutine", true),
	TOMATO(Sprite.COWBOY0, "Tomato", false), TOMATO_SLICE(Sprite.COWBOY0, "Tomato_Slice", false),
	MEAT(Sprite.COWBOY0, "Meat", false), COOKED_MEAT(Sprite.COWBOY0, "Cooked_Meat", false),
	HASHED_MEAT(Sprite.COWBOY0, "Hashed_Meat", false), COOKED_HASHED_MEAT(Sprite.COWBOY0, "Cooked_Hashed_Meat", false),
	CLASSIC_BURGER(Sprite.COWBOY0, "Classic_Burger", true), BREAD_SLICE(Sprite.COWBOY0, "Bread_Slice", false),
	VEGI_BURGER(Sprite.COWBOY0, "Vegi_Burger", true), SHEPHERDS_PIE(Sprite.COWBOY0, "Shepherds_Pie", true),
	BREAD(Sprite.COWBOY0, "Bread", false);

	Sprite sprite;
	String DisplayName;
	boolean sauceAllowed = false;

	ItemType(Sprite sprite, String DisplayName, boolean sauceAllowed) {
		this.sprite = sprite;
		this.DisplayName = DisplayName;
		this.sauceAllowed = sauceAllowed;
	}

}
