package info3.game.scene;

import java.io.IOException;

import info3.game.Game;
import info3.game.entity.CityDeliveryTile;
import info3.game.entity.CityTile;
import info3.game.entity.CookEntity;
import info3.game.entity.Tile;
import info3.game.graphics.Graphics;
import info3.game.position.PositionF;
import info3.game.worldgen.WorldGenerator;

public class CityScene extends Scene {

	private final PositionF center = new PositionF((float) pixelWidth / 2F - 4.5F, (float) pixelHeight / 2F - 4.5F);
	private PositionF vanPosition = PositionF.ZERO;
	private CookEntity cook; // To change with vanEntity
	private CityDeliveryTile deliveryTile;
	public final WorldGenerator worldGenerator = new WorldGenerator(0);

	public CityScene(int pixelWidth, int pixelHeight, Game g) {
		super(pixelWidth, pixelHeight, g);
		try {
			cook = new CookEntity(this, vanPosition);
			deliveryTile = new CityDeliveryTile(this);
		} catch (IOException e) {
			e.printStackTrace();
		} // To change with vanEntity
	}

	@Override
	public void tick(long elapsed) {
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
		return 0xffeb6c82;
	}

	public CookEntity getCook() {
		return cook;
	}
	
	public CityDeliveryTile getDeliveryTile() {
		return deliveryTile;
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		this.cook.render(g);
	}

}
