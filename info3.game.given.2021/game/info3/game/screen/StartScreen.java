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
		boolean zPressed = game.m_listener.isUp("Z");
		boolean sPressed = game.m_listener.isUp("S");
		boolean enterPressed = game.m_listener.isUp("ENTER");
		boolean spacePressed = game.m_listener.isUp("SPACE");

		if ((upPressed || zPressed) && !keyPressed) {
			if (scrollTop != 0)
				scrollTop--;
		}

		if ((downPressed || sPressed) && !keyPressed) {
			if (scrollTop < 4)
				scrollTop++;
		}

		if (enterPressed || spacePressed) {
			switch (scrollTop) {
			case 0:
				changeScreen(new GameScreen(game));
				break;
			case 1:
				changeScreen(new AutomatonSelectionScreen(game));
				break;
			case 2:
				changeScreen(new ControlsScreen(game));
				break;
			case 3:
				changeScreen(new CreditScreen(game));
				break;
			case 4:
				System.exit(0);
				break;
			default:
				break;

			}

		}

		keyPressed = upPressed || downPressed || enterPressed || zPressed || sPressed || spacePressed;
	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(Sprite.AS_BACKDROP, 0, 0);
		g.drawSprite(Sprite.AS_LOGO, 0, 0);
		g.drawSprite(Sprite.AS_BOX, 100, 32);
		g.drawSprite(Sprite.AS_BOX, 100, 47);
		g.drawSprite(Sprite.AS_BOX, 100, 62);
		g.drawSprite(Sprite.AS_BOX, 100, 77);
		g.drawSprite(Sprite.AS_BOX, 100, 92);
		g.drawSprite(Sprite.AS_RIGHT, 86, 32 + scrollTop * 15);

		g.drawText("JOUER", Align.LEFT, 103, 35);
		g.drawText("CHOIX AUTOMATES", Align.LEFT, 103, 50);
		g.drawText("CONTRÔLES", Align.LEFT, 103, 65);
		g.drawText("CRÉDITS", Align.LEFT, 103, 80);
		g.drawText("QUITTER", Align.LEFT, 103, 95);
		g.drawText("Meilleur score : " + game.highScore, Align.LEFT, 103, 115);
	}
}