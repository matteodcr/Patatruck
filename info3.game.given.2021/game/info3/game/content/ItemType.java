package info3.game.content;

import java.util.Random;

import info3.game.graphics.Sprite;

public enum ItemType {
	POTATO(Sprite.POTATO, "patate", false), COOKED_POTATO(Sprite.POTATOCOOKED, "patate cuite", false),
	MASHED_POTATO(Sprite.MASHEDPOTATO, "puree", false), FRIES(Sprite.FRIES, "frites", false),
	COOKED_FRIES(Sprite.COOKEDFRIES, "frites cuites", false), GALETTE(Sprite.GALETTE, "galette", false),
	COOKED_GALETTE(Sprite.GALETTECOOKED, "galette cuite", false), SALAD(Sprite.SALADE, "salade", false),
	SALAD_LEAF(Sprite.SALADELEAF, "feuille de salade", false),
	POTATO_SALAD(Sprite.POTATOSALAD, "salade de patate", true), CHEESE(Sprite.CHEESE, "fromage", false),
	POUTINE(Sprite.POUTINE, "poutine", true), TOMATO(Sprite.TOMATO, "tomate", false),
	TOMATO_SLICE(Sprite.TOMATOSLICE, "rondelle de tomate", false), MEAT(Sprite.MEAT, "viande", false),
	COOKED_MEAT(Sprite.COOKEDMEAT, "viande cuite", false), HASHED_MEAT(Sprite.HASHEDMEAT, "viande hachee", false),
	COOKED_HASHED_MEAT(Sprite.COOKEDHASHMEAT, "viande hachee cuite", false),
	CLASSIC_BURGER(Sprite.CLASSICBURGER, "burger classique", true),
	CLASSIC_BURGER_SALAD(Sprite.CLASSICBURGER, "burger classique", true),
	CLASSIC_BURGER_TOMATO(Sprite.CLASSICBURGER, "burger classique", true),
	CLASSIC_BURGER_SALAD_TOMATO(Sprite.CLASSICBURGER, "burger classique", true),
	BREAD_SLICE(Sprite.BREADSLICE, "tranche de pain", false), VEGI_BURGER(Sprite.VEGIBURGER, "burger vegetarien", true),
	VEGI_BURGER_SALAD(Sprite.VEGIBURGER, "burger classique", true),
	VEGI_BURGER_TOMATO(Sprite.VEGIBURGER, "burger classique", true),
	VEGI_BURGER_SALAD_TOMATO(Sprite.VEGIBURGER, "burger classique", true),
	SHEPHERDS_PIE(Sprite.HACHI, "hachis parmentier", true), BREAD(Sprite.BREAD, "pain", false),
	KETCHUP(Sprite.KETCHUP, "ketchup", false), MAYO(Sprite.MAYONNAISE, "ketchup", false),
	KETCHUP_MAYO(Sprite.KETCHUP, "ketchup", false), FAILED_Item(Sprite.FAILEDITEM, "recette ratee", false);

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

	public static ItemType getRandomRecipe() {
		Random rand = new Random();
		int i = rand.nextInt(getNbOfFinalItems());
		int j = 0;
		for (ItemType item : ItemType.values()) {
			if (item.isFinalItem()) {
				if (i == j)
					return item;
				j++;
			}
		}
		throw new IllegalStateException("no recipe found");
	}

	private static int getNbOfFinalItems() {
		int sum = 0;
		for (ItemType item : ItemType.values()) {
			if (item.isFinalItem())
				sum++;
		}
		return sum;
	}
}
