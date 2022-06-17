package info3.contentNotWorking;

import java.util.ArrayList;

public interface Recipe extends Item {
	boolean acceptsSauce = false;
	Sauce sauce = null;
	boolean acceptsSalad = false, hasSalad = false;
	boolean acceptsTomato = false, hasTomato = false;
	ArrayList<Ingredient> ingredientsList = new ArrayList<>();

	Item addSauce(Sauce sauce);

	Item addSalad();

	Item addTomato();
}
