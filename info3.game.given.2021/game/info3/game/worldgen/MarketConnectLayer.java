package info3.game.worldgen;

public class MarketConnectLayer implements Layer<GenTile> {
	private final Layer<GenTile> inner;

	public MarketConnectLayer(Layer<GenTile> inner) {
		this.inner = inner;
	}

	@Override
	public GenTile getAt(long seed, GridPos pos) {
		GenTile hereTile = inner.getAt(seed, pos);
		boolean here = hereTile.hasMarketPaving;
		boolean right = inner.getAt(seed, pos.right()).hasMarketPaving;
		boolean bottom = inner.getAt(seed, pos.bottom()).hasMarketPaving;

		GenTile.MarketRoad top = null, left = null;
		if (here && right)
			top = GenTile.MarketRoad.BOTH;
		if (here && !right)
			top = GenTile.MarketRoad.OUT;
		if (!here && right)
			top = GenTile.MarketRoad.IN;
		if (here && bottom)
			left = GenTile.MarketRoad.BOTH;
		if (here && !bottom)
			left = GenTile.MarketRoad.OUT;
		if (!here && bottom)
			left = GenTile.MarketRoad.IN;

		return hereTile.withMarketPaving(top, left);
	}

	@Override
	public void clearCaches() {
		inner.clearCaches();
	}
}
