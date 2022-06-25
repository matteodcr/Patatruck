package info3.game.worldgen;

import java.util.stream.IntStream;
import java.util.stream.Stream;

class SpiralStream {
	private static GridPos spiral(int n) {
		int r = (int) (Math.floor((Math.sqrt(n + 1) - 1) / 2)) + 1;
		int p = (8 * r * (r - 1)) / 2;
		int en = r * 2;
		int a = (1 + n - p) % (r * 8);
		switch (a / (r * 2)) {
		case 0:
			return new GridPos(a - r, -r);
		case 1:
			return new GridPos(r, (a % en) - r);
		case 2:
			return new GridPos(r - (a % en), r);
		case 3:
			return new GridPos(-r, r - (a % en));
		default:
			throw new IllegalStateException();
		}
	}

	public static Stream<GridPos> create(int centerX, int centerY) {
		return IntStream.iterate(0, i -> i + 1).mapToObj(SpiralStream::spiral)
				.map(pos -> new GridPos(pos.x + centerX, pos.y + centerY));
	}
}
