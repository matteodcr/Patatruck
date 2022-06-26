package info3.game.entity;

import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.PositionF;
import info3.game.scene.CityScene;
import info3.game.scene.KitchenScene;
import info3.game.scene.Scene;
import info3.game.screen.GameScreen;

public class SpeedBumpEntity extends Entity {

	private CityTile parentTile;
	int startPosX;
	int startPosY;
	int endPosX;
	int endPosY;

	SpeedBumpEntity(Scene parent, PositionF pos, CityTile tile) {
		super(parent, pos);
		category = AutCategory.D;
		parentTile = tile;
		parentScene.addEntity(this);
		start = -1;
		timeElapsed = 0;
	}

	@Override
	public EntityType getType() {
		return EntityType.SPEEDBUMP;
	}

	@Override
	public void tick(long elapsed) {
		super.tick(elapsed);

	}

	@Override
	public boolean pop(AutDirection direction) {
		CarEntity cookCar = ((CityScene) parentScene).getCook();
		if (cookCar.physics.getVelocity() >= 40 && cookCar.shuffleCooldown == 0) {
			KitchenScene kitchenScene = ((KitchenScene) ((GameScreen) this.parentScene.m_game.getScreen())
					.getKitchenScene());
			kitchenScene.shuffle();
			cookCar.shuffleCooldown = 100;
			return true;
		}
		return false;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		// C'est un ralentisseur y a pas 36 features
		return true;
	}

	public boolean cell(AutDirection direction, AutCategory category) {
		boolean top = parentTile.genTile.speedbumpTop;
		boolean left = parentTile.genTile.speedbumpLeft;
		AutDirection newDirection = convertRelativToAbsolutedir(direction);
		for (Entity entity : parentScene.entityList) {
			do {
				if (top) {
					startPosX = 9;
					startPosY = 0;
					endPosX = 19;
					endPosY = 8;
					top = false;
				} else {
					startPosX = 0;
					startPosY = 9;
					endPosX = 8;
					endPosY = 19;
					left = false;
				}
				switch (newDirection) {
				case H: {
					if (this.carInsideThisPos(entity) == category) {
						return true;
					}
					break;
				}
				default:
					super.cell(direction, category);
					break;
				}
			} while (left || top);
			top = parentTile.genTile.speedbumpTop;
			left = parentTile.genTile.speedbumpLeft;
		}
		return false;

	}

	@Override
	public boolean egg(AutDirection direction) {
		if (parentScene.entityList.size() <= Scene.MAXIMUM_ENTITIES) {
			Entity newEntity = null;
			int gX = parentTile.gridX;
			int gY = parentTile.gridY;
			switch (this.convertRelativToAbsolutedir(direction)) {
			case N:
				newEntity = new SpeedBumpEntity(this.parentScene, new PositionF(gX, gY - 1),
						(CityTile) parentScene.getTileAt(gX, gY - 1));
				break;
			case E:
				newEntity = new SpeedBumpEntity(this.parentScene, new PositionF(gX + 1, gY),
						(CityTile) parentScene.getTileAt(gX + 1, gY));
				break;
			case S:
				newEntity = new SpeedBumpEntity(this.parentScene, new PositionF(gX, gY + 1),
						(CityTile) parentScene.getTileAt(gX, gY + 1));
				break;
			case W:
				newEntity = new SpeedBumpEntity(this.parentScene, new PositionF(gX - 1, gY),
						(CityTile) parentScene.getTileAt(gX - 1, gY));
				break;
			default:
				break;
			}
			if (newEntity != null)
				return this.parentScene.addEntity(newEntity);
		}
		return false;
	}

	/*
	 * Fct qui renvoit la category de l'entite si la position donnée correspond à la
	 * sienne
	 */
	public AutCategory catAtThisPos(PositionF pos) {
		float posX = position.getX();
		float posY = position.getY();
		if (posX <= pos.getX() && pos.getX() <= posX + 19 && posY <= pos.getY() && pos.getY() <= posY + 19)
			return category;
		return null;
	}

	/*
	 * fct HARDCODE pour les comparaisons avec les voitures
	 */
	public AutCategory carInsideThisPos(Entity entity) {
		float posX = entity.getPosition().getX();
		float posY = entity.getPosition().getY();
		float sbStartPosX = this.position.getX() + startPosX;
		float sbStartPosY = this.position.getY() + startPosY;
		float sbEndPosX = this.position.getX() + endPosX;
		float sbEndPosY = this.position.getY() + endPosY;
		if ((sbStartPosX <= posX && posX <= sbEndPosX && sbStartPosY <= posY && posY <= sbEndPosY)
				|| (sbStartPosX <= posX + 3 && posX + 3 <= sbEndPosX && sbStartPosY <= posY + 3
						&& posY + 3 <= sbEndPosY))
			return entity.category;
		return null;
	}

}