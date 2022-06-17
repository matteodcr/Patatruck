package info3.game.entity;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.PositionF;
import info3.game.content.ItemType;
import info3.game.scene.Scene;

public class MarketEntity extends Entity {
	
	private final static int MIN_QUANTITY = 0;
	private final static int MAX_QUANTITY = 3;
	
    ItemType items[] = new ItemType[] {
    		ItemType.POTATO,
            ItemType.SALAD,
            ItemType.TOMATO,
            ItemType.MEAT,
            ItemType.BREAD,
            ItemType.CHEESE,
    };
	
	HashMap<ItemType, Integer> loot;
	
	MarketEntity(Scene parent, PositionF pos, int gX, int gY) {
		super(parent, pos, gX, gY); //FIXME define gridX and gridY
		automaton = parentScene.setupAutomaton("Market");
		current_state = automaton.initial;
		category = AutCategory.P;
		
		// Generating random loot
		int random_quantity;
		for (ItemType item : items) {
			random_quantity = (int)Math.random()*(MAX_QUANTITY - MIN_QUANTITY + 1)+MIN_QUANTITY;
			loot.put(item, random_quantity);
		}
		parentScene.addEntity(this);
	}

	@Override
	public boolean pop(AutDirection direction) {
		HashMap<ItemType, StockTable> stocktables = this.parentScene.m_game.getKitchenScene().getStocktables();
		for (Map.Entry<ItemType, StockTable> stocktable : stocktables.entrySet()) {
			for (Map.Entry<ItemType, Integer> itemset : loot.entrySet()) {
				if (stocktable.getKey().equals(itemset.getKey())) {
					stocktable.getValue().addStock((int)itemset.getValue());
				}
			}
		}
		return true;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		return true;
	}

	@Override
	public boolean gwait() {
		return false;
	}

	@Override
	public boolean egg(AutDirection direction) {
		return false;
	}

	@Override
	public boolean hit(AutDirection direction) {
		return false;
	}

	@Override
	public boolean jump(AutDirection direction) {
		return false;
	}

	@Override
	public boolean explode() {
		return false;
	}

	@Override
	public boolean pick(AutDirection direction) {
		return false;
	}

	@Override
	public boolean power() {
		return false;
	}

	@Override
	public boolean protect(AutDirection direction) {
		return false;
	}

	@Override
	public boolean store() {
		return false;
	}

	@Override
	public boolean turn(AutDirection direction) {
		return false;
	}

	@Override
	public boolean gthrow(AutDirection direction) {
		return false;
	}

	@Override
	public boolean myDir(AutDirection direction) {
		return false;
	}

	@Override
	public boolean closest(AutCategory category, AutDirection direction) {
		return false;
	}

	@Override
	public boolean gotPower() {
		return false;
	}

	@Override
	public boolean gotStuff() {
		return false;
	}

}
