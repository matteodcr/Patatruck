package position;

public class Position {
	float m_x;
	float m_y;

	Position(float x, float y) {
		m_x = x;
		m_y = y;
	}

	float getX() {
		return m_x;
	}

	void setX(float x) {
		m_x = x;
	}

	float getY() {
		return m_y;
	}

	void setY(float y) {
		m_y = y;
	}
}
