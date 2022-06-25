package info3.game.screen;

import info3.game.Game;
import info3.game.graphics.Graphics;
import info3.game.graphics.Graphics.Align;
import info3.game.graphics.Sprite;

public class CreditScreen extends Screen {
	public CreditScreen(Game game) {
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

		g.drawText("CRÉDITS", Align.LEFT, 98, 5);
		g.drawText("Edgar Onghena", Align.LEFT, 103, 80);
		g.drawText("Elise Dupont", Align.LEFT, 103, 95);
		g.drawText("Vincent Ducros", Align.LEFT, 103, 110);
		g.drawText("Mattéo Decorsaire", Align.LEFT, 103, 125);
		g.drawText("Paul Grandhomme", Align.LEFT, 103, 20);
		g.drawText("Loric Gallier", Align.LEFT, 103, 35);
		g.drawText("Mathis Grange", Align.LEFT, 103, 50);
		g.drawText("Aurélien Coste", Align.LEFT, 103, 65);

		g.drawSprite(Sprite.COOK_END, 20, 35);
	}

}
