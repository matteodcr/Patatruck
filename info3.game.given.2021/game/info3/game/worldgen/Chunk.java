package info3.game.worldgen;

import static info3.game.worldgen.WorldGenerator.CHUNK_SIZE;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class Chunk {
	private final GenTile[] tiles = new GenTile[CHUNK_SIZE * CHUNK_SIZE];
	// Caches empty cells for better speed
	private Set<GridPos> emptyCells;

	public GridPos getRandomEmptyCell(Random rng) {
		if (emptyCells == null) {
			emptyCells = new HashSet<>();

			for (int i = 0; i < tiles.length; i++) {
				if (tiles[i] == null)
					emptyCells.add(new GridPos(Math.floorMod(i, CHUNK_SIZE), i / CHUNK_SIZE));
			}
		}

		if (emptyCells.isEmpty())
			return null;

		int index = rng.nextInt(emptyCells.size());
		GridPos pos = emptyCells.stream().skip(index).findFirst().orElseThrow();
		emptyCells.remove(pos);
		return pos;
	}

	public void setBuilding(GridPos pos, GenTile tile) {
		assert pos.x >= 0 && pos.x < CHUNK_SIZE;
		assert pos.y >= 0 && pos.y < CHUNK_SIZE;

		int localOffset = pos.x + pos.y * CHUNK_SIZE;
		assert localOffset < CHUNK_SIZE * CHUNK_SIZE;

		assert tiles[localOffset] == null : "attempt to overwrite chunk tile";

		tiles[localOffset] = tile;
		if (emptyCells != null)
			emptyCells.remove(pos);
	}

	public boolean isCellFree(GridPos pos) {
		return pos.isWithin(0, CHUNK_SIZE, 0, CHUNK_SIZE) && tiles[pos.x + pos.y * CHUNK_SIZE] == null;
	}

	public GenTile[] getRaw() {
		return tiles;
	}
}
