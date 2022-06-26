package info3.game.automata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GTransition {
	public final Map<IFunction, Integer> action; // Action et pourcentage
	public final IFunction condition;
	public final GState destination;

	public GTransition(Map<IFunction, Integer> action2, IFunction condition, GState destination) {
		this.action = action2;
		this.condition = condition;
		this.destination = destination;
	}

	public GState doTransition(AutomatonListener aut) {
		if (condition.eval(aut)) {
			// si pas d'action = on effectue la transi, sinon on choisit une action a exec
			if (action.isEmpty() || chooseAction(aut)) {
				return destination;
			}
		}
		return null;
	}

	// si plusieurs actions, on en choisit une
	private boolean chooseAction(AutomatonListener aut) {
		Random rand = new Random();
		IFunction f = sumMap(action, rand.nextInt(101));
		if (f != null) {
			return f.eval(aut);
		} else {
			return false;
		}
	}

	// Fonction auxilliaire qui return l'action en fonction du % généré
	// aléatoirement
	private IFunction sumMap(Map<IFunction, Integer> map, int drawnPercent) {
		List<IFunction> keys = new ArrayList<>(map.keySet());
		int sum = 0;
		int cpt = 0;
		Collection<Integer> percentList = map.values();
		for (int percent : percentList) {
			sum += percent;
			if (sum >= drawnPercent) {
				return keys.get(cpt);
			}
			cpt++;
		}
		return null; // not meant to happen
	}

}
