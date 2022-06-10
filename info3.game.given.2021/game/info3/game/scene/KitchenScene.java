package info3.game.scene;

import info3.game.entity.Tile;
import info3.game.position.PositionF;

public class KitchenScene extends Scene {

	private static final PositionF KITCHEN_ORIGIN = new PositionF(46, 10);

	public KitchenScene(int pixelWidth, int pixelHeight) {
		super(pixelWidth, pixelHeight);
	}

	@Override public int getTileWidth() {
		return 13;
	}

	@Override public PositionF getOriginOffset() {
		return KITCHEN_ORIGIN.neg();
	}

	@Override public Tile getTileAt(int gridX, int gridY) {
		// TODO
		return null;
	}

	@Override protected int getBackgroundColor() {
		return 0x511e43;
	}

}
