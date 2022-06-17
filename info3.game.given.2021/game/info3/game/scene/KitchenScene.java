package info3.game.scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import info3.game.Game;
import info3.game.content.Item;
import info3.game.content.ItemType;
import info3.game.entity.BasicTableTile;
import info3.game.entity.CockroachEntity;
import info3.game.entity.CookEntity;
import info3.game.entity.CutTile;
import info3.game.entity.DeliveryTile;
import info3.game.entity.Entity;
import info3.game.entity.FrieTile;
import info3.game.entity.PanTile;
import info3.game.entity.SauceTableTile;
import info3.game.entity.StockTable;
import info3.game.entity.Tile;
import info3.game.entity.TrashTile;
import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutDirection;
import info3.game.position.Direction;
import info3.game.position.PositionF;

public class KitchenScene extends Scene {

	private static final PositionF KITCHEN_ORIGIN = new PositionF(41, 10);
	public static final int MAXIMUM_COCKROACH_NUMBER = 20;

	private CookEntity cook;
	private CockroachEntity cockroach;
	private int cockroach_counter = 1;

	private ArrayList<PositionF> indexEmplacements = new ArrayList<>(List.of(new PositionF(0, 1), new PositionF(0, 2),
			new PositionF(0, 3), new PositionF(0, 4), new PositionF(0, 5), new PositionF(3, 1), new PositionF(3, 2),
			new PositionF(3, 3), new PositionF(3, 4), new PositionF(3, 5)));
	private ArrayList<PositionF> indexStockEmplacements = new ArrayList<>(List.of(new PositionF(0, 6),
			new PositionF(0, 7), new PositionF(0, 8), new PositionF(3, 6), new PositionF(3, 7), new PositionF(3, 8)));

	public Tile[][] KitchenGrid = new Tile[][] {
			new Tile[] { new BasicTableTile(this, 0, 0, AutDirection.S), new BasicTableTile(this, 1, 0, AutDirection.S),
					new BasicTableTile(this, 2, 0, AutDirection.S), new FrieTile(this, 3, 0, AutDirection.S),
					new FrieTile(this, 4, 0, AutDirection.S), new CutTile(this, 5, 0, AutDirection.S),
					new StockTable(this, 6, 0, AutDirection.S, new Item(ItemType.TOMATO), Sprite.TOMATO),
					new StockTable(this, 7, 0, AutDirection.S, new Item(ItemType.POTATO), Sprite.POTATO),
					new StockTable(this, 8, 0, AutDirection.S, new Item(ItemType.CHEESE), Sprite.CHEESE),
					new BasicTableTile(this, 9, 0, AutDirection.S) },
			new Tile[] { new DeliveryTile(this, 0, 1, AutDirection.E), null, null, null, null, null, null, null, null,
					new SauceTableTile(this, 9, 1, AutDirection.W) },
			new Tile[] { new DeliveryTile(this, 0, 2, AutDirection.E), null, null, null, null, null, null, null, null,
					new SauceTableTile(this, 9, 2, AutDirection.W) },
			new Tile[] { new BasicTableTile(this, 0, 3, AutDirection.N), new TrashTile(this, 1, 3, AutDirection.N),
					new BasicTableTile(this, 2, 3, AutDirection.N), new PanTile(this, 3, 3, AutDirection.N),
					new PanTile(this, 4, 3, AutDirection.N), new CutTile(this, 5, 3, AutDirection.N),
					new StockTable(this, 6, 3, AutDirection.N, new Item(ItemType.SALAD), Sprite.SALADE),
					new StockTable(this, 7, 3, AutDirection.N, new Item(ItemType.BREAD), Sprite.BREAD),
					new StockTable(this, 8, 3, AutDirection.N, new Item(ItemType.MEAT), Sprite.MEAT),
					new BasicTableTile(this, 9, 3, AutDirection.N) } };

