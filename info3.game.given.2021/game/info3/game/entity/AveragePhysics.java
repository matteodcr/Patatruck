package info3.game.entity;

import info3.game.position.AutDirection;
import info3.game.position.PositionF;

public class AveragePhysics implements Physics {

	double accX = 0, accY = 0;
	double velX = 0, velY = 0;
	double maxVel = 2.5;
	double force = 0, friction = 2;

	double avgVelBuff = 0, avgVel = 0;
	int timerVel = 0, timerMaxVel = 10;

	/**
	 * average force is 3
	 * 
	 * @param force
	 */
	public AveragePhysics(int force) {
		this.force = force;
	}

	/**
	 * average force is 3
	 * 
	 * @param force
	 */
	public AveragePhysics(int force, double accX, double accY, double velX, double velY, double maxVel,
			double avgVelBuff, double avgVel, int timerVel, int timerMaxVel) {
		this.force = force;
		this.accX = accX;
		this.accY = accY;
		this.velX = velX;
		this.velY = velY;
		this.maxVel = maxVel;
		this.avgVelBuff = avgVelBuff;
		this.avgVel = avgVel;
		this.timerVel = timerVel;
		this.timerMaxVel = timerMaxVel;
	}

	@Override
	public void addForce(AutDirection absoluteDir) {
		switch (absoluteDir) {
		case N:
			accY -= force;
			break;
		case E:
			accX += force;
			break;
		case S:
			accY += force;
			break;
		case W:
			accX -= force;
			break;
		default:
			break;
		}
	}

	@Override
	public PositionF Shift(long elapsed) {
		float shiftX = 0, shiftY = 0;
		for (int i = 0; i < elapsed; i++) {
			shiftX += velX;
			shiftY += velY;

			velX += accX;
			velY += accY;

			if (velX >= friction) {
				velX -= friction;
			} else if (velX <= -friction) {
				velX += friction;
			} else {
				velX = 0;
			}
			if (velY >= friction) {
				velY -= friction;
			} else if (velY <= -friction) {
				velY += friction;
			} else {
				velY = 0;
			}

		}

		if (shiftX > maxVel) {
			shiftX = (float) maxVel;
			accX = 0;
			velX = maxVel;
		} else if (shiftX < -maxVel) {
			shiftX = (float) -maxVel;
			accX = 0;
			velX = -maxVel;
		}
		if (shiftY > maxVel) {
			shiftY = (float) maxVel;
			accY = 0;
			velY = maxVel;
		} else if (shiftY < -maxVel) {
			shiftY = (float) -maxVel;
			accY = 0;
			velY = -maxVel;
		}
		if (timerVel == 0) {
			timerVel = timerMaxVel;
			avgVel = avgVelBuff / 10;
			avgVelBuff = 0;
		} else {
			avgVelBuff += Math.hypot(shiftX, shiftY);
			timerVel--;
		}
		return new PositionF(shiftX, shiftY);
	}

	@Override
	public int getVelocity() {
		return (int) (25 * avgVel);
	}

	public double getAccX() {
		return accX;
	}

	public double getAccY() {
		return accY;
	}

	public double getVelX() {
		return velX;
	}

	public double getVelY() {
		return velY;
	}

	public double getMaxVel() {
		return maxVel;
	}

	public double getAvgVelBuff() {
		return avgVelBuff;
	}

	public double getAvgVel() {
		return avgVel;
	}

	public int getTimerVel() {
		return timerVel;
	}

	public int getTimerMaxVel() {
		return timerMaxVel;
	}

}
