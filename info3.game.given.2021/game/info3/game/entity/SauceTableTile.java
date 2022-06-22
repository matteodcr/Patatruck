package info3.game.entity;

import info3.game.content.Sauce;
import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.scene.Scene;

public class SauceTableTile extends KitchenTile {
	Sauce sauce;
	static final Sprite mayo = Sprite.MAYONNAISE, ketchup = Sprite.KETCHUP;

	public SauceTableTile(Scene parent, int gridX, int gridY, AutDirection d, Sauce sauce) {
		super(parent, gridX, gridY, null, d);
		this.sauce = sauce;
		if (sauce == Sauce.KETCHUP) {
			defaultSprite = ketchup;
		} else {
			defaultSprite = mayo;
		}
	}

	@Override
	public EntityType getType() {
		return EntityType.TILE_SAUCE;
	}

	@Override
	public boolean pop(AutDirection direction) {
		return true;
	}

	@Override
	public boolean wizz(AutDirection direction) {// mettre la sauce
		Entity eInteracting = selectEntityToInteractWith();

		if (eInteracting != null) {
			if (((CookEntity) eInteracting).m_assembly.getItems().size() == 0) {
				return false;
			} else {
				((CookEntity) eInteracting).m_assembly.getItems().get(0).setSauce(sauce);
				return true;
			}
		}
		return false;
	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(Sprite.SAUCE_TABLE_TILE, 0, 0);
		g.drawSprite(defaultSprite, 0, 0);
	}

	@Override
	public boolean gwait() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean egg(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hit(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean jump(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean explode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pick(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean power() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean protect(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean store() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean turn(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gthrow(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean myDir(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closest(AutCategory category, AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gotPower() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gotStuff() {
		// TODO Auto-generated method stub
		return false;
	}

}
