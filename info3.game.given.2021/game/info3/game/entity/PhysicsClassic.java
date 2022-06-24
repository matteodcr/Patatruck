package info3.game.entity;

public class PhysicsClassic extends AveragePhysics {

	/**
	 * average force is 3
	 * 
	 * @param force
	 */
	public PhysicsClassic(int force) {
		super(force);
		this.maxVel = 2;
	}

	/**
	 * average force is 3
	 * 
	 * @param force
	 */
	public PhysicsClassic(int force, double accX, double accY, double velX, double velY, double maxVel,
			double avgVelBuff, double avgVel, int timerVel, int timerMaxVel) {
		super(force, accX, accY, velX, velY, maxVel, avgVelBuff, avgVel, timerVel, timerMaxVel);
		this.maxVel = 2;
	}

}
