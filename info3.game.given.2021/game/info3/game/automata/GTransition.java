package info3.game.automata;

import java.util.Map;

public class GTransition {
	public Map<IFunction, Integer> action; // Action et pourcentage
	public IFunction condition;
	public GState destination;

	public GTransition(Map<IFunction, Integer> action2, IFunction condition, GState destination) {
		this.action = action2;
		this.condition = condition;
		this.destination = destination;
	}
}
