package info3.game.entity;

import info3.game.content.Sauce;
import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutDirection;
import info3.game.scene.Scene;

public class SauceTableTile extends KitchenTile {
	Sauce sauce;
	static final Sprite MAYO = Sprite.MAYONNAISE, KETCHUP = Sprite.KETCHUP;
	private boolean onPop = false;
	private int timerPop = 0, maxTimerPop = 20;

	public SauceTableTile(Scene parent, int gridX, int gridY, AutDirection d, Sauce sauce) {
		super(parent, gridX, gridY, null, d);
		this.sauce = sauce;
		if (sauce == Sauce.KETCHUP) {
			defaultSprite = KETCHUP;
		} else {
			defaultSprite = MAYO;
		}
	}

	@Override
	public EntityType getType() {
		return EntityType.TILE_SAUCE;
	}

	@Override
	public void tick(long elapsed) {
		super.tick(elapsed);
		if (onPop) {
			timerPop++;
			if (timerPop == maxTimerPop) {
				timerPop = 0;
				onPop = false;
			}
		}
	}

	@Override
	public boolean pop(AutDirection direction) {
		onPop = true;
		return true;
	}

	@Override
	public boolean wizz(AutDirection direction) {// mettre la sauce
		Entity eInteracting = selectEntityToInteractWith();
		if (eInteracting != null && eInteracting instanceof CookEntity) {
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
		if (onPop) {
			if (this.defaultSprite == MAYO)
				g.drawSprite(Sprite.SAUCE_TABLE_TILE_POP_MAYO, 0, 0);
			else
				g.drawSprite(Sprite.SAUCE_TABLE_TILE_POP_KETCHUP, 0, 0);
		} else
			g.drawSprite(Sprite.SAUCE_TABLE_TILE, 0, 0);
		g.drawSprite(defaultSprite, 0, 0);
	}

	@Override
	public boolean egg(AutDirection direction) {
		if (parentScene.entityList.size() <= Scene.MAXIMUM_ENTITIES) {
			Entity newEntity = null;
			newEntity = new SauceTableTile(this.parentScene, this.gridX, this.gridY, this.m_direction, sauce);
			return this.parentScene.addEntity(newEntity);
		}
		return false;
	}

	@Override
	public boolean gotStuff() {
		return sauce != null;
	}
}
