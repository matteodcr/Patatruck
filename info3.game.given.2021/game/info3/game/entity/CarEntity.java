package info3.game.entity;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.PositionF;
import info3.game.scene.CityScene;
import info3.game.scene.Scene;

public class CarEntity extends Entity {
	public Physics physics = new PhysicsClassic(3);
	boolean isTruck;
	// TODO Deplacer hitbox hardocdé et methode de collision (champ ou classe pr pos
	// bas a droite de l'entite

	public CarEntity(Scene parent, PositionF position, boolean isTruck) {
		super(parent, position);
		this.isTruck = isTruck;
		automaton = parentScene.setupAutomaton("Car");
		current_state = automaton.initial;
		category = AutCategory.A;
	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(Sprite.CAR_ENTITY, this.position.getX(), this.position.getY());
	}

	@Override
	public EntityType getType() {
		if (isTruck) {
			return EntityType.TRUCK;
		}
		return EntityType.CAR;
	}

	@Override
	public void tick(long elapsed) {
		super.tick(elapsed);
		this.position = this.position.add(physics.Shift(elapsed));
	}

	@Override
	public boolean pop(AutDirection direction) {
		// TODO transfert d'automates
		// penser à donner la physique aussi
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
		this.position = this.position.add(physics.bounce(m_direction.twoapart()));
		return true;
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

	// TODO Point de collision pr l'instant HARDCODE a l'entite CarEntity
	@Override
	public boolean cell(AutDirection direction, AutCategory category) {
		AutDirection newDirection = convertRelativToAbsolutedir(direction);
		switch (newDirection) {
		case N: {
			if (((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(0, -1))) == category
					|| ((CityScene) parentScene)
							.whatsTheCategoryOfTile(position.add(new PositionF(3, -1))) == category) {
				return true;
			}
			break;
		}
		case W: {
			if (((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(-1, 0))) == category
					|| ((CityScene) parentScene)
							.whatsTheCategoryOfTile(position.add(new PositionF(-1, 3))) == category) {
				return true;
			}
			break;
		}
		case E: {
			if (((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(4, 0))) == category
					|| ((CityScene) parentScene)
							.whatsTheCategoryOfTile(position.add(new PositionF(4, 3))) == category) {
				return true;
			}
			break;
		}
		case S: {
			if (((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(0, 4))) == category
					|| ((CityScene) parentScene)
							.whatsTheCategoryOfTile(position.add(new PositionF(3, 4))) == category) {
				return true;
			}
			break;
		}
		case H: {
			if (((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(0, 0))) == category
					|| ((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(3, 0))) == category
					|| ((CityScene) parentScene).whatsTheCategoryOfTile(position.add(new PositionF(0, 3))) == category
					|| ((CityScene) parentScene)
							.whatsTheCategoryOfTile(position.add(new PositionF(3, 3))) == category) {
				return true;

			}
			break;
		}
		default:
			return false;

		}
		return false;

	}

	public void toNoBreaksPhysics() {
		this.physics = new PhysicsNoBreaks(3, this.physics.getAccX(), this.physics.getAccY(), this.physics.getVelX(),
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
		carentity.isTruck = !carentity.isTruck;
		this.isTruck = !this.isTruck;
		if (this.isTruck) {
			((CityScene) this.parentScene).setCook(this);
		} else {
			((CityScene) this.parentScene).setCook(carentity);
		}
		Physics physics = this.physics;
		this.physics = carentity.physics;
		carentity.physics = physics;

	}

}
