package info3.game.entity;

import info3.game.position.AutDirection;
import info3.game.position.PositionF;
import info3.game.scene.Scene;

public class ConeEntity extends Entity {

	ConeEntity(Scene parent, PositionF pos) {
		super(parent, pos);
	}

	@Override
	public EntityType getType() {
		return EntityType.CONE;
	}

	@Override
	public boolean pop(AutDirection direction) {
		return true;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		return true;
	}

	@Override
	public boolean egg(AutDirection direction) {
		if (parentScene.entityList.size() <= Scene.MAXIMUM_ENTITIES) {
			Entity newEntity = null;
			newEntity = new ConeEntity(this.parentScene, position);
			return this.parentScene.addEntity(newEntity);
		}
		return false;
	}

}
