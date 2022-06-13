package info3.game.tests;

import java.util.List;

import info3.game.content.Item;
import info3.game.content.ItemType;
import info3.game.content.Recipe;
import info3.game.content.Sauce;

public class TestRecipes {
	public static void main(String[] args) {
		System.out.println("-----Test if we try to put a sauce on a item-----" + '\n');

		System.out.println("Potatoes can't have sauce so it has to return null when applying withSauce() :");
		Item item = new Item(ItemType.POTATO);
		item = item.withSalad().withTomato().withSauce(Sauce.KETCHUP);
		System.out.println("" + item + '\n');

		System.out.println("Poutine accepts sauce so it has to accept if with withSauce() :");
		item = new Item(ItemType.POUTINE);
		item = item.withSauce(Sauce.MAYO).withSalad();
		System.out.println("" + item + '\n');

		System.out.println("-----Test to recognise every assembly of receipt-----" + '\n');

		System.out.println("Try to recognize a regular recipe in the same order of items");
		System.out
				.println("" + Recipe.assemble(List.of(ItemType.CHEESE, ItemType.CHEESE, ItemType.COOKED_FRIES)) + '\n');

		System.out.println("Try to recognize a regular recipe in another order of items");
		System.out
				.println("" + Recipe.assemble(List.of(ItemType.COOKED_FRIES, ItemType.CHEESE, ItemType.CHEESE)) + '\n');

		System.out.println("Try to not recognize a recipe like poutine with 2 times the same item but with a error");
		System.out.println(
				"" + Recipe.assemble(List.of(ItemType.COOKED_FRIES, ItemType.COOKED_FRIES, ItemType.CHEESE)) + '\n');

		System.out.println("Try to not recognize a false recipe");
		System.out.println(
				"" + Recipe.assemble(List.of(ItemType.POTATO, ItemType.CLASSIC_BURGER, ItemType.COOKED_HASHED_MEAT))
						+ '\n');
	}

}
