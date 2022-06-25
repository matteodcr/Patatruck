package info3.game.worldgen;

import java.util.Random;

import info3.game.graphics.Sprite;

public class ChunkLayer implements Layer<Chunk> {
	@Override
	public Chunk getAt(long seed, GridPos pos) {
		Random rng = Utils.seedRandom(seed, pos);
		Chunk chunk = new Chunk();

		GridPos freePos;
		while ((freePos = chunk.getRandomEmptyCell(rng)) != null) {
			RootedBuilding[] buildings = RootedBuilding.valuesWithPredicate(freePos, chunk::isCellFree);

			RootedBuilding chosenBuilding = Utils.chooseRandom(buildings, rng);

			Sprite sprite = Utils.chooseRandom(chosenBuilding.sprites, rng);
			GridPos topLeft = chosenBuilding.topLeft();

			GridPos finalFreePos = freePos;
			chosenBuilding.coveredCells(freePos.x, freePos.y).forEach(bPos -> {
				int localX = bPos.x - finalFreePos.x, localY = bPos.y - finalFreePos.y;
				int spriteU = localX - topLeft.x, spriteV = localY - topLeft.y;
				GenTile.CollisionBox collision = chosenBuilding.getCollision(localX, localY);
				chunk.setBuilding(bPos, new GenTile(sprite, spriteU, spriteV, collision));
			});
		}

		return chunk;
	}
}
