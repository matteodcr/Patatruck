package info3.game.worldgen;

import static info3.game.worldgen.WorldGenerator.CHUNK_SIZE;
import static info3.game.worldgen.WorldGenerator.CHUNK_SIZE_LOG;
import static info3.game.worldgen.WorldGenerator.CHUNK_SIZE_MASK;

public class SamplerLayer implements Layer<GenTile> {
	private final Layer<Chunk> chunkLayer;

	public SamplerLayer(Layer<Chunk> chunkLayer) {
		this.chunkLayer = chunkLayer;
	}

	@Override
	public GenTile getAt(long seed, GridPos pos) {
		int chunkPosX = pos.x >> CHUNK_SIZE_LOG;
		int chunkPosY = pos.y >> CHUNK_SIZE_LOG;
		int localPosX = pos.x & CHUNK_SIZE_MASK;
		int localPosY = pos.y & CHUNK_SIZE_MASK;

		Chunk chunk = chunkLayer.getAt(seed, new GridPos(chunkPosX, chunkPosY));
		return chunk.getRaw()[localPosX + localPosY * CHUNK_SIZE];
	}
}
