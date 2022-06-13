package info3.game.automata;

public class AndCondition implements IFunction {

	IFunction left;
	IFunction right;

	public AndCondition(IFunction l, IFunction r) {
		left = l;
		right = r;
	}

	@Override
	public boolean eval(AutomatonListener aut) {
		return left.eval(aut) && right.eval(aut);
	}
}
