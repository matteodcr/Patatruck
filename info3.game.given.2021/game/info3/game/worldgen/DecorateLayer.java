package info3.game.worldgen;

/**
 * Adds speed bumps and market center tiles
 */
public class DecorateLayer implements Layer<GenTile> {
	private final Layer<GenTile> builtTiles;
	private final Layer<Boolean> speedBumps;
	private final Layer<Boolean> markets;

	public DecorateLayer(Layer<GenTile> builtTiles, Layer<Boolean> speedBumps, Layer<Boolean> markets) {
		this.builtTiles = builtTiles;
		this.speedBumps = speedBumps;
		this.markets = markets;
	}

	@Override
	public GenTile getAt(long seed, GridPos pos) {
		boolean speedBumpTop = speedBumps.getAt(seed, pos);
		boolean speedBumpLeft = speedBumps.getAt(seed * 2147483647, pos);
		return builtTiles.getAt(seed, pos).withDecoration(markets.getAt(seed, pos), speedBumpTop, speedBumpLeft);
	}

	@Override
	public void clearCaches() {
		builtTiles.clearCaches();
		speedBumps.clearCaches();
		markets.clearCaches();

	}
}
