package info3.game.worldgen;

public class NoiseSamplerLayer implements Layer<Integer> {
	private final float width;

	public NoiseSamplerLayer(float width) {
		assert width != 0;
		this.width = width;
	}

	@Override
	public Integer getAt(long seed, GridPos pos) {
		float noise = OpenSimplex2.noise2(seed, (float) pos.x / width, (float) pos.y / width);
		return (int) (noise * Integer.MAX_VALUE);
	}
}
