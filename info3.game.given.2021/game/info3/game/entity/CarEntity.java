package info3.game.entity;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.PositionF;
import info3.game.scene.CityScene;
import info3.game.scene.Scene;

public class CarEntity extends Entity {

	boolean isTruck;
	boolean isPlayer;
	private boolean swapInThisTick;
	private Entity entityEncountered;
	public Physics physics = new PhysicsClassic(3);
	// TODO Deplacer hitbox hardocdé et methode de collision (champ ou classe pr pos
	// bas a droite de l'entite (sinon on garde comme ça si ttes les entites = 4x4)

	public CarEntity(Scene parent, PositionF position, boolean isTruck, boolean isPlayer) {
		super(parent, position);
		category = AutCategory.A;
		this.isTruck = isTruck;
		this.isPlayer = isPlayer;
		changeCategory();
		this.swapInThisTick = false;
	}
	
	public boolean canDeliver() {
		if (isPlayer) {
			float tileX = ((CityScene)parentScene).getDeliveryTile().position.getX();
			float tileY = ((CityScene)parentScene).getDeliveryTile().position.getY();
			float truckX = this.position.getX();
			float truckY = this.position.getY();
			
			return truckX > tileX-15 && truckX < tileX+15 && truckY > tileY-15 && truckY < tileY+15;
		}
		return false;
	}

	@Override
	public void render(Graphics g) {
		switch (this.m_direction) {
		case N:
			if (!isTruck) {
				g.drawSprite(Sprite.RED_CAR_N, 0, 0);
			} else if (isPlayer) {
				g.drawSprite(Sprite.YELLOW_CAR_N, 0, 0);
			} else {
				g.drawSprite(Sprite.BLUE_CAR_N, 0, 0);
			}
			break;
		case S:
			if (!isTruck) {
				g.drawSprite(Sprite.RED_CAR_S, 0, 0);
			} else if (isPlayer) {
				g.drawSprite(Sprite.YELLOW_CAR_S, 0, 0);
			} else {
				g.drawSprite(Sprite.BLUE_CAR_S, 0, 0);
			}
			break;
		case E:
			if (!isTruck) {
				g.drawSprite(Sprite.RED_CAR_E, 0, 0);
			} else if (isPlayer) {
				g.drawSprite(Sprite.YELLOW_CAR_E, 0, 0);
			} else {
				g.drawSprite(Sprite.BLUE_CAR_E, 0, 0);
			}
			break;
		case W:
			if (!isTruck) {
				g.drawSprite(Sprite.RED_CAR_W, 0, 0);
			} else if (isPlayer) {
				g.drawSprite(Sprite.YELLOW_CAR_W, 0, 0);
			} else {
				g.drawSprite(Sprite.BLUE_CAR_W, 0, 0);
			}
			break;
		default:
			break;

		}

	}

	@Override
	public EntityType getType() {
		if (isPlayer) {
			return EntityType.TRUCK;
		}
		return EntityType.CAR;
	}

	@Override
	public void tick(long elapsed) {
		if (((CityScene) parentScene).isTooFarFromVan(this))
			this.parentScene.removeEntity(this);
		else {
			super.tick(elapsed);
			this.position = this.position.add(physics.Shift(elapsed));

			finish = System.currentTimeMillis();
			timeElapsed = finish - start;

			if (timeElapsed >= 1000) {
				this.swapInThisTick = false;
			}
		}

	}

	@Override
	public boolean pop(AutDirection direction) {
		if (!swapInThisTick && isPlayer) {
			this.swap((CarEntity) entityEncountered);
			start = System.currentTimeMillis();
		}
		return true;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		AutDirection newDirection = convertRelativToAbsolutedir(direction);
		m_direction = newDirection;
		switch (newDirection) {
		case N:
		case W:
		case E:
		case S:
			physics.addForce(m_direction);
			return true;
		default:
			return false;
		}

	}

	public boolean move(AutDirection direction) {
		AutDirection newDirection = convertRelativToAbsolutedir(direction);
		m_direction = newDirection;
		switch (newDirection) {
		case N: {
			PositionF newPos = new PositionF(0, -1);
			this.position = position.add(newPos);
			return true;
		}
		case W: {
			PositionF newPos = new PositionF(-1, 0);
			this.position = position.add(newPos);
			return true;
		}
		case E: {
			PositionF newPos = new PositionF(1, 0);
			this.position = position.add(newPos);
			return true;
		}
		case S: {
			PositionF newPos = new PositionF(0, 1);
			this.position = position.add(newPos);
			return true;
		}
		default:
			return false;
		}
	}

	@Override
	public boolean gwait() {
		// TODO : modified to prevent car not controlled by player from doing POP
		return true;
	}

	@Override
	public boolean egg(AutDirection direction) {
		return false;
	}

	@Override
	public boolean hit(AutDirection direction) {
		this.position = this.position.add(physics.bounce(m_direction.twoapart()));
		return true;
	}

	@Override
	public boolean jump(AutDirection direction) {
		return false;
	}

	@Override
	public boolean explode() {
		return false;
	}

	@Override
	public boolean pick(AutDirection direction) {
		return false;
	}

