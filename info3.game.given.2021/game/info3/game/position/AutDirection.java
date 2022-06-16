package info3.game.position;

public enum AutDirection {
	N, E, S, W, F, B, L, R, H {
		@Override
		public AutDirection next() {
			return null; //
		};

		public AutDirection previous() {
			return null;
		};

		public AutDirection twoapart() {
			return null;
		};

	};

	public AutDirection next() {
		return values()[Math.floorMod((ordinal() + 1), 4)];
	}

	public AutDirection previous() {
		return values()[Math.floorMod((ordinal() - 1), 4)];
	}

	public AutDirection twoapart() {
		return values()[Math.floorMod((ordinal() + 2), 4)];
	}
}
