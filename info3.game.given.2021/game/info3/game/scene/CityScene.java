package info3.game.scene;

import info3.game.entity.CityTile;
import info3.game.entity.Tile;
import info3.game.position.PositionF;

public class CityScene extends Scene {

	private final PositionF center = new PositionF((float) pixelWidth / 2F - 4.5F, (float) pixelHeight / 2F - 4.5F);
	private PositionF vanPosition = PositionF.ZERO;

	public CityScene(int pixelWidth, int pixelHeight) {
		super(pixelWidth, pixelHeight);
	}

	@Override public void tick() {
		vanPosition = vanPosition.add(new PositionF(0.2F, 0.1F));
	}

	@Override public int getTileWidth() {
		return 20;
	}

	@Override public PositionF getOriginOffset() {
		return vanPosition.add(center.neg()).add(vanPosition);
	}

	@Override public Tile getTileAt(int gridX, int gridY) {
		return new CityTile(gridX, gridY);
	}

	@Override protected int getBackgroundColor() {
		return 0xeb6c82;
	}

}
