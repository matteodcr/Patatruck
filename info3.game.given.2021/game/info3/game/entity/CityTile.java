package info3.game.entity;

import java.util.Map;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutDirection;
import info3.game.position.PositionF;
import info3.game.scene.CityScene;
import info3.game.scene.Scene;
import info3.game.worldgen.GenTile;

public class CityTile extends Tile {
	private static final Map<GenTile.MarketRoad, Sprite> MARKET_SPRITE_TOP = Map.of(GenTile.MarketRoad.IN,
			Sprite.CITY_MARKET_TL, GenTile.MarketRoad.OUT, Sprite.CITY_MARKET_TR, GenTile.MarketRoad.BOTH,
			Sprite.CITY_MARKET_T);
	private static final Map<GenTile.MarketRoad, Sprite> MARKET_SPRITE_LEFT = Map.of(GenTile.MarketRoad.IN,
			Sprite.CITY_MARKET_LT, GenTile.MarketRoad.OUT, Sprite.CITY_MARKET_LB, GenTile.MarketRoad.BOTH,
			Sprite.CITY_MARKET_L);

	public final GenTile genTile;
	private final Sprite topRoadSprite, leftRoadSprite;
	public SpeedBumpEntity eSpeedbump = null;

	public CityTile(Scene parent, int gridX, int gridY) {
		super(parent, gridX, gridY);
		genTile = ((CityScene) parent).worldGenerator.generate(gridX, gridY);
		topRoadSprite = genTile.marketPavingTop == null ? null : MARKET_SPRITE_TOP.get(genTile.marketPavingTop);
		leftRoadSprite = genTile.marketPavingLeft == null ? null : MARKET_SPRITE_LEFT.get(genTile.marketPavingLeft);
		if (genTile.speedbumpLeft || genTile.speedbumpTop)
			eSpeedbump = new SpeedBumpEntity(parent, position, this);
	}

	public GenTile getGenTile() {
		return genTile;
	}

	@Override
	public boolean move(AutDirection direction) {
		// Can't move
		return false;
	}

	@Override
	public EntityType getType() {
		return EntityType.TILE_CITY;
	}

	@Override
	public void render(Graphics g) {
		if (genTile.hasMarketPaving) {
			g.drawSprite(Sprite.CITY_MARKET_CROSSING, 0, 0);
		}

		if (topRoadSprite != null) {
			g.drawSprite(topRoadSprite, 0, 0);
		}

		if (leftRoadSprite != null) {
			g.drawSprite(leftRoadSprite, 0, 0);
		}
		if (genTile.speedbumpTop == true) {
			g.drawSprite(Sprite.TOMATO, 10, 0);
		}

		if (genTile.speedbumpLeft == true) {
			g.drawSprite(Sprite.TOMATO, 0, 10);
		}

		g.drawSpritePart(genTile.buildingSprite, 0, 0, genTile.buildingSpriteOffsetX, genTile.buildingSpriteOffsetY);
	}

	@Override
	public boolean pop(AutDirection direction) {
		// FIXME: Don't know what to do with this method
		return false;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		this.parentScene.addEntity(new CarEntity(this.parentScene,
				new PositionF(gridX * parentScene.getTileWidth(), gridY * parentScene.getTileWidth()), false, false));
		return true;
	}

	@Override
	public boolean egg(AutDirection direction) {
		this.parentScene.addEntity(new CarEntity(this.parentScene,
				new PositionF(gridX * parentScene.getTileWidth(), gridY * parentScene.getTileWidth()), true, false));
		return true;
	}
}
