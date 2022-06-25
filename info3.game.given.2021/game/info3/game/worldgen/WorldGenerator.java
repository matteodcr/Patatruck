package info3.game.worldgen;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class WorldGenerator {
	public static int CHUNK_SIZE_LOG = 4;
	public static int CHUNK_SIZE = 1 << CHUNK_SIZE_LOG;
	public static int CHUNK_SIZE_MASK = CHUNK_SIZE - 1;

	private long seed;
	private final Layer<Boolean> markets;
	private final Layer<GenTile> inner;
	private final Set<GridPos> seenMarketTiles = new HashSet<>();

	public WorldGenerator(long seed) {
		this.seed = new Random(seed).nextLong();

		// FIXME: speed bumps never appear
		Layer<Boolean> speedBumps = new ThresholdLayer(new PointSamplerLayer(), 0.5F);
		Layer<Boolean> markets = new ThresholdLayer(new NoiseSamplerLayer(24), 0.9F);
		Layer<GenTile> buildings = new ChunkSamplerLayer(new Cache<>(new ChunkLayer()));

		this.markets = markets;
		inner = new Cache<>(new MarketConnectLayer(new Cache<>(new DecorateLayer(buildings, speedBumps, markets))));
	}

	public void changeSeed(long seed) {
		inner.clearCaches();
		seenMarketTiles.clear();
		this.seed = seed;
	}

	public GenTile generate(int x, int y) {
		return inner.getAt(seed, new GridPos(x, y));
	}

	public Stream<LocatedMarket> locateMarkets(int centerX, int centerY) {
		return SpiralStream.create(centerX, centerY).filter(pos -> markets.getAt(seed, pos))
				.filter(Predicate.not(seenMarketTiles::contains)).map(pos -> new LocatedMarket(pos.x, pos.y));
	}

	public void markMarketAsSeen(int gridX, int gridY) {
		markMarketAsSeen(new GridPos(gridX, gridY));
	}

	private void markMarketAsSeen(GridPos pos) {
		if (seenMarketTiles.add(pos)) {
			for (GridPos around : List.of(pos.top(), pos.right(), pos.bottom(), pos.left())) {
				if (markets.getAt(seed, around))
					markMarketAsSeen(around);
			}
		}
	}

	public static class LocatedMarket {
		public final int x, y;

		LocatedMarket(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
