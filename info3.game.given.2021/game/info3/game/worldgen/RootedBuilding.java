package info3.game.worldgen;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

import info3.game.graphics.Sprite;

enum RootedBuilding {
	SQUARE_1(new GridPos[] { new GridPos(0, 0) }, Sprite.CITY_SQUARES_1),
	SQUARE_2_BR(new GridPos[] { new GridPos(0, 0), new GridPos(0, 1), new GridPos(1, 0), new GridPos(1, 1) },
			Sprite.CITY_SQUARES_2),
	SQUARE_2_BL(new GridPos[] { new GridPos(0, 0), new GridPos(0, 1), new GridPos(-1, 0), new GridPos(-1, 1) },
			Sprite.CITY_SQUARES_2),
	SQUARE_2_TL(new GridPos[] { new GridPos(0, 0), new GridPos(0, -1), new GridPos(-1, 0), new GridPos(-1, -1) },
			Sprite.CITY_SQUARES_2),
	SQUARE_2_TR(new GridPos[] { new GridPos(0, 0), new GridPos(0, -1), new GridPos(1, 0), new GridPos(1, -1) },
			Sprite.CITY_SQUARES_2),

	BAR_2_T(new GridPos[] { new GridPos(0, 0), new GridPos(0, -1) }, Sprite.CITY_BAR_2_VERT),
	BAR_2_R(new GridPos[] { new GridPos(0, 0), new GridPos(1, 0) }, Sprite.CITY_BAR_2_HORIZ),
	BAR_2_B(new GridPos[] { new GridPos(0, 0), new GridPos(0, 1) }, Sprite.CITY_BAR_2_VERT),
	BAR_2_L(new GridPos[] { new GridPos(0, 0), new GridPos(-1, 0) }, Sprite.CITY_BAR_2_HORIZ),

	BAR_3_CENTER_VERT(new GridPos[] { new GridPos(0, 0), new GridPos(0, -1), new GridPos(0, 1) },
			Sprite.CITY_BAR_3_VERT),
	BAR_3_CENTER_HORIZ(new GridPos[] { new GridPos(0, 0), new GridPos(-1, 0), new GridPos(1, 0) },
			Sprite.CITY_BAR_3_HORIZ),
	BAR_3_T(new GridPos[] { new GridPos(0, 0), new GridPos(0, -1), new GridPos(0, -2) }, Sprite.CITY_BAR_3_VERT),
	BAR_3_R(new GridPos[] { new GridPos(0, 0), new GridPos(1, 0), new GridPos(2, 0) }, Sprite.CITY_BAR_3_HORIZ),
	BAR_3_B(new GridPos[] { new GridPos(0, 0), new GridPos(0, 1), new GridPos(0, 2) }, Sprite.CITY_BAR_3_VERT),
	BAR_3_L(new GridPos[] { new GridPos(0, 0), new GridPos(-1, 0), new GridPos(-2, 0) }, Sprite.CITY_BAR_3_HORIZ),

	CORNER_BR(new GridPos[] { new GridPos(0, 0), new GridPos(0, 1), new GridPos(1, 0) },
			new Sprite[] { Sprite.CITY_CORNER_BR }),
	CORNER_BL(new GridPos[] { new GridPos(0, 0), new GridPos(0, 1), new GridPos(-1, 0) },
			new Sprite[] { Sprite.CITY_CORNER_BL }),
	CORNER_TL(new GridPos[] { new GridPos(0, 0), new GridPos(0, -1), new GridPos(-1, 0) },
			new Sprite[] { Sprite.CITY_CORNER_TL }),
	CORNER_TR(new GridPos[] { new GridPos(0, 0), new GridPos(0, -1), new GridPos(1, 0) },
			new Sprite[] { Sprite.CITY_CORNER_TR });

	private final GridPos[] offsets;
	private final GridPos topLeft;
	private final Map<GridPos, GenTile.CollisionBox> collisionBoxes;
	public final Sprite[] sprites;

	RootedBuilding(GridPos[] offsets, Sprite[] sprites) {
		this.offsets = offsets;
		this.topLeft = computeTopLeft(offsets);
		this.collisionBoxes = GenTile.CollisionBox.fromOffsets(offsets);
		this.sprites = sprites;
	}

	public GridPos topLeft() {
		return topLeft;
	}

	public GenTile.CollisionBox getCollision(int spriteU, int spriteV) {
		return collisionBoxes.get(new GridPos(spriteU, spriteV));
	}

	public Stream<GridPos> coveredCells(int x, int y) {
		return Arrays.stream(offsets).map(offset -> new GridPos(x + offset.x, y + offset.y));
	}

	public static RootedBuilding[] valuesWithPredicate(GridPos pos, Predicate<GridPos> isCellFree) {
		return Arrays.stream(values()).filter(b -> b.coveredCells(pos.x, pos.y).allMatch(isCellFree))
				.toArray(RootedBuilding[]::new);
	}

	private static GridPos computeTopLeft(GridPos[] offsets) {
		int x = Integer.MAX_VALUE;
		int y = Integer.MAX_VALUE;

		for (GridPos p : offsets) {
			x = Math.min(x, p.x);
			y = Math.min(y, p.y);
		}

		return new GridPos(x, y);
	}
}