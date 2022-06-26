package info3.game.entity;

import info3.game.content.Assembly;
import info3.game.content.Item;
import info3.game.graphics.Graphics;
import info3.game.graphics.RenderUtils;
import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.PositionF;
import info3.game.scene.Scene;

public class CookEntity extends Entity {
	private boolean usePopSprite = false;
	private int timerPopSprite = 0;
	private final int maxTimerPopSprite = 6;
	final Assembly assembly;

	public Item item;

	public CookEntity(Scene parent, PositionF position) {
		super(parent, position);
		category = AutCategory.AROBASE;
		assembly = new Assembly();
		this.timerToWait = 200;
	}

	@Override
	public EntityType getType() {
		return EntityType.COOK;
	}

	@Override
	public void tick(long elapsed) {
		super.tick(elapsed);
		if (usePopSprite) {
			if (timerPopSprite > 0) {
				timerPopSprite--;
			} else {
				timerPopSprite = maxTimerPopSprite;
				usePopSprite = false;
			}
		}
	}

	@Override
	public void render(Graphics g) {

		if (direction == AutDirection.N) {
			if (usePopSprite)
				g.drawSprite(Sprite.PLAYER_POP_N, 0, 0);
			else
				g.drawSprite(Sprite.PLAYER_KITCHEN_N, 0, 0);

		} else if (direction == AutDirection.E) {
			if (usePopSprite)
				g.drawSprite(Sprite.PLAYER_POP_E, 0, 0);
			else
				g.drawSprite(Sprite.PLAYER_KITCHEN_E, 0, 0);

		} else if (direction == AutDirection.W) {
			if (usePopSprite)
				g.drawSprite(Sprite.PLAYER_POP_W, 0, 0);
			else
				g.drawSprite(Sprite.PLAYER_KITCHEN_W, 0, 0);

		} else {
			if (usePopSprite)
				g.drawSprite(Sprite.PLAYER_POP_S, 0, 0);
			else
				g.drawSprite(Sprite.PLAYER_KITCHEN_S, 0, 0);

		}
		if (assembly.getItems().size() >= 1) {
			RenderUtils.drawItem(g, assembly.getItems().get(0), 0, 0);
		}
	}

	@Override
	public boolean pop(AutDirection direction) {
		timerPopSprite = maxTimerPopSprite;
		usePopSprite = true;
		return true;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		parentScene.game.playSound("footstep3");
		move(direction);
		return false;
	}

	@Override
	public boolean egg(AutDirection direction) {
		if (parentScene.entityList.size() <= Scene.MAXIMUM_ENTITIES) {
			Entity newEntity;
			newEntity = new CookEntity(this.parentScene, position);
			return this.parentScene.addEntity(newEntity);
		}
		return false;
	}

	@Override
	public boolean gotStuff() {
		return (this.item != null || this.assembly != null);
	}

	@Override
	public boolean cell(AutDirection direction, AutCategory category) {
		boolean c = super.cell(direction, category);
		this.direction = direction;
		return c;
	}
}
