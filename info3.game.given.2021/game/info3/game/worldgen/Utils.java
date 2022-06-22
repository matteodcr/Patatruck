package info3.game.worldgen;

import java.util.Random;

class Utils {
	public static <T> T chooseRandom(T[] array, Random rng) {
		assert array.length > 0 : "Can't choose random element in empty array";
		return array[rng.nextInt(array.length)];
	}

	private Utils() {
	}
}
