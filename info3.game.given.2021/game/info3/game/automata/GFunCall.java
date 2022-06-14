package info3.game.automata;

import java.util.List;

import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.AutKey;

public class GFunCall implements IFunction {
	private enum FunName {
		// Actions
		POP, WIZZ, MOVE, WAIT, EGG, HIT, JUMP, EXPLODE, PICK, POWER, PROTECT, STORE, TURN, THROW,
		// Conditions
		CELL, KEY, TRUE, MYDIR, CLOSEST, GOTPOWER, GOTSTUFF;
	}

	List<String> params;
	int percent;
	private FunName name;

	public GFunCall(String n, List<String> params_list, int p) {
		switch (n) {
		// Actions
		case "Pop":
			name = FunName.POP;
			break;
		case "Wizz":
			name = FunName.WIZZ;
			break;
		case "Move":
			name = FunName.MOVE;
			break;
		case "Wait":
			name = FunName.WAIT;
			break;
		case "Egg":
			name = FunName.EGG;
			break;
		case "Hit":
			name = FunName.HIT;
			break;
		case "Jump":
			name = FunName.JUMP;
			break;
		case "Explode":
			name = FunName.EXPLODE;
			break;
		case "Pick":
			name = FunName.PICK;
			break;
		case "Power":
			name = FunName.POWER;
			break;
		case "Protect":
			name = FunName.PROTECT;
			break;
		case "Store":
			name = FunName.STORE;
			break;
		case "Turn":
			name = FunName.TURN;
			break;
		case "Throw":
			name = FunName.THROW;
			break;
		// Conditions
		case "True":
			name = FunName.TRUE;
			break;
		case "Cell":
			name = FunName.CELL;
			break;
		case "Key":
			name = FunName.KEY;
			break;
		case "MyDir":
			name = FunName.MYDIR;
			break;
		case "Closest":
			name = FunName.CLOSEST;
			break;
		case "GotPower":
			name = FunName.GOTPOWER;
			break;
		case "GotStuff":
			name = FunName.GOTSTUFF;
			break;
		}
		params = params_list;
		percent = p;
	}

	private AutCategory getCategory(String cat) {
		if (cat.equals("@"))
			return AutCategory.AROBASE;
		if (cat.equals("_"))
			return AutCategory.WILDCARD;
		else
			return AutCategory.valueOf(cat);
	}

	// TODO : add missing actions
	@Override
	public boolean eval(AutomatonListener aut) {
		switch (name) {
		// Actions
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
		case WAIT:
			return aut.gwait();
		case EGG:
			if (params.size() == 0)
				return aut.egg(AutDirection.F);
			else
				return aut.egg(AutDirection.valueOf(params.get(0).toUpperCase()));
		case HIT:
			if (params.size() == 0)
				return aut.hit(AutDirection.F);
			else
				return aut.hit(AutDirection.valueOf(params.get(0).toUpperCase()));
		case JUMP:
			if (params.size() == 0)
				return aut.jump(AutDirection.F);
			else
				return aut.jump(AutDirection.valueOf(params.get(0).toUpperCase()));
		case EXPLODE:
			return aut.explode();
		case PICK:
			if (params.size() == 0)
				return aut.pick(AutDirection.F);
			else
				return aut.pick(AutDirection.valueOf(params.get(0).toUpperCase()));
		case POWER:
			return aut.power();
		case PROTECT:
			if (params.size() == 0)
				return aut.protect(AutDirection.F);
			else
				return aut.protect(AutDirection.valueOf(params.get(0).toUpperCase()));
		case STORE:
			return aut.store();
		case TURN:
			if (params.size() == 0)
				return aut.turn(AutDirection.R);
			else
				return aut.turn(AutDirection.valueOf(params.get(0).toUpperCase()));
		case THROW:
			if (params.size() == 0)
				return aut.gthrow(AutDirection.F);
			else
				return aut.gthrow(AutDirection.valueOf(params.get(0).toUpperCase()));

			// Conditions
		case TRUE:
			return true;
		case CELL:
			if (params.size() == 2)
				return aut.cell(AutDirection.valueOf(params.get(0).toUpperCase()), getCategory(params.get(1)));
		case KEY:
			if (params.size() == 1)
				return aut.key(AutKey.valueOf(params.get(0).toUpperCase()));
		case MYDIR:
			if (params.size() == 1)
				return aut.myDir(AutDirection.valueOf(params.get(0).toUpperCase()));
		case CLOSEST:
			if (params.size() == 2)
				aut.closest(getCategory(params.get(1)), AutDirection.valueOf(params.get(1).toUpperCase()));
		case GOTPOWER:
			return aut.gotPower();
		case GOTSTUFF:
			return aut.gotStuff();
		default:
			throw new IllegalStateException("panic");
		}
	}

}
