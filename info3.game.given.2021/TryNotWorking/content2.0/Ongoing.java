package info3.contentNotWorking;

import java.util.ArrayList;

public class Ongoing implements Item {

	ArrayList<Ingredient> ingredients = new ArrayList<>();

	public Ongoing(Ingredient item) {
		ingredients.add(item);
	}

	public Ongoing addIngredient(Item item) {
		return null;
	}
}
