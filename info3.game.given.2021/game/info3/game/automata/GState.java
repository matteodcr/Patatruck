package info3.game.automata;

import java.util.ArrayList;
import java.util.List;

public class GState {
	public List<GTransition> transitions;
	public String name;

	public GState(List<GTransition> transitions, String name) {
		this.transitions = transitions;
		this.name = name;
	}

	public GState(String name) {
		this.transitions = new ArrayList<GTransition>();
		this.name = name;
	}

	public void addTransition(GTransition transition) {
		this.transitions.add(transition);
	}
}
