package info3.game.worldgen;

import java.util.HashMap;
import java.util.Map;

class Cache<E> implements Layer<E> {
	private final Layer<E> inner;
	private final Map<GridPos, E> cache = new HashMap<>();

	public Cache(Layer<E> inner) {
		this.inner = inner;
	}

	@Override
	public E getAt(long seed, GridPos pos) {
		return cache.computeIfAbsent(pos, k -> inner.getAt(seed, pos));
	}

	@Override
	public void clearCaches() {
		cache.clear();
		inner.clearCaches();
	}
}
