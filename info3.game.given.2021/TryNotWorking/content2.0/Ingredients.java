package info3.contentNotWorking;

public enum Ingredients implements Ingredient {
	POTATO(null, null, FRIES), CHEESE, FRIES, COOKED_FRIES, POUTINE;

	Ingredients[] travail = new Ingredients[3];

	Ingredients(Ingredients onCook, Ingredients onFry, Ingredients onCut) {
		travail[0] = onCook;
		travail[1] = onFry;
		travail[2] = onCut;
	}

	@Override
	public Item cook() {
		return travail[0];
	}

	@Override
	public Item fry() {
		return travail[1];
	}

	@Override
	public Item cut() {
		return travail[2];
	}

}
