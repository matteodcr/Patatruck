package info3.game.screen;

import info3.game.Game;
import info3.game.graphics.Graphics;

public abstract class Screen {
	protected final Game game;

	protected Screen(Game game) {
		this.game = game;
	}

	public void changeScreen(Screen newScreen) {
		game.changeScreen(newScreen);
	}

	public int getEntityCount() {
		return 0;
	}

	public void tick(long elapsed) {
	}

	public abstract void render(Graphics g);
}
