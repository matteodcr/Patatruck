package info3.game.entity;

import info3.game.position.PositionF;

public class PhysicsClassic extends AveragePhysics {

	/**
	 * average force is 15
	 * 
	 * @param force
	 */
	public PhysicsClassic(int force) {
		super(force);
		this.maxVel = 150;
	}

	/**
	 * average force is 15
	 * 
	 * @param force
	 */
	public PhysicsClassic(int force, double accX, double accY, double velX, double velY, double maxVel,
			double avgVelBuff, double avgVel, int timerVel, int timerMaxVel, PositionF lastPosChange) {
		super(force, accX, accY, velX, velY, maxVel, avgVelBuff, avgVel, timerVel, timerMaxVel, lastPosChange);
		this.maxVel = 150;
	}

}
