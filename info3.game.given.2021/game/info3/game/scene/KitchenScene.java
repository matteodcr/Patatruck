package info3.game.scene;

import java.io.IOException;

import info3.game.entity.CookEntity;
import info3.game.entity.Tile;
import info3.game.graphics.Graphics;
import info3.game.position.PositionF;

public class KitchenScene extends Scene {

	private static final PositionF KITCHEN_ORIGIN = new PositionF(46, 10);

	private CookEntity cook;

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
		// TODO
		return null;
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
