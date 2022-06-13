package info3.game.content;

public class Item {
	final ItemType type;
	final Sauce sauce;
	final boolean salad;
	final boolean tomato;

	public Item(ItemType type) {
		this.type = type;
		this.sauce = null;
		this.salad = false;
		this.tomato = false;
	}

	/**
	 * Only use for debug or as private
	 * 
	 * @param type
	 * @param sauce
	 * @param salad
	 * @param tomato
	 */
	public Item(ItemType type, Sauce sauce, boolean salad, boolean tomato) {
		this.type = type;
		this.sauce = sauce;
		this.salad = salad;
		this.tomato = tomato;
	}

	/**
	 * Returns a new item with the sauce if it is allowed otherwise return null
	 * 
	 * @param sauce
	 * @return a new item or null
	 */
	public Item withSauce(Sauce sauce) {
		if (this.sauce == null && this.type.sauceAllowed) {
			return new Item(type, sauce, salad, tomato);
		} else if (this.type.sauceAllowed && this.sauce == Sauce.KETCHUP && sauce == Sauce.MAYO) {
			return new Item(type, Sauce.KETCHUP_MAYO, salad, tomato);
		} else if (this.type.sauceAllowed && this.sauce == Sauce.MAYO && sauce == Sauce.KETCHUP) {
			return new Item(type, Sauce.KETCHUP_MAYO, salad, tomato);
		}
		return null;
	}

	public Item withSalad() {
		return new Item(type, sauce, true, tomato);
	}

	public Item withTomato() {
		return new Item(type, sauce, salad, true);
	}

	@Override
	public String toString() {
		return new String("" + this.type + (this.type.sauceAllowed ? " " + this.sauce : " (sauce not allowed)")
				+ (this.tomato ? " with tomato" : " without tomato") + (this.salad ? " with salad" : " without salad"));
	}

}
