package info3.game.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import info3.game.content.ItemType;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.PositionF;
import info3.game.scene.KitchenScene;
import info3.game.scene.Scene;
import info3.game.screen.GameScreen;

public class MarketEntity extends Entity {

	private CityTile parentTile;

	ItemType items[] = new ItemType[] { ItemType.POTATO, ItemType.SALAD, ItemType.TOMATO, ItemType.MEAT, ItemType.BREAD,
			ItemType.CHEESE, };

	HashMap<ItemType, Integer> loot;

	MarketEntity(Scene parent, PositionF pos, CityTile tile) {
		super(parent, pos);
		category = AutCategory.P;
		parentTile = tile;
		loot = new HashMap<ItemType, Integer>();

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
		KitchenScene kitchenScene = ((KitchenScene) ((GameScreen) this.parentScene.m_game.getScreen())
				.getKitchenScene());
		HashMap<ItemType, StockTable> stocktables = new HashMap<ItemType, StockTable>();
		stocktables = kitchenScene.getStockTables();
		for (Map.Entry<ItemType, StockTable> stocktable : stocktables.entrySet()) {
			for (Map.Entry<ItemType, Integer> itemset : loot.entrySet()) {
				if (stocktable.getKey().equals(itemset.getKey())) {
					stocktable.getValue().addStock((int) itemset.getValue());
				}
			}
		}
		return true;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		loot.clear();
		Random rand = new Random();
		for (ItemType item : items) {
			int randomQuantity = rand.nextInt(4);
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
			switch (newDirection) {
			case H: {
				if (this.carInsideThisPos(entity) == category) {
					return true;
				}
				break;
			}
			default:
				super.cell(direction, category);
				break;
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
