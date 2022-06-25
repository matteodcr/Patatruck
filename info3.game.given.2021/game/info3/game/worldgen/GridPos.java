package info3.game.worldgen;

import java.util.Objects;

final class GridPos {
	public final int x, y;

	GridPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public GridPos top() {
		return new GridPos(x, y - 1);
	}

	public GridPos right() {
		return new GridPos(x + 1, y);
	}

	public GridPos bottom() {
		return new GridPos(x, y + 1);
	}

	public GridPos left() {
		return new GridPos(x - 1, y);
	}

	public boolean isWithin(int minX, int maxX, int minY, int maxY) {
		return x >= minX && x < maxX && y >= minY && y < maxY;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		GridPos gridPos = (GridPos) o;
		return x == gridPos.x && y == gridPos.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return "GridPos{" + "x=" + x + ", y=" + y + '}';
	}
}
