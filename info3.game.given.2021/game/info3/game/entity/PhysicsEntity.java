package info3.game.entity;

import info3.game.position.AutDirection;
import info3.game.position.PositionF;

public class PhysicsEntity {

	float maxAccelerationX = 2, maxAccelerationY = 2; // en pixels
	float accelerationX = 0, accelerationY = 0;
	float velocityX = 0, velocityY = 0;
	float power, airbreak = 10;

	/**
	 * 
	 * @param power will act on how fast it will go
	 */
	public PhysicsEntity(float power) {
		this.power = power;
	}

	/**
	 * 
	 * @param absoluteDir
	 * @param elapsed     time in ms
	 * @return the shifted position to add to the current position of the entity
	 */
	PositionF addAcceleration(AutDirection absoluteDir, long elapsed) {
		PositionF shift = new PositionF(0, 0);
		switch (absoluteDir) {
		case N:
			accelerationY -= power;
			break;
		case E:
			accelerationX += power;
			break;
		case S:
			accelerationY += power;
			break;
		case W:
			accelerationX -= power;
			break;
		default:
			return null;
		}
		return shift;
	}

}
