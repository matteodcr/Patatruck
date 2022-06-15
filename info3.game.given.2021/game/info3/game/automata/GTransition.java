package info3.game.automata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GTransition {
	public Map<IFunction, Integer> action; // Action et pourcentage
	public IFunction condition;
	public GState destination;

	public GTransition(Map<IFunction, Integer> action2, IFunction condition, GState destination) {
		this.action = action2;
		this.condition = condition;
		this.destination = destination;
	}

	public GState doTransition(AutomatonListener aut) {
		if (condition.eval(aut)) {
			if (chooseAction(aut)) {
				return destination;
			}
		}
		return null;
	}

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
	private IFunction sumMap(Map<IFunction, Integer> map, int drawn_percent) {
		List<IFunction> keys = new ArrayList<IFunction>(map.keySet());
		int sum = 0;
		int cpt = 0;
		Collection<Integer> percent_list = map.values();
		for (int percent : percent_list) {
			sum += percent;
			if (sum >= drawn_percent) {
				return keys.get(cpt);
			}
			cpt++;
		}
		return null; // not meant to happen
	}
}
