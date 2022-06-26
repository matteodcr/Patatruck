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

		finish = System.currentTimeMillis();
		if (start != -1)
			timeElapsed = finish - start;

		if (timeElapsed >= 2000) {
			((CityScene) parentScene).getCook().shuffleOnCooldown = false;
			start = -1;
			timeElapsed = 0;

		}

	}

	@Override
	public boolean pop(AutDirection direction) {
		CarEntity cookCar = ((CityScene) parentScene).getCook();
		if (cookCar.physics.getVelocity() >= 40 && !cookCar.shuffleOnCooldown) {
			KitchenScene kitchenScene = ((KitchenScene) ((GameScreen) this.parentScene.m_game.getScreen())
					.getKitchenScene());
			kitchenScene.shuffle();
			cookCar.shuffleOnCooldown = true;
			start = System.currentTimeMillis();
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
				int endPosX, endPosY;
				if (top) {
					endPosX = 10;
					endPosY = 8;
					top = false;
				} else {
					endPosX = 8;
					endPosY = 10;
					left = false;
				}
				switch (newDirection) {
				case N: {
					if (entity.catAtThisPos(position.add(new PositionF(0, -1))) == category
							|| entity.catAtThisPos(position.add(new PositionF(endPosX, -1))) == category) {
						return true;
					}
					break;
				}
				case W: {
					if (entity.catAtThisPos(position.add(new PositionF(-1, 0))) == category
							|| entity.catAtThisPos(position.add(new PositionF(-1, endPosY))) == category) {
						return true;
					}
					break;
				}
				case E: {
					if (entity.catAtThisPos(position.add(new PositionF(endPosX + 1, 0))) == category
							|| entity.catAtThisPos(position.add(new PositionF(endPosX + 1, endPosY))) == category) {
						return true;
					}
					break;
				}
				case S: {
					if (entity.catAtThisPos(position.add(new PositionF(0, endPosY + 1))) == category
							|| entity.catAtThisPos(position.add(new PositionF(endPosX, endPosY + 1))) == category) {
						return true;
					}
					break;
				}
				case H: {
					if (entity.catAtThisPos(position.add(new PositionF(0, 0))) == category
							|| entity.catAtThisPos(position.add(new PositionF(endPosX, 0))) == category
							|| entity.catAtThisPos(position.add(new PositionF(0, endPosY))) == category
							|| entity.catAtThisPos(position.add(new PositionF(endPosX, endPosY))) == category) {
						return true;
					}
					break;
				}
				default:
					break;
				}
			} while (left || top);
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

}