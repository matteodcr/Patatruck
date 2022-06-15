package info3.game.automata;

import java.util.List;
import java.util.Random;

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

	public GState run(AutomatonListener aut, GState current_state) {
		GState state = current_state.checkTransitions(aut);
		if (state != null && state.name.equals("_"))
			state = getRandomState();
		return state;
	}

	private GState getRandomState() {
		GState state;
		do {
			Random rand = new Random();
			state = states.get(rand.nextInt(states.size() - 1));
		} while (state.name.equals("_"));
		return state;
	}
}
