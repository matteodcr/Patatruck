package info3.game.screen;

import info3.game.Game;
import info3.game.graphics.Graphics;
import info3.game.graphics.Graphics.Align;
import info3.game.graphics.Sprite;

public class EndScreen extends Screen {
	long score;

	public EndScreen(Game game, long s) {
		super(game);
		score = s;
	}

	boolean keyPressed = false;

	@Override
	public void tick(long elapsed) {
		boolean upPressed = game.m_listener.isUp("UP");
		boolean downPressed = game.m_listener.isUp("DOWN");
		boolean spacePressed = game.m_listener.isUp("SPACE");

		if (upPressed && !keyPressed) {
		}

		if (downPressed && !keyPressed) {
		}

		if (spacePressed) {
			reloadGame();
		}

		keyPressed = upPressed || downPressed || spacePressed;
	}

	private void reloadGame() {

		changeScreen(new StartScreen(game));
	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(Sprite.AS_BACKDROP, 0, 0);
		g.drawSprite(Sprite.AS_LOGO, 0, 0);

		String tmp = "Tu as perdu sale naze, ton score est de " + score;
		g.drawText(tmp, Align.LEFT, 1, 50);
		tmp = "Le meilleur score est de " + game.highScore;
		g.drawText(tmp, Align.LEFT, 1, 60);
		g.drawText("Appuie sur [ESPACE] pour relancer", Align.LEFT, 1, 70);
	}
}