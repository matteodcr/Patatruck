package info3.game.content;

import info3.game.graphics.Sprite;

public enum Sauce {
	KETCHUP(Sprite.KETCHUP), MAYO(Sprite.MAYONNAISE), KETCHUP_MAYO(Sprite.KETCHUP_MAYO);

	Sauce(Sprite sprite) {
		this.sprite = sprite;
	}

	final Sprite sprite;

	public Sprite getSprite() {
		return sprite;
	}
}
