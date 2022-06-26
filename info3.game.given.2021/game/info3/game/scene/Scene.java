package info3.game.scene;

import java.util.ArrayList;

import info3.game.Game;
import info3.game.entity.Entity;
import info3.game.entity.Tile;
import info3.game.graphics.Graphics;
import info3.game.position.PositionF;
import info3.game.position.PositionI;

public abstract class Scene {

	public final Game game;

	public static final int MAXIMUM_ENTITIES = 30;

	protected final int pixelWidth, pixelHeight;
	public final ArrayList<Entity> entityList = new ArrayList<>();

	public Scene(int pixelWidth, int pixelHeight, Game g) {
		this.pixelWidth = pixelWidth;
		this.pixelHeight = pixelHeight;
		game = g;
	}

	public boolean addEntity(Entity entity) {
		return entityList.add(entity);
	}

	public boolean removeEntity(Entity entity) {
		return entityList.remove(entity);
	}

	public void tick(long elapsed) {
		ArrayList<Entity> entityListBuffered = new ArrayList<>(entityList);
		for (Entity entity : entityListBuffered) {
			entity.tick(elapsed);
		}
	}

	/**
	 * @return La taille d'une cellule sur cette grille
	 */
	public abstract int getTileWidth();

	/**
	 * @param gridX Une coordonnée X d'une cellule de grille
	 * @param gridY Une coordonnée Y d'une cellule de grille
	 * @return La cellule aux coordonnées spécifiées
	 */
	public abstract Tile getTileAt(int gridX, int gridY);

	/**
	 * @return La coordonnée en pixel du point qui s'affiche le plus en haut à
	 *         gauche de la zone de rendu
	 */
	public PositionF getOriginOffset() {
		return PositionF.ZERO;
	}

	protected int getBackgroundColor() {
		return 0xffffff;
	}

	public void render(Graphics g) {
		g.fill(getBackgroundColor());

		final int tileWidth = getTileWidth();
		final PositionF origin = getOriginOffset();
		PositionI min = getOriginOffset().divFloor(tileWidth).add(new PositionI(-1, -1));
		PositionI max = min.add(new PositionI(pixelWidth / tileWidth + 3, pixelHeight / tileWidth + 3));

		for (int y = min.getY(); y < max.getY(); y++) {
			for (int x = min.getX(); x < max.getX(); x++) {
				Graphics subGraphics = g.window(x * tileWidth - origin.getX(), y * tileWidth - origin.getY(), tileWidth,
						tileWidth);

				Tile tile = getTileAt(x, y);
				if (tile != null)
					tile.render(subGraphics);
			}
		}
	}

	public int getPixelWidth() {
		return pixelWidth;
	}

	public int getPixelHeight() {
		return pixelHeight;
	}

	public int getNbEntities() {
		return entityList.size();
	}
}
