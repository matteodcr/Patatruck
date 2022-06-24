package info3.game.worldgen;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import info3.game.graphics.Sprite;

public class GenTile {
	public final Sprite buildingSprite;
	public final int buildingSpriteOffsetX, buildingSpriteOffsetY;
	public final CollisionBox collisionBox;
	final boolean hasMarketPaving;

	GenTile(Sprite buildingSprite, int buildingSpriteOffsetX, int buildingSpriteOffsetY, CollisionBox collisionBox,
			boolean hasMarketPaving) {
		this.buildingSprite = buildingSprite;
		this.buildingSpriteOffsetX = buildingSpriteOffsetX;
		this.buildingSpriteOffsetY = buildingSpriteOffsetY;
		this.collisionBox = collisionBox;
		this.hasMarketPaving = hasMarketPaving;
	}

	public static class CollisionBox {
		public final boolean topLeft, top, left;

		private CollisionBox(boolean topLeft, boolean top, boolean left) {
			this.topLeft = topLeft;
			this.top = top;
			this.left = left;
		}

		static Map<GridPos, CollisionBox> fromOffsets(GridPos[] offsets) {
			return Arrays.stream(offsets).collect(Collectors.toMap(Function.identity(), pos -> {
				boolean topLeft = Arrays.asList(offsets).contains(pos.top().left());
				boolean top = Arrays.asList(offsets).contains(pos.top());
				boolean left = Arrays.asList(offsets).contains(pos.left());

				return new CollisionBox(topLeft, top, left);
			}));
		}
	}
	
	public boolean hasRoad() {
		return !(collisionBox.topLeft && collisionBox.top && collisionBox.left);
	}
}
