package info3.game.screen;

import info3.game.Game;
import info3.game.graphics.Graphics;
import info3.game.scene.CityScene;
import info3.game.scene.KitchenScene;
import info3.game.scene.Scene;

public class GameScreen extends Screen {
	private final KitchenScene kitchenScene = new KitchenScene(Game.WIDTH, Game.HEIGHT / 2, this.game);
	private final CityScene cityScene = new CityScene(Game.WIDTH, Game.HEIGHT / 2, this.game);

	public GameScreen(Game game) {
		super(game);
	}

	public Scene getCityScene() {
		return cityScene;
	}

	public Scene getKitchenScene() {
		return kitchenScene;
	}

	@Override
	public int getEntityCount() {
		return kitchenScene.getNbEntities() + cityScene.getNbEntities();
	}

	@Override
	public void tick(long elapsed) {
		kitchenScene.tick(elapsed);
		cityScene.tick(elapsed);
	}

	@Override
	public void render(Graphics g) {
		int half = g.getHeight() / 2;
		kitchenScene.render(g.window(0, 0, g.getWidth(), half));
		cityScene.render(g.window(0, half, g.getWidth(), half));
	}

}
