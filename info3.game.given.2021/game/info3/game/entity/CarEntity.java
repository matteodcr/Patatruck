package info3.game.entity;

import info3.game.position.PositionF;
import info3.game.scene.Scene;

public class CarEntity extends Entity {
	boolean isTruck;
	
	CarEntity(Scene parent, PositionF position, boolean isTruck) {
		super(parent, position);
		this.isTruck = isTruck;
	}
}
