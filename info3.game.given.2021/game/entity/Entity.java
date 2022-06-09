package entity;

import java.awt.Graphics;

import position.PositionF;

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
