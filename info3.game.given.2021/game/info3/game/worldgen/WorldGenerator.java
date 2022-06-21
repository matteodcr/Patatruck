package info3.game.worldgen;

public class WorldGenerator {
	public static int CHUNK_SIZE_LOG = 4;
	public static int CHUNK_SIZE = 1 << CHUNK_SIZE_LOG;
	public static int CHUNK_SIZE_MASK = CHUNK_SIZE - 1;

	private final long seed;
	private final Layer<GenTile> inner;

	public WorldGenerator(long seed) {
		this.seed = seed;
		inner = new Cache<>(new SamplerLayer(new Cache<>(new ChunkLayer())));
	}

	public GenTile generate(int x, int y) {
		return inner.getAt(seed, new GridPos(x, y));
	}
}
