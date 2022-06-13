package info3.game.content;

public class TestRecette {

	public static void main(String[] args) {
		Item item = new Item(ItemType.POTATO);
		item = item.withSalad().withTomato().withSauce(Sauce.KETCHUP);
		System.out.println("" + item);

		item = new Item(ItemType.POUTINE);
		item = item.withSauce(Sauce.MAYO);
		System.out.println("" + item);

	}

}
