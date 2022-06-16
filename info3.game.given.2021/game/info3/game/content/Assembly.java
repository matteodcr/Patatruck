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
			put(List.of(ItemType.COOKED_GALETTE, ItemType.CHEESE, ItemType.BREAD_SLICE), ItemType.VEGI_BURGER);
		}
	};

	private List<ItemType> getItemTypes() {
		return items.stream().map(item -> item.type).collect(Collectors.toList());
	}

	private static void removeOfType(List<Item> items, ItemType type) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).type == type) {
				items.remove(i);
				return;
			}
		}
	}

	public Assembly() {
	}

	private static boolean includes(List<ItemType> a, List<ItemType> b) {
		b = new ArrayList<>(b);
		for (ItemType i : a) {
			if (!b.remove(i))
				return false;
		}
		return true;
	}

	private void addItemBase(Item addition) {
		items.add(addition);
		// Assemblage

		List<ItemType> currentItems = getItemTypes();
		for (Entry<List<ItemType>, ItemType> entry : ASSEMBLE_RECIPES.entrySet()) {
			if (includes(entry.getKey(), currentItems)) {
				List<ItemType> recipe = new ArrayList<>(entry.getKey());
				items.removeIf(i -> recipe.remove(i.type));
				items.add(new Item(entry.getValue()));
			}
		}

		// Salade + tomate

		List<Item> newItems = new ArrayList<>(items);
		currentItems = getItemTypes();
		for (Item item : items) {
			if (item.type.acceptsOptionalSalad && currentItems.remove(ItemType.SALAD_LEAF)) {
				removeOfType(newItems, ItemType.SALAD_LEAF);
				newItems.remove(item);
				newItems.add(item.withSalad());
			}

			if (item.type.acceptsOptionalTomato && currentItems.remove(ItemType.TOMATO_SLICE)) {
				removeOfType(newItems, ItemType.TOMATO_SLICE);
				newItems.remove(item);
				newItems.add(item.withTomato());
			}
		}

		items = newItems;
	}

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

	public boolean addItem(Item item) {
		this.addItemBase(item);
		if (this.getItems().size() == 1) { // un item ou une recette assemblee
			return true;
		} else {
			boolean flag = false;
			for (List<ItemType> tmp : ASSEMBLE_RECIPES.keySet()) {
				if (includes(this.getItemTypes(), tmp)) {
					flag = true;
				}
			}
			if (flag) { // un assemblage incomplet
				return true;
			} else { // mauvais assemblage
				this.emptyAssembly();
				this.addItem(new Item(ItemType.FAILED_Item));
				return false;
			}
		}
	}
}
