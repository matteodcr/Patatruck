package info3.game.scene;

import java.util.HashMap;

import info3.game.Game;
import info3.game.entity.CarEntity;
import info3.game.entity.CityTile;
import info3.game.entity.Entity;
import info3.game.entity.PhysicsEntity;
import info3.game.entity.Tile;
import info3.game.graphics.Graphics;
import info3.game.graphics.Graphics.Align;
import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.PositionF;
import info3.game.worldgen.WorldGenerator;
import info3.game.position.PositionI;

public class CityScene extends Scene {

	private final PositionF center = new PositionF((float) pixelWidth / 2F - 4.5F, (float) pixelHeight / 2F - 4.5F);
	private PositionF vanPosition = PositionF.ZERO;
	public final WorldGenerator worldGenerator = new WorldGenerator(0);
	private CarEntity car;
	private CarEntity cookCar;

	private HashMap<PositionI, CityTile> cachedCityTiles;

	public CityScene(int pixelWidth, int pixelHeight, Game g) {
		super(pixelWidth, pixelHeight, g);
		cachedCityTiles = new HashMap<PositionI, CityTile>();
		try {
			// car = new CarEntity(this, center, false);
			// addEntity(car);
		cookCar = new CarEntity(this, vanPosition, true);
		this.entity_list.add(cookCar);
		} catch (IOException e) {
			e.printStackTrace();
		} // To change with vanEntity

	}

	@Override
	public void tick(long elapsed) {
		super.tick(elapsed);
		/*
		 * if (this.m_game.m_listener.isUp("UP")) {
		 * cookPhysics.addForce(AutDirection.N); } if
		 * (this.m_game.m_listener.isUp("DOWN")) { cookPhysics.addForce(AutDirection.S);
		 * } if (this.m_game.m_listener.isUp("LEFT")) {
		 * cookPhysics.addForce(AutDirection.W); } if
		 * (this.m_game.m_listener.isUp("RIGHT")) {
		 * cookPhysics.addForce(AutDirection.E); }
		 * cook.setPosition(cook.getPosition().add(cookPhysics.Shift(elapsed)));
		 */
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

	public CarEntity getCook() {
		return cookCar;
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		this.cookCar.render(g);

		Sprite speed = Sprite.SPEEDOMETER;
		if (cookCar.physics.getVelocity() < 20) {
			speed = Sprite.SPEEDOMETER_LOW;
		} else if (cookCar.physics.getVelocity() > 50) {
			speed = Sprite.SPEEDOMETER_HIGH;
		}
		g.drawSprite(speed, 2, 0);
		g.drawText(cookCar.physics.getVelocity() + "", Align.CENTER, 12, 18);

	}

//	private void debugCache() {
//		System.out.println("==========");
//		CachedCityTiles.entrySet();
//		for (Entry<PositionI, CityTile> entry : CachedCityTiles.entrySet()) {
//			System.out.println(entry.getKey() + "/" + entry.getValue());
//		}
//	}
}
