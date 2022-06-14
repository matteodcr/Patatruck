package info3.contentNotWorking;

public interface Ingredient extends Item {
	/**
	 * Returns the cooked item if possible, null otherwise
	 * 
	 * @return
	 */
	Item cook();

	/**
	 * Returns the fried item if possible, null otherwise
	 * 
	 * @return
	 */
	Item fry();

	/**
	 * Returns the cut item if possible, null otherwise
	 * 
	 * @return
	 */
	Item cut();

}
