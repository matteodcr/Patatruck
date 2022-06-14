package info3.game.tests;

import info3.game.content.Ingredients;
import info3.game.content.Recipe;

public class TestRecipes {
	public static void main(String[] args) {

		System.out.println("-----Test optional items-----" + '\n');

		System.out.println("Potatoes can't have sauce so it has to return null when applying ketchup :");
		Recipe testRecipe = new Recipe(Ingredients.POTATO);
		System.out.println("" + testRecipe.addIngredient(Ingredients.KETCHUP) + '\n');

		System.out.println("Poutine has to accepts sauce :");
		testRecipe = new Recipe(Ingredients.POUTINE);
		System.out.println("" + testRecipe.addIngredient(Ingredients.KETCHUP) + '\n');

		System.out.println("Poutine can't accepts optional salad so it has to return a failed recipe :");
		System.out.println("" + testRecipe.addIngredient(Ingredients.SALAD_LEAF) + '\n');

		System.out.println("Classic burger accepts salad :");
		testRecipe = new Recipe(Ingredients.CLASSIC_BURGER);
		System.out.println("" + testRecipe.addIngredient(Ingredients.SALAD_LEAF) + '\n');

		testRecipe = testRecipe.addIngredient(Ingredients.SALAD_LEAF);
		System.out.println("But it shouldn't accept 2 of them :");
		System.out.println("" + testRecipe.addIngredient(Ingredients.SALAD_LEAF) + '\n');

		System.out.println("-----Test to cut/cook/fry ingredients-----" + '\n');

		System.out.println("Try to cook a potato :");
		testRecipe = new Recipe(Ingredients.POTATO);
		System.out.println("" + testRecipe.cook() + '\n');

		System.out.println("Try to cut a potato :");
		System.out.println("" + testRecipe.cut() + '\n');

		System.out.println("Try to fry a potato :");
		System.out.println("" + testRecipe.fry() + '\n');

		System.out.println("-----Test to recognise every assembly of receipt-----" + '\n');

		System.out.println("Try to recognize a regular recipe in an order of items :");
		testRecipe = new Recipe(Ingredients.COOKED_FRIES);
		testRecipe = testRecipe.addIngredient(Ingredients.CHEESE);
		System.out.println("" + testRecipe.addIngredient(Ingredients.CHEESE) + '\n');

		System.out.println("Try to recognize a regular recipe in another order of items :");
		testRecipe = new Recipe(Ingredients.CHEESE);
		testRecipe = testRecipe.addIngredient(Ingredients.COOKED_FRIES);
		System.out.println("" + testRecipe.addIngredient(Ingredients.CHEESE) + '\n');

		System.out.println("Try to not recognize a recipe like poutine with 2 times the same item but with a error :");
		testRecipe = new Recipe(Ingredients.COOKED_FRIES);
		testRecipe = testRecipe.addIngredient(Ingredients.COOKED_FRIES);
		System.out.println("" + testRecipe.addIngredient(Ingredients.CHEESE) + '\n');

		System.out.println("Try to not recognize a false recipe :");
		testRecipe = new Recipe(Ingredients.TOMATO_SLICE);
		System.out.println("" + testRecipe.addIngredient(Ingredients.CHEESE) + '\n');

		System.out.println("Try to combine 2 final recipes :");
		testRecipe = new Recipe(Ingredients.CLASSIC_BURGER);
		System.out.println("" + testRecipe.addIngredient(Ingredients.VEGI_BURGER) + '\n');
	}

}
