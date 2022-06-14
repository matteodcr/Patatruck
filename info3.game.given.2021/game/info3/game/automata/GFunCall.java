package info3.game.automata;

import java.util.List;

import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.AutKey;

public class GFunCall implements IFunction {
	private enum FunName {
		POP, WIZZ, CELL, MOVE, KEY, TRUE
		// TODO : add missing actions and conditions
	}

	List<String> params;
	int percent;
	private FunName name;

	// TODO : add missing actions and conditions
	public GFunCall(String n, List<String> params_list, int p) {
		switch (n) {
		case "Pop":
			name = FunName.POP;
			break;
		case "Wizz":
			name = FunName.WIZZ;
			break;
		case "Move":
			name = FunName.MOVE;
			break;
		case "True":
			name = FunName.TRUE;
			break;
		case "Cell":
			name = FunName.CELL;
			break;
		case "Key":
			name = FunName.KEY;
			break;
		}
		params = params_list;
		percent = p;
	}

	// TODO : add missing actions and conditions
	@Override
	public boolean eval(AutomatonListener aut) {
		switch (name) {
		case POP:
			if (params.size() == 0)
				return aut.pop(AutDirection.F);
			else
				return aut.pop(AutDirection.valueOf(params.get(0).toUpperCase()));
		case WIZZ:
			if (params.size() == 0)
				return aut.wizz(AutDirection.F);
			else
				return aut.wizz(AutDirection.valueOf(params.get(0).toUpperCase()));
		case MOVE:
			if (params.size() == 0)
				return aut.move(AutDirection.F);
			else
				return aut.move(AutDirection.valueOf(params.get(0).toUpperCase()));
		case TRUE:
			return true;
		case CELL:
			if (params.size() == 0)
				return aut.cell(AutDirection.F, AutCategory.WILDCARD);
			else if (params.size() == 2) {
				String cat_tmp = params.get(1);
				AutCategory cat;
				if (cat_tmp == "@")
					cat = AutCategory.AROBASE;
				if (cat_tmp == "_")
					cat = AutCategory.WILDCARD;
				else
					cat = AutCategory.valueOf(cat_tmp);
				return aut.cell(AutDirection.valueOf(params.get(0).toUpperCase()), cat);
			}
		case KEY:
			if (params.size() == 0)
				return aut.key(AutKey.ENTER); // pas censé arrivé
			else
				return aut.key(AutKey.valueOf(params.get(0).toUpperCase()));
		default:
			throw new IllegalStateException("panic");
		}
	}

}
