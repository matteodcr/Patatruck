package info3.game.screen;

import info3.game.Game;
import info3.game.graphics.Graphics;
import info3.game.graphics.Graphics.Align;
import info3.game.graphics.Sprite;

public class ControlsScreen extends Screen {
	public ControlsScreen(Game game) {
		super(game);
	}

	boolean keyPressed = false;

	public void tick(long elapsed) {
		boolean escapePressed = game.m_listener.isUp("ESCAPE");

		if (escapePressed) {
			changeScreen(new StartScreen(game));
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(Sprite.AS_BACKDROP, 0, 0);
		g.drawSprite(Sprite.AS_LOGO, 0, 0);

		g.drawText("Avec les automates fournis par défaut", Align.LEFT, 95, 5);
		g.drawText("Z, Q, S, D : Directions J1", Align.LEFT, 103, 25);
		g.drawText("SPACE : Action J1", Align.LEFT, 103, 45);
		g.drawText("Flèches : Directions J2", Align.LEFT, 103, 65);
		g.drawText("ENTER : Action J2", Align.LEFT, 103, 85);
		g.drawText("ESC : Revenir en arr. / Aff. recettes", Align.LEFT, 103, 105);
	}

}
