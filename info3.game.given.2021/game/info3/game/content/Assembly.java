package info3.game.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Assembly {
	private List<Item> items = new ArrayList<>();

	private HashMap<List<ItemType>, ItemType> ASSEMBLE_RECIPES = new HashMap<>() {
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
		}
	};

	public Assembly() {
	}

	private List<ItemType> getItemTypes() {
		return items.stream().map(item -> item.type).collect(Collectors.toList());
	}

	/*
	 * private static void removeOfType(List<Item> items, ItemType type) { for (int
	 * i = 0; i < items.size(); i++) { if (items.get(i).type == type) {
	 * items.remove(i); return; } } }
	 */

	private static boolean includes(List<ItemType> a, List<ItemType> b) {
		b = new ArrayList<>(b);
		for (ItemType i : a) {
			if (!b.remove(i))
				return false;
		}
		return true;
	}

	/*
	 * private void addItemBase(Item addition) { items.add(addition); // Assemblage
	 * 
	 * List<ItemType> currentItems = getItemTypes(); for (Entry<List<ItemType>,
	 * ItemType> entry : ASSEMBLE_RECIPES.entrySet()) { if (includes(entry.getKey(),
	 * currentItems)) { List<ItemType> recipe = new ArrayList<>(entry.getKey());
	 * items.removeIf(i -> recipe.remove(i.type)); items.add(new
	 * Item(entry.g//etValue())); } } }
	 */

	public List<Item> getItems() {
		return items;
	}

	public void removeItem(Item item) {
		this.getItems().remove(item);
	}

	public void emptyAssembly() {
		while (this.getItems().size() > 0) {
			this.getItems().remove(0);
		}
	}

	private List<ItemType> getRecipe(ItemType item) {
		List<ItemType> tmp = new ArrayList<ItemType>();
		for (Entry<List<ItemType>, ItemType> entry : ASSEMBLE_RECIPES.entrySet()) {
			if (item.equals(entry.getValue())) {
				tmp = entry.getKey();
				return tmp;
			}
		}
		return null;
	}

	private void explode() {
		List<ItemType> currentItems = this.getItemTypes();
		List<ItemType> tmp = new ArrayList<ItemType>();

		for (ItemType iter : currentItems) {
			if (iter.isFinalItem()) {
				tmp = this.getRecipe(iter);
				currentItems.remove(iter);
				for (ItemType iter2 : tmp) {
					this.getItems().add(new Item(iter2, false, false, null)); // on utilise pas addItem
				}
			}
		}
	}

	private boolean equal(List<ItemType> l) {
		ArrayList<ItemType> x = new ArrayList<ItemType>(this.getItemTypes());
		ArrayList<ItemType> y = new ArrayList<ItemType>(l);
		if (y.size() != x.size()) {
			return false;
		}
		for (ItemType tmp : l) {
			y.remove(tmp);
			if (x.contains(tmp)) {
				x.remove(tmp);
			}
		}
		if (y.size() == x.size()) {
			return true;
		}
		return false;
	}

	public void addItem(Item item) {
		items.add(item);
		this.explode(); // on divise les potentielles recettes finales présentes dans le sac du
						// cuisinier
		if (this.getItems().size() == 1) {
			return;
		}
		List<ItemType> currentItems = getItemTypes();
		for (Entry<List<ItemType>, ItemType> entry : ASSEMBLE_RECIPES.entrySet()) {
			if (this.equal(entry.getKey())) { // on va former une recette
				this.emptyAssembly();
				this.getItems().add(new Item(entry.getValue(), entry.getValue().acceptsOptionalSalad,
						entry.getValue().acceptsOptionalTomato, null));
				return;
			} else if (includes(currentItems, entry.getKey())) {// on est en bonne voie
				return;
			}
		}
		this.emptyAssembly();
		this.getItems().add(new Item(ItemType.FAILED_Item));// on est en mauvaise voie
	}

	/*
	 * items.add(new Item(tmp1));
	 * 
	 * if (this.getItems().size() != 1) { return true; } else { boolean flag =
	 * false; for (List<ItemType> tmp : ASSEMBLE_RECIPES.keySet()) { if
	 * (includes(this.getItemTypes(), tmp)) { flag = true; } } if (flag) { // un
	 * assemblage incomplet return true; } else { // mauvais assemblage
	 * this.emptyAssembly(); this.addItem(new Item(ItemType.FAILED_Item)); return
	 * false; } }
	 */

}
