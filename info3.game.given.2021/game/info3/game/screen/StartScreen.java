package info3.game.screen;

import info3.game.Game;
import info3.game.graphics.Graphics;
import info3.game.graphics.Graphics.Align;
import info3.game.graphics.Sprite;

public class StartScreen extends Screen {

	long score;

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
			if (scrollTop < 2)
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
				System.out.println("credits");// TODO rajouter scene credits
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
		g.drawSprite(Sprite.AS_BOX, 97, 47);
		g.drawSprite(Sprite.AS_BOX, 97, 62);
		g.drawSprite(Sprite.AS_BOX, 97, 77);

		String tmp = "START ";
		g.drawText(tmp, Align.LEFT, 100, 50);
		tmp = "CHOIX AUTOMATES";
		g.drawText(tmp, Align.LEFT, 100, 65);
		g.drawText("CREDIT", Align.LEFT, 100, 80);
	}
}