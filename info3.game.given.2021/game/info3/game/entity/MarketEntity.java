package info3.game.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import info3.game.content.ItemType;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.PositionF;
import info3.game.scene.CityScene;
import info3.game.scene.KitchenScene;
import info3.game.scene.Scene;
import info3.game.screen.GameScreen;

public class MarketEntity extends Entity {

	private final CityTile parentTile;

	final ItemType[] items = new ItemType[] { ItemType.POTATO, ItemType.SALAD, ItemType.TOMATO, ItemType.MEAT,
			ItemType.BREAD, ItemType.CHEESE, };

	final HashMap<ItemType, Integer> loot = new HashMap<>();

	MarketEntity(Scene parent, PositionF pos, CityTile tile) {
		super(parent, pos);
		category = AutCategory.P;
		parentTile = tile;

		// Generating random loot
		Random rand = new Random();
		for (ItemType item : items) {
			int randomQuantity = rand.nextInt(2);
			loot.put(item, randomQuantity);
		}
		parentScene.addEntity(this);
	}

	@Override
	public EntityType getType() {
		return EntityType.MARKET;
	}

	@Override
	public boolean pop(AutDirection direction) {
		if (((CityScene) parentScene).getCook().marketScreamsCooldown == 0) {
			parentScene.game.playSound("market_crowd_panic");
			((CityScene) parentScene).getCook().marketScreamsCooldown = 50;
		}
		KitchenScene kitchenScene = ((KitchenScene) ((GameScreen) this.parentScene.game.getScreen()).getKitchenScene());
		HashMap<ItemType, StockTable> stocktables;
		stocktables = kitchenScene.getStockTables();
		for (Map.Entry<ItemType, StockTable> stocktable : stocktables.entrySet()) {
			for (Map.Entry<ItemType, Integer> itemset : loot.entrySet()) {
				if (stocktable.getKey().equals(itemset.getKey())) {
					stocktable.getValue().addStock(itemset.getValue());
				}
			}
		}
		((CityScene) parentScene).addToMarketCache(parentTile.gridX, parentTile.gridY);
		return true;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		loot.clear();
		Random rand = new Random();
		for (ItemType item : items) {
			int randomQuantity = rand.nextInt(2);
			loot.put(item, randomQuantity);
		}
		return true;
	}

	@Override
	public boolean egg(AutDirection direction) {
		return true;
	}

	public boolean cell(AutDirection direction, AutCategory category) {
		AutDirection newDirection = convertRelativToAbsolutedir(direction);
		for (Entity entity : parentScene.entityList) {
			if (newDirection == AutDirection.H) {
				if (this.carInsideThisPos(entity) == category) {
					return true;
				}
			} else {
				super.cell(direction, category);
			}
		}
		return false;

	}

	/*
	 * fct HARDCODE pour les comparaisons avec les voitures
	 */
	public AutCategory carInsideThisPos(Entity entity) {
		float posX = entity.getPosition().getX();
		float posY = entity.getPosition().getY();
		float meStartPosX = this.position.getX();
		float meStartPosY = this.position.getY();
		if ((meStartPosX <= posX && posX <= meStartPosX + 19 && meStartPosY <= posY && posY <= meStartPosY + 19)
				|| (meStartPosX <= posX + 3 && posX + 3 <= meStartPosX + 19 && meStartPosY <= posY + 3
						&& posY + 3 <= meStartPosY + 19))
			return entity.category;
		return null;
	}

}
