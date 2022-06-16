package info3.game.automata;

import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.AutKey;

public interface AutomatonListener {

	// Actions

	boolean pop(AutDirection direction);

	boolean wizz(AutDirection direction);

	boolean move(AutDirection direction);

	boolean gwait();

	boolean egg(AutDirection direction);

	boolean hit(AutDirection direction);

	boolean jump(AutDirection direction);

	boolean explode();

	boolean pick(AutDirection direction);

	boolean power();

	boolean protect(AutDirection direction);

	boolean store();

	boolean turn(AutDirection direction);

	boolean gthrow(AutDirection direction);

	// Conditions

	boolean cell(AutDirection direction, AutCategory category);

	boolean key(AutKey key);

	boolean myDir(AutDirection direction);

	boolean closest(AutCategory category, AutDirection direction);

	boolean gotPower();

	boolean gotStuff();

}
