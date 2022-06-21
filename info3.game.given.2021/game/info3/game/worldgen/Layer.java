package info3.game.worldgen;

interface Layer<E> {
	E getAt(long seed, GridPos pos);

	/**
	 * Recursively clears caches
	 */
	default void clearCaches() {
	}
}
