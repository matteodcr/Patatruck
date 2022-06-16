package info3.game.screen;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import info3.game.Game;
import info3.game.automata.GAutomaton;
import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;

public class AutomatonSelectionScreen extends Screen {
	public AutomatonSelectionScreen(Game game) {
		super(game);
	}

	int scrollTop = 0;
	boolean enterLastPressed = false;
	boolean keyPressed = false;

	private void cycleSelection(int delta) {
		Map.Entry<String, Integer> e = new ArrayList<>(entityMappings.entrySet()).get(scrollTop);
		int newVal = Math.floorMod(e.getValue() + delta, game.automata_list.size());
		entityMappings.put(e.getKey(), newVal);
	}

	@Override
	public void tick(long elapsed) {
		if (game.m_listener.isUp("ENTER")) {
			enterLastPressed = true;
		} else if (enterLastPressed) {
			changeScreen(new GameScreen(game));
		}

		boolean upPressed = game.m_listener.isUp("UP");
		boolean downPressed = game.m_listener.isUp("DOWN");
		boolean leftPressed = game.m_listener.isUp("LEFT");
		boolean rightPressed = game.m_listener.isUp("RIGHT");

		if (upPressed && !keyPressed) {
			if (scrollTop != 0)
				scrollTop--;
		}

		if (downPressed && !keyPressed) {
			if (scrollTop < entityMappings.size() - 1)
				scrollTop++;
		}

		if (leftPressed && !keyPressed) {
			cycleSelection(-1);
		}

		if (rightPressed && !keyPressed) {
			cycleSelection(1);
		}

		keyPressed = upPressed || downPressed || leftPressed || rightPressed;
	}

	static Map<String, Integer> entityMappings;
	static Map<String, Integer> defaultEntityMappings = new TreeMap<>();

	static {
		defaultEntityMappings.put("cuisinier", 1);
		defaultEntityMappings.put("étal de marché", 14);
		defaultEntityMappings.put("cafard", 15);
		entityMappings = new TreeMap<>(defaultEntityMappings);
	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(Sprite.AS_BACKDROP, 0, 0);
		g.drawSprite(Sprite.AS_LOGO, 0, -scrollTop * 16);

		int i = 0;
		int si = 3 - scrollTop;
		for (Map.Entry<String, Integer> entry : entityMappings.entrySet()) {
			GAutomaton currentAut = game.automata_list.get(entry.getValue());
			boolean isDefault = Objects.equals(defaultEntityMappings.get(entry.getKey()), entry.getValue());

			int y = si++ * 16;
			int textY = y + 4;

			g.drawText(entry.getKey(), Graphics.Align.RIGHT, 16 * 5, textY);

			if (i == scrollTop) {
				g.drawSprite(Sprite.AS_LEFT_BOX, 6 * 16, y);
				g.drawSprite(Sprite.AS_RIGHT_BOX, 13 * 16, y);

				if (!isDefault)
					g.drawSprite(Sprite.AS_RESET_BOX, 14 * 16, y);
			}

			g.drawSprite(Sprite.AS_LEFT, 6 * 16, y);

			g.drawSprite(Sprite.AS_BOX, 7 * 16, y);
			g.drawText(currentAut.name, Graphics.Align.LEFT, 7 * 16 + 5, textY);

			g.drawSprite(Sprite.AS_RIGHT, 13 * 16, y);
			if (!isDefault)
				g.drawSprite(Sprite.AS_RESET, 14 * 16, y);

			i++;
			if (si > 9)
				break;
		}
	}
}
