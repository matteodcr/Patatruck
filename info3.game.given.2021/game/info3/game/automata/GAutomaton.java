package info3.game.automata;

import java.util.List;

public class GAutomaton {
	public GState initial;
	public String name;
	public List<GState> states;

	public GAutomaton(GState initial, String name, List<GState> states) {
		this.initial = initial;
		this.name = name;
		this.states = states;
	}

	public void addState(GState gState) {
		this.states.add(gState);
	}
}
