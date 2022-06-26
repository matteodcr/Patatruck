package info3.game.entity;

import info3.game.content.Sauce;
import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutDirection;
import info3.game.scene.Scene;

public class SauceTableTile extends KitchenTile {
	final Sauce sauce;
	private boolean onPop = false;
	private int timerPop = 0;

	public SauceTableTile(Scene parent, int gridX, int gridY, AutDirection d, Sauce sauce) {
		super(parent, gridX, gridY, sauce == Sauce.KETCHUP ? Sprite.KETCHUP : Sprite.MAYONNAISE, d);
		this.sauce = sauce;
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
			int maxTimerPop = 20;
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
		if (eInteracting instanceof CookEntity) {
			if (((CookEntity) eInteracting).assembly.getItems().size() == 0) {
				return false;
			} else {
				parentScene.game.playSound("sauce");
				((CookEntity) eInteracting).assembly.getItems().get(0).setSauce(sauce);
				return true;
			}
		}
		return false;
	}

	@Override
	public void render(Graphics g) {
		if (onPop) {
			if (this.defaultSprite == Sprite.MAYONNAISE)
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
			Entity newEntity;
			newEntity = new SauceTableTile(this.parentScene, this.gridX, this.gridY, this.direction, sauce);
			return this.parentScene.addEntity(newEntity);
		}
		return false;
	}

	@Override
	public boolean gotStuff() {
		return sauce != null;
	}
}
