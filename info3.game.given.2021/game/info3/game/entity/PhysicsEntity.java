package info3.game.entity;

import info3.game.position.AutDirection;
import info3.game.position.PositionF;

public class PhysicsEntity {

	double maxShiftX = 2, maxShiftY = 2; // en pixels
	double accelerationX = 0, accelerationY = 0;
	double velocityX = 0, velocityY = 0;
	double power, airbreak = 2;
	double maxVelocity = 1.5;// max velocity for turning otherwise do mess
	double previousElapsed[] = { 0, 0 };
	int velocityToPrint = 0, refreshTimerVelocity = 5, timerVelocity = 0;

	/**
	 * Average power is 4
	 * 
	 * @param power  will act on how fast it will go
	 * @param effect -1=slower 0=normal 1=faster
	 */
	public PhysicsEntity(double power, int effect) {
		switch (effect) {
		case -1:
			this.power = power / 2;
			break;
		case 1:
			this.power = power * 2;
			break;
		default:
			this.power = power;
		}
	}

	/**
	 * 
	 * @param absoluteDir
	 * @param elapsed     time in ms
	 * @return if the truck is going too fast to turn
	 */
	public boolean addAcceleration(AutDirection absoluteDir) {
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
		if (Math.abs(velocityX) >= maxVelocity && Math.abs(velocityY) >= maxVelocity) {
			return true;
		}
		return false;
	}

	public PositionF Shift(long elapsed) {
		double minValue = 0.001;
		double smoothElapsed = (elapsed + previousElapsed[0] + previousElapsed[1]) / 3;// used because of spikes due to
																						// garbage collector
		previousElapsed[0] = previousElapsed[1];
		previousElapsed[1] = elapsed;
		// System.out.println(accelerationX + "\t" + velocityX);

		accelerationX = accelerationX / Math.pow(airbreak, smoothElapsed);
		if (Math.abs(accelerationX) < minValue)
			accelerationX = 0;
		accelerationY = accelerationY / Math.pow(airbreak, smoothElapsed);
		if (Math.abs(accelerationY) < minValue)
			accelerationY = 0;
		velocityX += smoothElapsed * accelerationX;
		velocityX /= airbreak;
		if (Math.abs(velocityX) < minValue)
			velocityX = 0;
		velocityY += smoothElapsed * accelerationY;
		velocityY /= airbreak;
		if (Math.abs(velocityY) < minValue)
			velocityY = 0;

		double shiftX = velocityX * smoothElapsed, shiftY = velocityY * smoothElapsed;
		if (shiftX > maxShiftX)
			shiftX = maxShiftX;
		else if (shiftX < -maxShiftX)
			shiftX = -maxShiftX;
		if (shiftY > maxShiftY)
			shiftY = maxShiftY;
		else if (shiftY < -maxShiftY)
			shiftY = -maxShiftY;

		if (timerVelocity == 0) {
			timerVelocity = refreshTimerVelocity;
			velocityToPrint = (int) (25 * Math.sqrt(shiftX * shiftX + shiftY * shiftY));
		} else {
			timerVelocity--;
		}

		return new PositionF((float) shiftX, (float) shiftY);
	}

	public int getVelocity() {
		return velocityToPrint;
	}

}