	public KitchenScene(int pixelWidth, int pixelHeight, Game g) {
		super(pixelWidth, pixelHeight, g);
		try {
			cook = new CookEntity(this,
					new PositionF(KITCHEN_ORIGIN.getX() + getTileWidth(), KITCHEN_ORIGIN.getY() + getTileWidth()));
			addEntity(cook);

			cockroach = new CockroachEntity(this, new PositionF(KITCHEN_ORIGIN.getX() + getTileWidth() * 2,
					KITCHEN_ORIGIN.getY() + getTileWidth() * 2), 2, 2);
			addEntity(cockroach);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void shuffle() {
		// generate future emplacements
		Random rdm = new Random();
		int[] movedTiles = new int[10], movedStockTables = new int[6];
		for (int i = 0; i < movedTiles.length; i++) {
			boolean acceptable;
			do {
				acceptable = true;
				movedTiles[i] = rdm.nextInt(movedTiles.length);
				for (int y = 0; y < i; y++) {
					if (movedTiles[i] == movedTiles[y]) {
						acceptable = false;
					}
				}
			} while (!acceptable);
		}
		for (int i = 0; i < movedStockTables.length; i++) {
			boolean acceptable;
			do {
				acceptable = true;
				movedStockTables[i] = rdm.nextInt(movedStockTables.length);
				for (int y = 0; y < i; y++) {
					if (movedStockTables[i] == movedStockTables[y]) {
						acceptable = false;
					}
				}
			} while (!acceptable);
		}
		Tile[][] futureKitchenGrid = new Tile[][] {
				new Tile[] { new BasicTableTile(this, 0, 0, AutDirection.S), null, null, null, null, null, null, null,
						null, new BasicTableTile(this, 9, 0, AutDirection.S) },
				new Tile[] { new DeliveryTile(this, 0, 1, AutDirection.E), null, null, null, null, null, null, null,
						null, new SauceTableTile(this, 9, 1, AutDirection.W) },
				new Tile[] { new DeliveryTile(this, 0, 2, AutDirection.E), null, null, null, null, null, null, null,
						null, new SauceTableTile(this, 9, 2, AutDirection.W) },
				new Tile[] { new BasicTableTile(this, 0, 0, AutDirection.S), null, null, null, null, null, null, null,
						null, new BasicTableTile(this, 9, 0, AutDirection.S) } };
		// replace everything
		for (int i = 0; i < movedTiles.length; i++) {
			PositionF pos = indexEmplacements.get(i), futurePos = indexEmplacements.get(movedTiles[i]);
			Tile toMove = KitchenGrid[(int) pos.getX()][(int) pos.getY()];
			toMove.setPosition(futurePos);
			if (movedTiles[i] < movedTiles.length) {
				toMove.myDir(AutDirection.N);
			} else {
				toMove.myDir(AutDirection.S);
			}

			futureKitchenGrid[(int) futurePos.getX()][(int) futurePos.getY()] = toMove;
		}
		for (int i = 0; i < movedStockTables.length; i++) {
			PositionF pos = indexStockEmplacements.get(i), futurePos = indexStockEmplacements.get(movedStockTables[i]);
			Tile toMove = KitchenGrid[(int) pos.getX()][(int) pos.getY()];
			toMove.setPosition(futurePos);
			if (movedStockTables[i] < movedStockTables.length) {
				toMove.myDir(AutDirection.N);
			} else {
				toMove.myDir(AutDirection.S);
			}
			futureKitchenGrid[(int) futurePos.getX()][(int) futurePos.getY()] = toMove;
		}
		this.KitchenGrid = futureKitchenGrid;
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
		if (gridX < 0 || gridY < 0 || gridX >= 10 || gridY >= 4)
			return null;
		return KitchenGrid[gridY][gridX];
	}

	@Override
	protected int getBackgroundColor() {
		return 0x000000;
	}

	public CookEntity getCook() {
		return cook;
	}

	@Override
	public void render(Graphics g) {
		g.fill(0xff511e43);
		g.drawSprite(Sprite.KITCHENTRUCK, (g.getWidth() / 2) - 100, -3); // Valeur calculées
		super.render(g); // Fond et case
		for (Entity entity : entity_list) {
			entity.render(g);
		}
	}

	public int getCockroach_counter() {
		return cockroach_counter;
	}

	public void setCockroach_counter(int cockroack_counter) {
		this.cockroach_counter = cockroack_counter;
	}

	@Override
	public void tick(long elapsed) {
		super.tick(elapsed);
		for (int i = 0; i < KitchenGrid.length; i++) {
			for (int j = 0; j < KitchenGrid[i].length; j++) {
				if (KitchenGrid[i][j] != null) {
					KitchenGrid[i][j].tick(elapsed);
				}
			}
		}

		Random rand = new Random();
		if (rand.nextInt(500) < 2) {
			try {
				this.addEntity(new CockroachEntity(this, new PositionF(132, 36), 7, 2));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
