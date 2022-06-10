package info3.game.entity;

import java.awt.Graphics;

import info3.game.position.PositionF;
import info3.game.scene.Scene;

public abstract class Entity {
	Scene parentScene = null;
	PositionF position;
	Automaton automate;
	int deathTime = 0;

	Entity(Scene parent, PositionF pos) {
		parentScene = parent;
		position = pos;
	}

	void setPosition(PositionF pos) {
		position = pos;
	}

	PositionF getPosition() {
		return position;
	}

	void tick() {
		// TODO
	}

	void destroySpin() {
		// TODO
	}

	void destroyCrush() {
		// TODO
	}

	void render(Graphics g) {
		// TODO
	}
}
