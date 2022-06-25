package info3.game.worldgen;

import java.util.Random;

public class PointSamplerLayer implements Layer<Integer> {
	@Override
	public Integer getAt(long seed, GridPos pos) {
		Random rng = Utils.seedRandom(seed, pos);
		return rng.nextInt();
	}
}
