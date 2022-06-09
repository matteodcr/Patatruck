package position;

public class Direction {
	public enum dir {
		NORD, OUEST, EST, SUD
	}

	private dir direction;

	Direction(dir direction) {
		this.direction = direction;
	}

	void setDir(dir direction) {
		this.direction = direction;
	}

	dir getDir() {
		return direction;
	}
}
