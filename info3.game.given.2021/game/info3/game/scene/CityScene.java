package info3.game.scene;

import java.io.IOException;

import info3.game.entity.CityTile;
import info3.game.entity.CookEntity;
import info3.game.entity.Tile;
import info3.game.graphics.Graphics;
import info3.game.position.PositionF;
import info3.game.position.PositionI;

public class CityScene extends Scene {

	private final PositionF center = new PositionF((float) pixelWidth / 2F - 4.5F, (float) pixelHeight / 2F - 4.5F);
	private PositionF vanPosition = PositionF.ZERO;
	private CookEntity cook; // To change with vanEntity

	public CityScene(int pixelWidth, int pixelHeight) {
		super(pixelWidth, pixelHeight);
		try {
			cook = new CookEntity(this, vanPosition);
		} catch (IOException e) {
			e.printStackTrace();
		} // To change with vanEntity
	}

	@Override
	public void tick() {
		vanPosition = vanPosition.add(new PositionF(0.2F, 0.1F));
	}

	@Override
	public int getTileWidth() {
		return 20;
	}

	@Override
	public PositionF getOriginOffset() {
		return vanPosition.add(center.neg()).add(vanPosition);
	}

	@Override
	public Tile getTileAt(int gridX, int gridY) {
		return new CityTile(this, gridX, gridY);
	}

	@Override
	protected int getBackgroundColor() {
		return 0xeb6c82;
	}

	public CookEntity getCook() {
		return cook;
	}

	@Override
	public void render(Graphics g) {
		g.fill(getBackgroundColor());

		final int tileWidth = getTileWidth();
		final PositionF origin = getOriginOffset();
		PositionI min = getOriginOffset().divFloor(tileWidth).add(new PositionI(-1, -1));
		PositionI max = min.add(new PositionI(pixelWidth / tileWidth + 3, pixelHeight / tileWidth + 3));

		for (int y = min.getY(); y < max.getY(); y++) {
			for (int x = min.getX(); x < max.getX(); x++) {
				Graphics subGraphics = g.window(x * tileWidth - origin.getX(), y * tileWidth - origin.getY(), tileWidth,
						tileWidth);

				Tile tile = getTileAt(x, y);
				if (tile != null)
					tile.render(subGraphics);
			}
		}
		this.cook.render(g);
	}

}
