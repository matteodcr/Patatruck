package info3.game.automata;

import java.util.ArrayList;
import java.util.List;

public class GState {
	public final List<GTransition> transitions;
	public final String name;

	public GState(List<GTransition> transitions, String name) {
		this.transitions = transitions;
		this.name = name;
	}

	public GState(String name) {
		this(new ArrayList<>(), name);
	}

	public void addTransition(GTransition transition) {
		this.transitions.add(transition);
	}

	public GState checkTransitions(AutomatonListener aut) {
		GState state = null;
		for (GTransition transition : transitions) {
			// on parcourt transi jusqu'a trouver une qui

			state = transition.doTransition(aut);
			if (state != null)
				break;
		}
		return state;

	}
}
