package info3.game.entity;

public class PhysicsNoBrakes extends AveragePhysics {

	/**
	 * average force is 3
	 * 
	 * @param force
	 */
	public PhysicsNoBrakes(int force) {
		super(force);
		this.maxVel = 2.5;
	}

	/**
	 * average force is 3
	 * 
	 * @param force
	 */
	public PhysicsNoBrakes(int force, double accX, double accY, double velX, double velY, double maxVel,
			double avgVelBuff, double avgVel, int timerVel, int timerMaxVel) {
		super(force, accX, accY, velX, velY, maxVel, avgVelBuff, avgVel, timerVel, timerMaxVel);
		this.maxVel = 2.5;
	}

}
