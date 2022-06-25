package info3.game.entity;

import info3.game.position.PositionF;

public class PhysicsNoBrakes extends AveragePhysics {

	/**
	 * average force is 15
	 * 
	 * @param force
	 */
	public PhysicsNoBrakes(int force) {
		super(force);
		this.maxVel = 200;
		this.friction = 1.15f;
	}

	/**
	 * average force is 15
	 * 
	 * @param force
	 */
	public PhysicsNoBrakes(int force, double accX, double accY, double velX, double velY, double maxVel,
			double avgVelBuff, double avgVel, int timerVel, int timerMaxVel, PositionF lastPosChange) {
		super(force, accX, accY, velX, velY, maxVel, avgVelBuff, avgVel, timerVel, timerMaxVel, lastPosChange);
		this.maxVel = 200;
		this.friction = 1.15f;
	}

}
