package info3.game.graphics;

import info3.game.content.Item;
import info3.game.content.Sauce;

public class RenderUtils {

	static public void drawItem(Graphics g, Item i, float x, float y) {
		g.drawSprite(i.getType().getSprite(), x, y);
		if (i.getSauce() == Sauce.KETCHUP) {
			g.drawSprite(Sprite.INDIC_KETCHUP_, x, y);
		}
		if (i.getSauce() == Sauce.MAYO) {
			g.drawSprite(Sprite.INDIC_MAYO_, x, y);

		}
		if (i.getSauce() == Sauce.KETCHUP_MAYO) {
			g.drawSprite(Sprite.KETCHUP_MAYO_INDIC_, x, y);
		}

	}
}
