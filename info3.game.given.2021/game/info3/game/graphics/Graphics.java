package info3.game.graphics;

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

	int measureText(String text);

	void drawText(String text, Align align, int x, int y);

}
