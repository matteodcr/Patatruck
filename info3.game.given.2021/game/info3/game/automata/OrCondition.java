package info3.game.automata;

public class OrCondition implements IFunction {

	IFunction left;
	IFunction right;

	public OrCondition(IFunction l, IFunction r) {
		left = l;
		right = r;
	}

	@Override
	public boolean eval(AutomatonListener aut) {
		return left.eval(aut) || right.eval(aut);
	}
}
