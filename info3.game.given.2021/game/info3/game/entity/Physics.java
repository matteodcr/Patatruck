package info3.game.entity;

import info3.game.position.AutDirection;
import info3.game.position.PositionF;

public interface Physics {

	/**
	 * 
	 * @param absoluteDir
	 * @param elapsed     time in ms
	 */
	public void addForce(AutDirection absoluteDir);

	/**
	 * Returns a PositionF which corresponds to the shift the entity has to do
	 * 
	 * @param elapsed
	 * @return
	 */
	public PositionF Shift(long elapsed);

	/**
	 * Same as addForce but with lower force
	 * 
	 * @param absoluteDir
	 */
	public PositionF bounce(AutDirection absoluteDir);

	/**
	 * returns the velocity but updated to a certain frequency
	 * 
	 * @return
	 */
	public int getVelocity();

	public int getInRealTimeVelocity();

	public double getAccX();

	public double getAccY();

	public double getVelX();

	public double getVelY();

	public double getMaxVel();

	public double getAvgVelBuff();

	public double getAvgVel();

	public int getTimerVel();

	public int getTimerMaxVel();
}
