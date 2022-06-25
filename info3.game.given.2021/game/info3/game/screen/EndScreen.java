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
			if (scrollTop < 1)
				scrollTop++;
		}

		if (enterPressed || spacePressed) {
			switch (scrollTop) {
			case 0:
				changeScreen(new StartScreen(game));
				break;
			case 1:
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

		g.drawText("Dommage, c'est perdu...", Align.LEFT, 98, 20);
		g.drawText("Votre score : " + score, Align.LEFT, 98, 32);
		g.drawText("Le meilleur score : " + game.highScore, Align.LEFT, 98, 44);

		g.drawSprite(Sprite.AS_BOX, 100, 62);
		g.drawSprite(Sprite.AS_BOX, 100, 77);
		g.drawSprite(Sprite.AS_RIGHT, 86, 62 + scrollTop * 15);
		g.drawText("REVENIR À L'ACCUEIL", Align.LEFT, 103, 65);
		g.drawText("QUITTER", Align.LEFT, 103, 80);

		g.drawSprite(Sprite.COOK_END, 20, 35);

	}
}