package info3.game.content;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Recipe extends Item {

	ArrayList<Ingredients> ingredients = new ArrayList<>();
	boolean hasOptionalSalad = false, hasOptionalTomato = false;
	Ingredients sauce;

	private HashMap<Ingredients, Ingredients> cookRecipe = new HashMap<>() {
		private static final long serialVersionUID = 1L;

		{
			put(Ingredients.POTATO, Ingredients.COOKED_POTATO);
			put(Ingredients.HASHED_MEAT, Ingredients.COOKED_HASHED_MEAT);
			put(Ingredients.MEAT, Ingredients.COOKED_MEAT);
		}
	};

	private HashMap<Ingredients, Ingredients> cutRecipe = new HashMap<>() {
		private static final long serialVersionUID = 1L;

		{
			put(Ingredients.POTATO, Ingredients.FRIES);
			put(Ingredients.FRIES, Ingredients.GALETTE);
			put(Ingredients.COOKED_POTATO, Ingredients.MASHED_POTATO);
			put(Ingredients.SALAD, Ingredients.SALAD_LEAF);
			put(Ingredients.TOMATO, Ingredients.TOMATO_SLICE);
			put(Ingredients.MEAT, Ingredients.HASHED_MEAT);
			put(Ingredients.BREAD, Ingredients.BREAD_SLICE);
		}
	};

	private HashMap<Ingredients, Ingredients> fryRecipe = new HashMap<>() {
		private static final long serialVersionUID = 1L;

		{
			put(Ingredients.FRIES, Ingredients.COOKED_FRIES);
			put(Ingredients.GALETTE, Ingredients.COOKED_GALETTE);
		}
	};

	private HashMap<List<Ingredients>, Ingredients> assembleRecipe = new HashMap<>() {
		private static final long serialVersionUID = 1L;

		{
			put(List.of(Ingredients.SALAD_LEAF, Ingredients.COOKED_POTATO), Ingredients.POTATO_SALAD);
			put(List.of(Ingredients.CHEESE, Ingredients.CHEESE, Ingredients.COOKED_FRIES), Ingredients.POUTINE);
			put(List.of(Ingredients.MASHED_POTATO, Ingredients.COOKED_HASHED_MEAT), Ingredients.SHEPHERDS_PIE);
			put(List.of(Ingredients.SALAD_LEAF, Ingredients.COOKED_GALETTE, Ingredients.TOMATO_SLICE,
					Ingredients.CHEESE, Ingredients.BREAD_SLICE), Ingredients.POUTINE);
			put(List.of(Ingredients.COOKED_MEAT, Ingredients.CHEESE, Ingredients.BREAD_SLICE),
					Ingredients.CLASSIC_BURGER);
			put(List.of(Ingredients.COOKED_GALETTE, Ingredients.CHEESE, Ingredients.BREAD_SLICE),
					Ingredients.VEGI_BURGER);
		}
	};

	// TODO A completer avec toutes les recettes intermediaires autorisees
	private List<List<Ingredients>> allowedOngoingRecipes = List.of(
			new ArrayList<Ingredients>(List.of(Ingredients.POTATO_SALAD)),
			new ArrayList<Ingredients>(List.of(Ingredients.POUTINE)),
			new ArrayList<Ingredients>(List.of(Ingredients.CLASSIC_BURGER)),
			new ArrayList<Ingredients>(List.of(Ingredients.VEGI_BURGER)),
			new ArrayList<Ingredients>(List.of(Ingredients.SHEPHERDS_PIE)),
			new ArrayList<Ingredients>(List.of(Ingredients.BREAD)),
			new ArrayList<Ingredients>(List.of(Ingredients.POTATO)),
			new ArrayList<Ingredients>(List.of(Ingredients.MEAT)),
			new ArrayList<Ingredients>(List.of(Ingredients.SALAD)),
			new ArrayList<Ingredients>(List.of(Ingredients.CHEESE)),
			new ArrayList<Ingredients>(List.of(Ingredients.TOMATO)),
			new ArrayList<Ingredients>(List.of(Ingredients.COOKED_FRIES)),
			new ArrayList<Ingredients>(List.of(Ingredients.TOMATO_SLICE)),
			new ArrayList<Ingredients>(List.of(Ingredients.SALAD_LEAF)));

	public Recipe(Ingredients ingredient) {
		super(ingredient.sprite, ingredient.DisplayName);
		ingredients.add(ingredient);
	}

	/**
	 * Adds ingredients to the current one(s)
	 * 
	 * @param ingredient
	 * @return failedRecipe if not possible, the recipe otherwise
	 */
	public Recipe addIngredient(Recipe ingredientList) {
		Recipe result = null;
		for (Ingredients ingredient : ingredientList.ingredients) {
			this.ingredients.add(ingredient);
			result = fuseRecipe();
		}
		return result;
	}

	/**
	 * Adds ingredient to the current one(s). Should only be used for clearer tests.
	 * Usually use addIngredient(Recipe ingredientList).
	 * 
	 * @param ingredient
	 * @return failedRecipe if not possible, the recipe otherwise
	 */
	public Recipe addIngredient(Ingredients ingredient) {
		this.ingredients.add(ingredient);
		return fuseRecipe();
	}

	/**
	 * Returns the finished recipe if it has all ingredients, himself with optional
	 * salad and/or tomato if possible, null otherwise
	 * 
	 * @return
	 */
	private Recipe fuseRecipe() {
		// nothing to fuse
		if (this.ingredients.size() <= 1)
			return null;

		// check no sauce if no need to sauce, same for salad and tomato
		boolean acceptsSauce = false, acceptSalad = false, acceptTomato = false;
		for (Ingredients ingredient : this.ingredients) {
			if (ingredient.finalRecipe) {
				acceptsSauce = true;
				if (ingredient.acceptsOptionalSalad && !this.hasOptionalSalad)
					acceptSalad = true;
				if (ingredient.acceptsOptionalTomato && !this.hasOptionalTomato)
					acceptTomato = true;
			}
		}
		if (!acceptsSauce
				&& (this.ingredients.contains(Ingredients.KETCHUP) || this.ingredients.contains(Ingredients.MAYO)))
			return null;
		if (!acceptSalad && this.ingredients.contains(Ingredients.SALAD_LEAF))
			return allowedOngoing(new Recipe(Ingredients.FAILED_RECIPE));
		if (!acceptTomato && this.ingredients.contains(Ingredients.TOMATO))
			return allowedOngoing(new Recipe(Ingredients.FAILED_RECIPE));

		// for adding optional salad, tomato or sauce
		for (Ingredients ingredient : this.ingredients) {
			if (ingredient.acceptsOptionalSalad && !this.hasOptionalSalad
					&& this.ingredients.contains(Ingredients.SALAD_LEAF)) {
				this.hasOptionalSalad = true;
				this.ingredients.remove(Ingredients.SALAD_LEAF);
				return allowedOngoing(this);
			}

			if (ingredient.acceptsOptionalTomato && !this.hasOptionalTomato
					&& this.ingredients.contains(Ingredients.TOMATO_SLICE)) {
				this.hasOptionalTomato = true;
				this.ingredients.remove(Ingredients.TOMATO_SLICE);
				return allowedOngoing(this);
			}
			if (ingredient.finalRecipe && this.ingredients.contains(Ingredients.KETCHUP)) {
				if (this.sauce == Ingredients.MAYO) {
					this.ingredients.remove(Ingredients.KETCHUP);
					this.sauce = Ingredients.KETCHUP_MAYO;
					return allowedOngoing(this);
				} else if (this.sauce == null) {
					this.ingredients.remove(Ingredients.KETCHUP);
					this.sauce = Ingredients.KETCHUP;
					return allowedOngoing(this);
				} else {
					return null;
				}
			}
			if (ingredient.finalRecipe && this.ingredients.contains(Ingredients.MAYO)) {
				if (this.sauce == Ingredients.KETCHUP) {
					this.ingredients.remove(Ingredients.MAYO);
					this.sauce = Ingredients.KETCHUP_MAYO;
					return allowedOngoing(this);
				} else if (this.sauce == null) {
					this.ingredients.remove(Ingredients.MAYO);
					this.sauce = Ingredients.MAYO;
					return allowedOngoing(this);
				} else {
					return null;
				}
			}
			// failed recipe
			if (ingredient.finalRecipe && this.ingredients.size() > 1) {
				return allowedOngoing(new Recipe(Ingredients.FAILED_RECIPE));
			}
		}
		ArrayList<Ingredients> SortedRecipe;
		Collections.sort(this.ingredients);
		for (List<Ingredients> recipe : assembleRecipe.keySet()) {
			if (recipe.size() == this.ingredients.size()) {
				SortedRecipe = new ArrayList<>(recipe);
				Collections.sort(SortedRecipe);
				if (SortedRecipe.equals(this.ingredients)) {
					return allowedOngoing(new Recipe(assembleRecipe.get(recipe)));
				}
			}
		}
		return allowedOngoing(this);
	}

	private Recipe allowedOngoing(Recipe recipe) {
		if (recipe == null)
			return null;
		Collections.sort(recipe.ingredients);
		boolean allowed = false;
		ArrayList<Ingredients> sortedPossibility;
		for (List<Ingredients> possibility : this.allowedOngoingRecipes) {
			sortedPossibility = new ArrayList<>(possibility);
			Collections.sort(sortedPossibility);
			if (sortedPossibility.equals(recipe.ingredients))
				allowed = true;
		}
		if (allowed) {
			return recipe;
		} else {
			return new Recipe(Ingredients.FAILED_RECIPE);
		}
	}

	/**
	 * Returns the cooked element if possible, null otherwise
	 * 
	 * @param ingredient
	 * @return
	 */
	public Recipe cook() {
		if (this.ingredients.size() != 1)
			return null;
		Ingredients result = cookRecipe.get(this.ingredients.get(0));
		if (result == null)
			return null;
		return new Recipe(result);
	}

	/**
	 * Returns the cut element if possible, null otherwise
	 * 
	 * @param ingredient
	 * @return
	 */
	public Recipe cut() {
		if (this.ingredients.size() != 1)
			return null;
		Ingredients result = cutRecipe.get(this.ingredients.get(0));
		if (result == null)
			return null;
		return new Recipe(result);
	}

	/**
	 * Returns the fried element if possible, null otherwise
	 * 
	 * @param ingredient
	 * @return
	 */
	public Recipe fry() {
		if (this.ingredients.size() != 1)
			return null;
		Ingredients result = fryRecipe.get(this.ingredients.get(0));
		if (result == null)
			return null;
		return new Recipe(result);
	}

	@Override
	public String toString() {
		String result = new String();
		result = result + this.ingredients.toString();

		if (hasOptionalSalad) {
			result = result + "+ Optional Salad ";
		}
		if (hasOptionalTomato) {
			result = result + "+ Optional Tomato ";
		}
		if (sauce != null) {
			result = result + "+ " + this.sauce;
		}
		return result;
	}

}
