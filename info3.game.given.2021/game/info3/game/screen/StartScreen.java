package info3.game.screen;

import info3.game.Game;
import info3.game.graphics.Graphics;
import info3.game.graphics.Graphics.Align;
import info3.game.graphics.Sprite;

public class StartScreen extends Screen {

	public StartScreen(Game game) {
		super(game);
	}

	boolean keyPressed = false;
	int scrollTop = 0;

	@Override
	public void tick(long elapsed) {
		boolean upPressed = game.m_listener.isUp("UP");
		boolean downPressed = game.m_listener.isUp("DOWN");
		boolean enterPressed = game.m_listener.isUp("ENTER");

		if (upPressed && !keyPressed) {
			if (scrollTop != 0)
				scrollTop--;
		}

		if (downPressed && !keyPressed) {
			if (scrollTop < 3)
				scrollTop++;
		}

		if (enterPressed) {
			switch (scrollTop) {
			case 0:
				changeScreen(new GameScreen(game));
				break;
			case 1:

				changeScreen(new AutomatonSelectionScreen(game));
				break;
			case 2:
				changeScreen(new CreditScreen(game));
				break;
			case 3:
				System.exit(0);
				break;
			default:
				break;

			}

		}

		keyPressed = upPressed || downPressed || enterPressed;
	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(Sprite.AS_BACKDROP, 0, 0);
		g.drawSprite(Sprite.AS_LOGO, 0, 0);
		g.drawSprite(Sprite.AS_BOX, 100, 47);
		g.drawSprite(Sprite.AS_BOX, 100, 62);
		g.drawSprite(Sprite.AS_BOX, 100, 77);
		g.drawSprite(Sprite.AS_BOX, 100, 92);
		g.drawSprite(Sprite.AS_RIGHT, 86, 47 + scrollTop * 15);

		g.drawText("JOUER", Align.LEFT, 103, 50);
		g.drawText("CHOIX AUTOMATES", Align.LEFT, 103, 65);
		g.drawText("CRÉDITS", Align.LEFT, 103, 80);
		g.drawText("QUITTER", Align.LEFT, 103, 95);
	}
}