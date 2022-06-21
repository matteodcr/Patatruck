package info3.game.graphics;

import info3.game.content.Item;
import info3.game.content.Sauce;

public interface Graphics {

	enum Align {
		LEFT, RIGHT, CENTER,
	}

	int getWidth();

	int getHeight();

	/**
	 * @return Une sous-toile
	 */
	Graphics window(int x, int y, int w, int h);

	/**
	 * @return Une sous-toile avec un décalage flottant
	 */
	default Graphics window(float x, float y, int w, int h) {
		return window((int) x, (int) y, w, h);
	}

	void fill(int color, int x, int y, int width, int height);

	default void fill(int color) {
		this.fill(color, 0, 0, getWidth(), getHeight());
	}

	void drawSprite(Sprite sprite, int x, int y);

	default void drawSprite(Sprite sprite, float x, float y) {
		drawSprite(sprite, (int) x, (int) y);
	}

	/**
	 * Dessine un morceau d'une sprite, c'est-à-dire un carré de la taille d'une
	 * tile de la spritesheet
	 */
	void drawSpritePart(Sprite sprite, int x, int y, int offsetU, int offsetV);

	default void drawItem(Item i, float x, float y) {
		drawSprite(i.getType().getSprite(), x, y);
		if (i.getSauce() == Sauce.KETCHUP) {
			drawSprite(Sprite.INDIC_KETCHUP_, x, y);
		}
		if (i.getSauce() == Sauce.MAYO) {
			drawSprite(Sprite.INDIC_MAYO_, x, y);

		}
		if (i.getSauce() == Sauce.KETCHUP_MAYO) {
			drawSprite(Sprite.KETCHUP_MAYO_INDIC_, x, y);
		}

	}

	int measureText(String text);

	void drawText(String text, Align align, int x, int y);

}
