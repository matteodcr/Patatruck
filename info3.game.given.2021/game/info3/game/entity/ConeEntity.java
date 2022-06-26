package info3.game.entity;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.PositionF;
import info3.game.scene.CityScene;
import info3.game.scene.Scene;

public class ConeEntity extends Entity {

	public Physics physics = new PhysicsClassic(5);
	private Sprite currentSprite = Sprite.CONE;
	private boolean onWizz = false;
	private int timerWizz = 20;

	public ConeEntity(Scene parent, PositionF pos) {
		super(parent, pos);
	}

	@Override
	public EntityType getType() {
		return EntityType.CONE;
	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(currentSprite, 0, 0);
	}

	@Override
	public void tick(long elapsed) {
		super.tick(elapsed);
		this.position = this.position.add(physics.shift());
		if (onWizz) {
			timerWizz--;
		}
		if (timerWizz == 0) {
			this.explode();
		}
	}

	@Override
	public boolean pop(AutDirection direction) {

		switch (direction) {
		case N:
		case W:
		case E:
		case S:
			physics.addForce(direction);
			wizz(direction);
			break;
		default:
			return false;
		}
		return true;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		this.currentSprite = Sprite.CONE_WIZZ;
		onWizz = true;
		return true;
	}

	@Override
	public boolean egg(AutDirection direction) {
		if (parentScene.entityList.size() <= Scene.MAXIMUM_ENTITIES) {
			Entity newEntity = null;
			newEntity = new ConeEntity(this.parentScene, position);
			return this.parentScene.addEntity(newEntity);
		}
		return false;
	}

	@Override
	public boolean hit(AutDirection direction) {
		System.out.println("bounce");
		this.position = this.position.add(physics.bounce());
		return true;
	}

	@Override
	public boolean cell(AutDirection direction, AutCategory category) {
		AutDirection newDirection = convertRelativToAbsolutedir(direction);
		// Les entités qui bougent
		for (Entity entity : parentScene.entityList) {
			switch (newDirection) {
			case N: {
				if (entity.position.add(new PositionF(0, 1)).distance(position) < 2 && entity.category == category) {
					return true;
				}
				break;
			}
			case W: {
				if (entity.position.add(new PositionF(1, 0)).distance(position) < 2 && entity.category == category) {
					return true;
				}
				break;
			}
			case E: {
				if (entity.position.add(new PositionF(-1, 0)).distance(position) < 2 && entity.category == category) {
					return true;
				}
				break;
			}
			case S: {
				if (entity.position.add(new PositionF(0, -1)).distance(position) < 2 && entity.category == category) {
					return true;
				}
				break;
			}
			case H: {
				if (entity.position.distance(position) < 1 && entity.category == category) {
					return true;
				}
				break;
			}
			default:
				return false;
			}
		}
		switch (newDirection) {
		case N: {
			if (((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(1, -1)), this) == category
					|| ((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(2, -1)),
							this) == category) {
				return true;
			}
			break;
		}
		case W: {
			if (((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(-1, 1)), this) == category
					|| ((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(-1, 2)),
							this) == category) {
				return true;
			}
			break;
		}
		case E: {
			if (((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(3, 1)), this) == category
					|| ((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(3, 2)),
							this) == category) {
				return true;
			}
			break;
		}
		case S: {
			if (((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(1, 3)), this) == category
					|| ((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(2, 3)),
							this) == category) {
				return true;
			}
			break;
		}
		case H: {
			if (((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(1, 1)), this) == category
					|| ((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(1, 2)),
							this) == category
					|| ((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(2, 1)),
							this) == category
					|| ((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(2, 2)),
							this) == category) {
				return true;

			}
			break;
		}
		default:
			return false;

		}
		return false;

	}

	@Override
	public AutCategory catAtThisPos(PositionF pos) {
		float posX = position.getX();
		float posY = position.getY();
		if (posX + 1 <= pos.getX() && pos.getX() <= posX + 2 && posY + 1 <= pos.getY() && pos.getY() <= posY + 2)
			return category;
		return null;
	}
}
