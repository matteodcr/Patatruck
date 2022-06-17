package info3.game.position;

import java.util.Objects;

public final class PositionI {
	public static PositionI ZERO = new PositionI(0, 0);

	private final int x, y;

	public PositionI(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public PositionI add(PositionI b) {
		return new PositionI(this.x + b.x, this.y + b.y);
	}

	public PositionI neg() {
		return new PositionI(-x, -y);
	}

	public PositionF toFloat() {
		return new PositionF(x, y);
	}

	@Override
	public String toString() {
		return "PositionI{" + "x=" + x + ", y=" + y + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof PositionI))
			return false;
		PositionI pos = (PositionI) o;
		return x == pos.x && y == pos.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
