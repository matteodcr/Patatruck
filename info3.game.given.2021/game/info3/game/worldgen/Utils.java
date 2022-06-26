package info3.game.worldgen;

import java.util.Random;

class Utils {
	public static Random seedRandom(long seed, GridPos pos) {
		Random rng = new Random((seed * 31L + pos.x) * 31L + pos.y);
		rng.nextLong();
		return rng;
	}

	public static <T> T chooseRandom(T[] array, Random rng) {
		assert array.length > 0 : "Can't choose random element in empty array";
		return array[rng.nextInt(array.length)];
	}

	private Utils() {
	}
}
