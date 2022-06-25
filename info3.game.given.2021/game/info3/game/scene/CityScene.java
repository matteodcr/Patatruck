package info3.game.scene;

import java.util.Random;

import info3.game.Game;
import info3.game.entity.CarEntity;
import info3.game.entity.CityDeliveryTile;
import info3.game.entity.CityTile;
import info3.game.entity.Entity;
import info3.game.entity.Tile;
import info3.game.graphics.Graphics;
import info3.game.graphics.Graphics.Align;
import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.PositionF;
import info3.game.position.PositionI;
import info3.game.worldgen.WorldGenerator;

public class CityScene extends Scene {

	private final PositionF center = new PositionF((float) pixelWidth / 2F - 4.5F, (float) pixelHeight / 2F - 4.5F);
	private PositionF vanPosition = PositionF.ZERO;
	private CityDeliveryTile deliveryTile;
	public final WorldGenerator worldGenerator = new WorldGenerator(0);
	private CarEntity cookCar;
	private CarEntity truck;
	private Sprite deliveryArrowSprite = Sprite.DELIVERY_DOWN_ARROW;
	private PositionF deliveryArrowPos = new PositionF(-10, -10);

	public CityScene(int pixelWidth, int pixelHeight, Game g) {
		super(pixelWidth, pixelHeight, g);
		cookCar = new CarEntity(this, vanPosition, true, true);
		addEntity(cookCar);
		truck = new CarEntity(this, vanPosition, true, false);
		addEntity(truck);
		deliveryTile = new CityDeliveryTile(this);
		addEntity(deliveryTile);

	}

	@Override
	public void tick(long elapsed) {
		super.tick(elapsed);
		if (this.entityList.size() < 50)
			getRandomTileNearViewport().tick(elapsed);

		this.updateArrow();
	}

	@Override
	public int getTileWidth() {
		return 20;
	}

	@Override
	public PositionF getOriginOffset() {
		return cookCar.getPosition().sub(center);
	}

	@Override
	public Tile getTileAt(int gridX, int gridY) {
		Tile tile = new CityTile(this, gridX, gridY);
		return tile;
	}

	/* Renvoit la categorie du cadrant de la tuile a cette pos */
	public AutCategory whatsTheCategoryOfTile(PositionF pos, Entity entity) {
		int gX = getGridPosFromPosCity(pos).getX();
		int gY = getGridPosFromPosCity(pos).getY();
		CityTile tile = (CityTile) getTileAt(gX, gY);

		switch (whereInTile(pos)) {
		case 0:
			if (tile.genTile.collisionBox.topLeft)
				return AutCategory.O;
			else
				return AutCategory.J;
		case 1:
			if (tile.genTile.collisionBox.top)
				return AutCategory.O;
			else
				return AutCategory.J;
		case 2:
			if (tile.genTile.collisionBox.left)
				return AutCategory.O;
			else
				return AutCategory.J;
		case 3:
			return AutCategory.O;
		default:
			System.out.println("panic");
		}
		return null;
	}

