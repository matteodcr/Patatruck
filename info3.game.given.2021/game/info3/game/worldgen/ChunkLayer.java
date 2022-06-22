package info3.game.worldgen;

import java.util.Random;

import info3.game.graphics.Sprite;

public class ChunkLayer implements Layer<Chunk> {
	@Override
	public Chunk getAt(long seed, GridPos pos) {
		Random rng = new Random((seed * 31L + pos.x) * 31L + pos.y);
		Chunk chunk = new Chunk();

		GridPos freePos;
		while ((freePos = chunk.getRandomEmptyCell(rng)) != null) {
			RootedBuilding[] buildings = RootedBuilding.valuesWithPredicate(freePos, chunk::isCellFree);

			RootedBuilding chosenBuilding = Utils.chooseRandom(buildings, rng);

			Sprite sprite = Utils.chooseRandom(chosenBuilding.sprites, rng);
			GridPos topLeft = chosenBuilding.topLeft();

			GridPos finalFreePos = freePos;
			chosenBuilding.coveredCells(freePos.x, freePos.y).forEach(bPos -> {
				int spriteU = bPos.x - finalFreePos.x - topLeft.x, spriteV = bPos.y - finalFreePos.y - topLeft.y;
				GenTile.CollisionBox collision = chosenBuilding.getCollision(spriteU, spriteV);
				// TODO market
				chunk.setBuilding(bPos, new GenTile(sprite, spriteU, spriteV, collision, false));
			});
		}

		return chunk;
	}
}
