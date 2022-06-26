package info3.game.automata;

public class AndCondition implements IFunction {

	final IFunction left;
	final IFunction right;

	public AndCondition(IFunction l, IFunction r) {
		left = l;
		right = r;
	}

	@Override
	public boolean eval(AutomatonListener aut) {
		return left.eval(aut) && right.eval(aut);
	}
}
