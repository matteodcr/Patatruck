package info3.game.screen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import info3.game.Game;
import info3.game.automata.GAutomaton;
import info3.game.entity.EntityType;
import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;

public class AutomatonSelectionScreen extends Screen {
	final List<GAutomaton> allAutomata;
	final Map<EntityType, Integer> selection = new TreeMap<>();
	final Map<EntityType, Integer> defaultSelection;

	public AutomatonSelectionScreen(Game game) {
		super(game);

		allAutomata = new ArrayList<>(game.getAllAutomata());

		for (EntityType type : EntityType.values()) {
			GAutomaton aut = game.getAutomaton(type.defaultAutomaton);
			selection.put(type, allAutomata.indexOf(aut));
		}

		defaultSelection = new HashMap<>(selection);
	}

	int scrollTop = 0;
	boolean keyPressed = false;

	private void cycleSelection(int delta) {
		Map.Entry<EntityType, Integer> e = new ArrayList<>(selection.entrySet()).get(scrollTop);

		int newVal;
		if (delta != 0) {
			newVal = Math.floorMod(e.getValue() + delta, allAutomata.size());
		} else {
			newVal = defaultSelection.get(e.getKey());
		}

		selection.put(e.getKey(), newVal);
	}

	@Override
	public void tick(long elapsed) {
		boolean upPressed = game.listener.isUp("UP");
		boolean downPressed = game.listener.isUp("DOWN");
		boolean leftPressed = game.listener.isUp("LEFT");
		boolean rightPressed = game.listener.isUp("RIGHT");
		boolean spacePressed = game.listener.isUp("SPACE");
		boolean escapePressed = game.listener.isUp("ESCAPE");
		boolean zPressed = game.listener.isUp("Z");
		boolean sPressed = game.listener.isUp("S");
		boolean qPressed = game.listener.isUp("Q");
		boolean dPressed = game.listener.isUp("D");
		boolean enterPressed = game.listener.isUp("ENTER");

		if (enterPressed) {
			saveAndClose();
		} else if (escapePressed) {
			changeScreen(new StartScreen(game));
		}
		if ((upPressed || zPressed) && !keyPressed) {
			if (scrollTop != 0)
				scrollTop--;
		}

		if ((downPressed || sPressed) && !keyPressed) {
			if (scrollTop < selection.size() - 1)
				scrollTop++;
		}

		if ((leftPressed || qPressed) && !keyPressed) {
			cycleSelection(-1);
		}

		if ((rightPressed || dPressed) && !keyPressed) {
			cycleSelection(1);
		}

		if (spacePressed) {
			cycleSelection(0);
		}

		keyPressed = upPressed || downPressed || leftPressed || rightPressed || spacePressed || zPressed || qPressed
				|| sPressed || dPressed;
	}

	private void saveAndClose() {
		game.boundAutomata = new HashMap<>();

		for (Map.Entry<EntityType, Integer> entry : selection.entrySet()) {
			game.boundAutomata.put(entry.getKey(), allAutomata.get(entry.getValue()));
		}

		changeScreen(new GameScreen(game));
	}

	@Override
	public void render(Graphics g) {
		g.drawSprite(Sprite.AS_BACKDROP, 0, 0);
		g.drawSprite(Sprite.AS_LOGO, 0, -scrollTop * 16);
		g.drawText("SPACE : Réinit. sélection automates", Graphics.Align.LEFT, 98, -scrollTop * 16 + 5);
		g.drawText("ENTER : Lancer le jeu avec ces aut.", Graphics.Align.LEFT, 98, -scrollTop * 16 + 17);

		int i = 0;
		int si = 3 - scrollTop;
		for (Map.Entry<EntityType, Integer> entry : selection.entrySet()) {
			GAutomaton currentAut = allAutomata.get(entry.getValue());
			boolean isDefault = Objects.equals(defaultSelection.get(entry.getKey()), entry.getValue());

			int y = si++ * 16;
			int textY = y + 4;

			g.drawText(entry.getKey().displayName, Graphics.Align.RIGHT, 16 * 5, textY);

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
