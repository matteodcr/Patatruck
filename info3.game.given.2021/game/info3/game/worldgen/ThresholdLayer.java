package info3.game.worldgen;

public class ThresholdLayer implements Layer<Boolean> {
	private final Layer<Integer> inner;
	private final int threshold;

	public ThresholdLayer(Layer<Integer> inner, float threshold) {
		this.inner = inner;
		this.threshold = (int) (threshold * Integer.MAX_VALUE);
	}

	@Override
	public Boolean getAt(long seed, GridPos pos) {
		return inner.getAt(seed, pos) > threshold;
	}

	@Override
	public void clearCaches() {
		inner.clearCaches();
	}
}