	@Override
	public boolean power() {
		return false;
	}

	@Override
	public boolean protect(AutDirection direction) {
		return false;
	}

	@Override
	public boolean store() {
		return false;
	}

	@Override
	public boolean turn(AutDirection direction) {
		return false;
	}

	@Override
	public boolean gthrow(AutDirection direction) {
		return false;
	}

	@Override
	public boolean myDir(AutDirection direction) {
		return false;
	}

	@Override
	public boolean closest(AutCategory category, AutDirection direction) {
		return false;
	}

	@Override
	public boolean gotPower() {
		return false;
	}

	@Override
	public boolean gotStuff() {
		return false;
	}

	// TODO Point de collision pr l'instant HARDCODE a l'entite CarEntity
	@Override
	public boolean cell(AutDirection direction, AutCategory category) {
		AutDirection newDirection = convertRelativToAbsolutedir(direction);
		for (Entity entity : parentScene.entityList) {
			switch (newDirection) {
			case N: {
				if (entity.catAtThisPos(position.add(new PositionF(0, -1))) == category
						|| entity.catAtThisPos(position.add(new PositionF(3, -1))) == category) {
					entityEncountered = entity;
					return true;
				}
				break;
			}
			case W: {
				if (entity.catAtThisPos(position.add(new PositionF(-1, 0))) == category
						|| entity.catAtThisPos(position.add(new PositionF(-1, 3))) == category) {
					entityEncountered = entity;
					return true;
				}
				break;
			}
			case E: {
				if (entity.catAtThisPos(position.add(new PositionF(4, 0))) == category
						|| entity.catAtThisPos(position.add(new PositionF(4, 3))) == category) {
					entityEncountered = entity;
					return true;
				}
				break;
			}
			case S: {
				if (entity.catAtThisPos(position.add(new PositionF(0, 4))) == category
						|| entity.catAtThisPos(position.add(new PositionF(3, 4))) == category) {
					entityEncountered = entity;
					return true;
				}
				break;
			}
			case H: {
				if (entity.catAtThisPos(position.add(new PositionF(0, 0))) == category
						|| entity.catAtThisPos(position.add(new PositionF(3, 0))) == category
						|| entity.catAtThisPos(position.add(new PositionF(0, 3))) == category
						|| entity.catAtThisPos(position.add(new PositionF(3, 3))) == category) {
					entityEncountered = entity;
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
			if (((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(0, -1)), this) == category
					|| ((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(3, -1)),
							this) == category) {
				return true;
			}
			break;
		}
		case W: {
			if (((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(-1, 0)), this) == category
					|| ((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(-1, 3)),
							this) == category) {
				return true;
			}
			break;
		}
		case E: {
			if (((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(4, 0)), this) == category
					|| ((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(4, 3)),
							this) == category) {
				return true;
			}
			break;
		}
		case S: {
			if (((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(0, 4)), this) == category
					|| ((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(3, 4)),
							this) == category) {
				return true;
			}
			break;
		}
		case H: {
			if (((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(0, 0)), this) == category
					|| ((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(3, 0)),
							this) == category
					|| ((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(0, 3)),
							this) == category
					|| ((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(3, 3)),
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

	public void changeCategory() {
		if (!isPlayer)
			category = AutCategory.A;
		else
			category = AutCategory.AROBASE;
	}

	public void toNoBreaksPhysics() {
		this.physics = new PhysicsNoBrakes(3, this.physics.getAccX(), this.physics.getAccY(), this.physics.getVelX(),
				this.physics.getVelY(), this.physics.getMaxVel(), this.physics.getAvgVelBuff(),
				this.physics.getAvgVel(), this.physics.getTimerVel(), this.physics.getTimerMaxVel());
	}

	public void toClassicPhysics() {
		this.physics = new PhysicsClassic(3, this.physics.getAccX(), this.physics.getAccY(), this.physics.getVelX(),
				this.physics.getVelY(), this.physics.getMaxVel(), this.physics.getAvgVelBuff(),
				this.physics.getAvgVel(), this.physics.getTimerVel(), this.physics.getTimerMaxVel());
	}

	public void toSmokePhysics() {
		this.physics = new PhysicsSmoke(3, this.physics.getAccX(), this.physics.getAccY(), this.physics.getVelX(),
				this.physics.getVelY(), this.physics.getMaxVel(), this.physics.getAvgVelBuff(),
				this.physics.getAvgVel(), this.physics.getTimerVel(), this.physics.getTimerMaxVel());
	}

	public void swap(CarEntity carentity) {
		carentity.isPlayer = !carentity.isPlayer;
		this.isPlayer = !this.isPlayer;
		this.changeCategory();
		carentity.changeCategory();

		((CityScene) this.parentScene).setCook(carentity);

		Physics physics = this.physics;
		this.physics = carentity.physics;
		carentity.physics = physics;

		carentity.swapInThisTick = true;
		this.swapInThisTick = true;
		carentity.physics.bounce(carentity.m_direction.twoapart());
		this.physics.bounce(this.m_direction.twoapart());

		this.start = System.currentTimeMillis();
		carentity.start = System.currentTimeMillis();

	}

}
