package info3.game.entity;

import info3.game.position.AutDirection;
import info3.game.position.PositionF;

public class PhysicsEntity {

	float maxShiftX = 2, maxShiftY = 2; // en pixels
	float accelerationX = 0, accelerationY = 0;
	float velocityX = 0, velocityY = 0;
	float power, airbreak = 2;

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
	public void addAcceleration(AutDirection absoluteDir) {
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
			break;
		}
	}

	public PositionF Shift(long elapsed) {
		System.out.println(accelerationX + "\t" + velocityX);

		accelerationX = (float) (accelerationX / Math.pow(airbreak, elapsed));
		if (Math.abs(accelerationX) < 0.01)
			accelerationX = 0;
		accelerationY = (float) (accelerationY / Math.pow(airbreak, elapsed));
		if (Math.abs(accelerationY) < 0.01)
			accelerationY = 0;
		velocityX += elapsed * accelerationX;
		velocityX /= airbreak * elapsed;
		if (Math.abs(velocityX) < 0.01)
			velocityX = 0;
		velocityY += elapsed * accelerationY;
		velocityY /= airbreak * elapsed;
		if (Math.abs(velocityY) < 0.01)
			velocityY = 0;

		float shiftX = velocityX * elapsed, shiftY = velocityY * elapsed;
		if (shiftX > maxShiftX)
			shiftX = maxShiftX;
		else if (shiftX < -maxShiftX)
			shiftX = -maxShiftX;
		if (shiftY > maxShiftY)
			shiftY = maxShiftY;
		else if (shiftY < -maxShiftY)
			shiftY = -maxShiftY;

		return new PositionF(shiftX, shiftY);
	}

}
