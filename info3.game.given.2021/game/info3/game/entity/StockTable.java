package info3.game.entity;

import info3.game.content.Item;
import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutDirection;
import info3.game.scene.Scene;

public class StockTable extends KitchenTile {
	private static final int[] FULL_INDICATOR_AMOUNTS = new int[] { 0, 1, 2, 3, 3, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 6 };

	Item item;
	int stock;
	Sprite stockItem, background = Sprite.STOCK_TABLE;
	boolean empty;
	private boolean onWizz = false;
	private int timerWizz = 0, maxTimerWizz = 10;

	public StockTable(Scene parent, int gridX, int gridY, AutDirection d, Item item, Sprite stockItem) {
		super(parent, gridX, gridY, null, d);
		this.stockItem = stockItem;
		this.stock = 5;
		this.item = item;
		this.stockItem = stockItem;
		this.defaultSprite = background;
		this.empty = false;
	}

	@Override
	public EntityType getType() {
		return EntityType.TILE_STOCK;
	}

	@Override
	public boolean pop(AutDirection direction) { // prendre un aliment
		Entity eInteracting = selectEntityToInteractWith();
		if (eInteracting instanceof CookEntity && ((CookEntity) eInteracting) != null) {
			if (((CookEntity) eInteracting).m_assembly.getItems().size() != 0) {
				parentScene.m_game.playSound("pick");
				return false;
			} else {
				if (stock == 0) {
					return false;
				} else {
					((CookEntity) eInteracting).m_assembly.getItems().add(item);
					stock--;
					if (gotStuff()) {
						empty = false;
						;
					} else {
						empty = true;
					}
					return true;
				}
			}

		}
		if (eInteracting instanceof CockroachEntity && ((CockroachEntity) eInteracting) != null) {
			if (((CockroachEntity) eInteracting).item != null) {
				return false;
			} else {
				if (stock == 0) {
					return false;
				} else {
					((CockroachEntity) eInteracting).item = item;
					stock--;
					if (gotStuff()) {
						empty = false;
						;
					} else {
						empty = true;
					}
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void tick(long elapsed) {
		super.tick(elapsed);
		if (onWizz) {
			timerWizz++;
			if (timerWizz == maxTimerWizz) {
				timerWizz = 0;
				onWizz = false;
			}
		}
	}

	@Override
	public boolean wizz(AutDirection direction) {
		onWizz = true;
		return true;
	}

	public int getStock() {
		return this.stock;
	}

	public void addStock(int x) {
		this.stock += x;
	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(m_direction == AutDirection.S ? Sprite.STOCK_TABLE : Sprite.STOCK_TABLE_N, 0, 0);
		if (!empty)
			g.drawSprite(this.stockItem, 0, 0);
		if (onWizz)
			g.drawSprite(Sprite.STOCKTABLE_WIZZ, 0, 0);

		Sprite fullIndicator;
		if (stock < FULL_INDICATOR_AMOUNTS.length) {
			fullIndicator = Sprite.FULL_INDICATORS[FULL_INDICATOR_AMOUNTS[stock]];
		} else {
			fullIndicator = Sprite.FULL_7;
		}
		g.drawSprite(fullIndicator, 0, m_direction == AutDirection.S ? 0 : 9);
	}

	@Override
	public boolean egg(AutDirection direction) {
		if (parentScene.entityList.size() <= Scene.MAXIMUM_ENTITIES) {
			Entity newEntity = null;
			newEntity = new StockTable(this.parentScene, this.gridX, this.gridY, this.m_direction, item,
					this.defaultSprite);
			return this.parentScene.addEntity(newEntity);
		}
		return false;
	}

	@Override
	public boolean gotStuff() {
		return stock > 0;
	}

}
