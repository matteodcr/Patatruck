package info3.game.worldgen;

import java.util.Random;

public class WorldGenerator {
	public static int CHUNK_SIZE_LOG = 4;
	public static int CHUNK_SIZE = 1 << CHUNK_SIZE_LOG;
	public static int CHUNK_SIZE_MASK = CHUNK_SIZE - 1;

	private final long seed;
	private final Layer<GenTile> inner;

	public WorldGenerator(long seed) {
		this.seed = new Random(seed).nextLong();

		// FIXME: speed bumps never appear
		Layer<Boolean> speedBumps = new ThresholdLayer(new PointSamplerLayer(), 0.5F);
		Layer<Boolean> markets = new ThresholdLayer(new NoiseSamplerLayer(24), 0.9F);
		Layer<GenTile> buildings = new ChunkSamplerLayer(new Cache<>(new ChunkLayer()));

		inner = new Cache<>(new MarketConnectLayer(new Cache<>(new DecorateLayer(buildings, speedBumps, markets))));
	}

	public GenTile generate(int x, int y) {
		return inner.getAt(seed, new GridPos(x, y));
	}
}
