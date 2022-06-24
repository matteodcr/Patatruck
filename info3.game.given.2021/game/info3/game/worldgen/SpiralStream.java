package info3.game.worldgen;

import java.util.stream.IntStream;
import java.util.stream.Stream;

class SpiralStream {
	private static GridPos spiral(int n) {
		int k = (int) Math.ceil((Math.sqrt(n) - 1) / 2);
		int t = 2 * k + 1;
		int m = t * t;
		t = t - 1;

		if (n >= m - t)
			return new GridPos(k - (m - n), -k);

		m = m - t;

		if (n >= m - t)
			return new GridPos(-k, -k + (m - n));

		m = m - t;

		if (n >= m - t)
			return new GridPos(-k + (m - n), k);

		return new GridPos(k, k - (m - n - t));
	}

	public static Stream<GridPos> create(int centerX, int centerY) {
		return IntStream.iterate(0, i -> i + 1).mapToObj(SpiralStream::spiral)
				.map(pos -> new GridPos(pos.x + centerX, pos.y + centerY));
	}
}
