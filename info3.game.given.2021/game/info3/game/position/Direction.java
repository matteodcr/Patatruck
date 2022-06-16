package info3.game.position;

public enum Direction {
	NORD, EST, SUD, OUEST {
		@Override
		public Direction next() {
			return null; // see below for options for this line
		};

		@Override
		public Direction previous() {
			return null; // see below for options for this line
		};

	};

	public Direction next() {
		// No bounds checking required here, because the last instance overrides
		return values()[Math.floorMod((ordinal() + 1), 4)];
	}

	public Direction previous() {
		// No bounds checking required here, because the last instance overrides
		return values()[Math.floorMod((ordinal() - 1), 4)];
	}

}
