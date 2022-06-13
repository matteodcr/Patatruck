package info3.game.position;

public class PositionF {

	public static final PositionF ZERO = new PositionF(0, 0);

	private final float x, y;

	public PositionF(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public PositionF(PositionI i) {
		this.x = i.getX();
		this.y = i.getY();
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public PositionF add(PositionF b) {
		return new PositionF(this.x + b.x, this.y + b.y);
	}

	public PositionF neg() {
		return new PositionF(-this.x, -this.y);
	}

	public PositionI divFloor(int b) {
		return new PositionI((int) (this.x / b), (int) (this.y / b));
	}

	public PositionF mul(int tileWidth) {
		return new PositionF(this.x * tileWidth, this.y * tileWidth);
	}

}
