package info3.game.automata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info3.automata.ast.AST;
import info3.automata.ast.Action;
import info3.automata.ast.Automaton;
import info3.automata.ast.Behaviour;
import info3.automata.ast.BinaryOp;
import info3.automata.ast.Category;
import info3.automata.ast.Condition;
import info3.automata.ast.Direction;
import info3.automata.ast.FunCall;
import info3.automata.ast.IVisitor;
import info3.automata.ast.Key;
import info3.automata.ast.Mode;
import info3.automata.ast.State;
import info3.automata.ast.Transition;
import info3.automata.ast.UnaryOp;
import info3.automata.ast.Underscore;
import info3.automata.ast.Value;

public class AutomataGenerator implements IVisitor {

	Integer current_source_state;

	List<GAutomaton> automata;

	GAutomaton current_automaton;

	/**
	 * /!\ States appear as source and target of transitions.
	 * <p>
	 * A naive implementation would create distinct copies of the same state: - one
	 * when it is a source, - one when it is a target resulting into disconnected
	 * automaton with floating transitions.
	 * <p>
	 * SOLUTION We need to build a mapping from State name -->
	 * DoState(id,name,options). Thus, when encountering a state that has already
	 * been stored in the mapping we can get the state already in the map to
	 * preserve links
	 */

	private Map<String, GState> stateMap;

	GState getState(State state) {
		GState stored_state = stateMap.get(state.name);
		if (stored_state != null) {
			return stored_state;
		} else {
			GState new_state = new GState(state.name);
			stateMap.put(state.name, new_state);
			return new_state;
		}
	}

	// CONSTRUCTOR

	public AutomataGenerator() {
		this.stateMap = new HashMap<String, GState>();
		this.automata = new ArrayList<GAutomaton>();
		this.current_automaton = null;
	}

	// Method from Ivisitor

	public Object visit(Category cat) {
		return cat.terminal.content; // str
	}

	public Object visit(Direction dir) {
		return dir.terminal.content; // str
	}

	public Object visit(Key key) {
		return key.terminal.content; // str
	}

	public Object visit(Value v) {
		return null;
	}

	public Object visit(Underscore u) {
		return null;
	}

	public void enter(FunCall funcall) {
	}

	// create a funcall (action or condition)
	public Object exit(FunCall funcall, List<Object> params) {
		List<String> params_list = new ArrayList<String>();
		for (Object param : params) {
			params_list.add((String) param);
		}
		return new GFunCall(funcall.name, params_list, funcall.percent);
	}

	// marche pas pr l'instant
	public Object visit(BinaryOp operator, Object left, Object right) {
		IFunction new_condition = null;
		GFunCall funcall_l = ((GFunCall) left);
		GFunCall funcall_r = ((GFunCall) right);
		switch (operator.operator) {
		case "&":
			new_condition = (IFunction) new AndCondition((IFunction) funcall_l, (IFunction) funcall_r);
			break;
		case "/":
			new_condition = (IFunction) new OrCondition((IFunction) funcall_l, (IFunction) funcall_r);
			break;
		default:
			throw new IllegalStateException("opération binaire de condition non défini");
		}
		return new_condition;
	}

	// marche pas pr l'instant
	public Object visit(UnaryOp operator, Object exp) {
		IFunction condition;
		switch (operator.operator) {
		case "!":
			condition = (IFunction) new NotCondition((IFunction) exp);
			break;
		default:
			throw new IllegalStateException("opération unaire de condition non défini");
		}
		return condition;
	}

	public Object visit(State state) {
		return getState(state); // return new state if not initialized, else the one already created
	}

	public void enter(Mode mode) {
	}

	// on ajoute ttes les transis lies a un etat
	public Object exit(Mode mode, Object source_state, Object behaviour) {
		@SuppressWarnings("unchecked") // we always call that after setting up a GTransition list
		List<GTransition> transitions = (List<GTransition>) behaviour;
		for (Object transition : transitions) {
			((GState) source_state).addTransition((GTransition) transition);
		}
		return source_state;
	}

	public void enter(Condition condition) {
	}

	public Object exit(Condition condition, Object exp) {
		return (IFunction) exp;
	}

	public void enter(Action action) {
	}

	// fct auxilliaire renvoie somme pourcentage deja present
	private int sum_map(Map<IFunction, Integer> map) {
		int sum = 0;
		Collection<Integer> percent_list = map.values();
		for (int percent : percent_list)
			sum += percent;
		return sum;
	}

	// On renvoie une liste d'actions possible pr une transi avec pourcentage
	// associe
	// on supp qu'on a pas de transi de la forme : <cond> ? 15%Pop / Move / 55%Wizz
	// : <etat>
	// si le pourcentage n'est pas donne, c'est qu'on a deja rentre ttes les actions
	// en ayant
	public Object exit(Action action, List<Object> funcalls) {
		Map<IFunction, Integer> action_list = new HashMap<IFunction, Integer>();
		for (Object funcall_tmp : funcalls) {
			GFunCall funcall = (GFunCall) funcall_tmp;
			if (funcall.percent == -1) { // si le pourcentage n'est pas donne, on le calcule
				int sum_in_array = sum_map(action_list);
				funcall.percent = (100 - sum_in_array) / (funcalls.size() - action_list.size());
			}
			action_list.put((IFunction) funcall, funcall.percent);
		}
		return action_list;
	}

	// on init une nouvelle transition
	@SuppressWarnings("unchecked")
	public Object visit(Transition transition, Object condition, Object action, Object target) {
		return new GTransition((Map<IFunction, Integer>) action, (IFunction) condition, (GState) target);
	}

	public Object visit(Behaviour behaviour, List<Object> transitions) {
		return transitions;
	}

	public void enter(Automaton automaton) {
		this.stateMap.clear();
		// on init un nouvel automate qu'on veut ajouter a la liste
		current_automaton = new GAutomaton(null, automaton.name, new ArrayList<GState>());
	}

	// On renvoie l'automate actuel apres avoir fait l'ajout des etats
	public Object exit(Automaton automaton, Object initial_state, List<Object> modes) {
		current_automaton.initial = (GState) initial_state;
		for (Object state : modes) {
			current_automaton.addState((GState) state);
		}
		return current_automaton;
	}

	// on renvoie la liste d'automates
	public Object visit(AST bot, List<Object> automata) {
		return automata;
	}

}
