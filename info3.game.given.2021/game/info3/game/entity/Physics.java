package info3.game.entity;

import info3.game.position.AutDirection;
import info3.game.position.PositionF;

public interface Physics {

	/**
	 * @param absoluteDir
	 */
	void addForce(AutDirection absoluteDir);

	/**
	 * Returns a PositionF which corresponds to the shift the entity has to do
	 * 
	 * @param
	 * @return
	 */
	PositionF shift();

	/**
	 * Reset acc and vel and moves back by the same amount as the last change
	 * 
	 * 
	 */
	PositionF bounce();

	/**
	 * returns the velocity but updated to a certain frequency
	 * 
	 * @return
	 */
	int getVelocity();

	int getInRealTimeVelocity();

	double getAccX();

	double getAccY();

	double getVelX();

	double getVelY();

	double getMaxVel();

	double getAvgVelBuff();

	double getAvgVel();

	int getTimerVel();

	int getTimerMaxVel();

	PositionF getLastPosChange();

	/*
	 * reinitialize acceleration and reduces speed if no key is pressed
	 */
	void removeForce();

	void stop();
}
