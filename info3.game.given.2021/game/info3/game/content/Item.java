package info3.game.content;

import java.util.HashMap;
import java.util.Random;

public class Item {
	final ItemType type;
	Sauce sauce;

	private final static HashMap<ItemType, ItemType> COOK_RECIPES = new HashMap<>() {
		private static final long serialVersionUID = 1L;

		{
			put(ItemType.POTATO, ItemType.COOKED_POTATO);
			put(ItemType.HASHED_MEAT, ItemType.COOKED_HASHED_MEAT);
			put(ItemType.MEAT, ItemType.COOKED_MEAT);
		}
	};

	private final static HashMap<ItemType, ItemType> CHOP_RECIPES = new HashMap<>() {
		private static final long serialVersionUID = 1L;

		{
			put(ItemType.POTATO, ItemType.FRIES);
			put(ItemType.FRIES, ItemType.GALETTE);
			put(ItemType.COOKED_POTATO, ItemType.MASHED_POTATO);
			put(ItemType.SALAD, ItemType.SALAD_LEAF);
			put(ItemType.TOMATO, ItemType.TOMATO_SLICE);
			put(ItemType.MEAT, ItemType.HASHED_MEAT);
			put(ItemType.BREAD, ItemType.BREAD_SLICE);
		}
	};

	private final static HashMap<ItemType, ItemType> FRY_RECIPES = new HashMap<>() {
		private static final long serialVersionUID = 1L;

		{
			put(ItemType.FRIES, ItemType.COOKED_FRIES);
			put(ItemType.GALETTE, ItemType.COOKED_GALETTE);
		}
	};

	public Item(ItemType type, Sauce sauce) {
		this.type = type;
		this.sauce = sauce;
	}

	public Item(ItemType type) {
		this(type, null);
	}

	public ItemType getType() {
		return type;
	}

	public Sauce getSauce() {
		return sauce;
	}

	public boolean hasOptionalSalad() {
		return this.getType() == ItemType.CLASSIC_BURGER_SALAD || this.getType() == ItemType.CLASSIC_BURGER_SALAD_TOMATO
				|| this.getType() == ItemType.VEGI_BURGER_SALAD || this.getType() == ItemType.VEGI_BURGER_SALAD_TOMATO;
	}

	public boolean hasOptionalTomato() {
		return this.getType() == ItemType.CLASSIC_BURGER_TOMATO
				|| this.getType() == ItemType.CLASSIC_BURGER_SALAD_TOMATO
				|| this.getType() == ItemType.VEGI_BURGER_TOMATO || this.getType() == ItemType.VEGI_BURGER_SALAD_TOMATO;
	}

	public void setSauce(Sauce sauce) {
		if (this.getType().finalItem) {
			if (this.sauce == Sauce.KETCHUP && sauce == Sauce.MAYO || this.sauce == Sauce.MAYO && sauce == Sauce.KETCHUP
					|| this.sauce == Sauce.KETCHUP_MAYO) {
				this.sauce = Sauce.KETCHUP_MAYO;
			} else {
				this.sauce = sauce;
			}
		}
	}

	/**
	 * Returns the cooked element if possible, null otherwise
	 */
	public Item cook() {
		ItemType result = COOK_RECIPES.get(this.type);
		if (result == null)
			return null;
		return new Item(result);
	}

	/**
	 * Returns the chopped element if possible, null otherwise
	 */
	public Item cut() {
		ItemType result = CHOP_RECIPES.get(this.type);
		if (result == null)
			return null;
		return new Item(result);
	}

	/**
	 * Returns the fried element if possible, null otherwise
	 */
	public Item fry() {
		ItemType result = FRY_RECIPES.get(this.type);
		if (result == null)
			return null;
		return new Item(result);
	}

	@Override
	public String toString() {
		String result = "";
		result = result + this.type.toString();
		if (sauce != null) {
			result = result + "+ " + this.sauce;
		}
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sauce == null) ? 0 : sauce.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (sauce != other.sauce)
			return false;
		return type == other.type;
	}

	private void getRandomSauce() {
		Random rand = new Random();
		int i = rand.nextInt(4);
		if (i == 0)
			this.setSauce(Sauce.KETCHUP);
		else if (i == 1)
			this.setSauce(Sauce.KETCHUP_MAYO);
		else if (i == 2)
			this.setSauce(Sauce.MAYO);
	}

	private static int getNbOfFinalItems() {
		int sum = 0;
		for (ItemType item : ItemType.values()) {
			if (item.isFinalItem())
				sum++;
		}
		return sum;
	}

	private static ItemType getRandomItemType() {
		Random rand = new Random();
		int i = rand.nextInt(getNbOfFinalItems());
		int j = 0;
		for (ItemType item : ItemType.values()) {
			if (item.isFinalItem()) {
				if (i == j) {
					if (item == ItemType.CLASSIC_BURGER || item == ItemType.CLASSIC_BURGER_SALAD
							|| item == ItemType.CLASSIC_BURGER_TOMATO || item == ItemType.CLASSIC_BURGER_SALAD_TOMATO
							|| item == ItemType.VEGI_BURGER || item == ItemType.VEGI_BURGER_SALAD
							|| item == ItemType.VEGI_BURGER_TOMATO || item == ItemType.VEGI_BURGER_SALAD_TOMATO) {
						Random rand2 = new Random();
						int x = rand2.nextInt(2);
						if (x == 0) {
							return item;
						} else {
							Random rand3 = new Random();
							int y = rand3.nextInt(4);
							if (y == 0) {
								return ItemType.COOKED_FRIES;
							} else if (y == 1) {
								return ItemType.SHEPHERDS_PIE;
							} else if (y == 2) {
								return ItemType.POTATO_SALAD;
							} else {
								return ItemType.POUTINE;
							}
						}
					} else {
						return item;
					}
				}
				j++;
			}
		}
		throw new IllegalStateException("no recipe found");

	}

	public static Item getRandomItem() {
		Item res = new Item(getRandomItemType());
		res.getRandomSauce();
		return res;
	}

}
