package info3.game.automata;

import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.AutKey;

public interface AutomatonListener {

	// Actions

	boolean pop(AutDirection direction);

	boolean wizz(AutDirection direction);

	boolean move(AutDirection direction);

	// Conditions

	boolean cell(AutDirection direction, AutCategory category);

	boolean key(AutKey key);
}