	/* Fct qui renvoit le cadrant parmi les 4 d'une tuile de la ville */
	public int whereInTile(PositionF pos) {
		PositionF posModuloTileWidth = pos.divMod(getTileWidth());
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

	public void setCook(CarEntity car) {
		cookCar = car;
	}

	public CityDeliveryTile getDeliveryTile() {
		return deliveryTile;
	}

	@Override
	public void render(Graphics g) {
		super.render(g);

		for (Entity entity : entityList) {
			PositionF posGraphics = getPosRelativeToVan(entity);
			Graphics subGraphics = g.window(posGraphics.getX(), posGraphics.getY(), 4, 4);

			entity.render(subGraphics);
		}

		Sprite speed = Sprite.SPEEDOMETER;
		if (cookCar.physics.getVelocity() < 20) {
			speed = Sprite.SPEEDOMETER_LOW;
		} else if (cookCar.physics.getVelocity() > 50) {
			speed = Sprite.SPEEDOMETER_HIGH;
		}
		g.drawSprite(speed, 2, 0);
		g.drawSprite(deliveryArrowSprite, deliveryArrowPos.getX(), deliveryArrowPos.getY());
		deliveryTile.render(g);
		g.drawText(cookCar.physics.getVelocity() + "", Align.CENTER, 12, 18);

	}

	/* Visuellement la fenêtre le van n'est PAS en 0,0 mais en center */
	public PositionF getPosRelativeToVan(Entity entity) {
		return entity.getPosition().add(center).sub(getCook().getPosition());
	}

	/*
	 * Fct qui renvoit la grille correspondante à une position en pixels(marche ds
	 * les 2 scènes) Pour la cityScene 0,0 est le centre du viewport, il faut donc
	 * faire qq calculs pour les grilles qui peuvent être négatives, si on a des
	 * valeurs positives, on divise juste la position reçue en retirant l'offset.
	 * 
	 */
	public PositionI getGridPosFromPosCity(PositionF pos) {
		if (pos.getX() < 0 || pos.getY() < 0) {
			pos = pos.divFloorF(getTileWidth());
			float posTmpX = pos.getX();
			float posTmpY = pos.getY();
			int posX = (int) posTmpX;
			int posY = (int) posTmpY;
			if (posTmpX < 0)
				posX = (int) Math.floor(posTmpX);
			if (posTmpY < 0)
				posY = (int) Math.floor(posTmpY);
			return new PositionI(posX, posY);
		} else {
			return pos.divFloor(getTileWidth());
		}

	}

	public void updateArrow() {
		float xCar = cookCar.getPosition().getX();
		float yCar = cookCar.getPosition().getY();
		float xTile = deliveryTile.getPosition().getX();
		float yTile = deliveryTile.getPosition().getY();

		if (yTile < yCar - pixelHeight / 2) {
			if (xTile > xCar + pixelWidth / 2) {
				deliveryArrowSprite = Sprite.DELIVERY_UPRIGHT_ARROW;
				deliveryArrowPos = new PositionF(pixelWidth - 10, 0);
			} else if (xTile < xCar - pixelWidth / 2) {
				deliveryArrowSprite = Sprite.DELIVERY_UPLEFT_ARROW;
				deliveryArrowPos = new PositionF(0, 0);
			} else {
				deliveryArrowSprite = Sprite.DELIVERY_UP_ARROW;
				deliveryArrowPos = new PositionF(Math.abs(xTile - (xCar - pixelWidth / 2)), 0);
			}
		}

		else if (yTile > yCar + pixelHeight / 2) {
			if (xTile > xCar + pixelWidth / 2) {
				deliveryArrowSprite = Sprite.DELIVERY_DOWNRIGHT_ARROW;
				deliveryArrowPos = new PositionF(pixelWidth - 10, pixelHeight - 15);
			} else if (xTile < xCar - pixelWidth / 2) {
				deliveryArrowSprite = Sprite.DELIVERY_DOWNLEFT_ARROW;
				deliveryArrowPos = new PositionF(0, pixelHeight - 15);
			} else {
				deliveryArrowSprite = Sprite.DELIVERY_DOWN_ARROW;
				deliveryArrowPos = new PositionF(Math.abs(xTile - (xCar - pixelWidth / 2)), pixelHeight - 15);
			}
		}

		else if (xTile > xCar + pixelWidth / 2) {
			deliveryArrowSprite = Sprite.DELIVERY_RIGHT_ARROW;
			deliveryArrowPos = new PositionF(pixelWidth - 10, Math.abs(yTile - yCar + pixelHeight / 2));
		}

		else if (xTile < xCar - pixelWidth / 2) {
			deliveryArrowSprite = Sprite.DELIVERY_LEFT_ARROW;
			deliveryArrowPos = new PositionF(0, Math.abs(yTile - yCar + pixelHeight / 2));
		} else {
			deliveryArrowSprite = Sprite.DELIVERY_DOWN_ARROW;
			deliveryArrowPos = new PositionF(-10, -10);
		}
	}

	private CityTile getRandomTileNearViewport() {
		Random rand = new Random();
		CityTile tile;
		do {
			PositionF randPos = new PositionF(rand.nextInt(13) * getTileWidth(), rand.nextInt(4) * getTileWidth());

			PositionF pos = randPos.sub(center).add(getCook().getPosition());
			PositionI gridPos = getGridPosFromPosCity(pos);
			tile = (CityTile) getTileAt(gridPos.getX(), gridPos.getY());
		} while (tile.genTile.collisionBox.topLeft);

		return tile;
	}

	public boolean isTooFarFromVan(Entity entity) {
		PositionF posFromVan = getPosRelativeToVan(entity).sub(center);
		float posFromVanX = posFromVan.getX();
		float posFromVanY = posFromVan.getY();
		if (Math.abs(posFromVanX) > 192f || Math.abs(posFromVanY) > 108f)
			return true;
		return false;
	}
}
