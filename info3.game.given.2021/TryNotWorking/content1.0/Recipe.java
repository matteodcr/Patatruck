package info3.game.contentAncien;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Recipe {

	private static HashMap<ItemType, ItemType> cookRecipe = new HashMap<>() {
		private static final long serialVersionUID = 1L;

		{
			put(ItemType.POTATO, ItemType.COOKED_POTATO);
			put(ItemType.HASHED_MEAT, ItemType.COOKED_HASHED_MEAT);
			put(ItemType.MEAT, ItemType.COOKED_MEAT);
		}
	};

	private static HashMap<ItemType, ItemType> cutRecipe = new HashMap<>() {
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

	private static HashMap<ItemType, ItemType> fryRecipe = new HashMap<>() {
		private static final long serialVersionUID = 1L;

		{
			put(ItemType.FRIES, ItemType.COOKED_FRIES);
			put(ItemType.GALETTE, ItemType.COOKED_GALETTE);
		}
	};

	private static HashMap<List<ItemType>, ItemType> assembleRecipe = new HashMap<>() {
		private static final long serialVersionUID = 1L;

		{
			put(List.of(ItemType.SALAD_LEAF, ItemType.COOKED_POTATO), ItemType.POTATO_SALAD);
			put(List.of(ItemType.CHEESE, ItemType.CHEESE, ItemType.COOKED_FRIES), ItemType.POUTINE);
			put(List.of(ItemType.MASHED_POTATO, ItemType.COOKED_HASHED_MEAT), ItemType.SHEPHERDS_PIE);
			put(List.of(ItemType.SALAD_LEAF, ItemType.COOKED_GALETTE, ItemType.TOMATO_SLICE, ItemType.CHEESE,
					ItemType.BREAD_SLICE), ItemType.POUTINE);
			put(List.of(ItemType.SALAD_LEAF, ItemType.COOKED_MEAT, ItemType.TOMATO_SLICE, ItemType.CHEESE,
					ItemType.BREAD_SLICE), ItemType.CLASSIC_BURGER);
		}
	};

	public ItemType cook(ItemType type) {
		return cookRecipe.get(type);
	}

	public ItemType cut(ItemType type) {
		return cutRecipe.get(type);
	}

	public ItemType fry(ItemType type) {
		return fryRecipe.get(type);
	}

	public ItemType assemble(List<ItemType> list) {
		ArrayList<ItemType> SortedList = new ArrayList<>(list);
		ArrayList<ItemType> SortedRecipe;
		Collections.sort(SortedList);
		Set<List<ItemType>> keys = assembleRecipe.keySet();
		for (List<ItemType> recipe : keys) {
			if (recipe.size() == list.size()) {
				SortedRecipe = new ArrayList<>(recipe);
				Collections.sort(SortedRecipe);
				if (SortedRecipe.equals(SortedList)) {
					return assembleRecipe.get(recipe);
				}
			}

		}
		return null;
	}

}
