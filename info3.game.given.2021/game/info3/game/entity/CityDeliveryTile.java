package info3.game.entity;

import info3.game.position.AutCategory;

import info3.game.position.AutDirection;
import info3.game.position.PositionF;
import info3.game.scene.Scene;
import info3.game.entity.CityTile;

public class CityDeliveryTile extends CityTile {
	private int deliveries = 0;
	
	static int RADIUS = 10;

	public CityDeliveryTile(Scene parent) {
		super(parent, 0, 0);
	}

	public void delivered() {
		deliveries += 1;
	}
	
	@Override
	public boolean pop(AutDirection direction) {
		// TODO Change tile sprite to make it blink
		return false;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		RADIUS += 10;
		deliveries = 0;
	
		double a = (Math.random() * 2 * 3.14);
		double r = (RADIUS * Math.sqrt(Math.random()));
			
		float x = (float)(r * Math.cos(a));
		float y = (float)(r * Math.sin(a));
		
		while (!(((CityTile)(parentScene.getTileAt((int)x, (int)y))).getGenTile().hasRoad())) {
			a = (Math.random() * 2 * 3.14);
			r = (RADIUS * Math.sqrt(Math.random()));
				
			x = (float)(r * Math.cos(a));
			y = (float)(r * Math.sin(a));
		}
		
		this.setPosition(new PositionF(this.gridX+x, this.gridY+y));
		
		return true;
	}

	@Override
	public boolean gwait() {
		return false;
	}

	@Override
	public boolean egg(AutDirection direction) {
		return false;
	}

	@Override
	public boolean hit(AutDirection direction) {
		return false;
	}

	@Override
	public boolean jump(AutDirection direction) {
		return false;
	}

	@Override
	public boolean explode() {
		return false;
	}

	@Override
	public boolean pick(AutDirection direction) {
		return false;
	}

	@Override
	public boolean power() {
		return false;
	}

	@Override
	public boolean protect(AutDirection direction) {
		return false;
	}

	@Override
	public boolean store() {
		return false;
	}

	@Override
	public boolean turn(AutDirection direction) {
		return false;
	}

	@Override
	public boolean gthrow(AutDirection direction) {
		return false;
	}

	@Override
	public boolean myDir(AutDirection direction) {
		return false;
	}

	@Override
	public boolean closest(AutCategory category, AutDirection direction) {
		return false;
	}

	@Override
	public boolean gotPower() {
		return false;
	}

	@Override
	public boolean gotStuff() {
		return deliveries == 2;
	}

	@Override
	public EntityType getType() {
		return EntityType.TILE_DELIVERY_CITY;
	}

}
