package info3.game.scene;

import java.io.IOException;
import java.util.HashMap;

import info3.game.Game;
import info3.game.entity.CarEntity;
import info3.game.entity.CityTile;
import info3.game.entity.CookEntity;
import info3.game.entity.Entity;
import info3.game.entity.PhysicsEntity;
import info3.game.entity.Tile;
import info3.game.graphics.Graphics;
import info3.game.graphics.Graphics.Align;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.PositionF;
import info3.game.worldgen.WorldGenerator;
import info3.game.position.PositionI;

public class CityScene extends Scene {

	private final PositionF center = new PositionF((float) pixelWidth / 2F - 4.5F, (float) pixelHeight / 2F - 4.5F);
	private PositionF vanPosition = PositionF.ZERO;
	private CookEntity cook; // To change with vanEntity
	public final WorldGenerator worldGenerator = new WorldGenerator(0);
	private CarEntity car;

	private HashMap<PositionI, CityTile> cachedCityTiles;

	public CityScene(int pixelWidth, int pixelHeight, Game g) {
		super(pixelWidth, pixelHeight, g);
		phyCook = new PhysicsEntity(4);
		cachedCityTiles = new HashMap<PositionI, CityTile>();
		try {

			cook = new CookEntity(this, vanPosition); // To change with vanEntity
			car = new CarEntity(this, center, false);
			addEntity(car);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tick(long elapsed) {
		super.tick(elapsed);
		if (m_game.m_listener.isUp("UP")) {
			phyCook.addAcceleration(AutDirection.N);
		}
		if (m_game.m_listener.isUp("DOWN")) {
			phyCook.addAcceleration(AutDirection.S);
		}
		if (m_game.m_listener.isUp("LEFT")) {
			phyCook.addAcceleration(AutDirection.W);
		}
		if (m_game.m_listener.isUp("RIGHT")) {
			phyCook.addAcceleration(AutDirection.E);
		}
		cook.setPosition(phyCook.Shift(elapsed).add(cook.getPosition()));
	}

	// Commenté pour tester avec une scène fixe
	// @Override
	// public void tick(long elapsed) {
	// vanPosition = vanPosition.add(new PositionF(0.2F, 0.1F));
	// }

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
		Tile tile = new CityTile(this, gridX, gridY);
		cachedCityTiles.put(new PositionI(gridX, gridY), (CityTile) tile);
		return tile;
	}

	/* Renvoit la categorie du cadrant de la tuile a cette pos */
	public AutCategory whatsTheCategoryOfTile(PositionF pos, Entity entity) {
		int gX = entity.getGridPosFromPos().getX();
		int gY = entity.getGridPosFromPos().getY();
		CityTile tile = cachedCityTiles.get(new PositionI(gX, gY));
		switch (whereInTile(pos)) {
		case 0:
			return tile.category0;
		case 1:
			return tile.category1;
		case 2:
			return tile.category2;
		case 3:
			return tile.category3;
		default:
			System.out.println("panic");
		}
		return null;
	}

	/* Fct qui renvoit le cadrant parmi les 4 d'une tuile de la ville */
	public int whereInTile(PositionF pos) {
		PositionF removedOriginOffset = pos.minus(center);
		PositionF posModuloTileWidth = removedOriginOffset.divMod(getTileWidth());
		if (posModuloTileWidth.getX() < 9 && posModuloTileWidth.getY() < 9)
			return 0;
		if (posModuloTileWidth.getX() >= 9 && posModuloTileWidth.getY() < 9)
			return 1;
		if (posModuloTileWidth.getX() < 9 && posModuloTileWidth.getY() >= 9)
			return 2;
		else
			return 3;
	}

	@Override
	protected int getBackgroundColor() {
		return 0xffeb6c82;
	}

	public CookEntity getCook() {
		return cook;
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		this.cook.render(g);
		this.car.render(g);
		// debugCache();
	}

//	private void debugCache() {
//		System.out.println("==========");
//		CachedCityTiles.entrySet();
//		for (Entry<PositionI, CityTile> entry : CachedCityTiles.entrySet()) {
//			System.out.println(entry.getKey() + "/" + entry.getValue());
//		}
//	}
}
