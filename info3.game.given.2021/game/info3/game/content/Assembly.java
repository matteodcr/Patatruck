package info3.game.content;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Assembly {
	private final List<Item> items = new ArrayList<>();

	private static final Map<List<ItemType>, ItemType> ASSEMBLE_RECIPES = new LinkedHashMap<>() {
		private static final long serialVersionUID = 1L;

		{
			put(List.of(ItemType.SALAD_LEAF, ItemType.COOKED_POTATO), ItemType.POTATO_SALAD);
			put(List.of(ItemType.CHEESE, ItemType.CHEESE, ItemType.COOKED_FRIES), ItemType.POUTINE);
			put(List.of(ItemType.MASHED_POTATO, ItemType.COOKED_HASHED_MEAT), ItemType.SHEPHERDS_PIE);

			put(List.of(ItemType.COOKED_MEAT, ItemType.CHEESE, ItemType.BREAD_SLICE), ItemType.CLASSIC_BURGER);
			put(List.of(ItemType.COOKED_MEAT, ItemType.CHEESE, ItemType.BREAD_SLICE, ItemType.SALAD_LEAF),
					ItemType.CLASSIC_BURGER_SALAD);
			put(List.of(ItemType.COOKED_MEAT, ItemType.CHEESE, ItemType.BREAD_SLICE, ItemType.TOMATO_SLICE),
					ItemType.CLASSIC_BURGER_TOMATO);
			put(List.of(ItemType.COOKED_MEAT, ItemType.CHEESE, ItemType.BREAD_SLICE, ItemType.SALAD_LEAF,
					ItemType.TOMATO_SLICE), ItemType.CLASSIC_BURGER_SALAD_TOMATO);

			put(List.of(ItemType.COOKED_GALETTE, ItemType.CHEESE, ItemType.BREAD_SLICE), ItemType.VEGI_BURGER);
			put(List.of(ItemType.COOKED_GALETTE, ItemType.CHEESE, ItemType.BREAD_SLICE, ItemType.SALAD_LEAF),
					ItemType.VEGI_BURGER_SALAD);
			put(List.of(ItemType.COOKED_GALETTE, ItemType.CHEESE, ItemType.BREAD_SLICE, ItemType.TOMATO_SLICE),
					ItemType.VEGI_BURGER_TOMATO);

			put(List.of(ItemType.COOKED_GALETTE, ItemType.CHEESE, ItemType.BREAD_SLICE, ItemType.SALAD_LEAF,
					ItemType.TOMATO_SLICE), ItemType.VEGI_BURGER_SALAD_TOMATO);
			put(List.of(ItemType.COOKED_FRIES), ItemType.COOKED_FRIES);
		}
	};

	private List<ItemType> getItemTypes() {
		return items.stream().map(item -> {
			if (item != null)
				return item.type;
			else
				return null;
		}).collect(Collectors.toList());
	}

	private static boolean includes(List<ItemType> a, List<ItemType> b) {
		b = new ArrayList<>(b);
		for (ItemType i : a) {
			if (!b.remove(i))
				return false;
		}
		return true;
	}

	public List<Item> getItems() {
		return items;
	}

	public void removeItem(Item item) {
		this.getItems().remove(item);
	}

	public void emptyAssembly() {
		this.getItems().clear();
	}

	private List<ItemType> getRecipe(ItemType item) {
		List<ItemType> tmp;
		for (Entry<List<ItemType>, ItemType> entry : ASSEMBLE_RECIPES.entrySet()) {
			if (item.equals(entry.getValue())) {
				tmp = entry.getKey();
				return tmp;
			}
		}
		return null;
	}

	/**
	 * getRecipe
	 * 
	 * @implSpec : Decompose a recipe into simple ingredients, in order to compose
	 *           more complex recipe
	 */
	private Sauce explode() {
		ArrayList<Item> currentItems = new ArrayList<>(this.getItems());
		List<ItemType> tmp;
		Sauce sauce = null;

		for (Item iter : currentItems) {
			if (iter != null && iter.getType().isFinalItem()) {
				sauce = iter.getSauce();
				tmp = this.getRecipe(iter.getType());
				this.removeItem(iter);
				for (ItemType iter2 : Objects.requireNonNull(tmp)) {
					this.getItems().add(new Item(iter2, null)); // on utilise pas addItem
				}
			}
		}
		return sauce;
	}

	private boolean equal(List<ItemType> l) {
		ArrayList<ItemType> x = new ArrayList<>(this.getItemTypes());
		ArrayList<ItemType> y = new ArrayList<>(l);
		if (y.size() != x.size()) {
			return false;
		}
		for (ItemType tmp : l) {
			y.remove(tmp);
			x.remove(tmp);
		}
		return y.size() == x.size();
	}

	public void addItem(Item item) {
		items.add(item);
		Sauce sauce = this.explode(); // on divise les potentielles recettes finales présentes dans le sac du
		// cuisinier
		if (this.getItems().size() == 1) {
			for (Item iter : items)
				if (iter != null)
					iter.setSauce(sauce);
			return;
		}
		List<ItemType> currentItems = getItemTypes();
		for (Entry<List<ItemType>, ItemType> entry : ASSEMBLE_RECIPES.entrySet()) {
			if (this.equal(entry.getKey())) { // on va former une recette
				this.emptyAssembly();
				this.getItems().add(new Item(entry.getValue(), sauce));
				return;
			} else if (includes(currentItems, entry.getKey())) {// on est en bonne voie
				return;
			}
		}
		this.emptyAssembly();
		this.getItems().add(new Item(ItemType.FAILED_Item));// on est en mauvaise voie
	}

	public void addAssembly(Assembly a) {
		for (Item item : a.getItems()) {
			this.addItem(item);
		}
	}

}
