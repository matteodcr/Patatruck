package info3.game.scene;

import java.io.IOException;

import info3.game.entity.CookEntity;
import info3.game.entity.StockTable;
import info3.game.entity.Tile;
import info3.game.graphics.Graphics;
import info3.game.position.Direction;
import info3.game.position.PositionF;

public class KitchenScene extends Scene {

	private static final PositionF KITCHEN_ORIGIN = new PositionF(46, 10);

	private CookEntity cook;

	Tile[][] KitchenGrid = new Tile[][] {
			new Tile[] { null, new StockTable(this, 1, 0, Direction.SUD), new StockTable(this, 2, 0, Direction.SUD),
					new StockTable(this, 3, 0, Direction.SUD), new StockTable(this, 4, 0, Direction.SUD),
					new StockTable(this, 5, 0, Direction.SUD), new StockTable(this, 6, 0, Direction.SUD),
					new StockTable(this, 7, 0, Direction.SUD), new StockTable(this, 8, 0, Direction.SUD), null },
			new Tile[] { new StockTable(this, 0, 1, Direction.EST), new StockTable(this, 1, 1, Direction.SUD), null,
					null, null, null, null, null, null, new StockTable(this, 9, 1, Direction.OUEST) },
			new Tile[] { new StockTable(this, 0, 2, Direction.EST), new StockTable(this, 1, 2, Direction.SUD), null,
					null, null, null, null, null, null, new StockTable(this, 9, 2, Direction.OUEST) },
			new Tile[] { null, new StockTable(this, 1, 3, Direction.NORD), new StockTable(this, 2, 3, Direction.NORD),
					new StockTable(this, 3, 3, Direction.NORD), new StockTable(this, 4, 3, Direction.NORD),
					new StockTable(this, 5, 3, Direction.NORD), new StockTable(this, 6, 3, Direction.NORD),
					new StockTable(this, 7, 3, Direction.NORD), new StockTable(this, 8, 3, Direction.NORD), null } };

	public KitchenScene(int pixelWidth, int pixelHeight) {
		super(pixelWidth, pixelHeight);
		try {
			cook = new CookEntity(this, KITCHEN_ORIGIN);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getTileWidth() {
		return 13;
	}

	@Override
	public PositionF getOriginOffset() {
		return KITCHEN_ORIGIN.neg();
	}

	@Override
	public Tile getTileAt(int gridX, int gridY) {
		return KitchenGrid[gridX][gridY];
	}

	@Override
	protected int getBackgroundColor() {
		return 0x511e43;
	}

	public CookEntity getCook() {
		return cook;
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		this.cook.render(g);
	}

}
